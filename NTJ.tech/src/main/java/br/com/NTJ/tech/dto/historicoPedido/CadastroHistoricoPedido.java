package br.com.NTJ.tech.dto.historicoPedido;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CadastroHistoricoPedido(
        @NotNull
        LocalDate dtHistorico,
        @NotBlank
        @Size(max = 100)
        String produto,
        @NotNull
        LocalDate dtPedido) {
}
