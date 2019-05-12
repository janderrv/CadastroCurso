package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoDados extends SQLiteOpenHelper {

    private static final int VERSAO_BANCO = 1;
    private static final String NOME_BANCO = "bd_cadastro";

    private static final String TABELA_ALUNO = "tb_aluno";
    private static final String COLUNA_ALUNO_ID = "alunoid";
    private static final String COLUNA_NOME_ALUNO = "nome_aluno";
    private static final String COLUNA_EMAIL = "email";
    private static final String COLUNA_TELEFONE = "telefone";
    private static final String COLUNA_CURSO_FK = "curso_aluno";


    private static final String TABELA_CURSO = "tb_curso";
    private static final String COLUNA_CURSO_ID = "cursoid";
    private static final String COLUNA_NOME_CURSO = "nome_curso";
    private static final String COLUNA_HORAS = "horas";


    public BancoDados(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //cria tabela curso

        String QUERY_COLUNA = "CREATE TABLE " + TABELA_CURSO + "("
                + COLUNA_CURSO_ID + " INTEGER PRIMARY KEY, " + COLUNA_NOME_CURSO + " TEXT,"
                + COLUNA_HORAS + " INTEGER)";

        db.execSQL(QUERY_COLUNA);

        // //cria tabela aluno
        /*
       QUERY_COLUNA = "CREATE TABLE " + TABELA_ALUNO + "("
                + TABELA_ALUNO + " INTEGER PRIMARY KEY, " + COLUNA_NOME_ALUNO + " TEXT,"
                + COLUNA_EMAIL + " TEXT,"
                + COLUNA_TELEFONE + " TEXT," + COLUNA_EMAIL + " TEXT,"
                + COLUNA_CURSO_FK + " INTEGER REFERENCES " + TABELA_CURSO + ",")";
        */
        QUERY_COLUNA = "CREATE TABLE " + TABELA_ALUNO +
                "(" +
                COLUNA_ALUNO_ID + " INTEGER PRIMARY KEY," + // Define a primary key
                COLUNA_CURSO_FK + " INTEGER REFERENCES " + TABELA_CURSO + "," + // Define a foreign key
                COLUNA_NOME_ALUNO + " TEXT," + COLUNA_TELEFONE + " TEXT," + COLUNA_EMAIL + " TEXT" +
                ")";

        db.execSQL(QUERY_COLUNA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    // CRUD ABAIXO
    void addCurso(Curso curso) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME_CURSO, curso.getNome());
        values.put(COLUNA_HORAS, curso.getHoras());

        db.insert(TABELA_CURSO, null, values);
        db.close();

    }

    void removeCurso(Curso curso) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_CURSO, COLUNA_CURSO_ID + " = ?", new String[]{String.valueOf(curso.getCodigo())});
        db.close();
    }

    void addAluno(Aluno aluno) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME_ALUNO, aluno.getNome());
        values.put(COLUNA_CURSO_FK, aluno.getCodigoCurso());
        values.put(COLUNA_TELEFONE, aluno.getTelefone());
        values.put(COLUNA_EMAIL, aluno.getEmail());

        db.insert(TABELA_ALUNO, null, values);
        db.close();

    }
}
