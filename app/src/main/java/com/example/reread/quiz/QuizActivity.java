package com.example.reread.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.reread.R;
import com.example.reread.enums.Categoria;

public class QuizActivity extends BaseQuiz implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup rg1, rg2, rg3, rg4;

    private Button btnAnterior, btnProximo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        rg1 = findViewById(R.id.radioGroup);
        rg2 = findViewById(R.id.radioGroup2);
        rg3 = findViewById(R.id.radioGroup3);
        rg4 = findViewById(R.id.radioGroup4);

        rg1.setOnCheckedChangeListener(this);
        rg2.setOnCheckedChangeListener(this);
        rg3.setOnCheckedChangeListener(this);
        rg4.setOnCheckedChangeListener(this);

        btnAnterior = findViewById(R.id.btnAnterior);
        btnProximo = findViewById(R.id.btnProximo);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

        if (radioGroup == rg1){
            RadioButton button = radioGroup.findViewById(checkedId);
            String resposta = button.getText().toString();
            somarPontos(atribuirValorReposta(resposta), Categoria.PRODUCAO_ENSINO);
        }else if(radioGroup == rg2){
            RadioButton button = radioGroup.findViewById(checkedId);
            String resposta = button.getText().toString();
            somarPontos(atribuirValorReposta(resposta), Categoria.BEM_ESTAR);
        }else if(radioGroup == rg3){
            RadioButton button = radioGroup.findViewById(checkedId);
            String resposta = button.getText().toString();
            somarPontos(atribuirValorReposta(resposta), Categoria.TECNOLOGIAS);
        }else{
            RadioButton button = radioGroup.findViewById(checkedId);
            String resposta = button.getText().toString();
            somarPontos(atribuirValorReposta(resposta), Categoria.TECNOLOGIAS);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        btnAnterior.setOnClickListener(view -> {
            Intent comecarQuiz = new Intent(getApplicationContext(), ComecarQuiz.class);
            startActivity(comecarQuiz);
        });


        btnProximo.setOnClickListener(view -> {
            Intent quiz2 = new Intent(getApplicationContext(), Quiz2Activity.class);
            quiz2.putExtra("pontosProducaoEnsino", getPontosProducaoEnsino());
            quiz2.putExtra("pontosTecnologias", getPontosTecnologias());
            quiz2.putExtra("pontosBemEstar", getPontosBemEstar());
            startActivity(quiz2);
        });
    }
}