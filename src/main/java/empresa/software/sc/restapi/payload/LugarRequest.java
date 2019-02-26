/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author pedro
 */
public class LugarRequest {
    
    @NotBlank
    @Size(max = 50)
    private String nombre;
    
    @NotBlank
    @Size(max = 255)
    private String descripcion;
    
    @NotBlank
    @Size(max = 255)
    private String direccion;
    
    @NotBlank
    @Size(max = 100)
    private String referencia;

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
