/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.controller;

import empresa.software.sc.restapi.model.Cliente;
import empresa.software.sc.restapi.model.Escort;
import org.springframework.context.ApplicationEvent;

/**
 *
 * @author pedro
 */
public class OnContratoCompleteEvent extends ApplicationEvent{
    private Escort escort;
    private Cliente cliente;
 
    public OnContratoCompleteEvent(Escort escort, Cliente cliente) {
        super (cliente);
        this.escort = escort;
    }

    public Escort getEscort() {
        return escort;
    }

    public void setEscort(Escort escort) {
        this.escort = escort;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
