package com.example.reread.quiz;

import static java.util.Collections.reverseOrder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.reread.R;
import com.example.reread.enums.Categoria;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.TreeMap;

public class QuizActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup rg1, rg2, rg3, rg4;

    private Button btnAnterior, btnProximo;

    private static final String NAO = "Não";
    private static final String PARCIALMENTE = "Parcialmente";
    private static final String SIM = "Sim";

    private Integer pontosProducaoEnsino = 0;
    private Integer pontosTecnologias = 0;
    private Integer pontosBemEstar = 0;

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

        TreeMap<Integer, Categoria> categorias;
        ArrayList<String> categoriasMaisVotadas = new ArrayList<>();

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
            case PRODUCAO_ENSINO:
                pontosProducaoEnsino += valor;
                break;
            case TECNOLOGIAS:
                pontosTecnologias += valor;
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
            Intent comecarQuiz = new Intent(getApplicationContext(), ComecarQuiz.class);
            startActivity(comecarQuiz);
        });


        btnProximo.setOnClickListener(view -> {
            Intent quiz2 = new Intent(getApplicationContext(), Quiz2Activity.class);
            quiz2.putExtra("pontosProducaoEnsino", pontosProducaoEnsino);
            quiz2.putExtra("pontosTecnologias", pontosTecnologias);
            quiz2.putExtra("pontosBemEstar", pontosBemEstar);
            startActivity(quiz2);
        });
    }
}