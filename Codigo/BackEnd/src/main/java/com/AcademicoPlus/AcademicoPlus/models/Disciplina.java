package com.AcademicoPlus.AcademicoPlus.models;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {
    private String nome;
    private Professor professor;
    private List<Aluno> alunosMatriculados = new ArrayList<>();

    public Disciplina(String nome, Professor professor) {
        this.nome = nome;
        this.professor = professor;
    }

    public String getNome() { return nome; }
    public Professor getProfessor() { return professor; }
    public List<Aluno> getAlunosMatriculados() { return alunosMatriculados; }

    public boolean adicionarAluno(Aluno aluno) {
        if (alunosMatriculados.size() < 60) {
            alunosMatriculados.add(aluno);
            return true;
        }
        return false;
    }
}
