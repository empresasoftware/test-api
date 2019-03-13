/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.payload;

import java.util.Date;

/**
 *
 * @author pedro
 */
public class ContratoResponse {
    private Long id;
    
    private Date fechaReserva;

    private int tiempo;
    
    private boolean aceptado;

    private boolean pagado;
    
    private String observaciones;
    
    private String nombreLugar;
    
    private String usernameCliente;

    private String usernameEscort;

    public ContratoResponse(Long id, Date fechaReserva, int tiempo, boolean aceptado, boolean pagado, String observaciones, String nombreLugar, String usernameCliente, String usernameEscort) {
        this.id = id;
        this.fechaReserva = fechaReserva;
        this.tiempo = tiempo;
        this.aceptado = aceptado;
        this.pagado = pagado;
        this.observaciones = observaciones;
        this.nombreLugar = nombreLugar;
        this.usernameCliente = usernameCliente;
        this.usernameEscort = usernameEscort;
    }
    
    public ContratoResponse(Long id, Date fechaReserva, int tiempo, boolean aceptado, boolean pagado, String observaciones, String nombreLugar, String username, int user) {
        this.id = id;
        this.fechaReserva = fechaReserva;
        this.tiempo = tiempo;
        this.aceptado = aceptado;
        this.pagado = pagado;
        this.observaciones = observaciones;
        this.nombreLugar = nombreLugar;
        if (user == 1){
            this.usernameEscort = username;
        }else{
            this.usernameCliente = username;
        }
        
    }

    public Long getId() {
        return id;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public int getTiempo() {
        return tiempo;
    }

    public boolean isAceptado() {
        return aceptado;
    }

    public boolean isPagado() {
        return pagado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public String getNombreLugar() {
        return nombreLugar;
    }

    public String getUsernameCliente() {
        return usernameCliente;
    }

    public String getUsernameEscort() {
        return usernameEscort;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public void setAceptado(boolean aceptado) {
        this.aceptado = aceptado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setNombreLugar(String nombreLugar) {
        this.nombreLugar = nombreLugar;
    }

    public void setUsernameCliente(String usernameCliente) {
        this.usernameCliente = usernameCliente;
    }

    public void setUsernameEscort(String usernameEscort) {
        this.usernameEscort = usernameEscort;
    }
}
