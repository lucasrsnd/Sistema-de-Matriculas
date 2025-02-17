package com.AcademicoPlus.AcademicoPlus.models;

import java.util.List;

// Classe Professor
public class Professor extends Usuario {
    private String codigo;
    private List<Disciplina> disciplinas;

    public List<Aluno> listarAlunos() { return null; }
    public List<Disciplina> consultarDisciplinas() { return disciplinas; }
    
    @Override
    public void fazerLogin() {
       
    }
    @Override
    public void fazerLogout() {

    }
}