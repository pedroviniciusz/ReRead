package com.example.reread.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reread.R;
import com.example.reread.enums.Categoria;

import java.math.BigDecimal;

public class Quiz3Activity extends BaseQuiz implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup rg1, rg2, rg3, rg4;

    private Button btnAnterior, btnProximo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz3);

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
        setPontosCinemaCultura(getIntent().getIntExtra("pontosCinemaCultura", 0));
        setPontosDeficiencias(getIntent().getIntExtra("pontosDeficiencias", 0));
        setPontosLinguasEstrangeiras(getIntent().getIntExtra("pontosLinguasEstrangeiras", 0));
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

        if (radioGroup == rg1) {
            RadioButton button = radioGroup.findViewById(checkedId);
            String resposta = button.getText().toString();
            somarPontos(atribuirValorReposta(resposta), Categoria.CINEMA_CULTURA);
        } else if (radioGroup == rg2) {
            RadioButton button = radioGroup.findViewById(checkedId);
            String resposta = button.getText().toString();
            somarPontos(atribuirValorReposta(resposta), Categoria.LEITURA_ALFABETIZACAO);
        } else if (radioGroup == rg3) {
            RadioButton button = radioGroup.findViewById(checkedId);
            String resposta = button.getText().toString();
            somarPontos(atribuirValorReposta(resposta), Categoria.CINEMA_CULTURA);
        } else {
            RadioButton button = radioGroup.findViewById(checkedId);
            String resposta = button.getText().toString();
            somarPontos(atribuirValorReposta(resposta), Categoria.BEM_ESTAR);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        btnAnterior.setOnClickListener(view -> {
            Intent quiz2 = new Intent(getApplicationContext(), Quiz2Activity.class);
            startActivity(quiz2);
        });


        btnProximo.setOnClickListener(view -> {
            Intent quiz4 = new Intent(getApplicationContext(), Quiz4Activity.class);
            quiz4.putExtra("pontosProducaoEnsino", getPontosProducaoEnsino());
            quiz4.putExtra("pontosTecnologias", getPontosTecnologias());
            quiz4.putExtra("pontosBemEstar", getPontosBemEstar());
            quiz4.putExtra("pontosCinemaCultura", getPontosCinemaCultura());
            quiz4.putExtra("pontosDeficiencias", getPontosDeficiencias());
            quiz4.putExtra("pontosLinguasEstrangeiras", getPontosLinguasEstrangeiras());
            quiz4.putExtra("pontosLeituraAlfabetizacao", getPontosLeituraAlfabetizacao());
            startActivity(quiz4);
        });
    }
}
