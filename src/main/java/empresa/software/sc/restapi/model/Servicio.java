/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.NaturalId;

/**
 *
 * @author pedro
 */
@Entity
@Table(name = "servicios")
public class Servicio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60, unique=true)
    private ServicioName name;

    public Servicio() {
    }

    public Servicio(Long id, ServicioName name) {
        this.id = id;
        this.name = name;
    }
    
    

    public Long getId() {
        return id;
    }

    public ServicioName getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(ServicioName name) {
        this.name = name;
    }
    
    @Override
     public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Servicio)) {
      return false;
    }
    Servicio cc = (Servicio)o;
    return Objects.equals(cc.id, id);
  }
    
}
