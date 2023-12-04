package com.projetGestionComp.Service;

import com.projetGestionComp.Models.Client;
import com.projetGestionComp.Models.Reglement;

import java.util.List;
import java.util.Map;

public interface ReglementService {

    public List<Reglement> getAllReglements();
    public List<Reglement> getReglementsByClientId(Long clientId);
    public Reglement getReglementById(Long reglementId);
    public Reglement payReglement(Long reglementId);
    public void payAllReglementsByClientId(Long clientId);
    public Client getClientDetails(Long clientId);
    public Integer nmbrReglementEnEspece();

    public Integer nmbrReglementEnCheque();

    public Integer nmbrReglementEnligne();

    public List<Map<String, Object>> findTotalAmountPerDayLast7Days();

    public List<Map<String, Object>> getTotalAmountPerMonthLast7Months();

    public List<Map<String, Object>> findTotalAmountPerYearLast7Years();

}
