package com.beneficiario.plano.saude.PlanoSaude.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.beneficiario.plano.saude.PlanoSaude.entity.Beneficiario;

@Repository
public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long> {
	
	@Query("SELECT b FROM beneficiario b LEFT JOIN FETCH b.documentos WHERE b.id = :id")
    Optional<Beneficiario> findBeneficiarioWithDocumentosById(@Param("id") Long id);

}
