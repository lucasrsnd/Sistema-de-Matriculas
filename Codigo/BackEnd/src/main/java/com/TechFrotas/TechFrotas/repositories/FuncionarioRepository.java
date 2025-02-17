package com.TechFrotas.TechFrotas.repositories;

import com.TechFrotas.TechFrotas.models.FuncionarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, Long> {
}
