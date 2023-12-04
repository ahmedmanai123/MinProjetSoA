package com.projetGestionComp.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetGestionComp.Models.Client;
import com.projetGestionComp.Models.EtatPaiement;
import com.projetGestionComp.Models.Facture;
import com.projetGestionComp.Models.modeReglement;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReglementDTO {
    private Long idRegelment;
    private Date dateReglement;
    private Double montantTotal;
    private String type;
    private modeReglement modePaiement;
    private boolean paye;
    public EtatPaiement etatPaiement;
    private Facture facture;
    private Client client;


}
