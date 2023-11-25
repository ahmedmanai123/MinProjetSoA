package com.projetGestionComp.Controller;

import com.projetGestionComp.Execption.FactureNotFoundException;
import com.projetGestionComp.Models.Facture;
import com.projetGestionComp.Service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Controller
@RestController
@RequestMapping("/gestionFacture")
public class FactureController {

    @Autowired
    private FactureService factureService;


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
    public Facture addFacture(@RequestBody Facture facture) {
        factureService.addFacture(facture);
        return facture;
    }
}
