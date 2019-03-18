/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.controller;

import empresa.software.sc.restapi.exception.AppException;
import empresa.software.sc.restapi.model.Escort;
import empresa.software.sc.restapi.model.Servicio;
import empresa.software.sc.restapi.model.ServicioName;
import empresa.software.sc.restapi.payload.ApiResponse;
import empresa.software.sc.restapi.payload.ServicioRequest;
import empresa.software.sc.restapi.repository.EscortRepository;
import empresa.software.sc.restapi.repository.ServicioRepository;
import empresa.software.sc.restapi.security.CurrentUser;
import empresa.software.sc.restapi.security.UserPrincipal;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pedro
 */
@RestController
@RequestMapping("/api/escorts")
public class ServicioController {

    @Autowired
    private EscortRepository escortRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Secured({"ROLE_ESCORT"})
    @GetMapping("/servicio")
    Set<Servicio> allEscortService(@CurrentUser UserPrincipal userprincipal) {
        Escort escort = escortRepository.findByUsername(userprincipal.getUsername()).get();
        Set<Servicio> servicio = escort.getServicios();
        if (servicio == null){
            servicio = new HashSet();
        }
        return servicio;
    }
    
    @Secured({"ROLE_ESCORT"})
    @PostMapping("/servicio")
    ResponseEntity<?> newEscortService(@CurrentUser UserPrincipal userprincipal, @Valid @RequestBody ServicioRequest servicioRequest) {
        Escort escort = escortRepository.findByUsername(userprincipal.getUsername()).get();
        Servicio escortServicio = null;
        
        if (servicioRequest.getName().equals("Blowjob")){
            escortServicio = servicioRepository.findByName(ServicioName.Blowjob)
                    .orElseThrow(() -> new AppException("Blowjob service not set."));              
        } else if (servicioRequest.getName().equals("Handjob")){
            escortServicio = servicioRepository.findByName(ServicioName.Handjob)
                    .orElseThrow(() -> new AppException("Handjob service not set."));  
        }
          

        Set<Servicio> servicios = escort.getServicios();
        if (servicios == null){
            servicios = new HashSet();            
        }
        servicios.add(escortServicio);
        return new ResponseEntity(new ApiResponse(true, "Servicio ha sido vinculado con escort!"),
                HttpStatus.ACCEPTED);
    }

    @Secured({"ROLE_ESCORT"})
    @DeleteMapping("/servicio/{id}")
    ResponseEntity<?> deleteEscortService(@CurrentUser UserPrincipal userprincipal, @PathVariable Long id) {
        Escort escort = escortRepository.findByUsername(userprincipal.getUsername()).get();
        Set<Servicio> servicios = escort.getServicios();
        servicios.remove(new Servicio(id, null));
        escort.setServicios(servicios);
        escortRepository.save(escort);
        return new ResponseEntity(new ApiResponse(true, "Servicio ha sido vinculado con escort!"),
                HttpStatus.ACCEPTED);
    }

}
