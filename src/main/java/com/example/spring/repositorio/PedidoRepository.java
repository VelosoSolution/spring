package com.example.spring.repositorio;

import com.example.spring.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Optional<Pedido> findByNumeroControle(String numeroControle);
    List<Pedido> findByCodigoCliente(Integer codigoCliente);
    List<Pedido> findByDataCadastro(LocalDate dataCadastro);
}
