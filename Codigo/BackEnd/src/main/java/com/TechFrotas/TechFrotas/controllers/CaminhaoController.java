package com.TechFrotas.TechFrotas.controllers;

import com.TechFrotas.TechFrotas.dtos.CaminhaoRecordDto;
import com.TechFrotas.TechFrotas.models.CaminhaoModel;
import com.TechFrotas.TechFrotas.repositories.CaminhaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CaminhaoController {

    @Autowired
    CaminhaoRepository caminhaoRepository;

    //http://localhost:8080/caminhoes
    @PostMapping("/caminhoes")
    public ResponseEntity<CaminhaoModel> criarCaminhao(@RequestBody CaminhaoRecordDto dto) {
        CaminhaoModel caminhao = new CaminhaoModel();
        caminhao.setPlaca(dto.placa());
        caminhao.setModelo(dto.modelo());
        caminhao.setAno(dto.ano());
        caminhao.setKm(dto.km());
        caminhao.setKmTrocaOleo(dto.kmTrocaOleo());
        caminhao.setUltimaTrocaOleoKm(0);
        return ResponseEntity.status(HttpStatus.CREATED).body(caminhaoRepository.save(caminhao));
    }

    @GetMapping("/caminhoes")
    public ResponseEntity<List<CaminhaoModel>> listarCaminhoes() {
        List<CaminhaoModel> caminhoes = caminhaoRepository.findAll();
        return ResponseEntity.ok(caminhoes);
    }

    @GetMapping("/caminhoes/{id}")
    public ResponseEntity<CaminhaoModel> buscarCaminhaoPorId(@PathVariable(value="id") Long id) {
        CaminhaoModel caminhao = caminhaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Caminhão não encontrado."));
        return ResponseEntity.ok(caminhao);
    }

    @PutMapping("/caminhoes/{id}")
    public ResponseEntity<CaminhaoModel> atualizarCaminhao(@PathVariable Long id, @RequestBody CaminhaoRecordDto dto) {
        CaminhaoModel caminhao = caminhaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Caminhão não encontrado."));

        caminhao.setPlaca(dto.placa());
        caminhao.setModelo(dto.modelo());
        caminhao.setAno(dto.ano());
        caminhao.setKmTrocaOleo(dto.kmTrocaOleo());
        return ResponseEntity.ok(caminhaoRepository.save(caminhao));
    }

    @DeleteMapping("/caminhoes/{id}")
    public ResponseEntity<Void> deletarCaminhao(@PathVariable Long id) {
        try {
            caminhaoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
