package com.example.myapplication;

public class Curso {

    private int codigo;
    private String nome;
    private int horas;

    //construtor de instacia
    public Curso() {

    }

    //construtor de criação
    public Curso(int _codigo, String _nome, int _horas) {
        this.horas = _horas;
        this.nome = _nome;
        this.codigo = _codigo;
    }

    //construtor de update
    public Curso(String _nome, int _horas) {
        this.horas = _horas;
        this.nome = _nome;
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

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }
}
