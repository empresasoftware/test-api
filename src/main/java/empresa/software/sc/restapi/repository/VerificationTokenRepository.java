/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.repository;

import empresa.software.sc.restapi.model.User;
import empresa.software.sc.restapi.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Steven
 */
public interface VerificationTokenRepository 
  extends JpaRepository<VerificationToken, Long> {
 
    VerificationToken findByToken(String token);
 
    @Query("SELECT v FROM VerificationToken v WHERE v.user.id= :user_id")
    VerificationToken findByUser(@Param("user_id") Long userId);
}
