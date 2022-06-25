package br.com.votaaiprevi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.votaaiprevi.entity.Candidato;
import br.com.votaaiprevi.repository.CandidatoRepository;

@Service
public class CandidatoService {

	@Autowired
	public CandidatoRepository candidatoRepository;

	public void salvarCandidato(Candidato candidato) {
		this.candidatoRepository.save(candidato);
	}

	public List<Candidato> consultarTodosCandidatos() {
		return this.candidatoRepository.findAll();
	}

	public Candidato consultarCandidatoPorId(Long id) {
		Candidato candidato = this.candidatoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id da Eleição inválida : " + id));

		return candidato;
	}

	public void excluirCandidato(Long id) {
		this.candidatoRepository.deleteById(id);
	}

}
