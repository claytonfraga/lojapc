package com.ufes.lojapc.model;

import java.io.Serializable;

public abstract class Componente implements Serializable {

    protected double preco;
    protected String descricao;

    public Componente(double preco, String descricao) {
        this.preco = preco;
        this.descricao = descricao;
    }

    public abstract String getDescricao();

    public abstract void restaura(TodoMemento memento);

    public abstract TodoMemento cria();

    public abstract void add(Componente componente);

    public abstract void add(String inicioNome, Componente componente);

    public abstract double getPreco();

}
