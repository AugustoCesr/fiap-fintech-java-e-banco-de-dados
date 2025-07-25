package br.com.fiap.model;

public class Usuario {
    private long idUsuario;
    private String nome;
    private String email;
    private String senha;
    private java.util.Date dtCadastro;

    public Usuario() {}

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public java.util.Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(java.util.Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }
}