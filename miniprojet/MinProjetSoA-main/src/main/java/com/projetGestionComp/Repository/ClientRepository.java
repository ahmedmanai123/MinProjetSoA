package com.projetGestionComp.Repository;

import com.projetGestionComp.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
   // @Query("SELECT c.name FROM Client c WHERE c.email = :email AND c.motdepass = :mot")
    //Client getClientByemail(@Param("email") String email, @Param("mot") String mot);

}
