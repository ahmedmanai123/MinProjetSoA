package com.projetGestionComp.Repository;

import com.projetGestionComp.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

   // @Query("SELECT c.name FROM Client c WHERE c.email = :email AND c.motdepass = :mot")
    //Client getClientByemail(@Param("email") String email, @Param("mot") String mot);


    @Query("select distinct c from Client c, Facture f where c.id = f.client.id and f.etatPaiement = 'NON_PAYEE'\n")
    List<Client> ClientNonPayee();


}
