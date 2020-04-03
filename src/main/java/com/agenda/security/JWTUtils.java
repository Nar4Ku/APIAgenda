package com.agenda.security;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import java.util.Date;

@Service
public class JWTUtils {

	private static final String secretKey = "TyBQYWxtZWlyYXMgbsOjbyB0ZW0gbXVuZGlhbCE=";

	public String setAuthorization(String usuario) {

		String token = Jwts.builder().setSubject(usuario).claim("auth", "Autorizado")
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.setExpiration(new Date(System.currentTimeMillis() + 86400000))
//				Define token válido por 1 dia -> 86400000
				.compact();
		return token;
	}

	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			return false;
		}
	}

	public Claims readToken(String token) {

		Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();

		return claims;
	}

}