package com.projetGestionComp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Entity
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFacture;

    @OneToOne
    private Devis devis;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Value("#{T(java.time.LocalDate).now()}")
    private Date dateFacture;

    @OneToMany(mappedBy ="facture")
    @JsonIgnore
    private List<FactureDetail> factureDetails;

    private Double montantTotal;

    @Enumerated(EnumType.STRING)
    private EtatPaiement etatPaiement;

    @ManyToOne
    @JoinColumn(name = "reglement")
    private Reglement reglement;





}
