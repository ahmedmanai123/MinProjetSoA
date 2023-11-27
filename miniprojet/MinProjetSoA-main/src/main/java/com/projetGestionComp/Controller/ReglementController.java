package com.projetGestionComp.Controller;

import com.projetGestionComp.Models.Client;
import com.projetGestionComp.Models.Reglement;
import com.projetGestionComp.Service.ReglementServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController

@RequestMapping("/api/reglements")
public class ReglementController {

    private final ReglementServiceImp reglementServiceImp;

    @Autowired
    public ReglementController(ReglementServiceImp reglementServiceImp) {
        this.reglementServiceImp = reglementServiceImp;
    }

    @GetMapping("/all")

    public List<Reglement> getAllReglements() {
        return reglementServiceImp.getAllReglements();
    }
    @GetMapping("/byClientId/{clientId}")
    public List<Reglement>getReglementsByClientId(@PathVariable Long clientId){
        return reglementServiceImp.getReglementsByClientId(clientId);
    }
    @GetMapping("/{reglementId}")
    public  List<Reglement> getReglementById(@PathVariable Long reglementId) {
        Reglement reglemnt = reglementServiceImp.getReglementById(reglementId);
        return Collections.singletonList(reglemnt);
    }
    @PostMapping("/pay/{reglementId}")
    public  List<Reglement>payReglement(@PathVariable Long reglementId){
        Reglement reglemnt = reglementServiceImp.payReglement(reglementId);
        return Collections.singletonList(reglemnt);
    }
    @PostMapping("/payAll/{clientId}")
    public void payAllReglementsByClientId(@PathVariable Long clientId){
         reglementServiceImp.payAllReglementsByClientId(clientId);
    }
    @GetMapping("/clientDetails/{clientId}")
    public List<Client>getClientDetails(@PathVariable Long clientId){
        return Collections.singletonList(reglementServiceImp.getClientDetails(clientId));
    }
}
