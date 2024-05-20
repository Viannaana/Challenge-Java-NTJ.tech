package br.com.NTJ.tech.dto.produto;

import br.com.NTJ.tech.model.produto.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CadastroProduto(
        @NotBlank
        @Size(max = 100)
        String nmProduto,
        @NotNull
        @Size(max = 100)
        String barra,
        @NotNull
        @Size(max = 2)
        TipoStatus status,
        @NotNull
        LocalDate dataCadastro,
        @NotNull
        LocalDate dataCancelamento,
        TipoMarca marca,
        TipoCor cor,
        TipoTecido tecido,
        TipoTamanho tamanho,
        @NotNull
        @Size(max = 100)
        String nome,
        @NotNull
        @Size(max = 200)
        String descricao
) {
}
