package com.TechFrotas.TechFrotas.controllers;

import com.TechFrotas.TechFrotas.dtos.GastosVeiculosRecordDto;
import com.TechFrotas.TechFrotas.models.CaminhaoModel;
import com.TechFrotas.TechFrotas.models.GastosVeiculosModel;
import com.TechFrotas.TechFrotas.repositories.CaminhaoRepository;
import com.TechFrotas.TechFrotas.repositories.GastosVeiculosRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:8080/gastos-veiculos
@RestController
@RequestMapping("/gastos-veiculos") // Adicione um prefixo à URL
public class GastosVeiculosController {

    @Autowired
    private GastosVeiculosRepository gastosVeiculosRepository;

    @Autowired
    private CaminhaoRepository caminhaoRepository;

    @PostMapping
    public ResponseEntity<GastosVeiculosModel> criarGasto(@RequestBody GastosVeiculosRecordDto dto) {
        CaminhaoModel caminhao = caminhaoRepository.findByPlaca(dto.placaCaminhao())
                .orElseThrow(() -> new EntityNotFoundException("Caminhão com placa " + dto.placaCaminhao() + " não encontrado."));

        GastosVeiculosModel gasto = new GastosVeiculosModel();
        gasto.setVeiculo(caminhao);
        gasto.setTipo(dto.tipo());
        gasto.setValor(dto.valor());
        gasto.setParcela(dto.parcela());
        gasto.setNumeroParcelasTotal(dto.numeroParcelasTotal());

        return ResponseEntity.status(HttpStatus.CREATED).body(gastosVeiculosRepository.save(gasto));
    }

    @GetMapping
    public ResponseEntity<List<GastosVeiculosModel>> listarGastos() {
        List<GastosVeiculosModel> gastos = gastosVeiculosRepository.findAll();
        return ResponseEntity.ok(gastos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GastosVeiculosModel> buscarGastoPorId(@PathVariable Long id) {
        GastosVeiculosModel gasto = gastosVeiculosRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Gasto não encontrado."));
        return ResponseEntity.ok(gasto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GastosVeiculosModel> atualizarGasto(@PathVariable Long id, @RequestBody GastosVeiculosRecordDto dto) {
        GastosVeiculosModel gasto = gastosVeiculosRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Gasto não encontrado."));

        // Atualiza os campos do gasto com os valores do DTO
        CaminhaoModel caminhao = caminhaoRepository.findByPlaca(dto.placaCaminhao()) // Usa placaCaminhao do DTO
                .orElseThrow(() -> new EntityNotFoundException("Caminhão com placa " + dto.placaCaminhao() + " não encontrado."));
        gasto.setVeiculo(caminhao);
        gasto.setTipo(dto.tipo());
        gasto.setValor(dto.valor());
        gasto.setParcela(dto.parcela());
        gasto.setNumeroParcelasTotal(dto.numeroParcelasTotal());

        return ResponseEntity.ok(gastosVeiculosRepository.save(gasto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGasto(@PathVariable Long id) {
        try {
            gastosVeiculosRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}