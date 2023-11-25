package com.projetGestionComp.Repository;

import com.projetGestionComp.Models.Reglement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReglementRepository extends JpaRepository<Reglement, Long> {


    List<Reglement> findByClientId(Long clientId);
}
