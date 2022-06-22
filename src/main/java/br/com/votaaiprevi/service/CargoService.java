package br.com.votaaiprevi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.votaaiprevi.entity.Cargo;
import br.com.votaaiprevi.repository.CargoRepository;

@Service
public class CargoService {

	@Autowired
	private CargoRepository cargoRepository;

	public void salvarCargo(Cargo cargo) {
		this.cargoRepository.save(cargo);
	}

	public List<Cargo> consultarTodosCargos() {
		return this.cargoRepository.findAll();
	}

	public Cargo consultarCargoPorId(long id) {
		Cargo cargo = this.cargoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id do cargo inválido"));
		return cargo;
	}

	public void excluirCargo(long id) {
		this.cargoRepository.deleteById(id);
	}

}
