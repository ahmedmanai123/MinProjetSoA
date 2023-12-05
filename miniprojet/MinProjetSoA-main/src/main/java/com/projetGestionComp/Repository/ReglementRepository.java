package com.projetGestionComp.Repository;

import com.projetGestionComp.Models.Reglement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
=======

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import com.projetGestionComp.Models.Reglement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ReglementRepository extends JpaRepository<Reglement, Long> {

    @Query("SELECT r FROM Reglement r JOIN r.client c WHERE c.name LIKE %:clientName%")
    List<Reglement> getReglementsByClientName(@Param("clientName") String clientName);
    @Query("SELECT SUM(r.montantTotal) FROM Reglement r WHERE YEAR(r.dateReglement) = YEAR(CURRENT_DATE) AND WEEK(r.dateReglement) = WEEK(CURRENT_DATE)")
     Double getTotalpayreglementSemaine();
    @Query("SELECT SUM(r.montantTotal) FROM Reglement r WHERE YEAR(r.dateReglement) = YEAR(CURRENT_DATE) AND MONTH(r.dateReglement) = MONTH(CURRENT_DATE) AND DAY(r.dateReglement) = DAY(CURRENT_DATE)")
    Double getTotalPayReglementDay();
    @Query("SELECT COUNT(r) FROM Reglement r WHERE r.etatPaiement = 'PAYEE'")
    Long getCountOfPaidReglements();
    @Query("SELECT COUNT(r) FROM Reglement r WHERE r.etatPaiement = 'NON_PAYEE'")
    Long getCountOfUnpaidReglements();
    @Query("SELECT COUNT(r) FROM Reglement r WHERE r.modePaiement = 'espece'")
    Integer nmbrReglementEnEspece();

    @Query("SELECT COUNT(r) FROM Reglement r WHERE r.modePaiement = 'cheque'")
    Integer nmbrReglementEnCheque();

    @Query("SELECT COUNT(r) FROM Reglement r WHERE r.modePaiement = 'enligne'")
    Integer nmbrReglementEnligne();

    @Query(value = "SELECT DATE(date_reglement) as reglement_date, SUM(montant_total) as total_amount " +
            "FROM Reglement " +
            "WHERE date_reglement >= CURRENT_DATE - INTERVAL 6 DAY " +
            "GROUP BY reglement_date " +
            "ORDER BY reglement_date", nativeQuery = true)
    List<Map<String, Object>> findTotalAmountPerDayLast7Days();


    @Query(value = "SELECT DATE_FORMAT(date_reglement, '%Y-%m') as reglement_date, SUM(montant_total) as total_amount " +
            "FROM Reglement " +
            "WHERE date_reglement >= CURRENT_DATE - INTERVAL 6 MONTH " +
            "GROUP BY reglement_date " +
            "ORDER BY reglement_date ", nativeQuery = true)
    List<Map<String, Object>> findTotalAmountPerMonthLast7Months();

    @Query(value = "SELECT YEAR(date_reglement) as reglement_date, SUM(montant_total) as total_amount " +
            "FROM Reglement " +
            "WHERE date_reglement >= CURRENT_DATE - INTERVAL 6 YEAR " +
            "GROUP BY reglement_date ", nativeQuery = true)
    List<Map<String, Object>> findTotalAmountPerYearLast7Years();






}

