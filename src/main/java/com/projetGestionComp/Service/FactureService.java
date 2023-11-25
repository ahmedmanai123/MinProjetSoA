package com.projetGestionComp.Service;
import com.projetGestionComp.Execption.FactureNotFoundException;
import com.projetGestionComp.Models.Facture;

import java.util.List;

public interface FactureService {
    public List<Facture> getAllFactures();
    public Facture getFacture(long id) throws FactureNotFoundException;
    public void suppFacture(long id);
    public void modFacture(long id ,Facture f);
    public void addFacture(Facture facture);
}
