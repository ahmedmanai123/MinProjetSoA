package com.projetGestionComp.Service;

import com.projetGestionComp.Dto.ReglementDTO;
import com.projetGestionComp.Execption.ClientNotFoundException;
import com.projetGestionComp.Execption.InvoiceNotBelongingToClientException;
import com.projetGestionComp.Execption.InvoiceNotFoundException;
import com.projetGestionComp.Models.Client;
import com.projetGestionComp.Models.EtatPaiement;
import com.projetGestionComp.Models.Reglement;

import java.util.List;

public interface ReglementService {

    public List<Reglement> getAllReglements();
    public List<Reglement> getReglementsByClientId(Long clientId);
    public Reglement getReglementById(Long reglementId);
    public Reglement payReglement(Long clientId,Long reglementId) throws ClientNotFoundException, InvoiceNotFoundException, InvoiceNotBelongingToClientException;
    public void payAllReglementsByClientId(Long clientId);
    public Client getClientDetails(Long clientId);
    public List<Reglement> getReglementsByClientName(String clientName);
    public Double getTotalpayreglementSemaine();

}
