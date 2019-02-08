/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author pedro
 */


@Entity
@Table(name = "escorts")
public class Escort extends User {

    @NotBlank
    @Size(max = 20)
    private String pais;

    @NotBlank
    private double estatura;

    @NotBlank
    private double peso;
    
    @NotBlank
    @Size(max = 100)
    private String idiomas;

    @NotBlank
    @Size(max = 255)
    private String biografia;
    
    @NotBlank
    @Size(max = 50)
    private String orientacion;

    @NotBlank
    @Size(max = 15)
    private String cabello;
    
    @NotBlank
    @Size(max = 12)
    private String medidas;
    
    @NotBlank
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    
    @NotBlank
    @Size(max = 100)
    private String lugares;
    
    @Size(max = 100)
    private String restricciones;
    
    

    public Escort() {
        
    }

    public Escort(String pais, double estatura, double peso, String idiomas, String biografia, String orientacion, String cabello, String medidas, Date fechaNacimiento, String lugares, String restricciones) {
        this.pais = pais;
        this.estatura = estatura;
        this.peso = peso;
        this.idiomas = idiomas;
        this.biografia = biografia;
        this.orientacion = orientacion;
        this.cabello = cabello;
        this.medidas = medidas;
        this.fechaNacimiento = fechaNacimiento;
        this.lugares = lugares;
        this.restricciones = restricciones;
    }

    public Escort(String pais, double estatura, double peso, String idiomas, String biografia, String orientacion, String cabello, String medidas, Date fechaNacimiento, String lugares, String restricciones, String name, String username, String email, String password) {
        super(name, username, email, password, true);
        this.pais = pais;
        this.estatura = estatura;
        this.peso = peso;
        this.idiomas = idiomas;
        this.biografia = biografia;
        this.orientacion = orientacion;
        this.cabello = cabello;
        this.medidas = medidas;
        this.fechaNacimiento = fechaNacimiento;
        this.lugares = lugares;
        this.restricciones = restricciones;
    }
    
    

    public String getPais() {
        return pais;
    }

    public double getEstatura() {
        return estatura;
    }

    public double getPeso() {
        return peso;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public String getBiografia() {
        return biografia;
    }

    public String getOrientacion() {
        return orientacion;
    }

    public String getCabello() {
        return cabello;
    }

    public String getMedidas() {
        return medidas;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getLugares() {
        return lugares;
    }

    public String getRestricciones() {
        return restricciones;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public void setOrientacion(String orientacion) {
        this.orientacion = orientacion;
    }

    public void setCabello(String cabello) {
        this.cabello = cabello;
    }

    public void setMedidas(String medidas) {
        this.medidas = medidas;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setLugares(String lugares) {
        this.lugares = lugares;
    }

    public void setRestricciones(String restricciones) {
        this.restricciones = restricciones;
    }
    
}
