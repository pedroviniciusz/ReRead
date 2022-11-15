package com.example.reread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ComecarQuiz extends AppCompatActivity {

    private Button btnAnterior, btnProximo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comecar_quiz);

        btnAnterior = findViewById(R.id.btnAnterior);
        btnProximo = findViewById(R.id.btnProximo);
    }

    @Override
    protected void onStart() {
        super.onStart();

        btnAnterior.setOnClickListener(view -> {
            Intent cadastro = new Intent(getApplicationContext(), CadastroActivity.class);
            startActivity(cadastro);
        });

        btnProximo.setOnClickListener(view -> {
            Intent quiz = new Intent(getApplicationContext(), QuizActivity.class);
            startActivity(quiz);
        });
    }
}