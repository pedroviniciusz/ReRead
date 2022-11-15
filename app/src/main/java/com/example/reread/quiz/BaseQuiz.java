package com.example.reread.quiz;

import static java.util.Collections.reverseOrder;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reread.enums.Categoria;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BaseQuiz extends AppCompatActivity {

    private static final String NAO = "Não";
    private static final String PARCIALMENTE = "Parcialmente";
    private static final String SIM = "Sim";

    private int pontosProducaoEnsino;
    private int pontosTecnologias;
    private int pontosBemEstar;
    private int pontosCinemaCultura;
    private int pontosDeficiencias ;
    private int pontosLinguasEstrangeiras;
    private int pontosLeituraAlfabetizacao;

    protected static int atribuirValorReposta(String resposta) {
        int pontos = 0;
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

    protected void somarPontos(Integer valor, Categoria categoria){

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
            case CINEMA_CULTURA:
                pontosCinemaCultura += valor;
                break;
            case DEFICIENCIAS:
                pontosDeficiencias += valor;
                break;
            case LINGUAS_ESTRANGEIRAS:
                pontosLinguasEstrangeiras += valor;
                break;
            case LEITURA_ALFABETIZACAO:
                pontosLeituraAlfabetizacao += valor;
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    protected TreeMap<Integer, Categoria> getPontosPorCategoria() {

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

    protected ArrayList<String> getMaisVotadas(TreeMap<Integer, Categoria> categorias) {

        ArrayList<String> valueList = new ArrayList<>();

        while (categorias.size() > 3 ){
            categorias.remove(categorias.lastKey());
        }
        for (Map.Entry<Integer, Categoria> entry : categorias.entrySet()) {
            valueList.add(entry.getValue().getNome());
        }

        return valueList;

    }

}
