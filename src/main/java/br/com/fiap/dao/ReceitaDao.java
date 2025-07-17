package br.com.fiap.dao;

import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Receita;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceitaDao {
    private static Connection conexao;

    // Bloco estático que inicializa a conexão uma única vez
    static {
        try {
            conexao = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }

    // Método para cadastrar uma nova receita no banco de dados
    public void cadastrarReceita(Receita receita) throws SQLException {
        String sql = "INSERT INTO T_FTC_RECEITAS (ID_USUARIO, ID_CONTA, DS_RECEITA, VL_RECEITA) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setLong(1, receita.getIdUsuario());
            stm.setLong(2, receita.getIdConta());
            stm.setString(3, receita.getDescricao());
            stm.setDouble(4, receita.getValor());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar receita: " + e.getMessage());
        }
    }

    // Método para consultar todas as receitas do banco de dados
    public List<Receita> consultarReceitas() throws SQLException {
        List<Receita> receitas = new ArrayList<>();
        String sql = "SELECT * FROM T_FTC_RECEITAS";
        try (PreparedStatement stm = conexao.prepareStatement(sql);
             ResultSet result = stm.executeQuery()) {
            while (result.next()) {
                Receita receita = new Receita();
                receita.setIdReceita(result.getLong("ID_RECEITA"));
                receita.setIdUsuario(result.getLong("ID_USUARIO"));
                receita.setIdConta(result.getLong("ID_CONTA"));
                receita.setDescricao(result.getString("DS_RECEITA"));
                receita.setValor(result.getDouble("VL_RECEITA"));
                receita.setDtReceita(result.getDate("DT_RECEITA"));
                receitas.add(receita);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao consultar receitas: " + e.getMessage());
        }
        return receitas;
    }

    // Método para fechar a conexão com o banco de dados
    public static void fecharConexao() throws SQLException {
        if (conexao != null && !conexao.isClosed()) {
            conexao.close();
        }
    }
}
