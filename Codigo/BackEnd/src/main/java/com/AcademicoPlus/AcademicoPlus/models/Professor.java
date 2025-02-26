package com.AcademicoPlus.AcademicoPlus.models;

import java.util.ArrayList;
import java.util.List;

public class Professor extends Usuario {
    private List<Disciplina> disciplinas = new ArrayList<>();

    public Professor(String cpf, String nome, String senha) {
        super(cpf, nome, senha);
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }
}
