package com.beneficiario.plano.saude.PlanoSaude.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beneficiario.plano.saude.PlanoSaude.dto.BeneficiarioDTO;
import com.beneficiario.plano.saude.PlanoSaude.dto.DocumentoDTO;
import com.beneficiario.plano.saude.PlanoSaude.entity.Beneficiario;
import com.beneficiario.plano.saude.PlanoSaude.entity.Documento;
import com.beneficiario.plano.saude.PlanoSaude.mapper.BeneficiarioMapper;
import com.beneficiario.plano.saude.PlanoSaude.repository.BeneficiarioRepository;
import com.beneficiario.plano.saude.PlanoSaude.repository.DocumentoRepository;

@Service
public class CadastroBeneficiarioService {

	@Autowired
	private BeneficiarioRepository repository;

	@Autowired
	private DocumentoRepository documentoRepository;

	@Transactional
	public Beneficiario cadastrar(BeneficiarioDTO beneficiarioDTO) {
		Beneficiario beneficiario = BeneficiarioMapper.toEntity(beneficiarioDTO);
		List<Documento> documentos = BeneficiarioMapper.toEntityDocumento(beneficiarioDTO, beneficiario);
		beneficiario = repository.save(beneficiario);
		documentos = documentoRepository.saveAll(documentos);
		beneficiario.setDocumentos(documentos);
		return beneficiario;
	}

	@Transactional
	public Optional<Beneficiario> getBeneficiarioWithDocumentos(Long id) {
		return repository.findBeneficiarioWithDocumentosById(id);
	}

	@Transactional
	public List<Beneficiario> getBuscarTodos() {
		return repository.findAll();
	}

	@Transactional
	public boolean excluirPorId(Long id) {
		Optional<Beneficiario> beneficiarioOptional = repository.findById(id);
		if (beneficiarioOptional.isPresent()) {
			repository.delete(beneficiarioOptional.get());
			return true;
		}
		return false;
	}

	@Transactional
	public Beneficiario atualizarBeneficiario(Long id, BeneficiarioDTO beneficiarioDTO) {
		Optional<Beneficiario> beneficiarioOptional = repository.findBeneficiarioWithDocumentosById(id);
		if (beneficiarioOptional.isPresent()) {
			Beneficiario beneficiario = beneficiarioOptional.get();

			if (beneficiarioDTO.getNome() != null && !beneficiarioDTO.getNome().isEmpty()) {
				beneficiario.setNome(beneficiarioDTO.getNome());
			}
			if (beneficiarioDTO.getTelefone() != null && !beneficiarioDTO.getTelefone().isEmpty()) {
				beneficiario.setTelefone(beneficiarioDTO.getTelefone());
			}
			if (beneficiarioDTO.getDataNascimento() != null) {
				beneficiario.setDataNascimento(beneficiarioDTO.getDataNascimento());
			}

			beneficiario.setDataAtualizacao(LocalDateTime.now());

			List<Documento> documentosAtualizados = new ArrayList<>();
			if (beneficiarioDTO.getDocumentos() != null) {
				for (DocumentoDTO documentoDTO : beneficiarioDTO.getDocumentos()) {
					Optional<Documento> documentoOptional = beneficiario.getDocumentos().stream()
							.filter(doc -> doc.getId().equals(documentoDTO.getId())).findFirst();

					if (documentoOptional.isPresent()) {
						Documento documento = documentoOptional.get();
						if (documentoDTO.getDescricao() != null && !documentoDTO.getDescricao().isEmpty()) {
							documento.setDescricao(documentoDTO.getDescricao());
						}
						if (documentoDTO.getTipoDocumento() != null) {
							documento.setTipoDocumento(documentoDTO.getTipoDocumento());
						}

						documento.setDataAtualizacao(LocalDateTime.now());
						documentosAtualizados.add(documento);
					} else {
						Documento novoDocumento = new Documento();
						novoDocumento.setDescricao(documentoDTO.getDescricao());
						novoDocumento.setTipoDocumento(documentoDTO.getTipoDocumento());
						novoDocumento.setBeneficiario(beneficiario);
						novoDocumento.setDataInclusao(LocalDateTime.now());
						documentosAtualizados.add(novoDocumento);
					}
				}
			}

			List<Long> documentoIdsDTO = beneficiarioDTO.getDocumentos().stream().map(DocumentoDTO::getId)
					.collect(Collectors.toList());

			beneficiario.getDocumentos().removeIf(doc -> doc.getId() != null && !documentoIdsDTO.contains(doc.getId()));
			beneficiario.getDocumentos().addAll(documentosAtualizados);
			repository.save(beneficiario);
			documentoRepository.saveAll(beneficiario.getDocumentos());
			return beneficiario;
		}
		return null;
	}
}
