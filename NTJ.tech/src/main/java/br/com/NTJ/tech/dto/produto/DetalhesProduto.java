package br.com.NTJ.tech.dto.produto;

import br.com.NTJ.tech.model.produto.*;
import java.time.LocalDate;

public record DetalhesProduto(Long idProduto, String nmProduto, String barra,
                              TipoStatus status, LocalDate dataCadastro, LocalDate dataCancelamento,
                              TipoMarca marca, TipoCor cor, TipoTecido tecido, TipoTamanho tamanho, String nome,
                              String descricao) {

    public DetalhesProduto (Produto produto){
        this(produto.getIdProduto(), produto.getNmProduto(), produto.getBarra(),
                produto.getStatus(), produto.getDataCadastro(), produto.getDataCancelamento(), produto.getMarca(),
                produto.getCor(), produto.getTecido(), produto.getTamanho(), produto.getCategoria().getNome(),
                produto.getCategoria().getDescricao());
    }
}
