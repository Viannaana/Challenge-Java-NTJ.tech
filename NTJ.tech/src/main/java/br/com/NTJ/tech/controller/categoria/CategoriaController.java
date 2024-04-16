package br.com.NTJ.tech.controller.categoria;

import br.com.NTJ.tech.dto.categoria.CadastroCategoria;
import br.com.NTJ.tech.dto.categoria.DetalhesCategoria;
import br.com.NTJ.tech.model.categoria.Categoria;
import br.com.NTJ.tech.repository.categoria.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequestMapping("categorias")
@Controller
public class CategoriaController {

    @Autowired
    private CategoriaRepository repository;

    @GetMapping
    public ResponseEntity<List<DetalhesCategoria>> listar(Pageable pageable){
        var lista = repository.findAll(pageable)
                .stream().map(DetalhesCategoria::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhesCategoria> buscar(@PathVariable("id") Long id){
        var categoria = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesCategoria(categoria));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesCategoria> cadastrar(@RequestBody CadastroCategoria categoriaPost,
                                                     UriComponentsBuilder uri){
        var categoria = new Categoria(categoriaPost);
        repository.save(categoria);
        var url = uri.path("/clientes/{id}").buildAndExpand(categoria.getCodigo()).toUri();
        return ResponseEntity.created(url).body(new DetalhesCategoria(categoria));
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesCategoria> atualizar(@PathVariable("id") Long id,
                                                     @RequestBody CadastroCategoria categoriaPut){
        var categoria = repository.getReferenceById(id);
        categoria.atualizarDados(categoriaPut);
        return ResponseEntity.ok(new DetalhesCategoria(categoria));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
