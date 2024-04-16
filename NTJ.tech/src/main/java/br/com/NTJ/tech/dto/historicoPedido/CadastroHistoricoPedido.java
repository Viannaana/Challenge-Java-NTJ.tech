package br.com.NTJ.tech.dto.historicoPedido;

import java.time.LocalDate;

public record CadastroHistoricoPedido(Long codigo, LocalDate dtHistorico, String produto, LocalDate dtPedido) {
}
