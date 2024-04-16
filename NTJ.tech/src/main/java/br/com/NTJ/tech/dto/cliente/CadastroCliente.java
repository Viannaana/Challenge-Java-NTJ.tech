package br.com.NTJ.tech.dto.cliente;
import java.time.LocalDate;

public record CadastroCliente(Long id, String nome, String email, String telefone,
                              LocalDate cadastro, LocalDate cancelamento, String status) {
}
