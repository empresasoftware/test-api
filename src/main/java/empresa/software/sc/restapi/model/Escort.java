/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.List;
import java.util.Set;
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
    @Size(max = 50)
    private String publicidad;

    private double estatura;

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
    
    @Column(name = "fecha_nacimiento",nullable=false)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    
    @NotBlank
    @Size(max = 100)
    private String lugares;
    
    @Size(max = 100)
    private String restricciones;
    
    @Column(name = "foto_perfil")
    @JsonIgnore
    protected Byte[] fotoPerfil;
    
    @Column(name = "tipo_foto")
    @JsonIgnore
    protected String tipoFoto;
    
    @OneToMany(mappedBy = "escort", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Contrato> contratos;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "escort_servicios",
            joinColumns = @JoinColumn(name = "escort_id"),
            inverseJoinColumns = @JoinColumn(name = "servicio_id"))
    private Set<Servicio> servicios;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Tarifa> tarifa;
    
    @ManyToMany(mappedBy = "likedEscorts")
    private Set<Cliente> likes;
    
    @OneToMany(mappedBy = "escort", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<EscortRating> ratings;
    
    @OneToMany(mappedBy = "escort", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<EscortComentario> comentarios;
 
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

    public Byte[] getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(Byte[] fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getTipoFoto() {
        return tipoFoto;
    }

    public void setTipoFoto(String tipoFoto) {
        this.tipoFoto = tipoFoto;
    }

    public Set<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(Set<Contrato> contratos) {
        this.contratos = contratos;
    }

    public String getPublicidad() {
        return publicidad;
    }

    public void setPublicidad(String publicidad) {
        this.publicidad = publicidad;
    }

    public Set<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(Set<Servicio> servicios) {
        this.servicios = servicios;
    }

    public List<Tarifa> getTarifa() {
        return tarifa;
    }

    public void setTarifa(List<Tarifa> tarifa) {
        this.tarifa = tarifa;
    }

    public Set<EscortRating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<EscortRating> ratings) {
        this.ratings = ratings;
    }

    public Set<EscortComentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<EscortComentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Set<Cliente> getLikes() {
        return likes;
    }

    public void setLikes(Set<Cliente> likes) {
        this.likes = likes;
    }
    
}
