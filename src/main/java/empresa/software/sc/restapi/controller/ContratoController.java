/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.controller;

import empresa.software.sc.restapi.exception.ResourceNotFoundException;
import empresa.software.sc.restapi.model.Cliente;
import empresa.software.sc.restapi.model.Contrato;
import empresa.software.sc.restapi.model.Escort;
import empresa.software.sc.restapi.model.Lugar;
import empresa.software.sc.restapi.model.RoleName;
import empresa.software.sc.restapi.payload.ApiResponse;
import empresa.software.sc.restapi.payload.ContratoRequest;
import empresa.software.sc.restapi.repository.ClienteRepository;
import empresa.software.sc.restapi.repository.ContratoRepository;
import empresa.software.sc.restapi.repository.EscortRepository;
import empresa.software.sc.restapi.repository.LugarRepository;
import empresa.software.sc.restapi.security.CurrentUser;
import empresa.software.sc.restapi.security.UserPrincipal;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author pedro
 */
@RestController
@RequestMapping("/api/contratos")
public class ContratoController {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EscortRepository escortRepository;

    @Autowired
    ContratoRepository contratoRepository;

    @Autowired
    LugarRepository lugarRepository;

    @Secured("ROLE_ADMIN")
    @GetMapping("/all/{page}/{pageSize}")
    public Page<Contrato> getAllPosts(@PathVariable int page, @PathVariable int pageSize, Pageable pageable) {
        Page<Contrato> resultPage = contratoRepository.findAll(PageRequest.of(page, pageSize));
        if (page > resultPage.getTotalPages()) {
            throw new ResourceNotFoundException("ContratoPage", "page", page);
        }
        return resultPage;
    }

    @Secured({"ROLE_CLIENTE","ROLE_ESCORT"})
    @GetMapping
    Page<Contrato> getContrato(@CurrentUser UserPrincipal userprincipal, Pageable pageable) {
        Collection<? extends GrantedAuthority> authorities
                = userprincipal.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals(RoleName.ROLE_ESCORT.toString())) {
                return contratoRepository.findByEscortId(userprincipal.getId(), pageable);
            } else if (grantedAuthority.getAuthority().equals(RoleName.ROLE_CLIENTE.toString())) {
                return contratoRepository.findByClienteId(userprincipal.getId(), pageable);
            }
        }
        return null;
    }

    @Secured("ROLE_CLIENTE")
    @GetMapping("/{id}")
    public Contrato getContrato(@PathVariable Long id) {
        return contratoRepository.findById(id).get();
    }

    @Secured("ROLE_CLIENTE")
    @PostMapping("/escort/{username}")
    ResponseEntity<?> newContrato(@CurrentUser UserPrincipal userprincipal, @PathVariable String username, @Valid @RequestBody ContratoRequest contratoRequest) {

        Cliente cliente = clienteRepository.findByUsername(userprincipal.getUsername()).get();
        Escort escort = escortRepository.findByUsername(username).get();
        Lugar lugar = lugarRepository.findById(contratoRequest.getLugar_id()).get();

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date = null;
        try {
            date = df.parse(contratoRequest.getFechaReserva());
        } catch (ParseException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Contrato contrato
                = new Contrato(date, contratoRequest.getTiempo(), false, false, contratoRequest.getObservaciones(), lugar, cliente, escort);
        Contrato result = contratoRepository.save(contrato);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/contratos/")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "Contrato registered successfully"));
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteSite(@PathVariable("id") Long id) {
        contratoRepository.deleteById(id);
        return new ResponseEntity(new ApiResponse(true, "Contrato ha sido eliminado!"),
                HttpStatus.ACCEPTED);
    }
}
