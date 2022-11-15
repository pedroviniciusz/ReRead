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

public class Quiz3Activity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup rg1, rg2, rg3, rg4;

    private Button btnAnterior, btnProximo;

    private static final String NAO = "Não";
    private static final String PARCIALMENTE = "Parcialmente";
    private static final String SIM = "Sim";

    private Integer pontosProducaoEnsino = 0;
    private Integer pontosTecnologias = 0;
    private Integer pontosBemEstar = 0;
    private Integer pontosCinemaCultura = 0;
    private Integer pontosDeficiencias = 0;
    private Integer pontosLinguasEstrangeiras = 0;
    private Integer pontosLeituraAlfabetizacao = 0;

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
        pontosProducaoEnsino = getIntent().getIntExtra("pontosProducaoEnsino", 0);
        pontosTecnologias = getIntent().getIntExtra("pontosTecnologias", 0);
        pontosBemEstar = getIntent().getIntExtra("pontosBemEstar", 0);
        pontosCinemaCultura = getIntent().getIntExtra("pontosCinemaCultura", 0);
        pontosDeficiencias = getIntent().getIntExtra("pontosDeficiencias", 0);
        pontosLinguasEstrangeiras = getIntent().getIntExtra("pontosLinguasEstrangeiras", 0);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

        if (radioGroup == rg1){
            RadioButton button = radioGroup.findViewById(checkedId);
            String resposta = button.getText().toString();
            somarPontos(atribuirValorReposta(resposta), Categoria.CINEMA_CULTURA);
        }else if(radioGroup == rg2){
            RadioButton button = radioGroup.findViewById(checkedId);
            String resposta = button.getText().toString();
            somarPontos(atribuirValorReposta(resposta), Categoria.LEITURA_ALFABETIZACAO);
        }else if(radioGroup == rg3){
            RadioButton button = radioGroup.findViewById(checkedId);
            String resposta = button.getText().toString();
            somarPontos(atribuirValorReposta(resposta), Categoria.CINEMA_CULTURA);
        }else{
            RadioButton button = radioGroup.findViewById(checkedId);
            String resposta = button.getText().toString();
            somarPontos(atribuirValorReposta(resposta), Categoria.BEM_ESTAR);
        }
    }

    private Integer atribuirValorReposta(String resposta) {
        Integer pontos = 0;
        switch (resposta){
            case NAO:
                pontos += BigDecimal.ZERO.intValue();
                break;
            case PARCIALMENTE:
                pontos += BigDecimal.valueOf(5).intValue();
                break;
            case SIM:
                pontos += BigDecimal.TEN.intValue();
                break;
            default:
                throw new IllegalArgumentException();
        }
        return pontos;
    }

    private void somarPontos(Integer valor, Categoria categoria){

        switch (categoria){
            case CINEMA_CULTURA:
                pontosCinemaCultura += valor;
                break;
            case LEITURA_ALFABETIZACAO:
                pontosLeituraAlfabetizacao += valor;
                break;
            case BEM_ESTAR:
                pontosBemEstar += valor;
                break;
            default:
                throw new IllegalArgumentException();
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
            quiz4.putExtra("pontosProducaoEnsino", pontosProducaoEnsino);
            quiz4.putExtra("pontosTecnologias", pontosTecnologias);
            quiz4.putExtra("pontosBemEstar", pontosBemEstar);
            quiz4.putExtra("pontosCinemaCultura", pontosCinemaCultura);
            quiz4.putExtra("pontosDeficiencias", pontosDeficiencias);
            quiz4.putExtra("pontosLinguasEstrangeiras", pontosLinguasEstrangeiras);
            quiz4.putExtra("pontosLeituraAlfabetizacao", pontosLeituraAlfabetizacao);
            startActivity(quiz4);
        });
    }
}
