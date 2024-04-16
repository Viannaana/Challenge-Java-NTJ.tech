package br.com.NTJ.tech.model.movimentoEstoque;

import br.com.NTJ.tech.dto.MovimentoEstoque.CadastroMovimentoEstoque;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "TB_MOVIMENTO")
public class MovimentoEstoque {

    @Id
    @GeneratedValue
    @Column(name = "ID_MOVIMENTO")
    private Long codigo;

    @Column(name = "SEQ_MOVIMENTO", length = 10, nullable = true)
    private Long sequencia;

    @Column(name = "DT_MOVIMENTO", nullable = false)
    private LocalDate data;

    @Column(name = "QT_MOVIMENTO", length = 10, nullable = true)
    private Long quantidade;

    public MovimentoEstoque(CadastroMovimentoEstoque movimentoEstoque){
        sequencia = movimentoEstoque.sequencia();
        data = movimentoEstoque.data();
        quantidade = movimentoEstoque.quantidade();
    }

    public void atualizarDados(CadastroMovimentoEstoque atualizacao){
        if(atualizacao.sequencia() != null)
            sequencia = atualizacao.sequencia();
        if(atualizacao.data() != null)
            data = atualizacao.data();
        if(atualizacao.quantidade() != null)
            quantidade = atualizacao.quantidade();
    }
}
