package com.projetGestionComp.Service;

import com.projetGestionComp.Execption.DevisNotFoundException;
import com.projetGestionComp.Models.Devis;
import com.projetGestionComp.Repository.DevisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DevisServiceImp implements DevisService{
    @Autowired
    private DevisRepository devisRepository;
    @Override
    public Devis getDevis(long id) throws DevisNotFoundException {
        return devisRepository.findById(id).orElseThrow(()->new DevisNotFoundException("devis no exist !!"));
    }

    @Override
    public List<Devis> getAllDevis() {
        return devisRepository.findAll();
    }

    @Override
    public Devis saveDevis(Devis d) {
        return devisRepository.save(d);
    }
}
