package com.projetGestionComp.Service;

import com.projetGestionComp.Execption.FactureNotFoundException;
import com.projetGestionComp.Models.Facture;

import java.util.List;
import java.util.Map;

public interface FactureService {
    public List<Facture> getAllFactures();
    public Facture getFacture(long id) throws FactureNotFoundException;
    public void suppFacture(long id);
    public void modFacture(long id ,Facture f);
    public void addFacture(Facture facture);
    public  Integer nmbrFacturePayee();
    public Integer nmbrFactureNonPayee();
    public Integer nmbrFactureInconnue();

    public List<Map<String, Object>> findNumberOfFacturesLast7Days();
    public List<Map<String, Object>> findNumberOfFacturesLast7Months();
    public List<Map<String, Object>> findNumberOfFacturesLast7Years();

    /*public Map<String, Integer> findNumberOfFacturesLast7Days();

    public Map<String, Integer> findNumberOfFacturesLast7Months();

    public Map<String, Integer> findNumberOfFacturesLast7Years();*/


}
