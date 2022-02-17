package br.com.famis.repository;


import br.com.famis.model.Pedido;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PedidoRepository extends CrudRepository<Pedido, UUID> {
}
