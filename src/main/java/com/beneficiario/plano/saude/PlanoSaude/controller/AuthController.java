package com.beneficiario.plano.saude.PlanoSaude.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.beneficiario.plano.saude.PlanoSaude.dto.Login;
import com.beneficiario.plano.saude.PlanoSaude.entity.Usuario;
import com.beneficiario.plano.saude.PlanoSaude.service.TokenService;

@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping("/login")
	public String login(@RequestBody Login login) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				login.getLogin(), login.getPassword());

		Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		var usuario = (Usuario) authenticate.getPrincipal();

		return tokenService.gerarToken(usuario);

	}
}