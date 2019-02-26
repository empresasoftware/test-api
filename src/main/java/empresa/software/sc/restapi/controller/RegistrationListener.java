/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.controller;

import empresa.software.sc.restapi.model.User;
import empresa.software.sc.restapi.model.VerificationToken;
import empresa.software.sc.restapi.repository.VerificationTokenRepository;
import java.util.Properties;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

/**
 *
 * @author Steven
 */
	
@Component
public class RegistrationListener implements
  ApplicationListener<OnRegistrationCompleteEvent> {
  
    //@Autowired
    //private IUserService service;
  
    @Autowired
    private MessageSource messages;
  
    @Autowired
    private VerificationTokenRepository tokenRepository;
    
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
    }
    
    @Autowired
    private JavaMailSender mailSender;
 
    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }
 
    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        //String token = UUID.randomUUID().toString();
        String token = "tokendeseguridad";
        
        System.out.println("--------------REGISTRATION LISTENER---------------");
        System.out.println(user.getId());
        System.out.println("--------------REGISTRATION LISTENER---------------");
        
        VerificationToken verificationToken = new VerificationToken(user,token);
         
        tokenRepository.save(verificationToken);
        
        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl 
          = "/registrationConfirm.html?token=" + token;
        //String message = messages.getMessage("message.regSucc", null, event.getLocale());
         
        String message = "MENSAJE DE PRUEBA";
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " rn " + "http://localhost:8080" + confirmationUrl);
        mailSender.send(email);
    }
}
