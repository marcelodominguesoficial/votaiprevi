package br.com.votaaiprevi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.votaaiprevi.entity.Candidato;
import br.com.votaaiprevi.entity.Cargo;
import br.com.votaaiprevi.service.CandidatoService;
import br.com.votaaiprevi.service.CargoService;

@Controller
public class CandidatoController {

	@Autowired
	private CandidatoService candidatoService;

	@Autowired
	private CargoService cargoService;

	@GetMapping("/candidato")
	public String iniciarCandidato(Model model) {
		List<Candidato> listaCandidatos = this.candidatoService.consultarTodosCandidatos();
		if (!listaCandidatos.isEmpty()) {
			model.addAttribute("listaCandidatos", listaCandidatos);
		}

		return "candidato";
	}

	@GetMapping("/cadastrarCandidato")
	public String cadastrarCandidato(Model model) {
		model.addAttribute("candidato", new Candidato());

		List<Cargo> listaCargos = this.cargoService.consultarTodosCargos();

		if (!listaCargos.isEmpty()) {
			model.addAttribute("listaCargos", listaCargos);
		}

		return "cadastrarCandidato";
	}

	@GetMapping("/editarCandidato/{id}")
	public String editarCandidato(@PathVariable("id") long id, Model model) {

		Candidato candidato = this.candidatoService.consultarCandidatoPorId(id);
		model.addAttribute("candidato", candidato);

		List<Cargo> listaCargos = this.cargoService.consultarTodosCargos();

		if (!listaCargos.isEmpty()) {
			model.addAttribute("listaCargos", listaCargos);
		}

		return "editarCandidato";
	}

	@GetMapping("/excluirCandidato/{id}")
	public String excluirCandidato(@PathVariable("id") long id, Model model) {
		this.candidatoService.excluirCandidato(id);
		List<Candidato> listaCandidatos = this.candidatoService.consultarTodosCandidatos();
		if (!listaCandidatos.isEmpty()) {
			model.addAttribute("listaCandidatos", listaCandidatos);
		}
		return "candidato";
	}

	@PostMapping("/salvarCandidato")
	public String salvarCandidato(@RequestParam("acao") String acao, @Valid Candidato candidato, BindingResult result,
			Model model) {

		if (candidato.getCargo().getId() == 0) {
			result.addError(new ObjectError("cargo", "Selecione um cargo"));
		}
		
		
		if (result.hasErrors()) {

			List<Cargo> listaCargos = this.cargoService.consultarTodosCargos();

			if (!listaCargos.isEmpty()) {
				model.addAttribute("listaCargos", listaCargos);
			}
		
			if ("inclusao".equals(acao)) {
				return "cadastrarCandidato";
			} else {
				return "editarCandidato";
			}
		}

		Cargo cargo = this.cargoService.consultarCargoPorId(candidato.getCargo().getId());
		candidato.setCargo(cargo);
		this.candidatoService.salvarCandidato(candidato);
		List<Candidato> listaCandidatos = this.candidatoService.consultarTodosCandidatos();

		if (!listaCandidatos.isEmpty()) {
			model.addAttribute("listaCandidatos", listaCandidatos);
		}

		return "candidato";
	}
}
