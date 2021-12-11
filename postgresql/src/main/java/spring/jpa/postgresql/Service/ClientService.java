package spring.jpa.postgresql.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.jpa.postgresql.Entity.Client;
import spring.jpa.postgresql.Repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired //importa automaticamente os beans dependentes
    private ClientRepository clientRepository;
    //1 métodos
    public Client salvar(Client client) {
        return clientRepository.save(client);
    }
    //2 métodos
    public List<Client> listaClient(){
        return clientRepository.findAll(); // findAll retorna uma lista em string
    }
    //3 métodos
    public Optional<Client> buscarPorId(Long id){
        return clientRepository.findById(id);
    }
    //4 método
    public void removerPorId(Long id){clientRepository.deleteById(id);
    }
}
