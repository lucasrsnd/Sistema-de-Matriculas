package com.TechFrotas.TechFrotas.repositories;

import com.TechFrotas.TechFrotas.models.CaminhaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CaminhaoRepository extends JpaRepository<CaminhaoModel, Long> {
    Optional<CaminhaoModel> findByPlaca(String placa);
}
