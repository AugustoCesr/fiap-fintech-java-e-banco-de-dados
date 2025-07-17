package br.com.fiap.dao;

import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Conta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContaDao {
    private static Connection conexao;

    // Bloco estático que inicializa a conexão uma única vez
    static {
        try {
            conexao = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }

    // Método para cadastrar uma nova conta no banco de dados
    public void cadastrarConta(Conta conta) throws SQLException {
        String sql = "INSERT INTO T_FTC_CONTA (ID_USUARIO, TP_CONTA, VL_SALDO) VALUES (?, ?, ?)";
        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setLong(1, conta.getIdUsuario());
            stm.setString(2, conta.getTipoConta());
            stm.setDouble(3, conta.getSaldo());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar conta: " + e.getMessage());
        }
    }

    // Método para consultar todas as contas do banco de dados
    public List<Conta> consultarContas() throws SQLException {
        List<Conta> contas = new ArrayList<>();
        String sql = "SELECT * FROM T_FTC_CONTA";
        try (PreparedStatement stm = conexao.prepareStatement(sql);
             ResultSet result = stm.executeQuery()) {
            while (result.next()) {
                Conta conta = new Conta();
                conta.setIdConta(result.getLong("ID_CONTA"));
                conta.setIdUsuario(result.getLong("ID_USUARIO"));
                conta.setTipoConta(result.getString("TP_CONTA"));
                conta.setSaldo(result.getDouble("VL_SALDO"));
                conta.setDtAbertura(result.getDate("DT_ABERTURA"));
                contas.add(conta);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao consultar contas: " + e.getMessage());
        }
        return contas;
    }

    // Método para fechar a conexão (opcional, se quiser fechar ao final da aplicação)
    public static void fecharConexao() throws SQLException {
        if (conexao != null && !conexao.isClosed()) {
            conexao.close();
        }
    }
}
