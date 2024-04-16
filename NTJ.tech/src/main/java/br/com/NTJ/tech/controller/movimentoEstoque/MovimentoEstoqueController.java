package br.com.NTJ.tech.controller.movimentoEstoque;

import br.com.NTJ.tech.dto.MovimentoEstoque.CadastroMovimentoEstoque;
import br.com.NTJ.tech.dto.MovimentoEstoque.DetalhesMovimentoEstoque;
import br.com.NTJ.tech.model.movimentoEstoque.MovimentoEstoque;
import br.com.NTJ.tech.repository.movimentoEstoque.MovimentoEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequestMapping("movimentos")
@Controller
public class MovimentoEstoqueController {

    @Autowired
    private MovimentoEstoqueRepository repository;

    @GetMapping
    public ResponseEntity<List<DetalhesMovimentoEstoque>> listar(Pageable pageable){
        var lista = repository.findAll(pageable)
                .stream().map(DetalhesMovimentoEstoque::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhesMovimentoEstoque> buscar(@PathVariable("id") Long id){
        var movimentoEstoque = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesMovimentoEstoque(movimentoEstoque));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesMovimentoEstoque> cadastrar(@RequestBody CadastroMovimentoEstoque movimentoPost,
                                                     UriComponentsBuilder uri){
        var movimentoEstoque = new MovimentoEstoque(movimentoPost);
        repository.save(movimentoEstoque);
        var url = uri.path("/movimentos/{id}").buildAndExpand(movimentoEstoque.getCodigo()).toUri();
        return ResponseEntity.created(url).body(new DetalhesMovimentoEstoque(movimentoEstoque));
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesMovimentoEstoque> atualizar(@PathVariable("id") Long id,
                                                     @RequestBody CadastroMovimentoEstoque movimentoPut){
        var movimentoEstoque = repository.getReferenceById(id);
        movimentoEstoque.atualizarDados(movimentoPut);
        return ResponseEntity.ok(new DetalhesMovimentoEstoque(movimentoEstoque));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
