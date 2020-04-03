package com.agenda.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.agenda.models.Contatos;

public interface ContatosRepository extends MongoRepository<Contatos, String> {

	List<Contatos> findAll();

	Optional<Contatos> findById(String id);

	Contatos save(Contatos contatos);

	void deleteById(String id);
}
