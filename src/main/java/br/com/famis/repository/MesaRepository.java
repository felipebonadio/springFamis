package br.com.famis.repository;

import br.com.famis.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
}
