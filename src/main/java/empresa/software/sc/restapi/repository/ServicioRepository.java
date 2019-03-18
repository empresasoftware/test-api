/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.repository;

import empresa.software.sc.restapi.model.Servicio;
import empresa.software.sc.restapi.model.ServicioName;
import empresa.software.sc.restapi.model.Servicio;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pedro
 */
@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long>{
        Optional<Servicio> findByName(ServicioName servicioName);
}
