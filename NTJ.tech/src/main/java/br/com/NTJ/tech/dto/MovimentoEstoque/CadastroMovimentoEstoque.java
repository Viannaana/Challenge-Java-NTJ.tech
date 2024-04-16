package br.com.NTJ.tech.dto.MovimentoEstoque;

import java.time.LocalDate;

public record CadastroMovimentoEstoque(Long codigo, Long sequencia, LocalDate data, Long quantidade) {
}
