/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.model;

import empresa.software.sc.restapi.model.audit.DateAudit;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author pedro
 */
public class Transaccion extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Size(max = 255)
    private String descripcion;
    
    private double costoTotal;
    
    private double comision;
    
    private short estado;

    @NotBlank
    @Size(max = 20)
    private String codigoPromocional;
    
    
}
