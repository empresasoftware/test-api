/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author pedro
 */
@Embeddable
public class EscortRatingKey implements Serializable {
 
    @Column(name = "escort_id")
    private Long escortId;
 
    @Column(name = "cliente_id")
    private Long clienteId;

    public Long getEscortId() {
        return escortId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setEscortId(Long escortId) {
        this.escortId = escortId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
}
