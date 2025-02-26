package com.AcademicoPlus.AcademicoPlus.repositories;

import com.AcademicoPlus.AcademicoPlus.models.Disciplina;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaRepository {
    private List<Disciplina> disciplinas = new ArrayList<>();

    public void adicionarDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
    }

    public List<Disciplina> listarDisciplinas() {
        return disciplinas;
    }
}
