package com.projetGestionComp.Service;

import com.projetGestionComp.Execption.ClientNotFoundException;
import com.projetGestionComp.Models.Client;
import com.projetGestionComp.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.List;

@Service
public class ClientServiceImp implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public Client getClient(long id) throws ClientNotFoundException {
        Client client= clientRepository.findById(id).orElseThrow(()->new ClientNotFoundException("client non existe!"));
        return client;
    }



    public Long getClientIdByEmail(String email, String motdepass) {
        List<Client> clients = clientRepository.findAll();

        for (Client c : clients) {
            if (c.getEmail().equals(email) && c.getMotdepass().equals(motdepass)) {
                return c.getId();
            }
        }

        return null; // or -1 or any default value indicating user not found
    }




    @Override
    public List<Client> ClientNonPayee(){
        return clientRepository.ClientNonPayee() ;

    }

}
