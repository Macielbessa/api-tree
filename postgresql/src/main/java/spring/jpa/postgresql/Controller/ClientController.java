package spring.jpa.postgresql.Controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)// a cada finalização com sucesso precisamos retornar um status // status 201
    public Client salvar(@RequestBody Client client){
        return clientService.salvar(client);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Client> listaClient(){
        return clientService.listaClient();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Client buscarClientPorId(@PathVariable("id") Long id){
        return clientService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado."));
        // Se um valor estiver presente, retorna o valor, caso contrário, lança a exceção informada no parâmetro.
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerClient(@PathVariable("id") Long id){
        clientService.buscarPorId(id)
                .map(client -> {
                    clientService.removerPorId(client.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado."));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarClient(@PathVariable("id") Long id, @RequestBody Client client) {
        clientService.buscarPorId(id)
                .map(clientBase -> {
                    modelMapper.map(client, clientBase);
                    clientService.salvar(clientBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado."));
    }
}
