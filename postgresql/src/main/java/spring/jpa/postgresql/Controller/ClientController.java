package spring.jpa.postgresql.Controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import spring.jpa.postgresql.Entity.Client;
import spring.jpa.postgresql.Service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping
    public ResponseEntity<Client> salvar(@RequestBody Client client){
        Client client1 = clientService.salvar(client);
        return ResponseEntity.ok(client);
    }


    @GetMapping
    public List<Client> listaClient(){
        return clientService.listaClient();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> buscarClientPorId(@PathVariable("id") Long id){
        Client client = clientService.buscarPorId(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CLiente não encontrado"));
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Client>removerClient(@PathVariable("id") Long id){
        clientService.buscarPorId(id)
                .map(client -> {
                    clientService.removerPorId(client.getId());
                    return ResponseEntity.ok(client);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado."));
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> atualizarClient(@PathVariable("id") Long id, @RequestBody Client client) {
        clientService.buscarPorId(id)
                .map(clientBase -> {
                    modelMapper.map(client, clientBase);
                    clientService.salvar(clientBase);
                    return ResponseEntity.ok(client);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado."));
        return null;
    }
}
