/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author pedro
 */
@Entity
@Table(name = "tarifas")
public class Tarifa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    protected int tiempo;
    
    protected double inversion;

    public Tarifa() {
    }

    public Tarifa(int tiempo, double inversion) {
        this.tiempo = tiempo;
        this.inversion = inversion;
    }
    
    

    public Long getId() {
        return id;
    }

    public int getTiempo() {
        return tiempo;
    }

    public double getInversion() {
        return inversion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public void setInversion(double inversion) {
        this.inversion = inversion;
    }
}
