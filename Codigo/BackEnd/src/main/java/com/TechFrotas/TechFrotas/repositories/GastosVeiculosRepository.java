package com.TechFrotas.TechFrotas.repositories;

import com.TechFrotas.TechFrotas.models.GastosVeiculosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GastosVeiculosRepository extends JpaRepository<GastosVeiculosModel, Long> {
}