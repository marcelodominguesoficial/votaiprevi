package br.com.votaaiprevi.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value = "C")
public class Candidato extends Usuario {

	@ManyToOne
	@JoinColumn(name = "cargo_id", referencedColumnName = "id", nullable = false)
	private Cargo cargo;

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

}
