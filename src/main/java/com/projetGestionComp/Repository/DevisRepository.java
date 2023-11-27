package com.projetGestionComp.Repository;

import com.projetGestionComp.Models.Devis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevisRepository extends JpaRepository<Devis,Long> {
}
