package br.com.NTJ.tech.model.pedido;


import br.com.NTJ.tech.dto.pedido.CadastroPedido;
import br.com.NTJ.tech.model.cliente.Cliente;
import br.com.NTJ.tech.model.historicoPedido.HistoricoPedido;
import br.com.NTJ.tech.model.produto.Produto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "TB_PEDIDO")
@EntityListeners(AuditingEntityListener.class)
public class Pedido {

    @Id
    @GeneratedValue
    @Column(name = "ID_PEDIDO")
    private Long codigo;

    @Column(name = "DT_PEDIDO", nullable = false)
    private LocalDate dtPedido;

    @Column(name = "DT_CANCELAMENTO", nullable = false)
    private LocalDate dtCancelamento;

    @Column(name = "DT_ENTREGA", nullable = false)
    private LocalDate dtEntrega;

    @Column(name = "VL_PEDIDO", length = 10, nullable = false)
    private Integer vlPedido;

    @Column(name = "VL_DESCONTO", length = 10, nullable = false)
    private Integer vlDesconto;

    @Column(name = "TP_PEDIDO", length = 100, nullable = false)
    private String tpPedido;

    @ManyToOne
    @JoinColumn(name = "ID_HISTORICO")
    private HistoricoPedido historicoPedido;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUTO")
    private Produto produto;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<Cliente> clientes;

    public Pedido(CadastroPedido pedido){
        dtPedido = pedido.dtPedido();
        dtCancelamento = pedido.dtCancelamento();
        dtEntrega = pedido.dtEntrega();
        vlPedido = pedido.vlPedido();
        vlDesconto = pedido.vlDesconto();
        tpPedido = pedido.tpPedido();
    }

    public Pedido(CadastroPedido pedido, HistoricoPedido historicoPedido){
        dtPedido = pedido.dtPedido();
        dtCancelamento = pedido.dtCancelamento();
        dtEntrega = pedido.dtEntrega();
        vlPedido = pedido.vlPedido();
        vlDesconto = pedido.vlDesconto();
        tpPedido = pedido.tpPedido();
        this.historicoPedido = historicoPedido;
    }

    public Pedido(CadastroPedido pedido, Produto produto){
        dtPedido = pedido.dtPedido();
        dtCancelamento = pedido.dtCancelamento();
        dtEntrega = pedido.dtEntrega();
        vlPedido = pedido.vlPedido();
        vlDesconto = pedido.vlDesconto();
        tpPedido = pedido.tpPedido();
        this.produto = produto;
    }

    public void atualizarDados(CadastroPedido atualizacao){
        if(atualizacao.dtPedido() != null)
            dtPedido = atualizacao.dtPedido();
        if(atualizacao.dtCancelamento() != null)
            dtCancelamento = atualizacao.dtCancelamento();
        if(atualizacao.dtEntrega() != null)
            dtEntrega = atualizacao.dtEntrega();
        if(atualizacao.vlPedido() != null)
            vlPedido = atualizacao.vlPedido();
        if(atualizacao.vlDesconto() != null)
            vlDesconto = atualizacao.vlDesconto();
        if(atualizacao.tpPedido() != null)
            tpPedido = atualizacao.tpPedido();
    }
}
