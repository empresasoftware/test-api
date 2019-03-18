/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.payload;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author pedro
 */
public class TarifaRequest {
    
    @Min(value=1, message = "Valores positivos" )
    @Digits(fraction = 0, integer = 1, message ="Valores enteros")
    private int tiempo;
    
    @Min(value=0, message = "Valores positivos" )
    @Digits(fraction = 2, integer = 10, message ="Valores decimales")
    private double inversion;

    public int getTiempo() {
        return tiempo;
    }

    public double getInversion() {
        return inversion;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public void setInversion(double inversion) {
        this.inversion = inversion;
    }
}
