package com.ufes.lojapc.model;

public class Parte extends Componente {

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
        throw new UnsupportedOperationException("Nao disponivel na parte"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TodoMemento cria() {
        throw new UnsupportedOperationException("Nao disponivel na parte"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Componente componente) {
        throw new UnsupportedOperationException("Nao disponivel na parte"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(String inicioNome, Componente componente) {
        throw new UnsupportedOperationException("Nao disponivel na parte"); //To change body of generated methods, choose Tools | Templates.
    }
}
