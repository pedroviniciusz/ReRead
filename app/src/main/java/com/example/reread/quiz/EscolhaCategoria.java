package com.example.reread.quiz;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reread.R;

import java.util.ArrayList;

public class EscolhaCategoria extends AppCompatActivity {

    private Button btnCategoria1, btnCategoria2, btnCategoria3;

    private ArrayList<String> categoriasMaisVotadas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolher_categoria);

        btnCategoria1 = findViewById(R.id.btnCategoria1);
        btnCategoria2 = findViewById(R.id.btnCategoria2);
        btnCategoria3 = findViewById(R.id.btnCategoria3);

        recuperarCategoriasMaisVotadas();

        btnCategoria1.setText(categoriasMaisVotadas.get(0));
        btnCategoria2.setText(categoriasMaisVotadas.get(1));
        btnCategoria3.setText(categoriasMaisVotadas.get(2));
    }

    private void recuperarCategoriasMaisVotadas(){
        categoriasMaisVotadas = getIntent().getStringArrayListExtra("categoriasMaisVotadas");
    }
}
