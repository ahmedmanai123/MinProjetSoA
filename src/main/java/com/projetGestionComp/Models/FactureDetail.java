package com.projetGestionComp.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.diagnostics.FailureAnalysis;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FactureDetail {

    @Id
    private long detailId;

    @ManyToOne
    @JoinColumn(name = "produit")
    private Produit produitId;

    @ManyToOne
    @JoinColumn(name = "facture")
    private Facture facture;

    @Column(nullable = false)
    private int produit_qte;

}
