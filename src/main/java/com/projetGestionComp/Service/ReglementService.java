package com.projetGestionComp.Service;

import com.projetGestionComp.Dto.ReglementDTO;
import com.projetGestionComp.Models.EtatPaiement;
import com.projetGestionComp.Models.Facture;
import com.projetGestionComp.Models.Reglement;
import com.projetGestionComp.Repository.FactureRepository;
import com.projetGestionComp.Repository.ReglementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReglementService {

    @Autowired
    private ReglementRepository reglementRepository;
    @Autowired
    private FactureRepository factureRepository;

    public List<ReglementDTO> getReglementsAvecEtatPaiement() {
        List<Reglement> reglements = reglementRepository.findAll();

        return reglements.stream()
                .map(this::mapReglementToDTO)
                .collect(Collectors.toList());
    }

    private ReglementDTO mapReglementToDTO(Reglement reglement) {
        ReglementDTO dto = new ReglementDTO();
        dto.setIdRegelment(reglement.getIdRegelment());
        dto.setDateReglement(reglement.getDateReglement());
        dto.setMontantTotal(reglement.getMontantTotal());
        dto.setType(reglement.getType());
        dto.setModePaiement(reglement.getModePaiement());
        dto.setFacture(reglement.getFacture());
        dto.setEtatPaiement(getEtatPaiement(reglement));

        return dto;
    }

    private EtatPaiement getEtatPaiement(Reglement reglement) {
        Facture facture = reglement.getFacture();

        if (facture == null) {
            return EtatPaiement.INCONNU;
        }

        if (facture.getReglements().stream().allMatch(Reglement::isPaye)) {
            return EtatPaiement.PAYEE;
        } else {
            return EtatPaiement.NON_PAYEE;
        }
    }



    public void payerFactureIndividuelle(Long idReglement) {
        Optional<Reglement> reglementOptional = reglementRepository.findById(idReglement);

        if (reglementOptional.isPresent()) {
            Reglement reglement = reglementOptional.get();
            payerFacture(reglement);
        } else {
            // Gérer le cas où le règlement avec l'ID spécifié n'est pas trouvé
            // Vous pouvez jeter une exception ou traiter de manière appropriée
            throw new RuntimeException("Règlement non trouvé avec l'ID : " + idReglement);
            // Ou utilisez un autre mécanisme pour traiter le cas où le règlement n'est pas trouvé
            // Par exemple, renvoyez un message d'erreur à l'utilisateur ou enregistrez un journal
        }
    }

    private void payerFacture(Reglement reglement) {
        // Récupérez la facture associée au règlement
        Facture facture = reglement.getFacture();

        // Vérifiez si la facture est déjà payée
        if (getEtatPaiement(reglement) == EtatPaiement.PAYEE) {
            // Logique si la facture est déjà payée
            // Vous pouvez jeter une exception, enregistrer des journaux, etc.
            throw new RuntimeException("La facture associée à ce règlement est déjà payée.");
        }

        // Implémentez votre logique de paiement ici
        // Par exemple, mettez à jour la facture, marquez le règlement comme payé, etc.

        // Exemple : Marquer le règlement comme payé
        reglement.setPaye(true);
        reglementRepository.save(reglement);

        if (facture.getReglements().stream().allMatch(Reglement::isPaye)) {
            facture.setEtatPaiement(EtatPaiement.PAYEE);
            factureRepository.save(facture);  // Assurez-vous d'utiliser l'instance de FactureRepository pour sauvegarder
        }
    }


    public void payerFacturesGroupées() {
        List<Reglement> reglements = reglementRepository.findAll();

        for (Reglement reglement : reglements) {
            payerFacture(reglement);
        }
    }
// Dans le service de gestion des règlements (ReglementService.java)

    public List<ReglementDTO> getReglementsAvecEtatPaiementParClient(Long clientId) {
        // Implémentez la logique pour récupérer les règlements associés à un client spécifique
        List<Reglement> reglements = reglementRepository.findByClientId(clientId);

        // Utilisez Java Streams pour mapper les entités Reglement vers des DTO
        return reglements.stream()
                .map(reglement -> {
                    ReglementDTO dto = new ReglementDTO();
                    dto.setIdRegelment(reglement.getIdRegelment());
                    dto.setDateReglement(reglement.getDateReglement());
                    dto.setMontantTotal(reglement.getMontantTotal());
                    dto.setType(reglement.getType());
                    dto.setModePaiement(reglement.getModePaiement());
                    dto.setFacture(reglement.getFacture());

                    // Ajoutez la logique pour déterminer l'état de paiement en fonction des règlements associés
                    dto.setEtatPaiement(getEtatPaiement(reglement));

                    return dto;
                })
                .collect(Collectors.toList());
    }


}
