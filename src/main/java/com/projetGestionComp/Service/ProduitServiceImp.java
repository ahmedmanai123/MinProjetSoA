package com.projetGestionComp.Service;

import com.projetGestionComp.Execption.ProduitNotFoundException;
import com.projetGestionComp.Models.Produit;
import com.projetGestionComp.Repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitServiceImp implements ProduitService{
    @Autowired
    private ProduitRepository produitRepository;

    @Override
    public Produit getByIdProduit(long id)throws ProduitNotFoundException {
        Produit produit=produitRepository.findById(id).orElseThrow(()->new ProduitNotFoundException("produit non existe !") );
        return produit;
    }
}
