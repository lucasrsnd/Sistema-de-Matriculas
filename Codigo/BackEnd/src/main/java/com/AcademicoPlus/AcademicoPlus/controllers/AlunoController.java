package com.AcademicoPlus.AcademicoPlus.controllers;

import com.AcademicoPlus.AcademicoPlus.models.Aluno;
import com.AcademicoPlus.AcademicoPlus.models.Disciplina;
import com.AcademicoPlus.AcademicoPlus.repositories.AlunoRepository;

public class AlunoController {
    private AlunoRepository alunoRepository;

    public AlunoController(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public void matricularAluno(String cpf, Disciplina disciplina) {
        Aluno aluno = alunoRepository.buscarAluno(cpf);
        if (aluno != null) {
            aluno.matricularDisciplina(disciplina);
            System.out.println("Matrícula realizada!");
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    public void cancelarMatricula(String cpf, Disciplina disciplina) {
        Aluno aluno = alunoRepository.buscarAluno(cpf);
        if (aluno != null) {
            aluno.cancelarDisciplina(disciplina);
            System.out.println("Matrícula cancelada.");
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }
}
