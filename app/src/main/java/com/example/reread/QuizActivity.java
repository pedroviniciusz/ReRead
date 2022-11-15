package com.example.reread;

import static com.example.reread.enums.Categoria.*;

import static java.util.Collections.reverseOrder;
import static java.util.Collections.singleton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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

    private Integer quantidadeDePontosProducaoEnsino = 0;
    private Integer quantidadeDePontosCategoriaTecnologias = 0;
    private Integer quantidadeDePontosCategoriaBemEstar = 0;

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

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

        TreeMap<Integer, Categoria> categorias;
        ArrayList<String> categoriasMaisVotadas = new ArrayList<>();

        if (radioGroup == rg1){
            RadioButton button = radioGroup.findViewById(checkedId);
            String resposta = button.getText().toString();
            somarPontos(atribuirValorReposta(resposta), PRODUCAO_ENSINO);
            categorias = getPontosPorCategoria();
            categoriasMaisVotadas = getMaisVotadas(categorias);
        }else if(radioGroup == rg2){
            RadioButton button = radioGroup.findViewById(checkedId);
            String resposta = button.getText().toString();
            somarPontos(atribuirValorReposta(resposta), TECNOLOGIAS);
            categorias = getPontosPorCategoria();
            categoriasMaisVotadas = getMaisVotadas(categorias);
        }else if(radioGroup == rg3){
            RadioButton button = radioGroup.findViewById(checkedId);
            String resposta = button.getText().toString();
            somarPontos(atribuirValorReposta(resposta), TECNOLOGIAS);
            categorias = getPontosPorCategoria();
            categoriasMaisVotadas = getMaisVotadas(categorias);
        }else{
            RadioButton button = radioGroup.findViewById(checkedId);
            String resposta = button.getText().toString();
            somarPontos(atribuirValorReposta(resposta), PRODUCAO_ENSINO);
            categorias = getPontosPorCategoria();
            categoriasMaisVotadas = getMaisVotadas(categorias);
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
                quantidadeDePontosProducaoEnsino += valor;
                break;
            case TECNOLOGIAS:
                quantidadeDePontosCategoriaTecnologias += valor;
                break;
            case BEM_ESTAR:
                quantidadeDePontosCategoriaBemEstar += valor;
                break;
            default:
                throw new IllegalArgumentException();
        }

    }

    private TreeMap<Integer, Categoria> getPontosPorCategoria() {
        TreeMap<Integer, Categoria> pontosPorCategoria = new TreeMap<>(reverseOrder());
        pontosPorCategoria.put(quantidadeDePontosProducaoEnsino, PRODUCAO_ENSINO);
        pontosPorCategoria.put(quantidadeDePontosCategoriaTecnologias, TECNOLOGIAS);
        pontosPorCategoria.put(quantidadeDePontosCategoriaBemEstar, BEM_ESTAR);

        return pontosPorCategoria;
    }

    private ArrayList getMaisVotadas(TreeMap<Integer, Categoria> categorias) {

        while (categorias.size() > 3 ){
            categorias.remove(categorias.lastKey());
        }

        ArrayList<String> valueList = new ArrayList<>(singleton(categorias.values().toString()));

        return valueList;

    }

    @Override
    protected void onStart() {
        super.onStart();

        btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cadastro = new Intent(getApplicationContext(), CadastroActivity.class);
                startActivity(cadastro);
            }
        });

        btnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}