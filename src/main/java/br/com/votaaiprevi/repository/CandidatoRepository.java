package br.com.votaaiprevi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.votaaiprevi.entity.Candidato;

public interface CandidatoRepository extends JpaRepository<Candidato, Long>{

}
