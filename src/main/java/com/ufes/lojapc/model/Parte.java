package com.ufes.lojapc.model;

public class Parte extends Componente {

    private final static String FALHA = "Nao disponivel na parte";

    public Parte(double preco, String descricao) {
        super(preco, descricao);

    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public double getPreco() {
        return preco;
    }

    @Override
    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public void restaura(TodoMemento memento) {
        throw new UnsupportedOperationException(FALHA);
    }

    @Override
    public TodoMemento cria() {
        throw new UnsupportedOperationException(FALHA);
    }

    @Override
    public void add(Componente componente) {
        throw new UnsupportedOperationException(FALHA);
    }

    @Override
    public void add(String inicioNome, Componente componente) {
        throw new UnsupportedOperationException(FALHA);
    }
}
