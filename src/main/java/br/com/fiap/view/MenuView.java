package br.com.fiap.view;

import br.com.fiap.dao.*;
import br.com.fiap.model.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuView {
    private static final Scanner scanner = new Scanner(System.in);
    private static UsuarioDao usuarioDao;
    private static ContaDao contaDao;
    private static ReceitaDao receitaDao;
    private static DespesaDao despesaDao;
    private static InvestimentoDao investimentoDao;

    public static void main(String[] args) {
        try {
            usuarioDao = new UsuarioDao();
            contaDao = new ContaDao();
            receitaDao = new ReceitaDao();
            despesaDao = new DespesaDao();
            investimentoDao = new InvestimentoDao();

            System.out.println("Bem-vindo ao Sistema de Gerenciamento Financeiro!");

            int escolha;
            do {
                exibirMenu();
                escolha = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha

                switch (escolha) {
                    case 1:
                        cadastrarUsuario();
                        break;
                    case 2:
                        consultarUsuarios();
                        break;
                    case 3:
                        cadastrarConta();
                        break;
                    case 4:
                        consultarContas();
                        break;
                    case 5:
                        cadastrarReceita();
                        break;
                    case 6:
                        consultarReceitas();
                        break;
                    case 7:
                        cadastrarDespesa();
                        break;
                    case 8:
                        consultarDespesas();
                        break;
                    case 9:
                        cadastrarInvestimento();
                        break;
                    case 10:
                        consultarInvestimentos();
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } while (escolha != 0);
        } finally {
            fecharConexao();
        }
    }

    private static void exibirMenu() {
        System.out.println("\nEscolha uma opção: " +
                "\n1. Cadastrar Usuário" +
                "\n2. Consultar Usuários" +
                "\n3. Cadastrar Conta" +
                "\n4. Consultar Contas" +
                "\n5. Cadastrar Receita" +
                "\n6. Consultar Receitas" +
                "\n7. Cadastrar Despesa" +
                "\n8. Consultar Despesas" +
                "\n9. Cadastrar Investimento" +
                "\n10. Consultar Investimentos" +
                "\n0. Sair");
    }

    private static void cadastrarUsuario() {
        Usuario usuario = new Usuario(
                lerString("Nome: "),
                lerString("Email: "),
                lerString("Senha: ")
        );
        try {
            usuarioDao.cadastrarUsuario(usuario);
            System.out.println("\nUsuário cadastrado com sucesso!");
        } catch (SQLException e) {
            tratarErro("Erro ao cadastrar usuário", e);
        }
    }

    private static void consultarUsuarios() {
        try {
            List<Usuario> usuarios = usuarioDao.consultarUsuarios();
            usuarios.forEach(usuario -> System.out.println("ID: " + usuario.getIdUsuario() + ", Nome: " + usuario.getNome() + ", Email: " + usuario.getEmail()));
        } catch (SQLException e) {
            tratarErro("Erro ao consultar usuários", e);
        }
    }

    private static void cadastrarConta() {
        long idUsuario = lerLong("ID do Usuário: ");
        String tipoConta;
        do {
            tipoConta = lerString("Tipo de Conta (CORRENTE, POUPANÇA): ").toUpperCase();
            if (!tipoConta.equals("CORRENTE") && !tipoConta.equals("POUPANÇA")) {
                System.out.println("Tipo de conta inválido. Tente novamente.");
            }
        } while (!tipoConta.equals("CORRENTE") && !tipoConta.equals("POUPANÇA"));

        double saldo = lerSaldo();
        Conta conta = new Conta(idUsuario, tipoConta, saldo);
        try {
            contaDao.cadastrarConta(conta);
            System.out.println("\nConta cadastrada com sucesso!");
        } catch (SQLException e) {
            tratarErro("Erro ao cadastrar conta", e);
        }
    }

    private static void consultarContas() {
        try {
            List<Conta> contas = contaDao.consultarContas();
            contas.forEach(conta -> System.out.println("ID Conta: " + conta.getIdConta() + ", ID Usuário: " + conta.getIdUsuario() + ", Tipo: " + conta.getTipoConta() + ", Saldo: " + conta.getSaldo()));
        } catch (SQLException e) {
            tratarErro("Erro ao consultar contas", e);
        }
    }

    private static void cadastrarReceita() {
        long idUsuario = lerLong("ID do Usuário: ");
        long idConta = lerLong("ID da Conta: ");
        String descricao = lerString("Descrição: ");
        double valor = lerValor("Valor (não pode ser negativo): ");
        Receita receita = new Receita(idUsuario, idConta, descricao, valor);
        try {
            receitaDao.cadastrarReceita(receita);
            System.out.println("\nReceita cadastrada com sucesso!");
        } catch (SQLException e) {
            tratarErro("Erro ao cadastrar receita", e);
        }
    }

    private static void consultarReceitas() {
        try {
            List<Receita> receitas = receitaDao.consultarReceitas();
            receitas.forEach(receita -> System.out.println("ID Receita: " + receita.getIdReceita() + ", Descrição: " + receita.getDescricao() + ", Valor: " + receita.getValor()));
        } catch (SQLException e) {
            tratarErro("Erro ao consultar receitas", e);
        }
    }

    private static void cadastrarDespesa() {
        long idUsuario = lerLong("ID do Usuário: ");
        long idConta = lerLong("ID da Conta: ");
        String descricao = lerString("Descrição: ");
        double valor = lerValor("Valor (não pode ser negativo): ");
        Despesa despesa = new Despesa(idUsuario, idConta, descricao, valor);
        try {
            despesaDao.cadastrarDespesa(despesa);
            System.out.println("\nDespesa cadastrada com sucesso!");
        } catch (SQLException e) {
            tratarErro("Erro ao cadastrar despesa", e);
        }
    }

    private static void consultarDespesas() {
        try {
            List<Despesa> despesas = despesaDao.consultarDespesas();
            despesas.forEach(despesa -> System.out.println("ID Despesa: " + despesa.getIdDespesa() + ", Descrição: " + despesa.getDescricao() + ", Valor: " + despesa.getValor()));
        } catch (SQLException e) {
            tratarErro("Erro ao consultar despesas", e);
        }
    }

    private static void cadastrarInvestimento() {
        long idUsuario = lerLong("ID do Usuário: ");
        long idConta = lerLong("ID da Conta: ");
        String tipo = lerString("Tipo de Investimento: ");
        double valor = lerValor("Valor (não pode ser negativo): ");

        Investimento investimento = new Investimento(idUsuario, idConta, tipo, valor);

        try {
            investimentoDao.cadastrarInvestimento(investimento);
            System.out.println("\nInvestimento cadastrado com sucesso!");
        } catch (SQLException e) {
            tratarErro("Erro ao cadastrar investimento", e);
        }
    }

    private static void consultarInvestimentos() {
        try {
            List<Investimento> investimentos = investimentoDao.consultarInvestimentos();
            investimentos.forEach(investimento -> System.out.println("ID Investimento: " + investimento.getIdInvestimento() + ", Tipo: " + investimento.getTipo() + ", Valor: " + investimento.getValor()));
        } catch (SQLException e) {
            tratarErro("Erro ao consultar investimentos", e);
        }
    }

    private static String lerString(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    private static long lerLong(String mensagem) {
        System.out.print(mensagem);
        long valor = scanner.nextLong();
        scanner.nextLine();  // Consumir o caractere de nova linha
        return valor;
    }

    private static double lerSaldo() {
        double saldo;
        do {
            saldo = lerValor("Saldo (não pode ser negativo): ");
        } while (saldo < 0);
        return saldo;
    }

    private static double lerValor(String mensagem) {
        System.out.print(mensagem);
        double valor = scanner.nextDouble();
        scanner.nextLine();  // Consumir o caractere de nova linha
        return valor;
    }

    private static void tratarErro(String mensagem, SQLException e) {
        System.err.println(mensagem + ": " + e.getMessage());
    }

    private static void fecharConexao() {
        try {
            if (usuarioDao != null) UsuarioDao.fecharConexao();
            if (contaDao != null) ContaDao.fecharConexao();
            if (receitaDao != null) ReceitaDao.fecharConexao();
            if (despesaDao != null) DespesaDao.fecharConexao();
            if (investimentoDao != null) InvestimentoDao.fecharConexao();
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }
}
