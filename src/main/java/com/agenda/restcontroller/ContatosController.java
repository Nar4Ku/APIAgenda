package com.agenda.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agenda.models.Contatos;
import com.agenda.response.Response;
import com.agenda.security.JWTUtils;
import com.agenda.services.ContatosService;
import com.mongodb.util.JSON;

@RestController
@RequestMapping("api/contatos")
public class ContatosController {

	@Autowired
	private ContatosService contatoService;

	@Autowired
	private JWTUtils jwt;

	@GetMapping(value = "/listarcontatos", produces = "application/json")
	public ResponseEntity<Response<Contatos>> listaContatos(@RequestHeader("authorization") String token) {
		Response<Contatos> response = new Response<Contatos>();
		try {

			token = token.replace("Bearer ", "");
			System.out.println(jwt.readToken(token));
			if (jwt.validateToken(token) == true) {

				List<Contatos> contatos = this.contatoService.listarContatos();

				if (contatos == null) {

					response.getErrors().add("Erro buscar usuários!");
					response.setMessage("NOK");
					response.setStatus(400);
					return ResponseEntity.badRequest().body(response);
				}

				response.setMessage("OK");
				response.setStatus(200);
				response.setManyData(contatos);

			} else {
				response.getErrors().add("Token Inválido!");
				response.setMessage("NOK");
				response.setStatus(400);
				return ResponseEntity.badRequest().body(response);
			}
		} catch (Exception e) {
			response.getErrors().add("Erro interno do servidor, contate Admin!");
			response.getErrors().add("Erro -> " + e.getMessage());
			response.setMessage("NOKS");
			response.setStatus(500);
			System.out.println("Erro" + e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);

	}

	@PostMapping(value = "/listarunico", produces = "application/json")
	public ResponseEntity<Response<Contatos>> listaUnicoContato(@RequestHeader("authorization") String token,
			@RequestBody Contatos contatos) {
		Response<Contatos> response = new Response<Contatos>();
		try {

			token = token.replace("Bearer ", "");

			if (jwt.validateToken(token) == true) {

				if (contatos.getId() == null) {

					response.getErrors().add("Id em branco ou inválido!");
					response.setMessage("NOK");
					response.setStatus(400);
					return ResponseEntity.badRequest().body(response);
				}

				System.out.println(contatos.getId());
				Optional<Contatos> contato = this.contatoService.listaUnicoContato(contatos.getId());

				if (!contato.isPresent()) {

					response.getErrors().add("Erro ao buscar usuário!");
					response.setMessage("NOK");
					response.setStatus(400);
					return ResponseEntity.badRequest().body(response);
				}

				response.setData(contato.get());
				response.setMessage("OK");
				response.setStatus(200);
			} else {
				response.getErrors().add("Token Inválido!");
				response.setMessage("NOK");
				response.setStatus(400);
				return ResponseEntity.badRequest().body(response);
			}

		} catch (Exception e) {
			response.getErrors().add("Erro interno do servidor, contate Admin!");
			response.getErrors().add("Erro -> " + e.getMessage());
			response.setMessage("NOKS");
			response.setStatus(500);
			System.out.println("Erro" + e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);

	}

	@PostMapping(value = "/editarcontatos", consumes = "application/json")
	public ResponseEntity<Response<Contatos>> editarContatos(@RequestBody Contatos contatos,
			@RequestHeader("authorization") String tokenPass) {
		Response<Contatos> response = new Response<Contatos>();
		try {

			String token = tokenPass.replace("Bearer ", "");

			if (jwt.validateToken(token) == true) {

				if (contatos.getId() == null) {
					response.getErrors().add("Dados em Branco! Campos Obrigatório");
					response.getErrors().add("Erro ao alterar contato!");
					response.setMessage("NOK");
					response.setStatus(400);
					return ResponseEntity.badRequest().body(response);
				}

				this.contatoService.editarContatos(contatos);
				response.setMessage("OK");
				response.setStatus(200);

			} else {
				response.getErrors().add("Token Inválido!");
				response.setMessage("NOK");
				response.setStatus(400);
				return ResponseEntity.badRequest().body(response);
			}

		} catch (Exception e) {
			response.getErrors().add("Erro interno do servidor, contate Admin!");
			response.getErrors().add("Erro -> " + e.getMessage());
			response.setMessage("NOKS");
			response.setStatus(500);
			System.out.println("Erro" + e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	@PostMapping(value = "/excluircontato", produces = "application/json")
	public ResponseEntity<Response<String>> excluirContato(@RequestBody Contatos contatos,
			@RequestHeader("authorization") String tokenPass) {

		Response<String> response = new Response<String>();

		try {
			String token = tokenPass.replace("Bearer ", "");

			if (jwt.validateToken(token) != true) {

				response.getErrors().add("Token Inválido!");
				response.setMessage("NOK");
				response.setStatus(400);
				return ResponseEntity.badRequest().body(response);

			}

			if (contatos.getId() == null) {

				response.getErrors().add("Dados em Branco! Campos Obrigatório");
				response.getErrors().add("Erro ao alterar contato!");
				response.setMessage("NOK");
				response.setStatus(400);
				return ResponseEntity.badRequest().body(response);

			}

			this.contatoService.deleteContatos(contatos.getId());
			response.setMessage("Ok");
			response.setStatus(200);

		} catch (Exception e) {
			response.getErrors().add("Erro interno do servidor, contate Admin!");
			response.getErrors().add("Erro -> " + e.getMessage());
			response.setMessage("NOKS");
			response.setStatus(500);
			System.out.println("Erro" + e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);

	}

}
