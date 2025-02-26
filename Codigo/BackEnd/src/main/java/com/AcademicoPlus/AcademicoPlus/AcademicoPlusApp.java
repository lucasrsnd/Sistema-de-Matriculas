package com.AcademicoPlus.AcademicoPlus;

import com.AcademicoPlus.AcademicoPlus.controllers.AlunoController;
import com.AcademicoPlus.AcademicoPlus.models.Aluno;
import com.AcademicoPlus.AcademicoPlus.models.Disciplina;
import com.AcademicoPlus.AcademicoPlus.models.Professor;
import com.AcademicoPlus.AcademicoPlus.repositories.AlunoRepository;

import java.util.Scanner;

public class AcademicoPlusApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AlunoRepository alunoRepository = new AlunoRepository();
        AlunoController alunoController = new AlunoController(alunoRepository);

        Aluno aluno = new Aluno("12345678900", "João Silva", "senha123");
        alunoRepository.adicionarAluno(aluno);

        Professor professor = new Professor("98765432100", "Prof. Maria", "senha123");
        Disciplina matematica = new Disciplina("Matemática", professor);

        while (true) {
            System.out.println("\n1. Matricular em disciplina");
            System.out.println("2. Cancelar matrícula");
            System.out.println("3. Sair");
            System.out.print("Escolha: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    alunoController.matricularAluno(aluno.getCpf(), matematica);
                    break;
                case 2:
                    alunoController.cancelarMatricula(aluno.getCpf(), matematica);
                    break;
                case 3:
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
