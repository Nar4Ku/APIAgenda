package com.agenda.servicesimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.agenda.models.Contatos;
import com.agenda.repositories.ContatosRepository;
import com.agenda.services.ContatosService;

@Service
public class ContatosImpl implements ContatosService {

	@Autowired
	private ContatosRepository contatosRepo;

	@Override
	public List<Contatos> listarContatos() {
		return this.contatosRepo.findAll();
	}

	@Override
	public Optional<Contatos> listaUnicoContato(String id) {
		return this.contatosRepo.findById(id);
	}

	@Override
	public Contatos editarContatos(Contatos contatos) {
		return this.contatosRepo.save(contatos);
	}

	@Override
	public void deleteContatos(String id) {
		this.contatosRepo.deleteById(id);
	}

}
