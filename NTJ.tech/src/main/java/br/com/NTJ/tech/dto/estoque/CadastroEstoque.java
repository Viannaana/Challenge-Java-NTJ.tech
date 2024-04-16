package br.com.NTJ.tech.dto.estoque;

import java.time.LocalDate;

public record CadastroEstoque(Long codigo, LocalDate dtEstoque, Long qtProduto) {
}
