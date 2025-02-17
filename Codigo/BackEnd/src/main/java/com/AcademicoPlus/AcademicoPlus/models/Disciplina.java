package com.AcademicoPlus.AcademicoPlus.models;

import java.util.List;

// Classe Disciplina
public class Disciplina {
    private String codigo;
    private String nome;
    private int cargaHoraria;
    private int numeroCreditos;
    private Professor professor;
    private List<Aluno> alunosMatriculados;

    public boolean verificarDisponibilidade() { return false; }
    public boolean adicionarAluno(Aluno aluno) { return false; }
    public boolean removerAluno(Aluno aluno) { return false; }
}
