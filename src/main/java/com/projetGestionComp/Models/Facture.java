package com.projetGestionComp.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFacture;
    private Double montantTotal;
    private Date dateFacture;
    @ManyToOne
    private Client client;


    @OneToMany(mappedBy = "facture")
    private List<Reglement> reglements;

    @Enumerated(EnumType.STRING)
    private EtatPaiement etatPaiement;


}
