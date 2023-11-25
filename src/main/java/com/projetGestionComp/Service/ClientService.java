package com.projetGestionComp.Service;

import com.projetGestionComp.Execption.ClientNotFoundException;
import com.projetGestionComp.Models.Client;

public interface ClientService {

    Client getClient(long id) throws ClientNotFoundException;
}
