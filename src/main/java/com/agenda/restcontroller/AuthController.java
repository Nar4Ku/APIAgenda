package com.agenda.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agenda.models.Usuario;
import com.agenda.response.Response;
import com.agenda.security.JWTUtils;
import com.agenda.services.UsuarioService;

@RestController
@RequestMapping("api/usuarios")
//@CrossOrigin(origins = "*") chamada alternativa do Cross Origins
public class AuthController {

	@Autowired
	private UsuarioService userService;

	@Autowired
	private JWTUtils jwt;

	@GetMapping(value = "/mensagem", produces = "application/text")
	public String msg() {
		return "Hello World!";
	}

	@PostMapping(value = "/autenticar", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Response<String>> autenticar(@RequestBody Usuario usuario) {
		Response<String> response = new Response<String>();

		try {

			Usuario usuarioLog = this.userService.autenticar(usuario.getUsuario(), usuario.getSenha());
//			System.out.println(usuarioLog);

			if (usuarioLog == null) {

				response.getErrors().add("Erro ao validar usuário!");
				response.setMessage("NOK");
				response.setStatus(400);
				return ResponseEntity.badRequest().body(response);

			}

			String token = jwt.setAuthorization(usuario.getUsuario());

			response.setStatus(200);
			response.setMessage("OK");
			response.setToken(token);

		} catch (Exception e) {
			response.getErrors().add("Erro ao validar usuário!");
			response.getErrors().add("Erro -> " + e.getMessage());
			response.setMessage("NOKS");
			response.setStatus(500);
			System.out.println("Erro" + e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}

}
