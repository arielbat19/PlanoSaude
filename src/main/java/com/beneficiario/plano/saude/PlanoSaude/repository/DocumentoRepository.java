package com.beneficiario.plano.saude.PlanoSaude.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beneficiario.plano.saude.PlanoSaude.entity.Documento;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {

}
