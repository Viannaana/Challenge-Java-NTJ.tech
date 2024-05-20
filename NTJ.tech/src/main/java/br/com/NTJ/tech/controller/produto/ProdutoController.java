package br.com.NTJ.tech.controller.produto;

import br.com.NTJ.tech.dto.produto.CadastroProduto;
import br.com.NTJ.tech.dto.produto.DetalhesMovimentoProduto;
import br.com.NTJ.tech.dto.produto.DetalhesProduto;
import br.com.NTJ.tech.model.produto.Produto;
import br.com.NTJ.tech.repository.estoque.EstoqueRepository;
import br.com.NTJ.tech.repository.movimentoEstoque.MovimentoEstoqueRepository;
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

@RequestMapping("produtos")
@Controller
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private MovimentoEstoqueRepository movimentoEstoqueRepository;

    @GetMapping
    public ResponseEntity<List<DetalhesProduto>> listar(Pageable pageable){
        var lista = repository.findAll(pageable)
                .stream().map(DetalhesProduto::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhesProduto> buscar(@PathVariable("id") Long id){
        var produto = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesProduto(produto));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesProduto> cadastrar(@RequestBody CadastroProduto produtoPost,
                                                     UriComponentsBuilder uri){
        var produto = new Produto(produtoPost);
        repository.save(produto);
        var url = uri.path("/produtos/{id}").buildAndExpand(produto.getIdProduto()).toUri();
        return ResponseEntity.created(url).body(new DetalhesProduto(produto));
    }

    //Post da tabela movimento estoque
    @PostMapping("{id}/movimentoProduto")
    @Transactional
    public ResponseEntity<DetalhesMovimentoProduto> postMovimentoProduto(@PathVariable("id")Long id,
                                                                         @RequestBody @Valid CadastroProduto dto,
                                                                         UriComponentsBuilder uriBuilder){
        var movimentoEstoque = movimentoEstoqueRepository.getReferenceById(id);
        var produto = new Produto(dto, movimentoEstoque);
        repository.save(produto);
        var uri = uriBuilder.path("movimentoProduto/{id}").buildAndExpand(produto.getIdProduto()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesMovimentoProduto(produto));
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesProduto> atualizar(@PathVariable("id") Long id,
                                                     @RequestBody CadastroProduto produtoPut){
        var produto = repository.getReferenceById(id);
        produto.atualizarDados(produtoPut);
        return ResponseEntity.ok(new DetalhesProduto(produto));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

