package com.example.reread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin, btnCadastro, btnComecar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        btnCadastro = findViewById(R.id.btnCadastro);
        btnComecar = findViewById(R.id.btnComecar);
    }

    @Override
    protected void onStart() {
        super.onStart();

        btnLogin.setOnClickListener(view -> {
            Intent login = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(login);
        });


        btnCadastro.setOnClickListener(view -> {
            Intent cadastrar = new Intent(getApplicationContext(), CadastroActivity.class);
            startActivity(cadastrar);
        });

        btnComecar.setOnClickListener(view -> {
            Intent comecar = new Intent(getApplicationContext(), ComecarQuiz.class);
            startActivity(comecar);
        });
    }

}