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
public class ContratoRequest {
    @NotBlank
    @Size(max = 20)
    private String fechaReserva;

    private int tiempo;
    
    private boolean aceptado;

    private boolean pagado;
    
    @NotBlank
    @Size(max = 100)
    private String observaciones;
    
    private Long lugar_id;

    public String getFechaReserva() {
        return fechaReserva;
    }

    public int getTiempo() {
        return tiempo;
    }

    public boolean isAceptado() {
        return aceptado;
    }

    public boolean isPagado() {
        return pagado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public Long getLugar_id() {
        return lugar_id;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public void setAceptado(boolean aceptado) {
        this.aceptado = aceptado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setLugar_id(Long lugar_id) {
        this.lugar_id = lugar_id;
    }
}
