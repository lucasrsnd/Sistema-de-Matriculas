package com.TechFrotas.TechFrotas.repositories;

import com.TechFrotas.TechFrotas.models.ViagemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ViagemRepository extends JpaRepository<ViagemModel, Long> {
}
