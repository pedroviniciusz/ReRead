package com.example.reread.enums;

public enum Categoria {

    PRODUCAO_ENSINO("Produção/Ensino"),
    BEM_ESTAR("Bem-Estar"),
    TECNOLOGIAS("Tecnologias"),
    CINEMA_CULTURA("Cinema/Cultura"),
    DEFICIENCIAS("Deficiências"),
    LINGUAS_ESTRANGEIRAS("Línguas Estrangeiras"),
    LEITURA_ALFABETIZACAO("Leitura/Alfabetização");

    private String nome;

    Categoria(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}
