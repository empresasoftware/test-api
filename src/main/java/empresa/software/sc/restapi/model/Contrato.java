/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.model;

import empresa.software.sc.restapi.model.audit.DateAudit;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author pedro
 */
@Entity
@Table(name = "contratos")
public class Contrato extends DateAudit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    @Column(name = "hora",nullable=false)
    @Temporal(TemporalType.DATE)
    protected Date fechaReserva;

    protected int tiempo;
    
    protected boolean aceptado;

    protected boolean pagado;
    
    @NotBlank
    @Size(max = 100)
    protected String observaciones;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    protected Lugar lugar;
    
    @ManyToOne
    @JoinColumn
    protected Cliente cliente;

    @ManyToOne
    @JoinColumn
    protected Escort escort;

    public Contrato(Date fechaReserva, int tiempo, boolean aceptado, boolean pagado, String observaciones, Lugar lugar, Cliente cliente, Escort escort) {
        this.fechaReserva = fechaReserva;
        this.tiempo = tiempo;
        this.aceptado = aceptado;
        this.pagado = pagado;
        this.observaciones = observaciones;
        this.lugar = lugar;
        this.cliente = cliente;
        this.escort = escort;
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

    public Cliente getCliente() {
        return cliente;
    }

    public Escort getEscort() {
        return escort;
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

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setEscort(Escort Escort) {
        this.escort = escort;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }
   
}
