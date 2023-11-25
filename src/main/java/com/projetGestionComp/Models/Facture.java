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

    private Double montantTotal;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Value("#{T(java.time.LocalDate).now()}")
    private Date dateFacture;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    private Reglement reglement;

    @Enumerated(EnumType.STRING)
    private EtatPaiement etatPaiement;


}
