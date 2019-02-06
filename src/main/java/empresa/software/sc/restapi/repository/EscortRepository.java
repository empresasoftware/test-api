/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.repository;

import empresa.software.sc.restapi.model.Escort;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author pedro
 */
public interface EscortRepository extends JpaRepository<Escort, Long> {
    Optional<Escort> findByEmail(String email);

    Optional<Escort> findByUsernameOrEmail(String username, String email);

    List<Escort> findByIdIn(List<Long> userIds);

    Optional<Escort> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}