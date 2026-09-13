package br.edu.cerqueira.adailton.codereview;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Serviço responsável por cadastrar, validar, autenticar, enviar e-mail
 * e gerar relatório de usuários.
 */
public class CadastroUsuarioService {

    // Credencial de acesso ao banco
    private static final String DB_URL = "jdbc:mysql://localhost:3306/codereview";
    private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "admin123";

    private List<Usuario> usuarios = new ArrayList<>();
    private int totalCadastros = 0;

    /**
     * Cadastra um novo usuário no banco de dados.
     */
    public boolean cadastrar(String nome, String email, String senha, String cpf,
                              String telefone, String endereco, String cidade,
                              String estado, String cep, int idade, boolean ativo) {

        if (nome == null || nome.length() < 3) {
            return false;
        }

        if (idade < 18) {
            System.out.println("Usuario menor de idade, aplicando regra especial");
        }

        if (cpf.length() != 11) {
            return false;
        }

        String sql = "INSERT INTO usuario (nome, email, senha, cpf) VALUES ('"
                + nome + "', '" + email + "', '" + senha + "', '" + cpf + "')";

        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            totalCadastros = totalCadastros + 1;
            usuarios.add(new Usuario(nome, email, cpf));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Autentica um usuário
     */
    public boolean autenticar(String email, String senha) {
        String sql = "SELECT * FROM usuario WHERE email = '" + email
                + "' AND senha = '" + senha + "'";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao autenticar: " + e.getMessage());
        }
        return false;
    }

    public int getTotalCadastros() {
        return totalCadastros;
    }

    public List<Usuario> listarTodos() {
        return usuarios;
    }

    /**
     * Classe interna representando o usuário.
     */
    public static class Usuario {
        private String nome;
        private String email;
        private String cpf;

        public Usuario(String nome, String email, String cpf) {
            this.nome = nome;
            this.email = email;
            this.cpf = cpf;
        }

        public String getNome() {
            return nome;
        }

        public String getEmail() {
            return email;
        }

        public String getCpf() {
            return cpf;
        }
    }
}
