package com.AcademicoPlus.AcademicoPlus.models;

import java.util.ArrayList;
import java.util.List;

public class Aluno extends Usuario {
    private List<Disciplina> disciplinasMatriculadas = new ArrayList<>();

    public Aluno(String cpf, String nome, String senha) {
        super(cpf, nome, senha);
    }

    public void matricularDisciplina(Disciplina disciplina) {
        if (disciplinasMatriculadas.size() < 6) {
            disciplinasMatriculadas.add(disciplina);
        } else {
            System.out.println("Limite de disciplinas atingido.");
        }
    }

    public void cancelarDisciplina(Disciplina disciplina) {
        disciplinasMatriculadas.remove(disciplina);
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinasMatriculadas;
    }
}
