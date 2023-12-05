package com.projetGestionComp.Service;

import com.projetGestionComp.Dto.ReglementDTO;
import com.projetGestionComp.Execption.ClientNotFoundException;
import com.projetGestionComp.Execption.InvoiceNotBelongingToClientException;
import com.projetGestionComp.Execption.InvoiceNotFoundException;
import com.projetGestionComp.Models.Client;
import com.projetGestionComp.Models.EtatPaiement;
import com.projetGestionComp.Models.Facture;
import com.projetGestionComp.Models.Reglement;
import com.projetGestionComp.Repository.ClientRepository;
import com.projetGestionComp.Repository.FactureRepository;
import com.projetGestionComp.Repository.ReglementRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReglementServiceImp implements ReglementService {

    @Autowired
    private ReglementRepository reglementRepository;
    @Autowired
    private FactureRepository factureRepository;
    @Autowired
    private ClientRepository clientRepository;


    @Override
    public List<Reglement> getAllReglements() {
        return reglementRepository.findAll();
    }

    @Override
    public List<Reglement> getReglementsByClientId(Long clientId) {
        Client client= clientRepository.findById(clientId).orElse(null);
        List<Reglement> reglements=new ArrayList<>(client.getReglements());

        return reglements;
    }

    @Override
    public Reglement getReglementById(Long reglementId) {
        return reglementRepository.findById(reglementId).orElse(null);
    }

    @Override
    public Reglement payReglement(Long clientId, Long reglementId) throws ClientNotFoundException, InvoiceNotFoundException, InvoiceNotBelongingToClientException {
        // Retrieve the client by ID
        Optional<Client> optionalClient = clientRepository.findById(clientId);

        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();

            // Retrieve the invoice (reglement) by ID
            Optional<Reglement> optionalReglement = reglementRepository.findById(reglementId);

            if (optionalReglement.isPresent()) {
                Reglement reglement = optionalReglement.get();

                if (reglement.getClient().equals(client)) {
                    reglement.setPaye(true);
                    reglement.setEtatPaiement(EtatPaiement.PAYEE);
                    reglement.setDateReglement(LocalDate.now());
                    reglement.setModePaiement("enligne");
                    reglementRepository.save(reglement);

                    return reglement;
                } else {
                    // Invoice does not belong to the specified client
                    throw new InvoiceNotBelongingToClientException("Invoice does not belong to the specified client");
                }
            } else {
                // Invoice not found
                throw new InvoiceNotFoundException("Invoice not found");
            }
        } else {
            // Client not found
            throw new ClientNotFoundException("Client not found");
        }
    }



    @Override
    public void payAllReglementsByClientId(Long clientId) {
        Client client= clientRepository.findById(clientId).orElse(null);
        if (client != null) {
            List<Reglement> reglements = new ArrayList<>(client.getReglements());

            reglements.forEach(reglement -> {
                reglement.setPaye(true);
                reglement.setEtatPaiement(EtatPaiement.PAYEE);
                reglement.setDateReglement(LocalDate.now());
                // Vous pouvez ajouter d'autres détails de paiement ici
            });

            reglementRepository.saveAll(reglements);
        }
    }

    @Override
    public Client getClientDetails(Long clientId) {
        try {
            return clientRepository.getById(clientId);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    @Override
    public List<Reglement> getReglementsByClientName(String clientName) {
        return reglementRepository.getReglementsByClientName(clientName);
    }
    public Double getTotalpayreglementSemaine() {
        return reglementRepository.getTotalpayreglementSemaine();
    }
    public Double getTotalPayReglementDay() {
        return reglementRepository.getTotalPayReglementDay();
    }

    public Long getCountOfPaidReglements() {
        return reglementRepository.getCountOfPaidReglements();
    }
    public Long getCountOfUnpaidReglements() {
        return reglementRepository.getCountOfUnpaidReglements();
    }
}
