package com.projetGestionComp.Service;

import com.projetGestionComp.Execption.ProduitNotFoundException;
import com.projetGestionComp.Models.Produit;

import java.util.List;

public interface ProduitService {

    Produit getByIdProduit(long id)throws ProduitNotFoundException;
}
