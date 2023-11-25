package com.projetGestionComp.Service;

import com.projetGestionComp.Execption.FactureNotFoundException;
import com.projetGestionComp.Models.Facture;
import com.projetGestionComp.Repository.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactureServiceImp implements FactureService{


    @Autowired
    private FactureRepository factureRepository;

    @Override
    public List<Facture> getAllFactures() {
        List<Facture> factures=factureRepository.findAll();
        return factures;
    }

    @Override
    public Facture getFacture(long id) throws FactureNotFoundException {
        Facture facture=factureRepository.findById(id).orElseThrow(()->new FactureNotFoundException("Facture non existe!!"));
        return facture;
    }

    @Override
    public void suppFacture(long id) {
        factureRepository.deleteById(id);

    }

    @Override
    public void modFacture(long id, Facture f) {
        Facture facture=f;
        facture.setIdFacture(id);
        factureRepository.save(facture);

    }

    @Override
    public void addFacture(Facture f) {
        factureRepository.save(f);

    }
}
