package br.com.votaaiprevi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.votaaiprevi.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
