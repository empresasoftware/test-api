/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.controller;

import empresa.software.sc.restapi.model.Cliente;
import empresa.software.sc.restapi.model.Escort;
import empresa.software.sc.restapi.model.User;
import empresa.software.sc.restapi.payload.ClienteRequest;
import empresa.software.sc.restapi.payload.EscortRequest;
import empresa.software.sc.restapi.repository.EscortRepository;
import empresa.software.sc.restapi.repository.UserRepository;
import empresa.software.sc.restapi.security.CurrentUser;
import empresa.software.sc.restapi.security.UserPrincipal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
public class EscortController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EscortRepository escortRepository;
    
    @GetMapping("/all")
    public Page<Escort> getAllPosts(Pageable pageable) {
        return escortRepository.findAll(pageable);
    }
    
    @GetMapping("/{username}")
    public User accountDetails(@PathVariable String username) {
        return escortRepository.findByUsername(username).get();
    }

    @Secured({"ROLE_ESCORT"})
    @GetMapping("/me")
    public User accountDetails(@CurrentUser UserPrincipal userprincipal) {
        return escortRepository.findByUsername(userprincipal.getUsername()).get();
    }
    

    @Secured({"ROLE_ESCORT"})
    @PostMapping("/registrar")
    ResponseEntity<?> registerUserEscort(@CurrentUser UserPrincipal userprincipal, @Valid @RequestBody EscortRequest escortRequest) {
                
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date = null;
        try {
            date = df.parse(escortRequest.getFechaNacimiento());
        } catch (ParseException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Escort escort = (Escort)userRepository.findByUsername(userprincipal.getUsername()).get();
        // Creating escort's account
        escort.setProlifeEdited(true);
        escort.setBiografia(escortRequest.getBiografia());
        escort.setCabello(escortRequest.getCabello());
        escort.setEstatura(Double.parseDouble(escortRequest.getEstatura()));
        escort.setFechaNacimiento(date);
        escort.setIdiomas(escortRequest.getIdiomas());
        escort.setLugares(escortRequest.getLugares());
        escort.setMedidas(escortRequest.getMedidas());
        escort.setOrientacion(escortRequest.getOrientacion());
        escort.setPeso(Double.parseDouble(escortRequest.getPeso()));
        escort.setRestricciones(escortRequest.getRestricciones());
        
        escortRepository.save(escort);
        
        return null;
    }
    
    @Secured({"ROLE_ADMIN"})
    @PostMapping("/registrar/{username}")
    ResponseEntity<?> registerUserEscort(@PathVariable String username, @Valid @RequestBody EscortRequest escortRequest) {
                
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date = null;
        try {
            date = df.parse(escortRequest.getFechaNacimiento());
        } catch (ParseException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Escort escort = (Escort)userRepository.findByUsername(username).get();
        // Creating escort's account
        escort.setProlifeEdited(true);
        escort.setBiografia(escortRequest.getBiografia());
        escort.setCabello(escortRequest.getCabello());
        escort.setEstatura(Double.parseDouble(escortRequest.getEstatura()));
        escort.setFechaNacimiento(date);
        escort.setIdiomas(escortRequest.getIdiomas());
        escort.setLugares(escortRequest.getLugares());
        escort.setMedidas(escortRequest.getMedidas());
        escort.setOrientacion(escortRequest.getOrientacion());
        escort.setPeso(Double.parseDouble(escortRequest.getPeso()));
        escort.setRestricciones(escortRequest.getRestricciones());
        
        escortRepository.save(escort);
        
        return null;
    }
}
