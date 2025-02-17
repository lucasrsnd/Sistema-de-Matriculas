package com.AcademicoPlus.AcademicoPlus.models;

import java.util.List;

// Classe Curso
public class Curso {
    private String codigo;
    private String nome;
    private List<Disciplina> disciplinas;

    public boolean adicionarDisciplina(Disciplina disciplina) { return false; }
    public boolean removerDisciplina(Disciplina disciplina) { return false; }
}