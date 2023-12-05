package com.projetGestionComp.Controller;

import com.projetGestionComp.Execption.ClientNotFoundException;
import com.projetGestionComp.Execption.InvoiceNotBelongingToClientException;
import com.projetGestionComp.Execption.InvoiceNotFoundException;
import com.projetGestionComp.Models.Client;
import com.projetGestionComp.Models.Reglement;
import com.projetGestionComp.Service.ClientServiceImp;
import com.projetGestionComp.Service.ReglementServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200",allowCredentials = "true")
@RestController

@RequestMapping("/api/reglements")
public class ReglementController {

    private final ReglementServiceImp reglementServiceImp;
   @Autowired
    private ClientServiceImp clientServiceImp;
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
    @PostMapping("/pay/{clientId}/{reglementId}")
    public ResponseEntity<List<Reglement>> payReglement(
            @PathVariable Long clientId,
            @PathVariable Long reglementId) {
        try {
            Reglement reglement = reglementServiceImp.payReglement(clientId, reglementId);
            return ResponseEntity.ok(Collections.singletonList(reglement));
        } catch (ClientNotFoundException e) {
            // Handle client not found exception
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (InvoiceNotFoundException e) {
            // Handle invoice not found exception
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (InvoiceNotBelongingToClientException e) {
            // Handle invoice not belonging to client exception
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
        }
    }

    @PostMapping("/payAll/{clientId}")
    public void payAllReglementsByClientId(@PathVariable Long clientId){
         reglementServiceImp.payAllReglementsByClientId(clientId);
    }
    @GetMapping("/clientDetails/{clientId}")
    public List<Client>getClientDetails(@PathVariable Long clientId){
        return Collections.singletonList(reglementServiceImp.getClientDetails(clientId));
    }
    @GetMapping("/getClientparmotpass")
    public Long getClientIdByEmail(@RequestParam String email, @RequestParam String motdepass) {
        Long clientId = clientServiceImp.getClientIdByEmail(email,motdepass);

        if (clientId != null) {

            return clientId;
        } else {
            return null;
        }
    }
    @GetMapping("/parClient")
    public ResponseEntity<List<Reglement>> getReglementsByClientName(@RequestParam(required = true) String clientName) {
        List<Reglement> reglements = reglementServiceImp.getReglementsByClientName(clientName);
        return new ResponseEntity<>(reglements, HttpStatus.OK);
    }
    @GetMapping("/parSemaine")
     public  Double getmotparSemaine(){
        return reglementServiceImp.getTotalpayreglementSemaine();
     }
   // @GetMapping("/parjour")
   // public  Double getTotalPayReglementDay(){
    //    return reglementServiceImp.getTotalPayReglementDay();
   // }
   // @GetMapping("/rgpay")
    //public  Long getCountOfPaidReglements(){
      //  return reglementServiceImp.getCountOfPaidReglements();

   // @GetMapping("/regnopay")
   // public  Long getCountOfUnpaidReglements(){
       // return reglementServiceImp.getCountOfUnpaidReglements();}










}
