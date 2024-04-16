package br.com.NTJ.tech.controller.pedido;

import br.com.NTJ.tech.dto.cliente.CadastroCliente;
import br.com.NTJ.tech.dto.cliente.DetalhesCliente;
import br.com.NTJ.tech.dto.pedido.CadastroPedido;
import br.com.NTJ.tech.dto.pedido.DetalhesPedido;
import br.com.NTJ.tech.model.cliente.Cliente;
import br.com.NTJ.tech.model.pedido.Pedido;
import br.com.NTJ.tech.repository.pedido.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequestMapping("pedidos")
@Controller
public class PedidoController {

    @Autowired
    private PedidoRepository repository;

    @GetMapping
    public ResponseEntity<List<DetalhesPedido>> listar(Pageable pageable){
        var lista = repository.findAll(pageable)
                .stream().map(DetalhesPedido::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhesPedido> buscar(@PathVariable("id") Long id){
        var pedido = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesPedido(pedido));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesPedido> cadastrar(@RequestBody CadastroPedido pedidoPost,
                                                     UriComponentsBuilder uri){
        var pedido = new Pedido(pedidoPost);
        repository.save(pedido);
        var url = uri.path("/pedidos/{id}").buildAndExpand(pedido.getCodigo()).toUri();
        return ResponseEntity.created(url).body(new DetalhesPedido(pedido));
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesPedido> atualizar(@PathVariable("id") Long id,
                                                     @RequestBody CadastroPedido pedidoPut){
        var pedido = repository.getReferenceById(id);
        pedido.atualizarDados(pedidoPut);
        return ResponseEntity.ok(new DetalhesPedido(pedido));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
