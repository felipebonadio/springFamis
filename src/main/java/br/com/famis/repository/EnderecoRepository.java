package br.com.famis.repository;

import br.com.famis.model.Endereco;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface EnderecoRepository extends CrudRepository<Endereco, UUID> {

}
