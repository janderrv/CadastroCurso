package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        /*
        //teste do crud
        bd.addCurso(new Curso("TESTE", 100));
        bd.addAluno(new Aluno("Jander", "xx", "67999999"));

        Toast.makeText(MainActivity.this, "Salvo com sucesso!", Toast.LENGTH_LONG).show();

        Curso curso = new Curso();
        curso.setCodigo(1);
        bd.removeCurso(curso);
        */

        // Curso curso = bd.selecionaCurso(3);

        // Log.d("Curso selecionado", "Codigo: " + curso.getCodigo() + " Nome: " + curso.getNome() + " Horas: " + curso.getHoras());

       /* Curso curso = new Curso();
        curso.setCodigo(3);
        curso.setNome("Engenharia de software");
        curso.setHoras(80000);
        bd.atualizarCurso(curso);

        Toast.makeText(MainActivity.this, "Atualizado com sucesso!", Toast.LENGTH_LONG).show();
        */
    }

    public void abrirTelaCurso(View view) {
        Intent intent = new Intent(this, Curso.class);
        startActivity(intent);
    }

    public void abrirTelaAluno(View view) {
        Intent intent = new Intent(this, Aluno.class);
        startActivity(intent);
    }

    public void abrirTelaPesquisarAlunos(View view) {
        Intent intent = new Intent(this, PesquisarAlunos.class);
        startActivity(intent);
    }


}
