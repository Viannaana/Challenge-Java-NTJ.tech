package br.com.NTJ.tech.controller.historicoPedido;

import br.com.NTJ.tech.dto.historicoPedido.CadastroHistoricoPedido;
import br.com.NTJ.tech.dto.historicoPedido.DetalhesHistoricoPedido;
import br.com.NTJ.tech.model.historicoPedido.HistoricoPedido;
import br.com.NTJ.tech.repository.historicoPedido.HistoricoPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequestMapping("historicos")
@Controller
public class HistoricoPedidoController {

    @Autowired
    private HistoricoPedidoRepository repository;

    @GetMapping
    public ResponseEntity<List<DetalhesHistoricoPedido>> listar(Pageable pageable){
        var lista = repository.findAll(pageable)
                .stream().map(DetalhesHistoricoPedido::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhesHistoricoPedido> buscar(@PathVariable("id") Long id){
        var historicoPedido = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesHistoricoPedido(historicoPedido));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesHistoricoPedido> cadastrar(@RequestBody CadastroHistoricoPedido historicoPost,
                                                     UriComponentsBuilder uri){
        var historicoPedido = new HistoricoPedido(historicoPost);
        repository.save(historicoPedido);
        var url = uri.path("/historicos/{id}").buildAndExpand(historicoPedido.getCodigo()).toUri();
        return ResponseEntity.created(url).body(new DetalhesHistoricoPedido(historicoPedido));
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesHistoricoPedido> atualizar(@PathVariable("id") Long id,
                                                     @RequestBody CadastroHistoricoPedido historicoPut){
        var historicoPedido = repository.getReferenceById(id);
        historicoPedido.atualizarDados(historicoPut);
        return ResponseEntity.ok(new DetalhesHistoricoPedido(historicoPedido));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
