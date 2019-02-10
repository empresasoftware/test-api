/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.payload;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author pedro
 */
public class EscortRequest extends SignUpRequest{
    
    @NotBlank
    @Size(max = 20)
    private String pais;

    @NotBlank
    private String estatura;

    @NotBlank
    private String peso;
    
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
    @Size(max = 20)
    private String fechaNacimiento;
    
    @NotBlank
    @Size(max = 100)
    private String lugares;
    
    @Size(max = 100)
    private String restricciones;

    public String getPais() {
        return pais;
    }

    public String getEstatura() {
        return estatura;
    }

    public String getPeso() {
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

    public String getFechaNacimiento() {
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

    public void setEstatura(String estatura) {
        this.estatura = estatura;
    }

    public void setPeso(String peso) {
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

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setLugares(String lugares) {
        this.lugares = lugares;
    }

    public void setRestricciones(String restricciones) {
        this.restricciones = restricciones;
    }
}
