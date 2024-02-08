package com.example.calculadoranotas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements  View.OnClickListener
{
    private EditText editTextNomeAluno;
    private EditText editTextNomeDisciplina;
    private EditText editTextAtividade1;
    private EditText editTextAtividade2;
    private EditText editTextNotaProva;
    private EditText editTextFaltas;
    private TextView editViewTitulo;

    private Button buttonAvaliar;
    private Button buttonLimpar;
    private Button buttonDeslogar;
    private SharedPreferences sp;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        editTextNomeAluno = findViewById(R.id.editTextNomeAluno);
        editTextNomeDisciplina= findViewById(R.id.editTextNomeDisciplina);
        editTextAtividade1= findViewById(R.id.editTextAtividade1);
        editTextAtividade2 = findViewById(R.id.editTextAtividade2);
        editTextNotaProva = findViewById(R.id.editTextNotaProva);
        editTextFaltas = findViewById(R.id.editTextFaltas);
        editViewTitulo = findViewById(R.id.textViewTitulo);

        buttonAvaliar = findViewById(R.id.buttonAvaliar);
        buttonAvaliar.setOnClickListener(this);
        buttonLimpar = findViewById(R.id.buttonLimpar);
        buttonLimpar.setOnClickListener(this);
        buttonDeslogar = findViewById(R.id.buttonDeslogar);
        buttonDeslogar.setOnClickListener(this);

        sp = getSharedPreferences("arquivo_conf", Context.MODE_PRIVATE);

        alterarTitulo();

    }




    private void avaliarAluno(){
        String nome = editTextNomeAluno.getText().toString();
        String nomeDisciplina = editTextNomeDisciplina.getText().toString();
        double nota1 = Double.parseDouble(editTextAtividade1.getText().toString());
        double nota2 = Double.parseDouble(editTextAtividade2.getText().toString());
        double prova = Double.parseDouble(editTextNotaProva.getText().toString());
        int faltas = Integer.parseInt(editTextFaltas.getText().toString());

        Alunos alunos = new Alunos(nome, nomeDisciplina, nota1,nota2,prova,faltas);
        alunos.calcularMedia();
        boolean aprovado = alunos.verificarAprovacao();

        String mensagem = "";
        if (aprovado){
            mensagem = "Parabens querido aluno você está aprovado";
        }else {
            mensagem = "Caro aluno, você está reprovado";
        }
        Toast.makeText(this,mensagem,Toast.LENGTH_LONG).show();
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonAvaliar){
            this.avaliarAluno();
        }
        else if (view.getId() == R.id.buttonLimpar){
            this.limpar();
        }else if (view.getId() == R.id.buttonDeslogar){
            this.deslogar();
        }
    }

    private void deslogar(){
        deleteSharedPreferences("arquivo_conf");
        finish();
    }

    private  void alterarTitulo() {
        String nomeUsuario = sp.getString("usuario", "");
        String titulo  = "Calculadora de Notas - " + nomeUsuario;
        editViewTitulo.setText(titulo);
    }


    private void limpar(){
        editTextNomeAluno.setText("");
        editTextNomeDisciplina.setText("");
        editTextAtividade1.setText("");
        editTextAtividade2.setText("");
        editTextNotaProva.setText("");
        editTextFaltas.setText("");
    }

    public void setEditTextNomeAluno(EditText editTextNomeAluno) {

    }
}