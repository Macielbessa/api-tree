package spring.jpa.postgresql.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.jpa.postgresql.Entity.Client;
import spring.jpa.postgresql.Repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public Client salvar(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> listaClient(){
        return clientRepository.findAll(); // findAll retorna uma lista em string
    }

    public Optional<Client> buscarPorId(Long id){
        return clientRepository.findById(id);
    }

    public void removerPorId(Long id){clientRepository.deleteById(id);
    }
}
