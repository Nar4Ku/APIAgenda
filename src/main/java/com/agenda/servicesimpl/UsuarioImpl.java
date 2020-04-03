package com.agenda.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.agenda.models.Usuario;
import com.agenda.repositories.UsuarioRepository;
import com.agenda.services.UsuarioService;

@Service
public class UsuarioImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepo;

	@Override
	public Usuario autenticar(String usuario, int senha) {
		return this.usuarioRepo.findByUsuarioAndSenha(usuario, senha);
	}

}
