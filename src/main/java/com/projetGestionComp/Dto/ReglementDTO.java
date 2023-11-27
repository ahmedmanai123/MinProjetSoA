package com.projetGestionComp.Dto;

import com.projetGestionComp.Models.Client;
import com.projetGestionComp.Models.EtatPaiement;
import com.projetGestionComp.Models.Facture;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReglementDTO {
    private Long idRegelment;

    private Date dateReglement;
    private Double montantTotal;
    private String type;
    private String modePaiement;
    // Assurez-vous d'utiliser le nom de la colonne correct
    private Facture facture;

    private EtatPaiement etatPaiement;
    private Client client;

}
