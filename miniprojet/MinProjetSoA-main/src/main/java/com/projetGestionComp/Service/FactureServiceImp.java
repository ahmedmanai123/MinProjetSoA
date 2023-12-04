package com.projetGestionComp.Service;

import com.projetGestionComp.Execption.FactureNotFoundException;
import com.projetGestionComp.Models.Facture;
import com.projetGestionComp.Repository.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    @Override
    public  Integer nmbrFacturePayee(){
        return factureRepository.nmbrFacturePayee() ;

    }
    @Override
    public  Integer nmbrFactureNonPayee(){
        return factureRepository.nmbrFactureNonPayee() ;

    }

    @Override
    public  Integer nmbrFactureInconnue(){
        return factureRepository.nmbrFactureInconnue() ;

    }

    /*@Override
    public Map<String, Integer> findNumberOfFacturesLast7Days() {
        LocalDateTime endDate = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(999999999);
        LocalDateTime startDate = endDate.minusDays(6).withHour(0).withMinute(0).withSecond(0).withNano(0);// seven days ago

        List<Object[]> result = factureRepository.findNumberOfFacturesLast7Days(startDate, endDate);

        Map<String, Integer> resultMap = new LinkedHashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        for (Object[] row : result) {
            if (row[0] instanceof java.sql.Date) {
                java.sql.Date sqlDate = (java.sql.Date) row[0];
                LocalDateTime date = sqlDate.toLocalDate().atStartOfDay();
                String formattedDate = date.format(formatter);
                Integer count = ((Number) row[1]).intValue();
                resultMap.put(formattedDate, count);
            } else if (row[0] instanceof java.sql.Timestamp) {
                java.sql.Timestamp timestamp = (java.sql.Timestamp) row[0];
                LocalDateTime date = timestamp.toLocalDateTime();
                String formattedDate = date.format(formatter);
                Integer count = ((Number) row[1]).intValue();
                resultMap.put(formattedDate, count);
            }
        }

        return resultMap;
    }







    @Override
    public Map<String, Integer> findNumberOfFacturesLast7Months() {
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusMonths(6); // seven months ago

        List<Object[]> result = factureRepository.findNumberOfFacturesLast7Months(startDate, endDate);

        Map<String, Integer> resultMap = new HashMap<>();
        for (Object[] row : result) {
            String monthYear = (String) row[0];
            Integer count = ((Number) row[1]).intValue();
            resultMap.put(monthYear, count);
        }

        return resultMap;
    }

    public Map<String, Integer> findNumberOfFacturesLast7Years() {
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusYears(6); // seven years ago

        List<Object[]> result = factureRepository.findNumberOfFacturesByYear(startDate, endDate);

        Map<String, Integer> resultMap = new LinkedHashMap<>();
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");

        for (Object[] row : result) {
            Integer year = (Integer) row[0];
            Long count = (Long) row[1];
            resultMap.put(yearFormatter.format(Year.of(year)), count.intValue());
        }

        return resultMap;
    }*/
    @Override
    public List<Map<String, Object>> findNumberOfFacturesLast7Days(){
        return factureRepository.findNumberOfFacturesLast7Days();
    }
    @Override
    public List<Map<String, Object>> findNumberOfFacturesLast7Months(){
        return factureRepository.findNumberOfFacturesLast7Months();
    }

    @Override
    public List<Map<String, Object>> findNumberOfFacturesLast7Years(){
        return factureRepository.findNumberOfFacturesLast7Years();
    }






}
