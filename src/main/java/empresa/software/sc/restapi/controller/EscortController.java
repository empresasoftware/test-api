/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.controller;

import empresa.software.sc.restapi.exception.ResourceNotFoundException;
import empresa.software.sc.restapi.model.Cliente;
import empresa.software.sc.restapi.model.Escort;
import empresa.software.sc.restapi.model.Foto;
import empresa.software.sc.restapi.model.User;
import empresa.software.sc.restapi.payload.ApiResponse;
import empresa.software.sc.restapi.payload.ClienteRequest;
import empresa.software.sc.restapi.payload.EscortRequest;
import empresa.software.sc.restapi.payload.EscortResponse;
import empresa.software.sc.restapi.repository.EscortRepository;
import empresa.software.sc.restapi.repository.FotoRepository;
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

    @Autowired
    private FotoRepository fotoRepository;

    @GetMapping("/all/{page}/{pageSize}")
    public Page<Escort> getAllPosts(@PathVariable int page, @PathVariable int pageSize, Pageable pageable) {
        Page<Escort> resultPage = escortRepository.findAll(PageRequest.of(page, pageSize));
        if (page > resultPage.getTotalPages()) {
            throw new ResourceNotFoundException("EscortPage", "page", page);
        }
        return resultPage;
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> accountDetails(@PathVariable String username, Pageable pageable) {

        Escort escort = escortRepository.findByUsername(username).get();
        Page<Foto> pageableFotos = fotoRepository.findByEscortId(escort.getId(), pageable);
        return new ResponseEntity(new EscortResponse(escort, pageableFotos),
                HttpStatus.ACCEPTED);
    }

    @Secured({"ROLE_ESCORT"})
    @GetMapping("/me")
    public User accountDetails(@CurrentUser UserPrincipal userprincipal) {
        return escortRepository.findByUsername(userprincipal.getUsername()).get();
    }

    @Secured({"ROLE_ESCORT"})
    @PutMapping("/me")
    public Escort registerUserEscort(@CurrentUser UserPrincipal userprincipal, @Valid @RequestBody EscortRequest escortRequest) {

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date = null;
        try {
            date = df.parse(escortRequest.getFechaNacimiento());
        } catch (ParseException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Escort escort = (Escort) userRepository.findByUsername(userprincipal.getUsername()).get();
        // Creating escort's account
        escort.setPais(escortRequest.getPais());
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

        escort = escortRepository.save(escort);

        return escort;
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{username}")
    public Escort registerUserEscort(@PathVariable String username, @Valid @RequestBody EscortRequest escortRequest) {

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date = null;
        try {
            date = df.parse(escortRequest.getFechaNacimiento());
        } catch (ParseException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Escort escort = (Escort) userRepository.findByUsername(username).get();
        // Creating escort's account
        escort.setPais(escortRequest.getPais());
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

        escort = escortRepository.save(escort);

        return escort;
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{username}")
    public ResponseEntity<?> DeleteEscort(@PathVariable String username) {
        Escort escort = (Escort) userRepository.findByUsername(username).get();
        escortRepository.delete(escort);
        return new ResponseEntity(new ApiResponse(true, "Escort eliminada! username+ " + escort.getUsername()),
                HttpStatus.ACCEPTED);
    }
}
