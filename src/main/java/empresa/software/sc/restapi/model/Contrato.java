/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.model;

import empresa.software.sc.restapi.model.audit.DateAudit;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author pedro
 */
public class Contrato extends DateAudit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    @Column(name = "hora",nullable=false)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @NotBlank
    @Size(max = 100)
    private String tiempo;
    
    @ManyToOne
    @JoinColumn
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn
    private Escort escort;
}
