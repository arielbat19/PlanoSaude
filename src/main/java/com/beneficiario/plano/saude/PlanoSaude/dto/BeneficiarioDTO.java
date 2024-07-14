package com.beneficiario.plano.saude.PlanoSaude.dto;

import java.util.Date;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public class BeneficiarioDTO {

	@Schema(description = "Nome do beneficiário", example = "João Silva")
	private String nome;
	@Schema(description = "Telefone do beneficiário", example = "(11) 99999-9999")
	private String telefone;
	@Schema(description = "Data de nascimento do beneficiário", example = "1990-01-01")
	private String dataNascimento;
	@Schema(description = "Data de inclusão do beneficiário", example = "1990-01-01")
	private Date dataInclusao;
	@Schema(description = "Data de atualizacao do beneficiário", example = "1990-01-01")
	private Date dataAtualizacao;
	@Schema(description = "Lista de documentos do beneficiário")
	private List<DocumentoDTO> documentos;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public List<DocumentoDTO> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<DocumentoDTO> documentos) {
		this.documentos = documentos;
	}

}
