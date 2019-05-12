package com.example.myapplication;

public class Aluno {

    private int codigo;
    private int codigoCurso;
    private String nome;
    private String telefone;
    private String email;

    //construtor vazio para instanciar
    public Aluno() {

    }

    //construtor de criação
    public Aluno(int _codigo, String _nome, String _email, String _telefone, int _codigoCurso) {
        this.codigo = _codigo;
        this.nome = _nome;
        this.email = _email;
        this.telefone = _telefone;
        this.codigoCurso = _codigoCurso;
    }

    //construtor de update
    public Aluno(String _nome, String _email, String _telefone) {
        this.nome = _nome;
        this.email = _email;
        this.telefone = _telefone;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(int codigoCurso) {
        this.codigoCurso = codigoCurso;
    }
}
