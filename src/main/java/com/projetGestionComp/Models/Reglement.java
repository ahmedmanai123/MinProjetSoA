package com.projetGestionComp.Models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reglement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegelment;

    private Date dateReglement;
    private Double montantTotal;
    private String type;
    private String modePaiement;
    private boolean paye;
    @Enumerated(EnumType.STRING)
    private EtatPaiement etatPaiement;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idFacture")
    @ToString.Exclude
    private Facture facture;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;


}
