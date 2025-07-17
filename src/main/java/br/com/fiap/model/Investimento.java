package br.com.fiap.model;

import java.util.Date;

public class Investimento {
    private long idInvestimento;
    private long idUsuario;
    private long idConta;
    private String tipo;
    private double valor;
    private Date dtInvestimento;

    public Investimento() {}

    public Investimento(long idUsuario, long idConta, String tipo, double valor) {
        this.idUsuario = idUsuario;
        this.idConta = idConta;
        this.tipo = tipo;
        this.valor = valor;
    }

    // Getters e Setters
    public long getIdInvestimento() {
        return idInvestimento;
    }

    public void setIdInvestimento(long idInvestimento) {
        this.idInvestimento = idInvestimento;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getIdConta() {
        return idConta;
    }

    public void setIdConta(long idConta) {
        this.idConta = idConta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDtInvestimento() {
        return dtInvestimento;
    }

    public void setDtInvestimento(Date dtInvestimento) {
        this.dtInvestimento = dtInvestimento;
    }
}
