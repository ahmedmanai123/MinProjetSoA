package com.projetGestionComp.Controller;

import com.projetGestionComp.Dto.ReglementDTO;
import com.projetGestionComp.Service.ReglementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reglements")
public class ReglementController {

    private final ReglementService reglementService;

    @Autowired
    public ReglementController(ReglementService reglementService) {
        this.reglementService = reglementService;
    }

    @GetMapping("/avecEtatPaiement")
    public List<ReglementDTO> getReglementsAvecEtatPaiement() {
        return reglementService.getReglementsAvecEtatPaiement();
    }
    // Endpoint pour payer une facture individuelle
    @PostMapping("/payer/{idReglement}")
    public ResponseEntity<String> payerFactureIndividuelle(@PathVariable Long idReglement) {
        try {
            reglementService.payerFactureIndividuelle(idReglement);
            return ResponseEntity.ok("Facture payée avec succès.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint pour payer toutes les factures groupées
    @GetMapping("/payer-groupes")
    public void payerFacturesGroupées() {
        reglementService.payerFacturesGroupées();
    }
    @GetMapping("/parclient/{clientId}")
    public ResponseEntity<List<ReglementDTO>> getReglementsAvecEtatPaiementParClient(@PathVariable Long clientId) {
        try {
            List<ReglementDTO> reglements = reglementService.getReglementsAvecEtatPaiementParClient(clientId);
            return ResponseEntity.ok(reglements);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
