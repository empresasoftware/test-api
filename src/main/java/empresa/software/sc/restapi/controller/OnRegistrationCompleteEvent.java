/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.controller;

import empresa.software.sc.restapi.model.User;
import java.util.Locale;
import org.springframework.context.ApplicationEvent;

/**
 *
 * @author Steven
 */
public class OnRegistrationCompleteEvent extends ApplicationEvent{
    private String appUrl;
    private Locale locale;
    private User user;
    private String token;
 
    public OnRegistrationCompleteEvent(User user, Locale locale, String appUrl, String token) {
        super(user);
         
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
        this.token = token;
    }
     
    
    
    // standard getters and setters

    /**
     * @return the appUrl
     */
    public String getAppUrl() {
        return appUrl;
    }

    /**
     * @param appUrl the appUrl to set
     */
    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    /**
     * @return the locale
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * @param locale the locale to set
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
