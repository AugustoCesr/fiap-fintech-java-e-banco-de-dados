package br.com.fiap.model;

import java.util.Date;

public class Despesa {
    private long idDespesa;
    private long idUsuario;
    private long idConta;
    private String descricao;
    private double valor;
    private Date dtDespesa;

    public Despesa() {}

    public Despesa(long idUsuario, long idConta, String descricao, double valor) {
        this.idUsuario = idUsuario;
        this.idConta = idConta;
        this.descricao = descricao;
        this.valor = valor;
    }

    // Getters e Setters
    public long getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(long idDespesa) {
        this.idDespesa = idDespesa;
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

    public Date getDtDespesa() {
        return dtDespesa;
    }

    public void setDtDespesa(Date dtDespesa) {
        this.dtDespesa = dtDespesa;
    }
}
