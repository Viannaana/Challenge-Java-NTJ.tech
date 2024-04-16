package br.com.NTJ.tech.controller.fornecedor;

import br.com.NTJ.tech.dto.fornecedor.CadastroFornecedor;
import br.com.NTJ.tech.dto.fornecedor.DetalhesFornecedor;
import br.com.NTJ.tech.model.fornecedor.Fornecedor;
import br.com.NTJ.tech.repository.fornecedor.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequestMapping("fornecedores")
@Controller
public class FornecedorController {

    @Autowired
    private FornecedorRepository repository;

    @GetMapping
    public ResponseEntity<List<DetalhesFornecedor>> listar(Pageable pageable){
        var lista = repository.findAll(pageable)
                .stream().map(DetalhesFornecedor::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhesFornecedor> buscar(@PathVariable("id") Long id){
        var fornecedor = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesFornecedor(fornecedor));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesFornecedor> cadastrar(@RequestBody CadastroFornecedor fornecedorPost,
                                                     UriComponentsBuilder uri){
        var fornecedor = new Fornecedor(fornecedorPost);
        repository.save(fornecedor);
        var url = uri.path("/fornecedores/{id}").buildAndExpand(fornecedor.getCodigo()).toUri();
        return ResponseEntity.created(url).body(new DetalhesFornecedor(fornecedor));
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesFornecedor> atualizar(@PathVariable("id") Long id,
                                                     @RequestBody CadastroFornecedor fornecedorPut){
        var fornecedor = repository.getReferenceById(id);
        fornecedor.atualizarDados(fornecedorPut);
        return ResponseEntity.ok(new DetalhesFornecedor(fornecedor));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
