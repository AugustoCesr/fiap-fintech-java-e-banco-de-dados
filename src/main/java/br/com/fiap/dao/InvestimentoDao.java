package br.com.fiap.dao;

import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Investimento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvestimentoDao {
    private static Connection conexao;

    // Bloco estático que inicializa a conexão uma única vez
    static {
        try {
            conexao = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }

    // Método para cadastrar um novo investimento no banco de dados
    public void cadastrarInvestimento(Investimento investimento) throws SQLException {
        String sql = "INSERT INTO T_FTC_INVESTIMENTOS (ID_USUARIO, ID_CONTA, DS_INVESTIMENTO, VL_INVESTIMENTO) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setLong(1, investimento.getIdUsuario());
            stm.setLong(2, investimento.getIdConta());
            stm.setString(3, investimento.getTipo());
            stm.setDouble(4, investimento.getValor());

            stm.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar investimento: " + e.getMessage());
        }
    }

    // Método para consultar todos os investimentos do banco de dados
    public List<Investimento> consultarInvestimentos() throws SQLException {
        List<Investimento> investimentos = new ArrayList<>();
        String sql = "SELECT * FROM T_FTC_INVESTIMENTOS";
        try (PreparedStatement stm = conexao.prepareStatement(sql);
             ResultSet result = stm.executeQuery()) {
            while (result.next()) {
                Investimento investimento = new Investimento();
                investimento.setIdInvestimento(result.getLong("ID_INVESTIMENTO"));
                investimento.setIdUsuario(result.getLong("ID_USUARIO"));
                investimento.setTipo(result.getString("DS_INVESTIMENTO"));
                investimento.setValor(result.getDouble("VL_INVESTIMENTO"));
                investimento.setDtInvestimento(result.getDate("DT_INVESTIMENTO"));
                investimentos.add(investimento);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao consultar investimentos: " + e.getMessage());
        }
        return investimentos;
    }

    // Método para fechar a conexão com o banco de dados
    public static void fecharConexao() throws SQLException {
        if (conexao != null && !conexao.isClosed()) {
            conexao.close();
        }
    }
}
