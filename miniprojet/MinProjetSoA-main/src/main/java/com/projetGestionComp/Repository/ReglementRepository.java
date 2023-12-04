package com.projetGestionComp.Repository;

import com.projetGestionComp.Models.Reglement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ReglementRepository extends JpaRepository<Reglement, Long> {


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
