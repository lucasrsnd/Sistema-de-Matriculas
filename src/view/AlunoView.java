package src.view;

import src.model.Matricula;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class AlunoView {

    private Scanner scanner = new Scanner(System.in);

    public void exibirMenu() {
        System.out.println("\n=== Menu Aluno ===");
        System.out.println("1. Matricular-se em Turmas");
        System.out.println("2. Gerenciar Matrículas");
        System.out.println("3. Visualizar Boleto");
        System.out.println("4. Voltar ao Menu Principal");
        System.out.print("Escolha uma opção: ");
    }

    public int obterOpcao() {
        return Integer.parseInt(scanner.nextLine());
    }

    public String obterNomeTurma() {
        System.out.print("Digite o nome da turma: ");
        return scanner.nextLine();
    }

    public String obterNomeCurso() {
        System.out.print("Digite o nome do curso: ");
        return scanner.nextLine();
    }

    public String obterCodigoAluno() {
        System.out.print("Digite seu código de aluno: ");
        return scanner.nextLine();
    }

    public String obterNomeAluno() {
        System.out.print("Digite seu nome: ");
        return scanner.nextLine();
    }

    public void mostrarBoleto(List<Matricula> matriculas) {
        double totalBoleto = 0;
        for (Matricula matricula : matriculas) {
            try (BufferedReader reader = new BufferedReader(new FileReader("src/data/Turmas.txt"))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    String[] dados = linha.split("\\|");
                    if (dados[0].equals(matricula.getNomeTurma())) {
                        totalBoleto += Double.parseDouble(dados[5]);
                    }
                }
            } catch (IOException e) {
                System.out.println("Erro ao calcular boleto: " + e.getMessage());
            }
        }
        System.out.println("Total a pagar: " + totalBoleto);
    }

    public void mostrarMatriculas(List<Matricula> matriculas) {
        System.out.println("\nSuas matrículas:");
        for (int i = 0; i < matriculas.size(); i++) {
            Matricula matricula = matriculas.get(i);
            System.out.println((i + 1) + ". " + matricula.getNomeTurma() + " - " + matricula.getNomeCurso());
        }
    }

}
