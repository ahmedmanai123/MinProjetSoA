package com.projetGestionComp.Controller;

import com.projetGestionComp.Execption.FactureNotFoundException;
import com.projetGestionComp.Models.Client;
import com.projetGestionComp.Models.Facture;
import com.projetGestionComp.Service.ClientService;
import com.projetGestionComp.Service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RestController
@RequestMapping("/gestionFacture")
public class FactureController {

    @Autowired
    private FactureService factureService;
    @Autowired
    private ClientService clientService;


    @GetMapping("/allFactures")
    @Procedure(MediaType.APPLICATION_JSON_VALUE)
    public List<Facture> getAllFactures() {
        List<Facture> factures=factureService.getAllFactures();
        return factures;
    }

    @GetMapping("/{id}")
    @Procedure(MediaType.APPLICATION_JSON_VALUE)

    public Facture getFacture(@PathVariable long id) throws FactureNotFoundException {
        Facture facture=factureService.getFacture(id);
        return facture;
    }

    @DeleteMapping("/{id}")
    @Procedure(MediaType.APPLICATION_JSON_VALUE)

    public void suppFacture(@PathVariable long id) {
        factureService.suppFacture(id);
    }
    @PutMapping("/{id}")
    public Facture modFacture(@PathVariable long id, @RequestBody Facture f) {
        Facture facture=f;
        facture.setIdFacture(id);
        factureService.addFacture(facture);
        return facture;
    }
    @PostMapping("/addFacture")
    @Procedure(MediaType.APPLICATION_JSON_VALUE)
    public Facture addFacture(@RequestBody Facture facture) {
        factureService.addFacture(facture);
        return facture;
    }
    @GetMapping("/nmbrFacturePayee")
    @Procedure(MediaType.APPLICATION_JSON_VALUE)
    public Integer nmbrFacturePayee(){
        return factureService.nmbrFacturePayee();
    }
    @GetMapping("/nmbrFactureNonPayee")
    @Procedure(MediaType.APPLICATION_JSON_VALUE)
    public Integer nmbrFactureNonPayee(){
        return factureService.nmbrFactureNonPayee();
    }

    @GetMapping("/nmbrFactureInconnue")
    @Procedure(MediaType.APPLICATION_JSON_VALUE)
    public Integer nmbrFactureInconnue(){
        return factureService.nmbrFactureInconnue();
    }

    @GetMapping("/clientNonPayee")
    @Procedure(MediaType.APPLICATION_JSON_VALUE)
    public List<Client> ClientNonPayee(){
        return clientService.ClientNonPayee();
    }

    @GetMapping("/nmbrFactureLast7Days")
    public List<Map<String, Object>> findNumberOfFacturesLast7Days(){
        return factureService.findNumberOfFacturesLast7Days();

    }

    /*
    @Procedure(MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Integer> findNumberOfFacturesLast7Days(){
        return factureService.findNumberOfFacturesLast7Days();
    }*/


    @GetMapping("/nmbrFactureLast7Months")
    public List<Map<String, Object>> findNumberOfFacturesLast7Months(){
        return factureService.findNumberOfFacturesLast7Months();

    }

    /*
    @Procedure(MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Integer> findNumberOfFacturesLast7Months(){
        return factureService.findNumberOfFacturesLast7Months();
    }*/

   @GetMapping("/nmbrFactureLast7Years")
   public List<Map<String, Object>> findNumberOfFacturesLast7Years(){
       return factureService.findNumberOfFacturesLast7Years();

   }

    /*
    @Procedure(MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Integer> findNumberOfFacturesLast7Years(){
        return factureService.findNumberOfFacturesLast7Years();
    }*/





}
