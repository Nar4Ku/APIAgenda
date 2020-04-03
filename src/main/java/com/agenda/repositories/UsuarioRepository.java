package com.agenda.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.agenda.models.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
	Usuario findByUsuarioAndSenha(String usuario, int senha);
}
