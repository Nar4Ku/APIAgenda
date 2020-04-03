package com.agenda.services;

import com.agenda.models.Usuario;

public interface UsuarioService {

	public Usuario autenticar(String usuario, int senha);

}
