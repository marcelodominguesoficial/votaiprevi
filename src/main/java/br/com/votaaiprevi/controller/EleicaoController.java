package br.com.votaaiprevi.controller;

import java.util.List;

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
		List<Eleicao> listaEleicoes = this.eleicaoService.consultarTodasEleicoes();
		if (!listaEleicoes.isEmpty()) {
			model.addAttribute("listaEleicoes", listaEleicoes);
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
		List<Eleicao> listaEleicoes = this.eleicaoService.consultarTodasEleicoes();
		if (!listaEleicoes.isEmpty()) {
			model.addAttribute("listaEleicoes", listaEleicoes);
		}
		return "eleicao";
	}
	
	
	@PostMapping("/salvarEleicao")
	public String salvarEleicao(@Valid Eleicao eleicao, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
		    return "cadastrarEleicao";
		}
		
		this.eleicaoService.salvarEleicao(eleicao);
		List<Eleicao> listaEleicoes = this.eleicaoService.consultarTodasEleicoes();
		if (!listaEleicoes.isEmpty()) {
			model.addAttribute("listaEleicoes", listaEleicoes);
		}
		return "eleicao";
	}
	
}
