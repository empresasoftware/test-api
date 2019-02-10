/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.controller;

import empresa.software.sc.restapi.model.Foto;
import empresa.software.sc.restapi.repository.EscortRepository;
import empresa.software.sc.restapi.repository.FotoRepository;
import empresa.software.sc.restapi.security.CurrentUser;
import empresa.software.sc.restapi.security.UserPrincipal;
import empresa.software.sc.restapi.service.DBFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
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
@RequestMapping("/api/escort")
public class FotoController {

    @Autowired
    private FotoRepository fotoRepository;

    @Autowired
    private EscortRepository escortRepository;

    @Autowired
    DBFileStorageService bFileStorageService;

    @GetMapping("/{username}/fotos")
    public Page<Foto> getAllPhotosByEscortUsername(@PathVariable(value = "username") String escortUsername,
            Pageable pageable) {
        return fotoRepository.findByEscortId(escortRepository.findByUsername(escortUsername).get().getId(), pageable);
    }

    @Secured({"ROLE_ESCORT"})
    @PostMapping("/uploadFoto")
    public UploadFileResponse uploadFile(@CurrentUser UserPrincipal userprincipal,
            @RequestParam("file") MultipartFile file) {
        Foto foto = bFileStorageService.storeFile(file, escortRepository.findByUsername(userprincipal.getUsername()).get());

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/escort/{username}/foto/{idFoto}")
                .path(foto.getId().toString())
                .toUriString();

        return new UploadFileResponse(foto.getNombre(), fileDownloadUri,
                file.getContentType(), file.getSize());
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
