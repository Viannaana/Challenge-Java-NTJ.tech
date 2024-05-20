package br.com.NTJ.tech.dto.pedido;

import br.com.NTJ.tech.dto.produto.DetalhesProduto;
import br.com.NTJ.tech.model.pedido.Pedido;

import java.time.LocalDate;

public record DetalhesPedidoProduto(Long Codigo, LocalDate dtPedido, LocalDate dtCancelamento,
                                    LocalDate dtEntrega, Integer vlPedido, Integer vlDesconto, String tpPedido,
                                    DetalhesProduto produto) {

    public DetalhesPedidoProduto(Pedido pedido){
        this(pedido.getCodigo(), pedido.getDtPedido(), pedido.getDtCancelamento(), pedido.getDtEntrega(),
                pedido.getVlPedido(), pedido.getVlDesconto(), pedido.getTpPedido(),
                new DetalhesProduto(pedido.getProduto()));
    }
}


