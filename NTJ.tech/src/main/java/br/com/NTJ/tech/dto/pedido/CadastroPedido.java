package br.com.NTJ.tech.dto.pedido;

import java.time.LocalDate;

public record CadastroPedido(Long Codigo, LocalDate dtPedido, LocalDate dtCancelamento,
                             LocalDate dtEntrega, Integer vlPedido, Integer vlDesconto, String tpPedido) {
}
