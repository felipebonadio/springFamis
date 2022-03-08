package br.com.famis.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.famis.model.Colaborador;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

}
