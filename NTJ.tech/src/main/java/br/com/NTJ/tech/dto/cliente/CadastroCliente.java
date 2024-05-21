package br.com.NTJ.tech.dto.cliente;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CadastroCliente(

        @Size(max = 100)
        String nome,
        @Size(max = 100)
        String email,
        @Size(max = 15)
        String telefone,
        LocalDate cadastro,
        LocalDate cancelamento,
        @Size(max = 100)
        String status) {
}
