package br.com.NTJ.tech.dto.pedido;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CadastroPedido(
        @NotNull
        LocalDate dtPedido,
        @NotNull
        LocalDate dtCancelamento,
        @NotNull
        LocalDate dtEntrega,
        Integer vlPedido,
        Integer vlDesconto,
        @NotBlank
        String tpPedido) {
}
