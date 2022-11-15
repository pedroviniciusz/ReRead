package com.example.reread.quiz;

import static java.util.Collections.reverseOrder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.reread.EscolhaCategoria;
import com.example.reread.R;
import com.example.reread.enums.Categoria;

import java.util.ArrayList;

public class Quiz4Activity extends BaseQuiz implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup rg1, rg2, rg3, rg4;

    private Button btnAnterior, btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz4);

        rg1 = findViewById(R.id.radioGroup);
        rg2 = findViewById(R.id.radioGroup2);
        rg3 = findViewById(R.id.radioGroup3);
        rg4 = findViewById(R.id.radioGroup4);

        rg1.setOnCheckedChangeListener(this);
        rg2.setOnCheckedChangeListener(this);
        rg3.setOnCheckedChangeListener(this);
        rg4.setOnCheckedChangeListener(this);

        btnAnterior = findViewById(R.id.btnAnterior);
        btnEnviar = findViewById(R.id.btnEnviar);

        recuperarSoma();
    }

    private void recuperarSoma(){
        setPontosProducaoEnsino(getIntent().getIntExtra("pontosProducaoEnsino", 0));
        setPontosTecnologias(getIntent().getIntExtra("pontosTecnologias", 0));
        setPontosBemEstar(getIntent().getIntExtra("pontosBemEstar", 0));
        setPontosCinemaCultura(getIntent().getIntExtra("pontosCinemaCultura", 0));
        setPontosDeficiencias(getIntent().getIntExtra("pontosDeficiencias", 0));
        setPontosLinguasEstrangeiras(getIntent().getIntExtra("pontosLinguasEstrangeiras", 0));
        setPontosLeituraAlfabetizacao(getIntent().getIntExtra("pontosLeituraAlfabetizacao", 0));
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

        if (radioGroup == rg1){
            RadioButton button = radioGroup.findViewById(checkedId);
            String resposta = button.getText().toString();
            somarPontos(atribuirValorReposta(resposta), Categoria.BEM_ESTAR);
        }else if(radioGroup == rg2){
            RadioButton button = radioGroup.findViewById(checkedId);
            String resposta = button.getText().toString();
            somarPontos(atribuirValorReposta(resposta), Categoria.LINGUAS_ESTRANGEIRAS);
        }else if(radioGroup == rg3){
            RadioButton button = radioGroup.findViewById(checkedId);
            String resposta = button.getText().toString();
            somarPontos(atribuirValorReposta(resposta), Categoria.PRODUCAO_ENSINO);
        }else{
            RadioButton button = radioGroup.findViewById(checkedId);
            String resposta = button.getText().toString();
            somarPontos(atribuirValorReposta(resposta), Categoria.PRODUCAO_ENSINO);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        btnAnterior.setOnClickListener(view -> {
            Intent quiz3 = new Intent(getApplicationContext(), Quiz3Activity.class);
            startActivity(quiz3);
        });


        btnEnviar.setOnClickListener(view -> {
            Intent escolherCategoria = new Intent(getApplicationContext(), EscolhaCategoria.class);

            ArrayList<String> categoriasMaisVotadas = getMaisVotadas(getPontosPorCategoria());

            escolherCategoria.putExtra("categoriasMaisVotadas", categoriasMaisVotadas);

            startActivity(escolherCategoria);
        });
    }
}
