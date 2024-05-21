package br.com.NTJ.tech.dto.MovimentoEstoque;

import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CadastroMovimentoEstoque(

        LocalDate data,
        Long quantidade) {
}
