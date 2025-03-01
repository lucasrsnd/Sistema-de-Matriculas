package Codigo;


import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class SistemaMatricula {

    private static final String PATH = "Codigo/data/";
    private static final String REGISTRO_MATRICULAS = PATH + "registro_matriculas.txt";
    private static Map<String, Usuario> usuarios = new HashMap<>();
    static Map<String, Turma> turmas = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        File dataFolder = new File(PATH);
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        carregarUsuarios();
        carregarTurmas();

        while (true) {
            System.out.println("\nSistema de Matrículas");
            System.out.println("1. Modo Administrador");
            System.out.println("2. Modo Aluno");
            System.out.println("3. Modo Professor");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            String escolha = scanner.nextLine();

            switch (escolha) {
                case "1":
                    menuAdministrador(scanner);
                    break;
                case "2":
                    menuAluno(scanner);
                    break;
                case "3":
                    menuProfessor(scanner);
                    break;
                case "4":
                    System.out.println("Saindo...");
                    salvarUsuarios();
                    salvarTurmas();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

   // Modo Administrador
private static void menuAdministrador(Scanner scanner) {
    String escolha;
    while (true) {
        System.out.println("\nModo Administrador");
        System.out.println("1. Criar Turma");
        System.out.println("2. Criar Usuário");
        System.out.println("3. Visualizar Turmas Criadas");
        System.out.println("4. Gerenciar Matrículas");
        System.out.println("5. Voltar");
        System.out.print("Escolha uma opção: ");
        escolha = scanner.nextLine();

        switch (escolha) {
            case "1":
                criarTurma(scanner);
                break;
            case "2":
                criarUsuario(scanner);
                break;
            case "3":
                visualizarTurmasCriadas();
                break;
            case "4":
                gerenciarMatriculas(scanner); // Aqui chamamos o método para gerenciar matrículas
                break;
            case "5":
                return;
            default:
                System.out.println("Opção inválida.");
        }
    }
}

// Gerenciar Matrículas de Alunos no modo Administrador
private static void gerenciarMatriculas(Scanner scanner) {
    System.out.print("Digite o nome da turma para gerenciar as matrículas: ");
    String nomeTurma = scanner.nextLine();

    if (!turmas.containsKey(nomeTurma)) {
        System.out.println("Turma não encontrada!");
        return;
    }

    Turma turma = turmas.get(nomeTurma);
    System.out.println("\nAlunos Matriculados na Turma: " + turma.getNome());

    // Listar alunos matriculados
    List<Usuario> alunosMatriculados = turma.getAlunosMatriculados();
    if (alunosMatriculados.isEmpty()) {
        System.out.println("Nenhum aluno matriculado nesta turma.");
        return;
    }

    for (int i = 0; i < alunosMatriculados.size(); i++) {
        Usuario aluno = alunosMatriculados.get(i);
        System.out.println(i + 1 + ". " + aluno.getNome() + " | Código: " + aluno.getCodigo());
    }

    System.out.print("\nEscolha o número do aluno para remover da matrícula ou digite 0 para voltar: ");
    int escolha = Integer.parseInt(scanner.nextLine());

    if (escolha == 0) {
        return; // Volta ao menu anterior
    }

    if (escolha > 0 && escolha <= alunosMatriculados.size()) {
        Usuario alunoRemovido = alunosMatriculados.get(escolha - 1);

        // Usando a mesma função que já funciona no menu do aluno
        cancelarMatricula(scanner, alunoRemovido);

    } else {
        System.out.println("Opção inválida.");
    }
}


// Criar uma nova turma
private static void criarTurma(Scanner scanner) {
    System.out.print("Nome da turma: ");
    String nome = scanner.nextLine();
    System.out.print("Curso da turma: ");
    String curso = scanner.nextLine();
    System.out.print("Turno (manhã, tarde, noite): ");
    String turno = scanner.nextLine();
    System.out.print("Quantidade mínima de vagas: ");
    int vagasMin = Integer.parseInt(scanner.nextLine());
    System.out.print("Quantidade máxima de vagas: ");
    int vagasMax = Integer.parseInt(scanner.nextLine());
    System.out.print("É turma obrigatória? (true/false): ");
    boolean obrigatoria = Boolean.parseBoolean(scanner.nextLine());
    System.out.print("Preço da disciplina: ");
    double preco = Double.parseDouble(scanner.nextLine());
    System.out.print("Código do professor: ");
    String codigoProfessor = scanner.nextLine();

    Turma turma = new Turma(nome, curso, turno, vagasMin, vagasMax, obrigatoria, preco, codigoProfessor);
    turmas.put(nome, turma);
    salvarTurmas();
    System.out.println("Turma criada com sucesso!");
}

// Criar um novo usuário (Aluno ou Professor)
private static void criarUsuario(Scanner scanner) {
    System.out.print("Nome do usuário: ");
    String nome = scanner.nextLine();
    System.out.print("Tipo de usuário (Aluno ou Professor): ");
    String tipo = scanner.nextLine();
    System.out.print("Código do usuário: ");
    String codigo = scanner.nextLine();

    Usuario usuario = new Usuario(nome, tipo, codigo);
    usuarios.put(codigo, usuario);
    salvarUsuarios();
    System.out.println("Usuário criado com sucesso!");
}

// Visualizar turmas criadas
private static void visualizarTurmasCriadas() {
    System.out.println("\nTurmas Criadas:");
    for (Turma turma : turmas.values()) {
        System.out.println(turma);
    }
}

   // Modo Aluno
private static void menuAluno(Scanner scanner) {
    System.out.print("Código do Aluno: ");
    String codigo = scanner.nextLine();

    if (!usuarios.containsKey(codigo) || !usuarios.get(codigo).getTipo().equals("Aluno")) {
        System.out.println("Aluno não encontrado!");
        return;
    }

    Usuario aluno = usuarios.get(codigo);
    String escolha;
    while (true) {
        System.out.println("\nModo Aluno");
        System.out.println("1. Matricular em Turmas");
        System.out.println("2. Visualizar Minhas Turmas");
        System.out.println("3. Cancelar Matrícula");
        System.out.println("4. Visualizar Boleto");
        System.out.println("5. Voltar");
        System.out.print("Escolha uma opção: ");
        escolha = scanner.nextLine();

        switch (escolha) {
            case "1":
                matricularAluno(scanner, aluno);
                break;
            case "2":
                visualizarTurmasAluno(codigo); // Chama o método para visualizar turmas do aluno
                break;
            case "3":
                cancelarMatricula(scanner, aluno);
                break;
            case "4":
                aluno.visualizarBoleto();
                break;
            case "5":
                salvarRegistroMatriculas(); // Salva o registro de matrículas ao sair
                return;
            default:
                System.out.println("Opção inválida.");
        }
    }
}

// Método para visualizar as turmas matriculadas de um aluno
private static void visualizarTurmasAluno(String codigoAluno) {
    System.out.println("\nTurmas Matrículadas do Aluno:");

    boolean encontrou = false;
    try (BufferedReader reader = new BufferedReader(new FileReader(REGISTRO_MATRICULAS))) {
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split(",");
            if (dados.length == 3 && dados[0].equals(codigoAluno)) {  // Verifica se o código do aluno bate
                System.out.println("Turma: " + dados[1] + " | Curso: " + dados[2]);
                encontrou = true;
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    if (!encontrou) {
        System.out.println("Nenhuma turma encontrada para este aluno.");
    }
}

// Matricular aluno
private static void matricularAluno(Scanner scanner, Usuario aluno) {
    System.out.println("\nEscolha as turmas para matricular (máximo de 4 turmas).");
    List<String> turmasSelecionadas = new ArrayList<>();
    while (turmasSelecionadas.size() < 4) {
        System.out.print("Nome da turma (ou 'sair' para finalizar): ");
        String nomeTurma = scanner.nextLine();
        if (nomeTurma.equals("sair")) break;
        if (turmas.containsKey(nomeTurma)) {
            turmasSelecionadas.add(nomeTurma);
        } else {
            System.out.println("Turma não encontrada.");
        }
    }
    aluno.matricular(turmasSelecionadas);
    salvarUsuarios();  // Salva os dados dos usuários após a matrícula
    salvarRegistroMatriculas();  // Salva o registro de matrículas no arquivo
    System.out.println("Matrícula realizada com sucesso!");
}

// Cancelar matrícula de aluno
private static void cancelarMatricula(Scanner scanner, Usuario aluno) {
    System.out.print("Nome da turma para cancelar: ");
    String nomeTurma = scanner.nextLine();
    if (turmas.containsKey(nomeTurma)) {
        aluno.cancelarMatricula(turmas.get(nomeTurma));
        System.out.println("Matrícula cancelada com sucesso!");
        salvarRegistroMatriculas();  // Salva o registro de matrículas após o cancelamento
    } else {
        System.out.println("Turma não encontrada.");
    }
}

// Método para salvar as matrículas dos alunos no arquivo
private static void salvarRegistroMatriculas() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(REGISTRO_MATRICULAS))) {
        for (Usuario aluno : usuarios.values()) {
            if (aluno.getTipo().equals("Aluno")) {
                for (Turma turma : aluno.getTurmasMatriculadas()) {
                    writer.write(aluno.getCodigo() + "," + turma.getNome() + "," + turma.getCurso());
                    writer.newLine();
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

// Modo Professor
private static void menuProfessor(Scanner scanner) {
    System.out.print("Código do Professor: ");
    String codigo = scanner.nextLine();

    if (!usuarios.containsKey(codigo) || !usuarios.get(codigo).getTipo().equals("Professor")) {
        System.out.println("Professor não encontrado!");
        return;
    }

    while (true) {
        System.out.println("\nModo Professor");
        System.out.println("1. Visualizar Minhas Turmas");
        System.out.println("2. Visualizar Alunos Matriculados");
        System.out.println("3. Voltar");
        System.out.print("Escolha uma opção: ");
        String escolha = scanner.nextLine();

        switch (escolha) {
            case "1":
                visualizarTurmasProfessor(codigo);
                break;
            case "2":
                visualizarAlunosMatriculados(scanner);
                break;
            case "3":
                return;
            default:
                System.out.println("Opção inválida.");
        }
    }
}

private static void visualizarTurmasProfessor(String codigoProfessor) {
    System.out.println("\nTurmas do Professor:");
    boolean encontrou = false;
    
    try (BufferedReader reader = new BufferedReader(new FileReader(PATH + "turmas.txt"))) {
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split(",");
            if (dados.length > 7 && dados[7].equals(codigoProfessor)) {
                System.out.println("Disciplina: " + dados[0] + " | Curso: " + dados[1]);
                encontrou = true;
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    if (!encontrou) {
        System.out.println("Nenhuma turma encontrada para este professor.");
    }
}

private static void visualizarAlunosMatriculados(Scanner scanner) {
    System.out.print("Nome da disciplina: ");
    String nomeDisciplina = scanner.nextLine();
    System.out.print("Nome do curso: ");
    String nomeCurso = scanner.nextLine();

    System.out.println("\nAlunos Matriculados:");
    boolean encontrou = false;

    try (BufferedReader reader = new BufferedReader(new FileReader(PATH + "registro_matriculas.txt"))) {
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split(",");
            if (dados.length == 3 && dados[1].equals(nomeDisciplina) && dados[2].equals(nomeCurso)) {
                String codigoAluno = dados[0];
                Usuario aluno = usuarios.get(codigoAluno);
                System.out.println("Código: " + codigoAluno + " | Aluno: " + aluno.getNome());
                encontrou = true;
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    if (!encontrou) {
        System.out.println("Nenhum aluno matriculado nesta disciplina.");
    }
}

    // Salvar turmas no arquivo
    private static void salvarTurmas() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + "turmas.txt"))) {
            for (Turma turma : turmas.values()) {
                writer.write(turma.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carregar turmas de um arquivo
    private static void carregarTurmas() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH + "turmas.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Turma turma = Turma.fromString(linha);
                turmas.put(turma.getNome(), turma);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Salvar usuários no arquivo
    private static void salvarUsuarios() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + "usuarios.txt"))) {
            for (Usuario usuario : usuarios.values()) {
                writer.write(usuario.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carregar usuários do arquivo
    private static void carregarUsuarios() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH + "usuarios.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Usuario usuario = Usuario.fromString(linha);
                usuarios.put(usuario.getCodigo(), usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Usuario {
    private String nome;
    private String tipo;
    private String codigo;
    private List<Turma> turmasMatriculadas;

    public Usuario(String nome, String tipo, String codigo) {
        this.nome = nome;
        this.tipo = tipo;
        this.codigo = codigo;
        this.turmasMatriculadas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public List<Turma> getTurmasMatriculadas() {
        return turmasMatriculadas;
    }
    

    public void matricular(List<String> turmasSelecionadas) {
        for (String turmaNome : turmasSelecionadas) {
            Turma turma = SistemaMatricula.turmas.get(turmaNome);
            if (turma != null && turma.getVagasOcupadas() < turma.getVagasMax()) {
                turmasMatriculadas.add(turma);
                turma.matricularAluno(this);
            }
        }
    }

    public void visualizarTurmas() {
        for (Turma turma : turmasMatriculadas) {
            System.out.println(turma.getNome());
        }
    }

    public void cancelarMatricula(Turma turma) {
        turmasMatriculadas.remove(turma);
        turma.cancelarMatricula(this);
    }

    public void visualizarBoleto() {
        double total = 0;
        for (Turma turma : turmasMatriculadas) {
            total += turma.getPreco();
        }
        System.out.println("Boleto total: " + total);
    }

    public static Usuario fromString(String linha) {
        String[] dados = linha.split(",");
        return new Usuario(dados[0], dados[1], dados[2]);
    }

    @Override
    public String toString() {
        return nome + "," + tipo + "," + codigo;
    }
}

class Turma {
    private String nome;
    private String curso;
    private String turno;
    private int vagasMin;
    private int vagasMax;
    private boolean obrigatoria;
    private double preco;
    private String codigoProfessor;
    private int vagasOcupadas;
    private List<Usuario> alunosMatriculados;

    public Turma(String nome, String curso, String turno, int vagasMin, int vagasMax, boolean obrigatoria, double preco, String codigoProfessor) {
        this.nome = nome;
        this.curso = curso;
        this.turno = turno;
        this.vagasMin = vagasMin;
        this.vagasMax = vagasMax;
        this.obrigatoria = obrigatoria;
        this.preco = preco;
        this.codigoProfessor = codigoProfessor;
        this.vagasOcupadas = 0;
        this.alunosMatriculados = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public int getVagasOcupadas() {
        return vagasOcupadas;
    }

    public int getVagasMax() {
        return vagasMax;
    }

    public List<Usuario> getAlunosMatriculados() {
        return alunosMatriculados;
    }
    

    public double getPreco() {
        return preco;
    }

    public String getCurso() {
        return curso;
    }
    

    public void matricularAluno(Usuario aluno) {
        if (vagasOcupadas < vagasMax) {
            alunosMatriculados.add(aluno);
            vagasOcupadas++;
        }
    }

    public void cancelarMatricula(Usuario aluno) {
        alunosMatriculados.remove(aluno);
        vagasOcupadas--;
    }

    @Override
    public String toString() {
        return nome + "," + curso + "," + turno + "," + vagasMin + "," + vagasMax + "," + obrigatoria + "," + preco + "," + codigoProfessor;
    }

    public static Turma fromString(String linha) {
        String[] dados = linha.split(",");
        Turma turma = new Turma(dados[0], dados[1], dados[2], Integer.parseInt(dados[3]), Integer.parseInt(dados[4]),
                Boolean.parseBoolean(dados[5]), Double.parseDouble(dados[6]), dados[7]);
        return turma;
    }
}
