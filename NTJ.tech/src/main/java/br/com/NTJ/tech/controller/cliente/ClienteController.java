package br.com.NTJ.tech.controller.cliente;

import br.com.NTJ.tech.dto.cliente.CadastroCliente;
import br.com.NTJ.tech.dto.cliente.DetalhesCliente;
import br.com.NTJ.tech.dto.cliente.DetalhesPedidoCliente;
import br.com.NTJ.tech.model.cliente.Cliente;
import br.com.NTJ.tech.repository.cliente.ClienteRepository;
import br.com.NTJ.tech.repository.pedido.PedidoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequestMapping("clientes")
@Controller
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public ResponseEntity<List<DetalhesCliente>> listar(Pageable pageable){
        var lista = repository.findAll(pageable)
                .stream().map(DetalhesCliente::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhesCliente> buscar(@PathVariable("id") Long id){
        var cliente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesCliente(cliente));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesCliente> cadastrar(@RequestBody CadastroCliente clientePost,
                                                     UriComponentsBuilder uri){
        var cliente = new Cliente(clientePost);
        repository.save(cliente);
        var url = uri.path("/clientes/{id}").buildAndExpand(cliente.getCodigo()).toUri();
        return ResponseEntity.created(url).body(new DetalhesCliente(cliente));
    }

    //Post da tabela Cliente para pedido
   @PostMapping("{id}/pedidoCliente")
   @Transactional
   public ResponseEntity<DetalhesPedidoCliente> postPedidoCliente(@PathVariable("id") Long id,
                                                                  @RequestBody @Valid CadastroCliente dto,
                                                                  UriComponentsBuilder uriBuilder){
        var pedido = pedidoRepository.getReferenceById(id);
        var cliente = new Cliente(dto, pedido);
        repository.save(cliente);
        var uri = uriBuilder.path("id/pedidoCliente").buildAndExpand(cliente.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesPedidoCliente(cliente));
   }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesCliente> atualizar(@PathVariable("id") Long id,
                                                     @RequestBody CadastroCliente clientePut){
        var cliente = repository.getReferenceById(id);
        cliente.atualizarDados(clientePut);
        return ResponseEntity.ok(new DetalhesCliente(cliente));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
