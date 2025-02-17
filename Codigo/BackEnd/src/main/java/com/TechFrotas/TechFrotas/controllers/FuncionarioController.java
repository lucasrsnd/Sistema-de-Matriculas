package com.TechFrotas.TechFrotas.controllers;

import com.TechFrotas.TechFrotas.dtos.FuncionarioRecordDto;
import com.TechFrotas.TechFrotas.models.CaminhaoModel;
import com.TechFrotas.TechFrotas.repositories.CaminhaoRepository;
import com.TechFrotas.TechFrotas.models.FuncionarioModel;
import com.TechFrotas.TechFrotas.repositories.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FuncionarioController {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    CaminhaoRepository caminhaoRepository;

    //http://localhost:8080/funcionarios
    @PostMapping("/funcionarios")
    public ResponseEntity<FuncionarioModel> criarFuncionario(@RequestBody FuncionarioRecordDto dto) {
        CaminhaoModel caminhao = null;
        if (dto.placaCaminhao() != null) {
            caminhao = caminhaoRepository.findByPlaca(dto.placaCaminhao())
                    .orElseThrow(() -> new EntityNotFoundException("Caminhão com placa " + dto.placaCaminhao() + " não encontrado."));
        }

        FuncionarioModel funcionario = new FuncionarioModel();
        funcionario.setNome(dto.nome());
        funcionario.setCpf(dto.cpf());
        funcionario.setSalario(dto.salario());
        funcionario.setTelefone(dto.telefone());
        funcionario.setCaminhao(caminhao); // Associa o caminhão encontrado

        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioRepository.save(funcionario));
    }

    @GetMapping("/funcionarios")
    public ResponseEntity<List<FuncionarioModel>> listarFuncionarios() {
        List<FuncionarioModel> funcionarios = funcionarioRepository.findAll();
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/funcionarios/{id}")
    public ResponseEntity<FuncionarioModel> buscarFuncionarioPorId(@PathVariable(value="id") Long id) {
        FuncionarioModel funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado."));
        return ResponseEntity.ok(funcionario);
    }

    @PutMapping("/funcionarios/{id}")
    public ResponseEntity<FuncionarioModel> atualizarFuncionario(@PathVariable Long id, @RequestBody FuncionarioRecordDto dto) {
        FuncionarioModel funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado."));

        funcionario.setNome(dto.nome());
        funcionario.setCpf(dto.cpf());
        funcionario.setSalario(dto.salario());
        funcionario.setTelefone(dto.telefone());

        if (dto.placaCaminhao() != null) {
            CaminhaoModel caminhao = caminhaoRepository.findByPlaca(dto.placaCaminhao())
                    .orElseThrow(() -> new EntityNotFoundException("Caminhão com placa " + dto.placaCaminhao() + " não encontrado."));
            funcionario.setCaminhao(caminhao);
        } else {
            // Se placaCaminhao for nula no DTO, remove a associação com o caminhão
            funcionario.setCaminhao(null);
        }

        return ResponseEntity.ok(funcionarioRepository.save(funcionario));
    }

    @DeleteMapping("/funcionarios/{id}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Long id) {
        try {
            funcionarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
