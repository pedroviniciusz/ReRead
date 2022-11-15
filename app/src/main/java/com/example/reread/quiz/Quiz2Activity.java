package com.example.reread.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.reread.R;
import com.example.reread.enums.Categoria;


public class Quiz2Activity extends BaseQuiz implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup rg1, rg2, rg3, rg4;

    private Button btnAnterior, btnProximo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2);

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

        recuperarSoma();
    }

    private void recuperarSoma(){
        setPontosProducaoEnsino(getIntent().getIntExtra("pontosProducaoEnsino", 0));
        setPontosTecnologias(getIntent().getIntExtra("pontosTecnologias", 0));
        setPontosBemEstar(getIntent().getIntExtra("pontosBemEstar", 0));
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
            somarPontos(atribuirValorReposta(resposta), Categoria.CINEMA_CULTURA);
        }else if(radioGroup == rg3){
            RadioButton button = radioGroup.findViewById(checkedId);
            String resposta = button.getText().toString();
            somarPontos(atribuirValorReposta(resposta), Categoria.DEFICIENCIAS);
        }else{
            RadioButton button = radioGroup.findViewById(checkedId);
            String resposta = button.getText().toString();
            somarPontos(atribuirValorReposta(resposta), Categoria.LINGUAS_ESTRANGEIRAS);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        btnAnterior.setOnClickListener(view -> {
            Intent quiz = new Intent(getApplicationContext(), QuizActivity.class);
            startActivity(quiz);
        });


        btnProximo.setOnClickListener(view -> {
            Intent quiz3 = new Intent(getApplicationContext(), Quiz3Activity.class);
            quiz3.putExtra("pontosProducaoEnsino", getPontosProducaoEnsino());
            quiz3.putExtra("pontosTecnologias", getPontosTecnologias());
            quiz3.putExtra("pontosBemEstar", getPontosBemEstar());
            quiz3.putExtra("pontosCinemaCultura", getPontosCinemaCultura());
            quiz3.putExtra("pontosDeficiencias", getPontosDeficiencias());
            quiz3.putExtra("pontosLinguasEstrangeiras", getPontosLinguasEstrangeiras());
            startActivity(quiz3);
        });
    }
}
