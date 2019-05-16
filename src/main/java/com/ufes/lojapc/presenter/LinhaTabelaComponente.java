package com.ufes.lojapc.presenter;

public class LinhaTabelaComponente {

    private String descricao;
    private double preco;

    public LinhaTabelaComponente(String descricao, double preco) {
        this.descricao = descricao;
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return descricao + "#" + preco;
    }

}
