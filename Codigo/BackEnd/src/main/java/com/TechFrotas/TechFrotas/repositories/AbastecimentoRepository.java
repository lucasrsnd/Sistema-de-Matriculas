package com.TechFrotas.TechFrotas.repositories;

import com.TechFrotas.TechFrotas.models.AbastecimentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbastecimentoRepository extends JpaRepository<AbastecimentoModel, Long> {
}