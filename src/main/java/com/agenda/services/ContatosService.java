package com.agenda.services;

import java.util.List;
import java.util.Optional;
import com.agenda.models.Contatos;

public interface ContatosService {

	List<Contatos> listarContatos();

	Optional<Contatos> listaUnicoContato(String id);

	Contatos editarContatos(Contatos contatos);

	void deleteContatos(String id);

}
