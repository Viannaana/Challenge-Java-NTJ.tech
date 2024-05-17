package br.com.NTJ.tech.controller.pedido;


import br.com.NTJ.tech.dto.pedido.CadastroPedido;
import br.com.NTJ.tech.dto.pedido.DetalhesDeHistoricoPedido;
import br.com.NTJ.tech.dto.pedido.DetalhesPedido;
import br.com.NTJ.tech.dto.pedido.DetalhesPedidoProduto;
import br.com.NTJ.tech.model.pedido.Pedido;
import br.com.NTJ.tech.repository.historicoPedido.HistoricoPedidoRepository;
import br.com.NTJ.tech.repository.pedido.PedidoRepository;
import br.com.NTJ.tech.repository.produto.ProdutoRepository;
import jakarta.validation.Valid;
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

    @Autowired
    private HistoricoPedidoRepository historicoPedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

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
    //Post da tabela historico pedido
    @PostMapping("{id}/historicoDePedido")
    @Transactional
    public  ResponseEntity<DetalhesDeHistoricoPedido> postHistoricoDePedido(@PathVariable("id")Long id,
                                                                            @RequestBody @Valid CadastroPedido dto,
                                                                            UriComponentsBuilder uriBuilder){
        var historicoPedido = historicoPedidoRepository.getReferenceById(id);
        var pedido = new Pedido(dto, historicoPedido);
        repository.save(pedido);
        var uri = uriBuilder.path("historicoDePedido/{id}").buildAndExpand(pedido.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesDeHistoricoPedido(pedido));
    }

    //Post da tabela produto
    @PostMapping("{id}/produtos")
    @Transactional
    public  ResponseEntity<DetalhesPedidoProduto> postProduto(@PathVariable("id")Long id,
                                                              @RequestBody @Valid CadastroPedido dto,
                                                              UriComponentsBuilder uriBuilder){
        var produto = produtoRepository.getReferenceById(id);
        var pedido = new Pedido(dto, produto);
        repository.save(pedido);
        var uri = uriBuilder.path("produtos/{id}").buildAndExpand(pedido.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesPedidoProduto(pedido));
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
