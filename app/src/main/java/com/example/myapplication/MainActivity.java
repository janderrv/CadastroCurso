package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtNome, edtEmail, edtTelefone, edtCodigo, edtHoras;
    Button btnLimpar, btnSalvar, btnExcluir, btnCadastrarCurso;
    ListView listCursos;

    BancoDados bd = new BancoDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNome = findViewById(R.id.edtNome);
        edtEmail = findViewById(R.id.edtEmail);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtCodigo = findViewById(R.id.edtCodigo);
        edtHoras = findViewById(R.id.edtHoras);

        btnLimpar = findViewById(R.id.btnLimpar);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnCadastrarCurso = findViewById(R.id.btnCadastrarCurso);

        listCursos = findViewById(R.id.listCursos);


        //teste do crud
        bd.addCurso(new Curso("TESTE", 100));
        bd.addAluno(new Aluno("Jander", "xx", "67999999"));

        Toast.makeText(MainActivity.this, "Salvo com sucesso!", Toast.LENGTH_LONG).show();

        Curso curso = new Curso();
        curso.setCodigo(1);
        bd.removeCurso(curso);


    }
}
