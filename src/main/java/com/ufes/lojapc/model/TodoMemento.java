package com.ufes.lojapc.model;

import java.io.Serializable;
import java.util.ArrayList;

public class TodoMemento implements Serializable {

    private ArrayList<Componente> componentes;
    private final double preco;
    private final String descricao;

    TodoMemento(double preco, String descricao) {
        this.preco = preco;
        this.descricao = descricao;
    }

    void add(ArrayList<Componente> componentes) {
        this.componentes = componentes;
    }

    ArrayList<Componente> getComponentes() {
        return componentes;
    }

    public String getDescricao() {
        return descricao;
    }

    double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return "Todo Memento: " + descricao + ",preco" + preco;
    }
}
