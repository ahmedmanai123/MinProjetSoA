package com.projetGestionComp.Service;

import com.projetGestionComp.Dto.ReglementDTO;
import com.projetGestionComp.Models.EtatPaiement;
import com.projetGestionComp.Models.Reglement;

import java.util.List;

public interface ReglementService {

    List<ReglementDTO> getReglementsAvecEtatPaiement();

    ReglementDTO mapReglementToDTO(Reglement reglement);

    EtatPaiement getEtatPaiement(Reglement reglement);

    void payerFactureIndividuelle(Long idReglement);

    void payerFacture(Reglement reglement);
}
