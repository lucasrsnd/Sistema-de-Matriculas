package com.academicoplus.model;

import java.util.List;

// Classe Aluno
public class Aluno extends Usuario {
    private int matricula;
    private String curso;
    private String codigo;

    public void notificarDisciplina(Disciplina disciplina) {}
    public void desmatricularDisciplina(Disciplina disciplina) {}
    public List<Disciplina> visualizarDisciplinas() { return null; }
    public void solicitarBolsa() {}
}