package com.TechFrotas.TechFrotas.repositories;

import com.TechFrotas.TechFrotas.models.PedagioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedagioRepository extends JpaRepository<PedagioModel, Long> {
}