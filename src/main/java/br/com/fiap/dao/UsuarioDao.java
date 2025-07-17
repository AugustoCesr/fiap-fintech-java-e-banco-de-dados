package br.com.fiap.dao;

import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {
    private static Connection conexao;

    // Bloco estático que inicializa a conexão uma única vez
    static {
        try {
            conexao = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }

    // Método para cadastrar um novo usuário no banco de dados
    public void cadastrarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO T_FTC_USUARIO (NM_USUARIO, EMAIL, SENHA) VALUES (?, ?, ?)";
        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setString(1, usuario.getNome());
            stm.setString(2, usuario.getEmail());
            stm.setString(3, usuario.getSenha());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    // Método para consultar todos os usuários do banco de dados
    public List<Usuario> consultarUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM T_FTC_USUARIO";
        try (PreparedStatement stm = conexao.prepareStatement(sql);
             ResultSet result = stm.executeQuery()) {
            while (result.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(result.getLong("ID_USUARIO"));
                usuario.setNome(result.getString("NM_USUARIO"));
                usuario.setEmail(result.getString("EMAIL"));
                usuario.setSenha(result.getString("SENHA"));
                usuario.setDtCadastro(result.getDate("DT_CADASTRO"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao consultar usuários: " + e.getMessage());
        }
        return usuarios;
    }

    // Método para fechar a conexão com o banco de dados
    public static void fecharConexao() throws SQLException {
        if (conexao != null && !conexao.isClosed()) {
            conexao.close();
        }
    }
}