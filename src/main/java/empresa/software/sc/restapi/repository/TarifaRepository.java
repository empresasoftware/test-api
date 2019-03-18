/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.repository;

import empresa.software.sc.restapi.model.Tarifa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pedro
 */
@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Long>{
    @Query("SELECT c FROM Tarifa c WHERE c.escort.id= :escort_id")
    Page<Tarifa> findByEscortId(@Param("escort_id") Long escortId, Pageable pageable);
}
