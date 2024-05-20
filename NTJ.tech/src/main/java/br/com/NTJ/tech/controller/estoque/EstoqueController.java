package br.com.NTJ.tech.controller.estoque;

import br.com.NTJ.tech.dto.estoque.CadastroEstoque;
import br.com.NTJ.tech.dto.estoque.DetalhesEstoque;
import br.com.NTJ.tech.model.estoque.Estoque;
import br.com.NTJ.tech.repository.estoque.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequestMapping("estoques")
@Controller
public class EstoqueController {

    @Autowired
    private EstoqueRepository repository;

    @GetMapping
    public ResponseEntity<List<DetalhesEstoque>> listar(Pageable pageable){
        var lista = repository.findAll(pageable)
                .stream().map(DetalhesEstoque::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhesEstoque> buscar(@PathVariable("id") Long id){
        var estoque = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesEstoque(estoque));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesEstoque> cadastrar(@RequestBody CadastroEstoque estoquePost,
                                                     UriComponentsBuilder uri){
        var estoque = new Estoque(estoquePost);
        repository.save(estoque);
        var url = uri.path("/estoques/{id}").buildAndExpand(estoque.getCodigo()).toUri();
        return ResponseEntity.created(url).body(new DetalhesEstoque(estoque));
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesEstoque> atualizar(@PathVariable("id") Long id,
                                                     @RequestBody CadastroEstoque estoquePut){
        var estoque = repository.getReferenceById(id);
        estoque.atualizarDados(estoquePut);
        return ResponseEntity.ok(new DetalhesEstoque(estoque));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
