package com.AcademicoPlus.AcademicoPlus.repositories;

import com.AcademicoPlus.AcademicoPlus.models.Aluno;
import java.util.HashMap;
import java.util.Map;

public class AlunoRepository {
    private Map<String, Aluno> alunos = new HashMap<>();

    public void adicionarAluno(Aluno aluno) {
        alunos.put(aluno.getCpf(), aluno);
    }

    public Aluno buscarAluno(String cpf) {
        return alunos.get(cpf);
    }

    public void removerAluno(String cpf) {
        alunos.remove(cpf);
    }
}
