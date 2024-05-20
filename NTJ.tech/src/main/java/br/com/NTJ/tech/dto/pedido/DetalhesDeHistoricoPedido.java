package br.com.NTJ.tech.dto.pedido;

import br.com.NTJ.tech.dto.historicoPedido.DetalhesHistoricoPedido;
import br.com.NTJ.tech.model.pedido.Pedido;

import java.time.LocalDate;

public record DetalhesDeHistoricoPedido(Long Codigo, LocalDate dtPedido, LocalDate dtCancelamento,
                                        LocalDate dtEntrega, Integer vlPedido, Integer vlDesconto, String tpPedido,
                                        DetalhesHistoricoPedido historicoPedido) {

    public DetalhesDeHistoricoPedido(Pedido pedido){
        this(pedido.getCodigo(), pedido.getDtPedido(), pedido.getDtCancelamento(), pedido.getDtEntrega(),
                pedido.getVlPedido(), pedido.getVlDesconto(), pedido.getTpPedido(), new DetalhesHistoricoPedido(pedido.getHistoricoPedido()));
    }
}
