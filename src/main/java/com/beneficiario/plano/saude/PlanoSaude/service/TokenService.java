package com.beneficiario.plano.saude.PlanoSaude.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.beneficiario.plano.saude.PlanoSaude.entity.Usuario;

@Service
public class TokenService {

	public String gerarToken(Usuario usuario) {
		return JWT.create().withIssuer("Beneficiarios").withSubject(usuario.getUsername()).withClaim("id", usuario.getId())
				.withExpiresAt(LocalDateTime.now().plusMinutes(30).toInstant(ZoneOffset.of("-03:00")))
				.sign(Algorithm.HMAC256("secreta"));
	}

	public String getSubject(String token) {
		return JWT.require(Algorithm.HMAC256("secreta")).withIssuer("Beneficiarios").build().verify(token).getSubject();

	}
}
