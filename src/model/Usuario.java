package src.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Usuario {
    private String codigo;
    private String nome;
    private String tipoUsuario;

    public Usuario(String codigo, String nome, String tipoUsuario) {
        this.codigo = codigo;
        this.nome = nome;
        this.tipoUsuario = tipoUsuario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String toString() {
        return codigo + "|" + nome + "|" + tipoUsuario;
    }

    public static Usuario buscarUsuarioPorCodigo(String codigo) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/data/Usuarios.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split("\\|");
                if (dados[0].equals(codigo)) {
                    return new Usuario(dados[0], dados[1], dados[2]);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        }
        return null;
    }
}
