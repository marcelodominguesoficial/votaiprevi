package br.com.votaaiprevi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.votaaiprevi.entity.Cargo;
import br.com.votaaiprevi.service.CargoService;

@Controller
public class CargoController {

	@Autowired
	private CargoService cargoService;
	
	@GetMapping("/cargo")
	public String iniciarCargo(Model model) {
		if (!this.cargoService.consultarTodosCargos().isEmpty()){
			model.addAttribute("listaCargos", this.cargoService.consultarTodosCargos());
		}
		return "cargo";
	}
	
	@GetMapping("/cadastrarCargo")
	public String cadastrarCargo(Model model) {		
		model.addAttribute("cargo", new Cargo());
		
		return "cadastrarCargo";
	}
	
	@GetMapping("/editarCargo/{id}")
	public String editarCargo(@PathVariable("id") long id, Model model) {
		Cargo cargo = this.cargoService.consultarCargoPorId(id);
		model.addAttribute("cargo", cargo);
		return "editarCargo";
	}
	
	
	@GetMapping("/excluirCargo/{id}")
	public String excluirCargo(@PathVariable("id") long id, Model model) {
		this.cargoService.excluirCargo(id);
		if (!this.cargoService.consultarTodosCargos().isEmpty()) {
			model.addAttribute("listaCargos", this.cargoService.consultarTodosCargos());
		}
		return "redirect:/cargo";
	}
	
	@PostMapping("/salvarCargo")
	public String salvarCargo(@Valid Cargo cargo, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			return "cadastrarCargo";
		}
		
		this.cargoService.salvarCargo(cargo);
		
		if (!this.cargoService.consultarTodosCargos().isEmpty()) {
			model.addAttribute("listaCargos", this.cargoService.consultarTodosCargos());
		}
		
		return "redirect:/cargo";
	}
	
}
