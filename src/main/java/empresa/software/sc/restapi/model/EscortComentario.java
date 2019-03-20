/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.model;

import empresa.software.sc.restapi.model.audit.DateAudit;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author pedro
 */

@Entity
public class EscortComentario extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
 
    @ManyToOne
    @JoinColumn(name = "escort_id")
    private Escort escort;
 
    private String comentario;

    public EscortComentario() {
    }

    public EscortComentario(Cliente cliente, Escort escort, String comentario) {
        this.cliente = cliente;
        this.escort = escort;
        this.comentario = comentario;
    }
    
    

    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Escort getEscort() {
        return escort;
    }

    public String getComentario() {
        return comentario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setEscort(Escort escort) {
        this.escort = escort;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
}
