package br.com.votaaiprevi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.votaaiprevi.entity.Eleicao;
import br.com.votaaiprevi.repository.EleicaoRepository;

@Service
public class EleicaoService {

	@Autowired
	private EleicaoRepository eleicaoRepository;

	public void salvarEleicao(Eleicao eleicao) {
		this.eleicaoRepository.save(eleicao);
	}

	public List<Eleicao> consultarTodasEleicoes() {
		return eleicaoRepository.findAll();
	}

	public Eleicao consultarEleicaoPorId(long id) {
		Eleicao eleicao = this.eleicaoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id da Eleição inválida:" + id));
		return eleicao;
	}

	public void excluirEleicao(long id) {
		this.eleicaoRepository.deleteById(id);
	}

}
