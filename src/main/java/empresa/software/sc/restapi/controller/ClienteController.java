/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.controller;

import empresa.software.sc.restapi.model.Cliente;
import empresa.software.sc.restapi.model.User;
import empresa.software.sc.restapi.payload.ClienteRequest;
import empresa.software.sc.restapi.repository.ClienteRepository;
import empresa.software.sc.restapi.repository.UserRepository;
import empresa.software.sc.restapi.security.CurrentUser;
import empresa.software.sc.restapi.security.UserPrincipal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@RequestMapping("/api/clientes")
public class ClienteController {
 
    @Autowired
    ClienteRepository clienteRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @Secured("ROLE_ADMIN")
    @GetMapping("/all")
    public Page<Cliente> getAllPosts(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/{username}")
    public User accountDetails(@PathVariable String username) {
        return clienteRepository.findByUsername(username).get();
    }

    @Secured({"ROLE_CLIENTE"})
    @GetMapping("/me")
    public User accountDetails(@CurrentUser UserPrincipal userprincipal) {
        return clienteRepository.findByUsername(userprincipal.getUsername()).get();
    }
    
    @Secured({"ROLE_CLIENTE"})
    @PostMapping("/regitrar")
    ResponseEntity<?> registerUserCliente(@CurrentUser UserPrincipal userprincipal, @Valid @RequestBody ClienteRequest clienteRequest) {
                
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date = null;
        try {
            date = df.parse(clienteRequest.getFechaNacimiento());
        } catch (ParseException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Cliente cliente = (Cliente)userRepository.findByUsername(userprincipal.getUsername()).get();
        // Creating cliente's account
        cliente.setProlifeEdited(true);
        cliente.setEstado(clienteRequest.getEstado());
        cliente.setFechaNacimiento(date);
        cliente.setPais(clienteRequest.getPais());
        Cliente result = clienteRepository.save(cliente);
        
        return null;
    }
    
    @Secured({"ROLE_ADMIN"})
    @PostMapping("/registrar/{username}")
    ResponseEntity<?> registerUserCliente(@PathVariable String username, @Valid @RequestBody ClienteRequest clienteRequest) {
                
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date = null;
        try {
            date = df.parse(clienteRequest.getFechaNacimiento());
        } catch (ParseException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Cliente cliente = (Cliente)userRepository.findByUsername(username).get();
        // Creating cliente's account
        cliente.setProlifeEdited(true);
        cliente.setEstado(clienteRequest.getEstado());
        cliente.setFechaNacimiento(date);
        cliente.setPais(clienteRequest.getPais());
        Cliente result = clienteRepository.save(cliente);
        
        return null;
    }
}
