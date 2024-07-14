package com.beneficiario.plano.saude.PlanoSaude.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.beneficiario.plano.saude.PlanoSaude.dto.BeneficiarioDTO;
import com.beneficiario.plano.saude.PlanoSaude.dto.DocumentoDTO;
import com.beneficiario.plano.saude.PlanoSaude.entity.Beneficiario;
import com.beneficiario.plano.saude.PlanoSaude.entity.Documento;

public class BeneficiarioMapper {

	public static Beneficiario toEntity(BeneficiarioDTO dto) {
		if (dto == null) {
			return null;
		}

		Beneficiario beneficiario = new Beneficiario();
		beneficiario.setNome(dto.getNome());
		beneficiario.setTelefone(dto.getTelefone());
		beneficiario.setDataNascimento(dto.getDataNascimento());
		beneficiario.setDataInclusao(LocalDateTime.now());
		return beneficiario;
	}
	
	public static List<Documento> toEntityDocumento(BeneficiarioDTO dto, Beneficiario beneficiario) {
	    if (dto == null) {
	        return null;
	    }

	    List<Documento> documentos = new ArrayList<>();

	    for (DocumentoDTO docDto : dto.getDocumentos()) {
	        Documento documento = new Documento();
	        documento.setDescricao(docDto.getDescricao());
	        documento.setTipoDocumento(docDto.getTipoDocumento());
	        documento.setDataInclusao(LocalDateTime.now());
	        documento.setDataAtualizacao(LocalDateTime.now());
	        documento.setTipoDocumento(docDto.getTipoDocumento());
	        documento.setBeneficiario(beneficiario);
	        documentos.add(documento);
	    }

	    return documentos;
	}

}
