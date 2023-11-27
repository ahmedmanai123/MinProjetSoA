package com.projetGestionComp.Controller;


import com.projetGestionComp.Models.Devis;
import com.projetGestionComp.Service.DevisServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@RequestMapping("/gestionDevise")
public class DeviseController {
    @Autowired
    private DevisServiceImp devisServiceImp;

    @PostMapping("/addDevise")
    @Procedure(MediaType.APPLICATION_JSON_VALUE)
    public Devis addDevise(@RequestBody Devis d){
        devisServiceImp.saveDevis(d);
        return d;
    }
}
