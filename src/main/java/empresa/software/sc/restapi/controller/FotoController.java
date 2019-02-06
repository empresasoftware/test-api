/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.controller;

import empresa.software.sc.restapi.exception.ResourceNotFoundException;
import empresa.software.sc.restapi.model.Foto;
import empresa.software.sc.restapi.repository.EscortRepository;
import empresa.software.sc.restapi.repository.FotoRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pedro
 */
@RestController
public class FotoController {
    
    @Autowired
    private FotoRepository fotoRepository;

    @Autowired
    private EscortRepository escortRepository;
    
    @GetMapping("/escort/{escortId}/fotos")
    public Page<Foto> getAllPhotosByEscortId(@PathVariable (value = "escortId") Long escortId,
                                                Pageable pageable) {
        return fotoRepository.findByEscortId(escortId, pageable);
    }
    
    @PostMapping("/escort/{escortId}/fotos")
    public Foto createPhoto(@PathVariable (value = "escortId") Long escortId,
                                 @Valid @RequestBody Foto foto) {
        return escortRepository.findById(escortId).map(escort -> {
            foto.setEscort(escort);
            return fotoRepository.save(foto);
        }).orElseThrow(() -> new ResourceNotFoundException("Foto","EscortId ", escortId));
    }
}
