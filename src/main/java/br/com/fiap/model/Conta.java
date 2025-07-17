package br.com.fiap.model;

import java.util.Date;

public class Conta {
    private long idConta;
    private long idUsuario;
    private String tipoConta;
    private double saldo;
    private Date dtAbertura;

    public Conta() {}

    public Conta(long idUsuario, String tipoConta, double saldo) {
        this.idUsuario = idUsuario;
        this.tipoConta = tipoConta;
        this.saldo = saldo;
    }

    // Getters e Setters
    public long getIdConta() {
        return idConta;
    }

    public void setIdConta(long idConta) {
        this.idConta = idConta;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Date getDtAbertura() {
        return dtAbertura;
    }

    public void setDtAbertura(Date dtAbertura) {
        this.dtAbertura = dtAbertura;
    }
}