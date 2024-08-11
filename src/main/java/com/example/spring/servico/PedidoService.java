package com.example.spring.servico;
import com.example.spring.model.Pedido;
import com.example.spring.repositorio.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido criarPedido(Pedido pedido) {
        if (pedidoRepository.findByNumeroControle(pedido.getNumeroControle()).isPresent()) {
            throw new IllegalArgumentException("Número de controle já cadastrado.");
        }

        if (pedido.getDataCadastro() == null) {
            pedido.setDataCadastro(LocalDate.now());	
        }

        if (pedido.getQuantidade() <= 0) {
            pedido.setQuantidade(1);
        }

        BigDecimal valorTotal = pedido.getValorUnitario().multiply(BigDecimal.valueOf(pedido.getQuantidade()));
        if (pedido.getQuantidade() > 5 && pedido.getQuantidade() < 10) {
            valorTotal = valorTotal.multiply(BigDecimal.valueOf(0.95)); 
        } else if (pedido.getQuantidade() >= 10) {
            valorTotal = valorTotal.multiply(BigDecimal.valueOf(0.90)); 
        }
        pedido.setValorTotal(valorTotal);
        
        if (pedido.getQuantidade() > 10) {
            throw new IllegalArgumentException("Quantidade invalida  maior que o estabelecido");
        } else  return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public List<Pedido> buscarPorCodigoCliente(int codigoCliente) {
        return pedidoRepository.findByCodigoCliente(codigoCliente);
    }

    public List<Pedido> buscarPorDataCadastro(LocalDate dataCadastro) {
        return pedidoRepository.findByDataCadastro(dataCadastro);
    }
}
