package com.projetGestionComp.Service;

import com.projetGestionComp.Execption.ClientNotFoundException;
import com.projetGestionComp.Models.Client;
import com.projetGestionComp.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class ClientServiceImp {
    @Autowired
    private ClientRepository clientRepository;

    public Client getClient(long id) throws ClientNotFoundException {
        Client client= clientRepository.findById(id).orElseThrow(()->new ClientNotFoundException("client non existe!"));
        return client;
    }
}
