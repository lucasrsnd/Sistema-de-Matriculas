package com.TechFrotas.TechFrotas.controllers;

import com.TechFrotas.TechFrotas.dtos.AbastecimentoRecordDto;
import com.TechFrotas.TechFrotas.models.AbastecimentoModel;
import com.TechFrotas.TechFrotas.models.CaminhaoModel;
import com.TechFrotas.TechFrotas.models.NotificacaoModel;
import com.TechFrotas.TechFrotas.repositories.AbastecimentoRepository;
import com.TechFrotas.TechFrotas.repositories.CaminhaoRepository;
import com.TechFrotas.TechFrotas.repositories.NotificacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AbastecimentoController {

    @Autowired
    AbastecimentoRepository abastecimentoRepository;

    @Autowired
    CaminhaoRepository caminhaoRepository;

    @Autowired
    NotificacaoRepository notificacaoRepository;

    //http://localhost:8080/abastecimentos
    @PostMapping("/abastecimentos")
    public ResponseEntity<AbastecimentoModel> criarAbastecimento(@RequestBody AbastecimentoRecordDto dto) {
        CaminhaoModel caminhao = caminhaoRepository.findByPlaca(dto.placaCaminhao()) // Usa placaCaminhao do DTO
                .orElseThrow(() -> new EntityNotFoundException("Caminhão com placa " + dto.placaCaminhao() + " não encontrado."));

        AbastecimentoModel abastecimento = new AbastecimentoModel();
        abastecimento.setValor(dto.valor());
        abastecimento.setLitros(dto.litros());
        abastecimento.setLocal(dto.local());
        abastecimento.setData(dto.data());
        abastecimento.setKm(dto.km());
        abastecimento.setCaminhao(caminhao);

        // Adiciona a quilometragem do abastecimento ao caminhão
        int novaKm = caminhao.getKm() + dto.km();
        caminhao.setKm(novaKm);

        // Verifica se precisa de troca de óleo
        if (novaKm >= (caminhao.getUltimaTrocaOleoKm() + caminhao.getKmTrocaOleo())) {
            // Cria uma nova notificação
            NotificacaoModel notificacao = new NotificacaoModel();
            notificacao.setAssunto("Troca de Óleo");
            notificacao.setTexto("Trocar óleo do caminhão " + caminhao.getPlaca());
            notificacaoRepository.save(notificacao);

            // Atualiza a última quilometragem de troca de óleo DEPOIS de gerar a notificação
            caminhao.setUltimaTrocaOleoKm(novaKm);
        }

        caminhaoRepository.save(caminhao);
        return ResponseEntity.status(HttpStatus.CREATED).body(abastecimentoRepository.save(abastecimento));
    }

    @GetMapping("/abastecimentos")
    public ResponseEntity<List<AbastecimentoModel>> listarAbastecimentos() {
        List<AbastecimentoModel> abastecimentos = abastecimentoRepository.findAll();
        return ResponseEntity.ok(abastecimentos);
    }

    @GetMapping("/abastecimentos/{id}")
    public ResponseEntity<AbastecimentoModel> buscarAbastecimentoPorId(@PathVariable Long id) {
        AbastecimentoModel abastecimento = abastecimentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Abastecimento não encontrado."));
        return ResponseEntity.ok(abastecimento);
    }

    @PutMapping("/abastecimentos/{id}")
    public ResponseEntity<AbastecimentoModel> atualizarAbastecimento(@PathVariable Long id, @RequestBody AbastecimentoRecordDto dto) {
        AbastecimentoModel abastecimento = abastecimentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Abastecimento não encontrado."));

        abastecimento.setValor(dto.valor());
        abastecimento.setLitros(dto.litros());
        abastecimento.setLocal(dto.local());
        abastecimento.setData(dto.data());
        abastecimento.setKm(dto.km());

        if (dto.placaCaminhao() != null) {
            CaminhaoModel caminhao = caminhaoRepository.findByPlaca(dto.placaCaminhao()) // Usa placaCaminhao do DTO
                    .orElseThrow(() -> new EntityNotFoundException("Caminhão com placa " + dto.placaCaminhao() + " não encontrado."));
            abastecimento.setCaminhao(caminhao);
        } else {
            abastecimento.setCaminhao(null);
        }

        return ResponseEntity.ok(abastecimentoRepository.save(abastecimento));
    }

    @DeleteMapping("/abastecimentos/{id}")
    public ResponseEntity<Void> deletarAbastecimento(@PathVariable Long id) {
        try {
            abastecimentoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}