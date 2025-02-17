package com.TechFrotas.TechFrotas.controllers;

import com.TechFrotas.TechFrotas.dtos.PedagioRecordDto;
import com.TechFrotas.TechFrotas.models.CaminhaoModel;
import com.TechFrotas.TechFrotas.models.PedagioModel;
import com.TechFrotas.TechFrotas.repositories.CaminhaoRepository;
import com.TechFrotas.TechFrotas.repositories.PedagioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PedagioController {

    @Autowired
    PedagioRepository pedagioRepository;

    @Autowired
    CaminhaoRepository caminhaoRepository;

    //http://localhost:8080/pedagios
    @PostMapping("/pedagios")
    public ResponseEntity<PedagioModel> criarPedagio(@RequestBody PedagioRecordDto dto) {
        CaminhaoModel caminhao = caminhaoRepository.findByPlaca(dto.placaCaminhao()) // Usa placaCaminhao do DTO
                .orElseThrow(() -> new EntityNotFoundException("Caminhão com placa " + dto.placaCaminhao() + " não encontrado."));

        PedagioModel pedagio = new PedagioModel();
        pedagio.setValor(dto.valor());
        pedagio.setLocal(dto.local());
        pedagio.setData(dto.data());
        pedagio.setCaminhao(caminhao);

        return ResponseEntity.status(HttpStatus.CREATED).body(pedagioRepository.save(pedagio));
    }

    @GetMapping("/pedagios")
    public ResponseEntity<List<PedagioModel>> listarPedagios() {
        List<PedagioModel> pedagios = pedagioRepository.findAll();
        return ResponseEntity.ok(pedagios);
    }

    @GetMapping("/pedagios/{id}")
    public ResponseEntity<PedagioModel> buscarPedagioPorId(@PathVariable Long id) {
        PedagioModel pedagio = pedagioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedágio não encontrado."));
        return ResponseEntity.ok(pedagio);
    }

    @PutMapping("/pedagios/{id}")
    public ResponseEntity<PedagioModel> atualizarPedagio(@PathVariable Long id, @RequestBody PedagioRecordDto dto) {
        PedagioModel pedagio = pedagioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedágio não encontrado."));

        pedagio.setValor(dto.valor());
        pedagio.setLocal(dto.local());
        pedagio.setData(dto.data());

        if (dto.placaCaminhao() != null) {
            CaminhaoModel caminhao = caminhaoRepository.findByPlaca(dto.placaCaminhao()) // Usa placaCaminhao do DTO
                    .orElseThrow(() -> new EntityNotFoundException("Caminhão com placa " + dto.placaCaminhao() + " não encontrado."));
            pedagio.setCaminhao(caminhao);
        } else {
            pedagio.setCaminhao(null);
        }

        return ResponseEntity.ok(pedagioRepository.save(pedagio));
    }

    @DeleteMapping("/pedagios/{id}")
    public ResponseEntity<Void> deletarPedagio(@PathVariable Long id) {
        try {
            pedagioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}