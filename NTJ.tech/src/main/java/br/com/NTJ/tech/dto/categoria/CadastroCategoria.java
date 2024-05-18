package br.com.NTJ.tech.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CadastroCategoria(
        @NotBlank
        @Size(max = 100)
        String nome,

        @NotBlank
        @Size(max = 200)
        String descricao) {
}
