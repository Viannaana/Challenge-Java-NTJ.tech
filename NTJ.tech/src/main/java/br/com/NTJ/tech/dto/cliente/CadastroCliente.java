package br.com.NTJ.tech.dto.cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CadastroCliente(
        @NotBlank
        @Size(max = 100)
        String nome,
        @NotBlank
        @Size(max = 100)
        String email,
        @NotBlank
        @Size(max = 15)
        String telefone,
        LocalDate cadastro,
        LocalDate cancelamento,
        @NotBlank
        @Size(max = 100)
        String status) {
}
