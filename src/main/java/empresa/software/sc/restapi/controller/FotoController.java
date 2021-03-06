/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.controller;

import empresa.software.sc.restapi.exception.ResourceNotFoundException;
import empresa.software.sc.restapi.model.Escort;
import empresa.software.sc.restapi.model.Foto;
import empresa.software.sc.restapi.payload.ApiResponse;
import empresa.software.sc.restapi.repository.EscortRepository;
import empresa.software.sc.restapi.repository.FotoRepository;
import empresa.software.sc.restapi.security.CurrentUser;
import empresa.software.sc.restapi.security.UserPrincipal;
import empresa.software.sc.restapi.service.DBFileStorageService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author pedro
 */
@RestController
@RequestMapping("/api/escorts")
public class FotoController {

    @Autowired
    private FotoRepository fotoRepository;

    @Autowired
    private EscortRepository escortRepository;

    @Autowired
    DBFileStorageService bFileStorageService;

    @GetMapping("/{username}/fotos/{page}/{pageSize}")
    public Page<Foto> getAllPhotosByEscortUsername(@PathVariable(value = "username") String escortUsername,
            @PathVariable(value = "page") int page, @PathVariable(value = "pageSize") int pageSize, Pageable pageable) {
        Page<Foto> resultPage = fotoRepository.findByEscortId(escortRepository.findByUsername(escortUsername).get().getId(), PageRequest.of(page, pageSize));
        if (page > resultPage.getTotalPages()) {
            throw new ResourceNotFoundException("FotoPage", "page", page);
        }
        return resultPage;
    }

    @Secured({"ROLE_ESCORT"})
    @PostMapping("/uploadFoto")
    public UploadFileResponse uploadFile(@CurrentUser UserPrincipal userprincipal,
            @RequestParam("file") MultipartFile file) {
        Foto foto = bFileStorageService.storeFile(file, escortRepository.findByUsername(userprincipal.getUsername()).get());

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/escorts/{username}/foto/{idFoto}")
                .path(foto.getId().toString())
                .toUriString();

        return new UploadFileResponse(foto.getNombre(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @Secured({"ROLE_ESCORT"})
    @PostMapping("/uploadFotoPerfil")
    public ResponseEntity<?> uploadFotoPerfil(@CurrentUser UserPrincipal userprincipal,
            @RequestParam("file") MultipartFile file) {
        bFileStorageService.storeFotoPerfil(file, escortRepository.findByUsername(userprincipal.getUsername()).get());

        return new ResponseEntity(new ApiResponse(true, "Foto de perfil actualizada!"),
                HttpStatus.ACCEPTED);
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/{username}/uploadFotoPerfil")
    public ResponseEntity<?> uploadFotoPerfil(@PathVariable(value = "username") String username,
            @RequestParam("file") MultipartFile file) {
        bFileStorageService.storeFotoPerfil(file, escortRepository.findByUsername(username).get());

        return new ResponseEntity(new ApiResponse(true, "Foto de perfil actualizada!"),
                HttpStatus.ACCEPTED);
    }

    @GetMapping("/{username}/getFotoPerfil")
    public ResponseEntity<?> getFotoPerfil(@PathVariable(value = "username") String username) {
        Escort escort = escortRepository.findByUsername(username).get();

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(escort.getTipoFoto()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + escort.getName() + "\"")
                .body(new ByteArrayResource(ArrayUtils.toPrimitive(escort.getFotoPerfil())));
    }

    @GetMapping("/{username}/foto/{idFoto}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long idFoto) {
        // Load file from database
        Foto foto = bFileStorageService.getFile(idFoto);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(foto.getTipo()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + foto.getNombre() + "\"")
                .body(new ByteArrayResource(foto.getFoto()));
    }
}
