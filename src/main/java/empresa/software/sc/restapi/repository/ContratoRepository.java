/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.repository;

import empresa.software.sc.restapi.model.Contrato;
import empresa.software.sc.restapi.payload.ContratoResponse;
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
    @Query("SELECT new empresa.software.sc.restapi.payload.ContratoResponse"
            + "(c.id, c.fechaReserva, c.tiempo, c.aceptado, c.pagado, c.observaciones, c.lugar.nombre, c.escort.username, 1)"
            + " FROM Contrato c WHERE c.cliente.id= :cliente_id")
    Page<Contrato> findByClienteId(@Param("cliente_id") Long clienteId, Pageable pageable);

    @Query("SELECT new empresa.software.sc.restapi.payload.ContratoResponse"
            + "(c.id, c.fechaReserva, c.tiempo, c.aceptado, c.pagado, c.observaciones, c.lugar.nombre, c.cliente.username, 2)"
            + " FROM Contrato c  WHERE c.escort.id= :escort_id")
    Page<Contrato> findByEscortId(@Param("escort_id") Long escortId, Pageable pageable);

    @Query("SELECT new empresa.software.sc.restapi.payload.ContratoResponse"
             + "(c.id, c.fechaReserva, c.tiempo, c.aceptado, c.pagado, c.observaciones, c.lugar.nombre, c.cliente.username, c.escort.username)"
             + " FROM Contrato c  WHERE c.lugar.id= :lugar_id")
    Page<Contrato> findByLugarId(@Param("lugar_id") Long lugarId, Pageable pageable);
    
    @Query("SELECT new empresa.software.sc.restapi.payload.ContratoResponse"
            + "(c.id, c.fechaReserva, c.tiempo, c.aceptado, c.pagado, c.observaciones, c.lugar.nombre, c.cliente.username, c.escort.username)"
            + " FROM Contrato c ")
    Page<ContratoResponse> findAllResume(Pageable pageable);
    
    @Query("SELECT new empresa.software.sc.restapi.payload.ContratoResponse"
            + "(c.id, c.fechaReserva, c.tiempo, c.aceptado, c.pagado, c.observaciones, c.lugar.nombre, c.cliente.username, c.escort.username)"
            + " FROM Contrato c WHERE c.id= :contrato_id")
    ContratoResponse findbyIdResume(@Param("contrato_id") Long contratoId);
}
