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
        @Size(max = 100)
        String barra,

        TipoStatus status,
        @NotNull
        LocalDate dataCadastro,
        @NotNull
        LocalDate dataCancelamento,
        TipoMarca marca,
        TipoCor cor,
        TipoTecido tecido,
        TipoTamanho tamanho
) {
}
