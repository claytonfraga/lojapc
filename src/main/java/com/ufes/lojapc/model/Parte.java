package com.ufes.lojapc.model;

public class Parte extends Componente {

    private static final String FALHA = "Nao disponivel na parte";

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
