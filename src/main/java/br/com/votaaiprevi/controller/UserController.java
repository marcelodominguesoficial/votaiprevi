package br.com.votaaiprevi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.votaaiprevi.entity.Usuario;
import br.com.votaaiprevi.repository.UsuarioRepository;

@Controller
public class UserController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@RequestMapping("/cadastrarUsuario")
	public String getUsuarios(Model model) {
		model.addAttribute("listaUsuarios", this.usuarioRepository.findAll());
		return "cadastrarUsuario";
	}
	
	@PostMapping("/salvarUsuario")
	public String cadastrarUsuario(@ModelAttribute Usuario usuario) {
		
		return "resultCadastroUsuario";
	}

}
