package com.projetGestionComp.Repository;

import com.projetGestionComp.Models.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Repository
public interface FactureRepository extends JpaRepository<Facture,Long> {
    @Query("SELECT COUNT(f) FROM Facture f WHERE f.etatPaiement = 'PAYEE'")
    Integer nmbrFacturePayee();

    @Query("SELECT COUNT(f) FROM Facture f WHERE f.etatPaiement = 'NON_PAYEE'")
    Integer nmbrFactureNonPayee();

    @Query("SELECT COUNT(f) FROM Facture f WHERE f.etatPaiement = 'INCONNU'")
    Integer nmbrFactureInconnue();

    /*@Query("SELECT DATE(f.dateFacture) AS date, COUNT(f) FROM Facture f WHERE f.dateFacture BETWEEN :startDate AND :endDate GROUP BY date")
    List<Object[]> findNumberOfFacturesLast7Days(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );



    @Query("SELECT CONCAT(MONTH(f.dateFacture), '/', YEAR(f.dateFacture)) AS monthYear, COUNT(f) FROM Facture f WHERE f.dateFacture BETWEEN :startDate AND :endDate GROUP BY monthYear")
    List<Object[]> findNumberOfFacturesLast7Months(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    @Query("SELECT YEAR(f.dateFacture), COUNT(f) FROM Facture f WHERE f.dateFacture BETWEEN :startDate AND :endDate GROUP BY YEAR(f.dateFacture)")
    List<Object[]> findNumberOfFacturesByYear(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
*/



    @Query(value = "SELECT DATE(date_facture) AS factureDate, COUNT(id_Facture) AS factureCount FROM Facture WHERE date_facture >= CURRENT_DATE - INTERVAL 6 DAY AND date_facture <= CURRENT_DATE + INTERVAL 1 DAY GROUP BY factureDate;", nativeQuery = true)
    List<Map<String, Object>> findNumberOfFacturesLast7Days();

    @Query(value = "SELECT DATE_FORMAT(date_facture, '%Y-%m') AS factureDate, COUNT(id_Facture) AS factureCount FROM Facture WHERE date_facture >= CURRENT_DATE - INTERVAL 6 MONTH GROUP BY factureDate;", nativeQuery = true)
    List<Map<String, Object>> findNumberOfFacturesLast7Months();

    @Query(value = "SELECT YEAR(date_facture) AS factureDate, COUNT(id_facture) AS factureCount \n" +
            "FROM Facture \n" +

            "WHERE date_facture >= CURRENT_DATE - INTERVAL 6 YEAR\n" +
            "GROUP BY factureDate;\n", nativeQuery = true)
    List<Map<String, Object>> findNumberOfFacturesLast7Years();










}
