package com.beneficiario.plano.saude.PlanoSaude.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beneficiario.plano.saude.PlanoSaude.dto.BeneficiarioDTO;
import com.beneficiario.plano.saude.PlanoSaude.entity.Beneficiario;
import com.beneficiario.plano.saude.PlanoSaude.service.CadastroBeneficiarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("api/v1/planoSaude")
public class CadastroBeneficiarioController {

	@Autowired
	private CadastroBeneficiarioService service;

	@Operation(summary = "Cadastrar um novo beneficiário")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Beneficiário cadastrado com sucesso"),
	@ApiResponse(responseCode = "400", description = "Requisição inválida"),
	@ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
	@PostMapping("/cadastroBeneficiario")
	public ResponseEntity<Beneficiario> cadastraBeneficiario(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do beneficiário para cadastro") @RequestBody BeneficiarioDTO beneficiarioDTO) {
		try {
			Beneficiario beneficiarioCadastrado = service.cadastrar(beneficiarioDTO);
			return ResponseEntity.ok(beneficiarioCadastrado);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary = "Buscar beneficiário por ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Beneficiário encontrado"),
	@ApiResponse(responseCode = "404", description = "Beneficiário não encontrado") })
	@GetMapping("/buscarPorId/{id}")
	public ResponseEntity<Optional<Beneficiario>> buscarPorId(
			@Parameter(description = "ID do beneficiário a ser buscado", example = "1") @PathVariable Long id) {
		Optional<Beneficiario> beneficiario = service.getBeneficiarioWithDocumentos(id);
		if (beneficiario.isPresent()) {
			return ResponseEntity.ok(beneficiario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Buscar todos os beneficiários cadastrados")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de beneficiários encontrada"),
	@ApiResponse(responseCode = "204", description = "Nenhum beneficiário encontrado") })
	@GetMapping("/todos")
	public ResponseEntity<List<Beneficiario>> buscarTodos() {
		List<Beneficiario> beneficiarios = service.getBuscarTodos();
		if (!beneficiarios.isEmpty()) {
			return ResponseEntity.ok(beneficiarios);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@Operation(summary = "Excluir beneficiário por ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Beneficiário excluído com sucesso"),
	@ApiResponse(responseCode = "404", description = "Beneficiário não encontrado") })
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<Void> excluirPorId(@PathVariable Long id) {
		boolean deleted = service.excluirPorId(id);
		if (deleted) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Atualizar beneficiário por ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Beneficiário atualizado com sucesso"),
	@ApiResponse(responseCode = "404", description = "Beneficiário não encontrado") })
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<Beneficiario> atualizarBeneficiario(@PathVariable Long id,
			@RequestBody @Validated BeneficiarioDTO beneficiarioDTO) {
		Beneficiario beneficiarioAtualizado = service.atualizarBeneficiario(id, beneficiarioDTO);
		if (beneficiarioAtualizado != null) {
			return ResponseEntity.ok(beneficiarioAtualizado);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
