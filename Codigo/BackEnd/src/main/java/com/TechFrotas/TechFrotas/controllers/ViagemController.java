package com.TechFrotas.TechFrotas.controllers;

import com.TechFrotas.TechFrotas.dtos.ViagemRecordDto;
import com.TechFrotas.TechFrotas.models.CaminhaoModel;
import com.TechFrotas.TechFrotas.models.ViagemModel;
import com.TechFrotas.TechFrotas.repositories.CaminhaoRepository;
import com.TechFrotas.TechFrotas.repositories.ViagemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ViagemController {

    @Autowired
    ViagemRepository viagemRepository;

    @Autowired
    CaminhaoRepository caminhaoRepository;

    //http://localhost:8080/viagens
    @PostMapping("/viagens")
    public ResponseEntity<ViagemModel> criarViagem(@RequestBody ViagemRecordDto dto) {
        // Busque o caminhão pela placa
        CaminhaoModel caminhao = caminhaoRepository.findByPlaca(dto.placaCaminhao())
                .orElseThrow(() -> new EntityNotFoundException("Caminhão com placa " + dto.placaCaminhao() + " não encontrado."));

        ViagemModel viagem = new ViagemModel();
        viagem.setOrigem(dto.origem());
        viagem.setDestino(dto.destino());
        viagem.setDataSaida(dto.dataSaida());
        viagem.setDataChegada(dto.dataChegada());
        viagem.setValor(dto.valor());
        viagem.setPesoTransportado(dto.pesoTransportado());
        viagem.setCaminhao(caminhao); // Associa o caminhão encontrado

        return ResponseEntity.status(HttpStatus.CREATED).body(viagemRepository.save(viagem));
    }

    @GetMapping("/viagens")
    public ResponseEntity<List<ViagemModel>> listarViagens() {
        List<ViagemModel> viagens = viagemRepository.findAll();
        return ResponseEntity.ok(viagens);
    }

    @GetMapping("/viagens/{id}")
    public ResponseEntity<ViagemModel> buscarViagemPorId(@PathVariable(value="id") Long id) {
        ViagemModel viagem = viagemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viagem não encontrada."));
        return ResponseEntity.ok(viagem);
    }

    @PutMapping("/viagens/{id}")
    public ResponseEntity<ViagemModel> atualizarViagem(@PathVariable Long id, @RequestBody ViagemRecordDto dto) {
        ViagemModel viagem = viagemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viagem não encontrada."));

        viagem.setOrigem(dto.origem());
        viagem.setDestino(dto.destino());
        viagem.setDataSaida(dto.dataSaida());
        viagem.setDataChegada(dto.dataChegada());
        viagem.setValor(dto.valor());
        viagem.setPesoTransportado(dto.pesoTransportado());

        if (dto.placaCaminhao() != null) {
            CaminhaoModel caminhao = caminhaoRepository.findByPlaca(dto.placaCaminhao())
                    .orElseThrow(() -> new EntityNotFoundException("Caminhão com placa " + dto.placaCaminhao() + " não encontrado."));
            viagem.setCaminhao(caminhao);
        } else {
            // Se placaCaminhao for nula no DTO, remove a associação com o caminhão
            viagem.setCaminhao(null);
        }

        return ResponseEntity.ok(viagemRepository.save(viagem));
    }

    @DeleteMapping("/viagens/{id}")
    public ResponseEntity<Void> deletarViagem(@PathVariable Long id) {
        try {
            viagemRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
