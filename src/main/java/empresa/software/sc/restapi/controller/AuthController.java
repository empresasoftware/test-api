/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.controller;

/**
 *
 * @author Steven
 */
import empresa.software.sc.restapi.exception.AppException;
import empresa.software.sc.restapi.model.Cliente;
import empresa.software.sc.restapi.model.Escort;
import empresa.software.sc.restapi.model.Role;
import empresa.software.sc.restapi.model.RoleName;
import empresa.software.sc.restapi.model.User;
import empresa.software.sc.restapi.payload.AccountDetailsRequest;
import empresa.software.sc.restapi.payload.ApiResponse;
import empresa.software.sc.restapi.payload.ClienteRequest;
import empresa.software.sc.restapi.payload.EscortRequest;
import empresa.software.sc.restapi.payload.JwtAuthenticationResponse;
import empresa.software.sc.restapi.payload.LoginRequest;
import empresa.software.sc.restapi.payload.SignUpRequest;
import empresa.software.sc.restapi.repository.ClienteRepository;
import empresa.software.sc.restapi.repository.EscortRepository;
import empresa.software.sc.restapi.repository.RoleRepository;
import empresa.software.sc.restapi.repository.UserRepository;
import empresa.software.sc.restapi.security.JwtTokenProvider;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.Properties;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.MimeMessage;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EscortRepository escortRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;
  
    //private JavaMailSender ms = new JavaMailSenderImpl();
   /*
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("escorts.cuenca90@gmail.com");
        mailSender.setPassword("Esc.cuenca.90");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }*/
    
    
    //@Autowired
    //private JavaMailSender mailSender;
    
    @Autowired
    ApplicationEventPublisher eventPublisher;
    
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/cliente/signup")
    public ResponseEntity<?> registerUserClient(@Valid @RequestBody ClienteRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        
        Date date = null;
        try {
            date = df.parse(signUpRequest.getFechaNacimiento());
        } catch (ParseException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Creating user's account
        Cliente user = new Cliente(signUpRequest.getPais(),signUpRequest.getEstado(), date,
                signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_CLIENTE)
                .orElseThrow(() -> new AppException("Cliente Role not set."));

        user.setRoles(Collections.singleton(userRole));

        Cliente result = clienteRepository.save(user);
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/clientes/")
                .buildAndExpand(result.getUsername()).toUri();
 
        
        //String appUrl = signUpRequest.getContextPath();
        Locale locale = new Locale("en");
        //locale = new Locale
        //eventPublisher.publishEvent(new OnRegistrationCompleteEvent
         // (user, locale, "/cliente/signup"));
        
        //SimpleMailMessage email = new SimpleMailMessage();
        //email.setTo(user.getEmail());
        //email.setSubject("Asunto del correo");
        //email.setText("Mensaje" + " rn" + "http://localhost:8080" + " código de confirmación");
        //ms.send(email);
        //mailSender.send(email);
        User usuario = userRepository.findByUsername(result.getUsername()).get();
        
        System.out.println("----------------------AQUI----------------------");
        System.out.println(user.getId());
        System.out.println("----------------------AQUI----------------------");
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent
          (usuario, locale, "urlejemplo"));
        
        return ResponseEntity.created(location).body(new ApiResponse(true, "Cliente registered successfully"));
    }

    @PostMapping("/escort/signup")
    public ResponseEntity<?> registerUserEscort(@Valid @RequestBody EscortRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        
        Date date = null;
        try {
            date = df.parse(signUpRequest.getFechaNacimiento());
        } catch (ParseException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Creating escort's account
        Escort user = new Escort(signUpRequest.getPais(), Double.parseDouble(signUpRequest.getEstatura()), Double.parseDouble(signUpRequest.getPeso()), signUpRequest.getIdiomas(), signUpRequest.getBiografia(), signUpRequest.getOrientacion(), signUpRequest.getCabello(), signUpRequest.getMedidas(), date, signUpRequest.getLugares(), signUpRequest.getRestricciones(), 
                signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_ESCORT)
                .orElseThrow(() -> new AppException("Escort Role not set."));

        user.setRoles(Collections.singleton(userRole));

        Escort result = escortRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/escorts/")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "Escort registered successfully"));
    }
}
