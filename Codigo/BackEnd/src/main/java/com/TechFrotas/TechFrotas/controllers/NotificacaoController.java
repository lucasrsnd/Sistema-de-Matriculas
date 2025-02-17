package com.TechFrotas.TechFrotas.controllers;

import com.TechFrotas.TechFrotas.models.NotificacaoModel;
import com.TechFrotas.TechFrotas.repositories.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificacaoController {

    @Autowired
    NotificacaoRepository notificacaoRepository;
    //http://localhost:8080/notificacoes
    @GetMapping("/notificacoes")
    public ResponseEntity<List<NotificacaoModel>> listarNotificacoes() {
        List<NotificacaoModel> notificacoes = notificacaoRepository.findAll();
        return ResponseEntity.ok(notificacoes);
    }

    @DeleteMapping("/notificacoes/{id}")
    public ResponseEntity<Void> deletarNotificacao(@PathVariable Long id) {
        try {
            notificacaoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}