package com.beneficiario.plano.saude.PlanoSaude.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

public class DocumentoDTO {

	private Long id;
	@Schema(description = "Tipo de Documento", example = "CPF")
	private String tipoDocumento;
	@Schema(description = "Descrição do Documento", example = "Cadastro de Pessoa Fisica")
	private String descricao;
	@Schema(description = "Data de inclusão do documento", example = "1990-01-01")
	private LocalDateTime dataInclusao;
	@Schema(description = "Data de atualizacao do documento", example = "1990-01-01")
	private LocalDateTime dataAtualizacao;
	@Schema(description = "Beneficiario relacionado ao documento")
	private BeneficiarioDTO beneficiario;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(LocalDateTime dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public LocalDateTime getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public BeneficiarioDTO getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(BeneficiarioDTO beneficiario) {
		this.beneficiario = beneficiario;
	}

}
