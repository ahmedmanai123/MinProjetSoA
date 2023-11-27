package com.projetGestionComp.Dto;

import com.projetGestionComp.Models.Client;
import com.projetGestionComp.Models.EtatPaiement;
import com.projetGestionComp.Models.Reglement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FactureDTO {
    private Long idFacture;
    private Double montantTotal;
    private Date dateFacture;
    private Client client;
    private Reglement reglement;
    private EtatPaiement etatPaiement;


}
