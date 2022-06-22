package br.com.votaaiprevi.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.votaaiprevi.util.LocalDateAttributeConverter;

@Entity
public class Eleicao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Size(max = 50, message = "{Size.Eleicao.nomeEleicao}")
	@NotEmpty(message = "{Empty.Eleicao.nomeEleicao}")
	private String nomeEleicao;

	@Column
	@NotNull(message = "{Empty.Eleicao.dataInicio}")
	@Convert(converter = LocalDateAttributeConverter.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataInicio;

	@Column
	@NotNull(message = "{Empty.Eleicao.dataFim}")
	@Convert(converter = LocalDateAttributeConverter.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataFim;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeEleicao() {
		return nomeEleicao;
	}

	public void setNomeEleicao(String nomeEleicao) {
		this.nomeEleicao = nomeEleicao;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

}
