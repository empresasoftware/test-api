/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "clientes")
public class Cliente extends User {

    @NotBlank
    @Size(max = 20)
    private String pais;

    @Size(max = 20)
    private String estado;
    
    @NotBlank
    private Date fechaNacimiento;

    public Cliente() {
    }

    public Cliente(String pais, String estado, Date fechaNacimiento) {
        this.pais = pais;
        this.estado = estado;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Cliente(String pais, String estado, Date fechaNacimiento, String name, String username, String email, String password) {
        super(name, username, email, password, true);
        this.pais = pais;
        this.estado = estado;
        this.fechaNacimiento = fechaNacimiento;
    }
    
    

    public String getPais() {
        return pais;
    }

    public String getEstado() {
        return estado;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
