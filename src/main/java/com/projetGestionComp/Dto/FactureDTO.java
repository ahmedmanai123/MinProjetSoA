package com.projetGestionComp.Dto;

import com.projetGestionComp.Models.Client;
import com.projetGestionComp.Models.EtatPaiement;
import com.projetGestionComp.Models.Reglement;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

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
    private List<Reglement> reglements;
    private EtatPaiement etatPaiement;


}
