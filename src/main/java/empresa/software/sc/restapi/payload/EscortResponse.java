/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.payload;

import empresa.software.sc.restapi.model.Escort;
import empresa.software.sc.restapi.model.Foto;
import org.springframework.data.domain.Page;

/**
 *
 * @author pedro
 */
public class EscortResponse {
    protected Escort escort;
    protected Page<Foto> fotos;

    public EscortResponse(Escort escort, Page<Foto> fotos) {
        this.escort = escort;
        this.fotos = fotos;
    }

    public Escort getEscort() {
        return escort;
    }

    public Page<Foto> getFotos() {
        return fotos;
    }

    public void setEscort(Escort escort) {
        this.escort = escort;
    }

    public void setFotos(Page<Foto> fotos) {
        this.fotos = fotos;
    }
}
