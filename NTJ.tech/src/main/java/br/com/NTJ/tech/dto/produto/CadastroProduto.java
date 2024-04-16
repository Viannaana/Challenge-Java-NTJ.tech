package br.com.NTJ.tech.dto.produto;

import br.com.NTJ.tech.model.produto.*;

import java.time.LocalDate;

public record CadastroProduto(Long idProduto, String nmProduto, String barra,
                              TipoStatus status, LocalDate dataCadastro, LocalDate dataCancelamento,
                              TipoMarca marca, TipoCor cor, TipoTecido tecido, TipoTamanho tamanho) {
}
