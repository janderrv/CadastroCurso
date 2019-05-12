package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Curso extends AppCompatActivity {

    private int codigo;
    private String nome;
    private int horas;

    EditText edtNome, edtEmail, edtTelefone, edtCodigo, edtHoras, edtCurso;
    Button btnLimpar, btnSalvarCurso, btnExcluir, btnNovoCurso;
    ListView listCursos;

    BancoDados bd = new BancoDados(this);

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;



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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_curso);

        edtCodigo = findViewById(R.id.edtCodigo);
        edtHoras = findViewById(R.id.edtHoras);
        edtCurso = findViewById(R.id.edtCurso);

        btnLimpar = findViewById(R.id.btnLimpar);
        btnSalvarCurso = findViewById(R.id.btnSalvarCurso);
        btnNovoCurso = findViewById(R.id.btnNovoCurso);
        btnExcluir = findViewById(R.id.btnExcluir);

        listCursos = findViewById(R.id.listAlunos);

        listarCursos();
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpaCampos();
            }
        });
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = edtCodigo.getText().toString();

                if (codigo.isEmpty()) {
                    Toast.makeText(Curso.this, "Nenhum curso está selecionado!", Toast.LENGTH_LONG).show();
                } else {
                    Curso curso = new Curso();
                    curso.setCodigo(Integer.parseInt(codigo));
                    bd.removeCurso(curso);

                    Toast.makeText(Curso.this, "Curso excluído!", Toast.LENGTH_LONG).show();


                    limpaCampos();
                    listarCursos();
                }
            }
        });
        btnSalvarCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String codigo = edtCodigo.getText().toString();
                String curso = edtCurso.getText().toString();
                String horas = edtHoras.getText().toString();

                if (curso.isEmpty()) {
                    edtCurso.setError("Este campo é obrigatório");

                } else if (horas.isEmpty()) {
                    edtHoras.setError("Este campo é obrigatório");
                } else {
                    if (codigo.isEmpty()) {
                        //insert
                        bd.addCurso(new Curso(curso, Integer.parseInt(horas)));
                        Toast.makeText(Curso.this, "Curso adicionado!", Toast.LENGTH_LONG).show();
                        limpaCampos();
                        listarCursos();
                    } else {
                        //uṕdate
                        bd.atualizarCurso(new Curso(Integer.parseInt(codigo), curso, Integer.parseInt(horas)));
                        Toast.makeText(Curso.this, "Curso atualizado!", Toast.LENGTH_LONG).show();
                        limpaCampos();
                        listarCursos();
                    }
                }
            }
        });
        listCursos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String conteudo = (String) listCursos.getItemAtPosition(position);

                Toast.makeText(Curso.this, "Selecionado: " + conteudo, Toast.LENGTH_LONG).show();

                String codigo = conteudo.substring(0, conteudo.indexOf("-"));

                Curso curso = bd.selecionaCurso(Integer.parseInt(codigo));

                edtCodigo.setText(String.valueOf(curso.getCodigo()));
                edtCurso.setText(curso.getNome());
                edtHoras.setText(String.valueOf(curso.getHoras()));
            }
        });
    }

    public void listarCursos() {

        List<Curso> cursos = bd.listarCursos();

        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(Curso.this, android.R.layout.simple_list_item_1, arrayList);

        listCursos.setAdapter(adapter);


        for (Curso c : cursos) {
            // Log.d("Lista", "\nID: " + c.getCodigo() + " Nome: " + c.getNome() + " Horas: " +c.getHoras());
            arrayList.add(c.getCodigo() + "-" + c.getNome());
            adapter.notifyDataSetChanged();
        }
    }

    public void limpaCampos() {
        edtCodigo.setText("");
        edtCurso.setText("");
        edtHoras.setText("");

        edtCurso.requestFocus();
    }
}
