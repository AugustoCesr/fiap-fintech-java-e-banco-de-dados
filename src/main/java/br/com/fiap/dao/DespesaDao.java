package br.com.fiap.dao;

import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Despesa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DespesaDao {
    private static Connection conexao;

    // Bloco estático que inicializa a conexão uma única vez
    static {
        try {
            conexao = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }

    // Método para cadastrar uma nova despesa no banco de dados
    public void cadastrarDespesa(Despesa despesa) throws SQLException {
        String sql = "INSERT INTO T_FTC_DESPESAS (ID_USUARIO, ID_CONTA, DS_DESPESA, VL_DESPESA) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setLong(1, despesa.getIdUsuario());
            stm.setLong(2, despesa.getIdConta());
            stm.setString(3, despesa.getDescricao());
            stm.setDouble(4, despesa.getValor());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar despesa: " + e.getMessage());
        }
    }

    // Método para consultar todas as despesas do banco de dados
    public List<Despesa> consultarDespesas() throws SQLException {
        List<Despesa> despesas = new ArrayList<>();
        String sql = "SELECT * FROM T_FTC_DESPESAS";
        try (PreparedStatement stm = conexao.prepareStatement(sql);
             ResultSet result = stm.executeQuery()) {
            while (result.next()) {
                Despesa despesa = new Despesa();
                despesa.setIdDespesa(result.getLong("ID_DESPESA"));
                despesa.setIdUsuario(result.getLong("ID_USUARIO"));
                despesa.setIdConta(result.getLong("ID_CONTA"));
                despesa.setDescricao(result.getString("DS_DESPESA"));
                despesa.setValor(result.getDouble("VL_DESPESA"));
                despesa.setDtDespesa(result.getDate("DT_DESPESA"));
                despesas.add(despesa);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao consultar despesas: " + e.getMessage());
        }
        return despesas;
    }

    // Método para fechar a conexão com o banco de dados
    public static void fecharConexao() throws SQLException {
        if (conexao != null && !conexao.isClosed()) {
            conexao.close();
        }
    }
}
