package com.TechFrotas.TechFrotas.repositories;

import com.TechFrotas.TechFrotas.models.NotificacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacaoRepository extends JpaRepository<NotificacaoModel, Long> {
}