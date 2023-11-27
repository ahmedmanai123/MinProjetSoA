package com.projetGestionComp.Service;

import com.projetGestionComp.Dto.ReglementDTO;
import com.projetGestionComp.Models.Client;
import com.projetGestionComp.Models.EtatPaiement;
import com.projetGestionComp.Models.Reglement;

import java.util.List;

public interface ReglementService {

    public List<Reglement> getAllReglements();
    public List<Reglement> getReglementsByClientId(Long clientId);
    public Reglement getReglementById(Long reglementId);
    public Reglement payReglement(Long reglementId);
    public void payAllReglementsByClientId(Long clientId);
    public Client getClientDetails(Long clientId);
}
