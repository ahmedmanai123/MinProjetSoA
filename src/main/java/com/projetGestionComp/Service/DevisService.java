package com.projetGestionComp.Service;

import com.projetGestionComp.Execption.DevisNotFoundException;
import com.projetGestionComp.Models.Devis;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DevisService {
    Devis getDevis(long id)throws DevisNotFoundException;
    List<Devis>getAllDevis();
    Devis saveDevis(Devis d);
}
