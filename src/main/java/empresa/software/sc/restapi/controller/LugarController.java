/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.controller;

import empresa.software.sc.restapi.exception.ResourceNotFoundException;
import empresa.software.sc.restapi.model.Lugar;
import empresa.software.sc.restapi.payload.ApiResponse;
import empresa.software.sc.restapi.payload.LugarRequest;
import empresa.software.sc.restapi.repository.LugarRepository;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author pedro
 */
@RestController
@RequestMapping("/api/sitios")
public class LugarController {

    @Autowired
    LugarRepository lugarRepository;

    @GetMapping("/all/{page}/{pageSize}")
    public Page<Lugar> getAllPosts(@PathVariable int page, @PathVariable int pageSize, Pageable pageable) {
        Page<Lugar> resultPage = lugarRepository.findAll(PageRequest.of(page, pageSize));
        if (page > resultPage.getTotalPages()) {
            throw new ResourceNotFoundException("LugarPage", "page", page);
        }
        return resultPage;
    }
    
    @Secured("ROLE_ADMIN")
    @PostMapping
    ResponseEntity<?> newSite(@Valid @RequestBody LugarRequest lugarRequest) {
        Lugar lugar = new Lugar(lugarRequest.getNombre(), lugarRequest.getDescripcion(), lugarRequest.getDireccion(), lugarRequest.getReferencia());
        Lugar result = lugarRepository.save(lugar);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/sitios/")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "Lugar registered successfully"));
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    ResponseEntity<?> updateSite(@PathVariable("id") Long id, @Valid @RequestBody LugarRequest lugarRequest) {
        Lugar lugar = lugarRepository.findById(id).get();
        lugar.setNombre(lugarRequest.getNombre());
        lugar.setDescripcion(lugarRequest.getDescripcion());
        lugar.setDireccion(lugarRequest.getDireccion());
        lugar.setReferencia(lugarRequest.getReferencia());
        Lugar result = lugarRepository.save(lugar);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/sitios/")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "Lugar registered successfully"));
    }
    
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteSite(@PathVariable("id") Long id) {
        lugarRepository.deleteById(id);
        return new ResponseEntity(new ApiResponse(true, "Lugar ha sido eliminado!"),
                    HttpStatus.ACCEPTED);
    }
}
