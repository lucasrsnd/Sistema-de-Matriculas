package main.java.com.academicoplus.model;

import java.util.List;

// Classe Turma
public class Turma {
    private String id;
    private Disciplina disciplina;
    private List<Aluno> alunos;
    private String semestre;
    private int capacidade;
    private String codigo;

    public boolean verificarDisponibilidade() { return false; }
    public boolean matricularAluno(Aluno aluno) { return false; }
}
