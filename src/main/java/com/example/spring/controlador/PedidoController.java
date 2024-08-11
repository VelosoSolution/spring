package com.example.spring.controlador;
import com.example.spring.model.Pedido;
import com.example.spring.servico.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public Pedido criarPedido(@RequestBody Pedido pedido) {
        return pedidoService.criarPedido(pedido);
    }

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    @GetMapping("/cliente/{codigoCliente}")
    public List<Pedido> buscarPorCodigoCliente(@PathVariable int codigoCliente) {
        return pedidoService.buscarPorCodigoCliente(codigoCliente);
    }

    @GetMapping("/data/{dataCadastro}")
    public List<Pedido> buscarPorDataCadastro(@PathVariable String dataCadastro) {
        return pedidoService.buscarPorDataCadastro(LocalDate.parse(dataCadastro));
    }
}

