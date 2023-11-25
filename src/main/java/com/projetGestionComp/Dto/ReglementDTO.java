package com.projetGestionComp.Dto;

import com.projetGestionComp.Models.Client;
import com.projetGestionComp.Models.EtatPaiement;
import com.projetGestionComp.Models.Facture;
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
    private boolean paye;
    private EtatPaiement etatPaiement;
    private Facture facture;
    private Client client;

}
