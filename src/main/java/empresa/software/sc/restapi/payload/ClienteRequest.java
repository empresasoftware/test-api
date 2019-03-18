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
public class ClienteRequest extends SignUpRequest{
    
    @NotBlank
    @Size(max = 20)
    private String pais;
    
    @NotBlank
    @Size(max = 20)
    private String fechaNacimiento;

    public String getPais() {
        return pais;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
