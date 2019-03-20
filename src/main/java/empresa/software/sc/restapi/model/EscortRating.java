/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

/**
 *
 * @author pedro
 */

@Entity
public class EscortRating {

    @EmbeddedId
    private EscortRatingKey id;
    
    @ManyToOne
    @MapsId("cliente_id")
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
 
    @ManyToOne
    @MapsId("escort_id")
    @JoinColumn(name = "escort_id")
    private Escort escort;
 
    private int rating;

    public EscortRatingKey getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Escort getEscort() {
        return escort;
    }

    public int getRating() {
        return rating;
    }

    public void setId(EscortRatingKey id) {
        this.id = id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setEscort(Escort escort) {
        this.escort = escort;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
