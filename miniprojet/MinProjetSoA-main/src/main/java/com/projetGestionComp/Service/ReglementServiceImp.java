package com.projetGestionComp.Service;

import com.projetGestionComp.Dto.ReglementDTO;
import com.projetGestionComp.Models.Client;
import com.projetGestionComp.Models.EtatPaiement;
import com.projetGestionComp.Models.Facture;
import com.projetGestionComp.Models.Reglement;
import com.projetGestionComp.Repository.ClientRepository;
import com.projetGestionComp.Repository.FactureRepository;
import com.projetGestionComp.Repository.ReglementRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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
    public Reglement payReglement(Long reglementId) {
        Optional<Reglement> optionalReglement = reglementRepository.findById(reglementId);
        if (optionalReglement.isPresent()) {
            Reglement reglement = optionalReglement.get();
            reglement.setPaye(true);
            reglement.setEtatPaiement(EtatPaiement.PAYEE); // Set etatPaiement to PAYEE
            reglementRepository.save(reglement);
            return reglement;
        }
        return null;
    }


    @Override
    public void payAllReglementsByClientId(Long clientId) {
        Client client= clientRepository.findById(clientId).orElse(null);
        List<Reglement> reglements=new ArrayList<>(client.getReglements());

          reglements.forEach(reglement -> reglement.setPaye(true));
          reglementRepository.saveAll(reglements);
    }

    @Override
    public Client getClientDetails(Long clientId) {
        try {
            return clientRepository.getById(clientId);
        } catch (EntityNotFoundException e) {
            return null; // or handle the exception as needed
        }
    }
    @Override
    public Integer nmbrReglementEnEspece(){
        return reglementRepository.nmbrReglementEnEspece();
    }

    @Override
    public Integer nmbrReglementEnCheque(){
        return reglementRepository.nmbrReglementEnCheque();
    }

    @Override
    public Integer nmbrReglementEnligne(){
        return reglementRepository.nmbrReglementEnligne();
    }

    @Override
    public List<Map<String, Object>> findTotalAmountPerDayLast7Days() {
        return reglementRepository.findTotalAmountPerDayLast7Days();
    }
    @Override
    public List<Map<String, Object>> getTotalAmountPerMonthLast7Months() {
        return reglementRepository.findTotalAmountPerMonthLast7Months();
    }

    @Override
    public List<Map<String, Object>> findTotalAmountPerYearLast7Years(){
        return reglementRepository.findTotalAmountPerYearLast7Years();
    }



}
