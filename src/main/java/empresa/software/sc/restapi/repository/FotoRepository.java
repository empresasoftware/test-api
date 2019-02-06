/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.repository;

import empresa.software.sc.restapi.model.Foto;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author pedro
 */
public interface FotoRepository extends JpaRepository<Foto, Long> {
    Page<Foto> findByEscortId(Long escortId, Pageable pageable);
    Optional<Foto> findByIdAndEscortId(Long id, Long escortId);
}
