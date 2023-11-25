package com.projetGestionComp.Repository;

import com.projetGestionComp.Models.Client;
import com.projetGestionComp.Models.Facture;
import com.projetGestionComp.Models.Reglement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactureRepository extends JpaRepository<Facture,Long> {

}
