package com.example.reread.quiz;

import static java.util.Collections.reverseOrder;
import static java.util.Collections.singleton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reread.R;
import com.example.reread.enums.Categoria;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Quiz4Activity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup rg1, rg2, rg3, rg4;

    private Button btnAnterior, btnEnviar;

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
        pontosProducaoEnsino = getIntent().getIntExtra("pontosProducaoEnsino", 0);
        pontosTecnologias = getIntent().getIntExtra("pontosTecnologias", 0);
        pontosBemEstar = getIntent().getIntExtra("pontosBemEstar", 0);
        pontosCinemaCultura = getIntent().getIntExtra("pontosCinemaCultura", 0);
        pontosDeficiencias = getIntent().getIntExtra("pontosDeficiencias", 0);
        pontosLinguasEstrangeiras = getIntent().getIntExtra("pontosLinguasEstrangeiras", 0);
        pontosLeituraAlfabetizacao = getIntent().getIntExtra("pontosLeituraAlfabetizacao", 0);
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
            case BEM_ESTAR:
                pontosBemEstar += valor;
                break;
            case LINGUAS_ESTRANGEIRAS:
                pontosLinguasEstrangeiras += valor;
                break;
            case PRODUCAO_ENSINO:
                pontosProducaoEnsino += valor;
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

     private TreeMap<Integer, Categoria> getPontosPorCategoria() {

     TreeMap<Integer, Categoria> pontosPorCategoria = new TreeMap<>(reverseOrder());

     pontosPorCategoria.put(pontosProducaoEnsino, Categoria.PRODUCAO_ENSINO);
     pontosPorCategoria.put(pontosTecnologias, Categoria.TECNOLOGIAS);
     pontosPorCategoria.put(pontosBemEstar, Categoria.BEM_ESTAR);
     pontosPorCategoria.put(pontosCinemaCultura, Categoria.CINEMA_CULTURA);
     pontosPorCategoria.put(pontosDeficiencias, Categoria.DEFICIENCIAS);
     pontosPorCategoria.put(pontosLinguasEstrangeiras, Categoria.LINGUAS_ESTRANGEIRAS);
     pontosPorCategoria.put(pontosLeituraAlfabetizacao, Categoria.LEITURA_ALFABETIZACAO);

     return pontosPorCategoria;
     }

     private ArrayList<String> getMaisVotadas(TreeMap<Integer, Categoria> categorias) {

     ArrayList<String> valueList = new ArrayList<>();

     while (categorias.size() > 3 ){
     categorias.remove(categorias.lastKey());
     }
     for (Map.Entry<Integer, Categoria> entry : categorias.entrySet()) {
         valueList.add(entry.getValue().getNome());
     }

     return valueList;

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
