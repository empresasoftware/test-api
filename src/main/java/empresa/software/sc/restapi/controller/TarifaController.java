/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.controller;

import empresa.software.sc.restapi.model.Escort;
import empresa.software.sc.restapi.model.Tarifa;
import empresa.software.sc.restapi.payload.ApiResponse;
import empresa.software.sc.restapi.payload.TarifaRequest;
import empresa.software.sc.restapi.repository.EscortRepository;
import empresa.software.sc.restapi.repository.TarifaRepository;
import empresa.software.sc.restapi.security.CurrentUser;
import empresa.software.sc.restapi.security.UserPrincipal;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RequestMapping("/api/escorts")
public class TarifaController {

    @Autowired
    private EscortRepository escortRepository;
    
    @Autowired
    TarifaRepository tarifaRepository;
    
    @Secured("ROLE_ESCORT")
    @GetMapping("/tarifa")
    Page<Tarifa> getTarifas(@CurrentUser UserPrincipal userprincipal, Pageable pageable) {
        return tarifaRepository.findByEscortId(userprincipal.getId(), pageable);
    }

    
    
    @Secured("ROLE_ESCORT")
    @PostMapping("/tarifa")
    ResponseEntity<?> newTarifa(@CurrentUser UserPrincipal userprincipal, @Valid @RequestBody TarifaRequest tarifaRequest) {
        Escort escort = escortRepository.findByUsername(userprincipal.getUsername()).get();
        Tarifa tarifa = new Tarifa(tarifaRequest.getTiempo(), tarifaRequest.getInversion(), escort);
        Tarifa result = tarifaRepository.save(tarifa);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/escort/tarifas")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "Tarifa registered successfully"));
    }

    
    @Secured("ROLE_ESCORT")
    @PutMapping("/tarifa/{id}")
    ResponseEntity<?> updateTarifa(@PathVariable Long id, @Valid @RequestBody TarifaRequest tarifaRequest) {
        Tarifa tarifa = tarifaRepository.findById(id).get();
        tarifa.setInversion(tarifaRequest.getInversion());
        tarifa.setTiempo(tarifaRequest.getTiempo());
        tarifaRepository.save(tarifa);
        return new ResponseEntity(new ApiResponse(true, "Tarifa actualizada!"),
                    HttpStatus.ACCEPTED);
    }

    @Secured("ROLE_ESCORT")
    @DeleteMapping("/tarifa/{id}")
    ResponseEntity<?> deleteTarifa(@PathVariable Long id) {
        tarifaRepository.deleteById(id);
        return new ResponseEntity(new ApiResponse(true, "Tarifa eliminada!"),
                    HttpStatus.ACCEPTED);
    }
    
}
