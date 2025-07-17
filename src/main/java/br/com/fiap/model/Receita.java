package br.com.fiap.model;

import java.util.Date;

public class Receita {
    private long idReceita;
    private long idUsuario;
    private long idConta;
    private String descricao;
    private double valor;
    private Date dtReceita;

    public Receita() {}

    public Receita(long idUsuario, long idConta, String descricao, double valor) {
        this.idUsuario = idUsuario;
        this.idConta = idConta;
        this.descricao = descricao;
        this.valor = valor;
    }

    // Getters e Setters
    public long getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(long idReceita) {
        this.idReceita = idReceita;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDtReceita() {
        return dtReceita;
    }

    public void setDtReceita(Date dtReceita) {
        this.dtReceita = dtReceita;
    }
}
