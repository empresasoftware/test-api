/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author pedro
 */
@Entity
@Table(name = "lugares")
public class Lugar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    @NotBlank
    @Size(max = 50)
    protected String nombre;
    
    @NotBlank
    @Size(max = 255)
    protected String descripcion;
    
    @NotBlank
    @Size(max = 255)
    protected String direccion;
    
    @NotBlank
    @Size(max = 100)
    protected String referencia;

    public Lugar() {
    }
    
    

    public Lugar(String nombre, String descripcion, String direccion, String referencia) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.referencia = referencia;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}
