/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.repository;

import empresa.software.sc.restapi.model.Contrato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author pedro
 */
public interface ContratoRepository extends JpaRepository<Contrato, Long> {
    @Query("SELECT c FROM Contrato c WHERE c.cliente.id= :cliente_id")
    Page<Contrato> findByClienteId(@Param("cliente_id") Long clienteId, Pageable pageable);

    @Query("SELECT c FROM Contrato c WHERE c.escort.id= :escort_id")
    Page<Contrato> findByEscortId(@Param("escort_id") Long escortId, Pageable pageable);

    @Query("SELECT c FROM Contrato c WHERE c.lugar.id= :lugar_id")
    Page<Contrato> findByLugarId(@Param("lugar_id") Long lugarId, Pageable pageable);
}
