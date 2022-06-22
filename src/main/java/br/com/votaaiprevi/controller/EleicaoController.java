package br.com.votaaiprevi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.votaaiprevi.entity.Eleicao;
import br.com.votaaiprevi.service.EleicaoService;

@Controller
public class EleicaoController {

	@Autowired
	private EleicaoService eleicaoService;

	@GetMapping("/eleicao")
	public String iniciarEleicao(Model model) {
		if (!this.eleicaoService.consultarTodasEleicoes().isEmpty()) {
			model.addAttribute("listaEleicoes", this.eleicaoService.consultarTodasEleicoes());
		}

		return "eleicao";
	}

	@GetMapping("/cadastrarEleicao")
	public String cadastrarEleicao(Model model) {
		model.addAttribute("eleicao", new Eleicao());
		return "cadastrarEleicao";
	}

	@GetMapping("/editarEleicao/{id}")
	public String editarEleicao(@PathVariable("id") long id, Model model) {
		Eleicao eleicao = this.eleicaoService.consultarEleicaoPorId(id);

		model.addAttribute("eleicao", eleicao);
		return "editarEleicao";
	}

	
	@GetMapping("/excluirEleicao/{id}")
	public String excluirEleicao(@PathVariable("id") long id, Model model) {
		this.eleicaoService.excluirEleicao(id);
		if (!this.eleicaoService.consultarTodasEleicoes().isEmpty()) {
			model.addAttribute("listaEleicoes", this.eleicaoService.consultarTodasEleicoes());
		}
		return "redirect:/eleicao";
	}
	
	
	@PostMapping("/salvarEleicao")
	public String salvarEleicao(@Valid Eleicao eleicao, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
		    return "cadastrarEleicao";
		}
		
		this.eleicaoService.salvarEleicao(eleicao);
		if (!this.eleicaoService.consultarTodasEleicoes().isEmpty()) {
			model.addAttribute("listaEleicoes", this.eleicaoService.consultarTodasEleicoes());
		}
		return "redirect:/eleicao";
	}
	
}
