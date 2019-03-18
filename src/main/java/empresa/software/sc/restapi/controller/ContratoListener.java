/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.controller;

import empresa.software.sc.restapi.model.Cliente;
import empresa.software.sc.restapi.model.Escort;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 *
 * @author pedro
 */
public class ContratoListener implements
  ApplicationListener<OnContratoCompleteEvent> {

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
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.debug", "true");

        return mailSender;
    }
    
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnContratoCompleteEvent event) {
        this.confirmRegistration(event);
    }
    
    private void confirmRegistration(OnContratoCompleteEvent event) {
        Escort user = event.getEscort();
        //String token = UUID.randomUUID().toString();
        Cliente cliente = event.getCliente();
        
        
        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        //String message = messages.getMessage("message.regSucc", null, event.getLocale());
         
        String message = "MENSAJE DE PRUEBA";
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);
    }
 
}
