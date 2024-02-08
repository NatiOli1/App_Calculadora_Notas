package com.example.calculadoranotas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener {

    private EditText editTextUsuario;
    private CheckBox checkBoxLogado;
    private Button buttonEntrar;

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("arquivo_conf", Context.MODE_PRIVATE);

        editTextUsuario = findViewById(R.id.editTextUsuario);
        checkBoxLogado = findViewById(R.id.checkBoxLogado);
        buttonEntrar = findViewById(R.id.buttonEntrar);

        buttonEntrar.setOnClickListener(this);
        verificarUsuarioLogado();;

    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonEntrar){
            if (editTextUsuario.getText().toString().isEmpty()){

            }else {
                if (this.checkBoxLogado.isChecked()){
                    salvarUsuarioLogado();
                }
                abrirTela();
            }

    }}

    private void salvarUsuarioLogado() {
        String nomeUsuario = editTextUsuario.getText().toString();

        SharedPreferences.Editor editor = sp.edit();
        editor.putString("usuario", nomeUsuario);
        editor.putBoolean("logado", true);
        editor.apply();
        }

    private void verificarUsuarioLogado(){
        boolean logado = sp.getBoolean("logado", false);
        if (logado){
            abrirTela();
        }
    }
    private void abrirTela(){
        Intent telaHome = new Intent(this,HomeActivity.class);
        startActivity(telaHome);
        finish();
    }


}