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
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Aluno extends AppCompatActivity {

    private int codigo;
    private int codigoCurso;
    private String nome;
    private String telefone;
    private String email;

    EditText edtNome, edtEmail, edtTelefone, edtCodigoAluno;
    Button btnLimpar, btnSalvarAluno, btnExcluir;
    ListView listAlunos;
    Spinner spinnerCurso;

    BancoDados bd = new BancoDados(this);

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

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
    public Aluno(String _nome, String _email, String _telefone, int _codigoCurso) {
        this.nome = _nome;
        this.email = _email;
        this.telefone = _telefone;
        this.codigoCurso = _codigoCurso;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_aluno);

        edtNome = findViewById(R.id.edtNome);
        edtEmail = findViewById(R.id.edtEmail);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtCodigoAluno = findViewById(R.id.edtCodigoAluno);

        btnLimpar = findViewById(R.id.btnLimpar);
        btnSalvarAluno = findViewById(R.id.btnSalvarAluno);
        btnExcluir = findViewById(R.id.btnExcluir);

        spinnerCurso = findViewById(R.id.spinnerCurso);
        listAlunos = findViewById(R.id.listAlunos);

        listarAlunos();
        listarCursosSpinner();
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpaCampos();
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = edtCodigoAluno.getText().toString();

                if (codigo.isEmpty()) {
                    Toast.makeText(Aluno.this, "Nenhum aluno está selecionado!", Toast.LENGTH_LONG).show();
                } else {
                    Aluno aluno = new Aluno();
                    aluno.setCodigo(Integer.parseInt(codigo));
                    bd.removeAluno(aluno);

                    Toast.makeText(Aluno.this, "Aluno excluído!", Toast.LENGTH_LONG).show();


                    limpaCampos();
                    listarAlunos();
                }
            }
        });
        btnSalvarAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String codigo = edtCodigoAluno.getText().toString();
                String nome = edtNome.getText().toString();
                String email = edtEmail.getText().toString();
                String telefone = edtTelefone.getText().toString();
                String codigoCurso;
                String curso;
                try {
                    codigoCurso = String.valueOf(bd.listarCursoSpinner().get(0).getCodigo());
                    curso = String.valueOf(bd.listarCursoSpinner().get(1));

                } catch (Exception e) {
                    codigoCurso = null;
                    curso = null;
                    Toast.makeText(Aluno.this, "Cadastre um curso!", Toast.LENGTH_LONG).show();
                }

                if (nome.isEmpty()) {
                    edtNome.setError("Este campo é obrigatório");

                } else if (email.isEmpty()) {
                    edtEmail.setError("Este campo é obrigatório");
                } else if (telefone.isEmpty()) {
                    edtTelefone.setError("Este campo é obrigatório");
                } else if (codigoCurso.isEmpty()) {
                    Toast.makeText(Aluno.this, "Selecione um curso!", Toast.LENGTH_LONG).show();
                } else {
                    if (codigo.isEmpty()) {
                        //insert
                        bd.addAluno(new Aluno(nome, email, telefone, Integer.parseInt(codigoCurso)));
                        Toast.makeText(Aluno.this, "Aluno adicionado!", Toast.LENGTH_LONG).show();
                        limpaCampos();
                        listarAlunos();
                    } else {
                        //uṕdate
                        bd.atualizarAluno(new Aluno(Integer.parseInt(codigo), nome, email, telefone, Integer.parseInt(codigoCurso)));
                        Toast.makeText(Aluno.this, "Aluno atualizado!", Toast.LENGTH_LONG).show();
                        limpaCampos();
                        listarAlunos();
                    }
                }
            }
        });


        listAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String conteudo = (String) listAlunos.getItemAtPosition(position);

                Toast.makeText(Aluno.this, "Selecionado: " + conteudo, Toast.LENGTH_LONG).show();

                String codigo = conteudo.substring(0, conteudo.indexOf("-"));

                Aluno aluno = bd.selecionaAluno(Integer.parseInt(codigo));

                edtCodigoAluno.setText(String.valueOf(aluno.getCodigo()));
                edtNome.setText(aluno.getNome());
                edtEmail.setText(String.valueOf(aluno.getEmail()));
                edtTelefone.setText(String.valueOf(aluno.getTelefone()));
            }
        });
    }

    public void listarAlunos() {

        List<Aluno> alunos = bd.listarAlunos();

        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(Aluno.this, android.R.layout.simple_list_item_1, arrayList);


        listAlunos.setAdapter(adapter);


        for (Aluno c : alunos) {
            // Log.d("Lista", "\nID: " + c.getCodigo() + " Nome: " + c.getNome() + " Horas: " +c.getHoras());
            arrayList.add(c.getCodigo() + "-" + c.getNome());
            adapter.notifyDataSetChanged();
        }
    }

    public void listarCursosSpinner() {

        List<Curso> cursos = bd.listarCursoSpinner();

        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(Aluno.this, android.R.layout.simple_spinner_item, arrayList);

        spinnerCurso.setAdapter(adapter);

        for (Curso c : cursos) {
            // Log.d("Lista", "\nID: " + c.getCodigo() + " Nome: " + c.getNome() + " Horas: " +c.getHoras());
            arrayList.add(c.getCodigo() + "-" + c.getNome());
            adapter.notifyDataSetChanged();
        }
    }

    public void limpaCampos() {
        edtCodigoAluno.setText("");
        edtNome.setText("");
        edtEmail.setText("");
        edtTelefone.setText("");

        edtNome.requestFocus();
    }

}
