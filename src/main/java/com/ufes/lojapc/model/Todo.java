package com.ufes.lojapc.model;

import com.ufes.lojapc.chain.ProcessadorConfiguracaoComputador;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Todo extends Componente {

    private ArrayList<Componente> componentes = new ArrayList<>();

    public Todo(double preco, String descricao) {
        super(preco, descricao);
    }

    @Override
    public void add(String inicioNome, Componente componente) {
        ProcessadorConfiguracaoComputador processador = new ProcessadorConfiguracaoComputador();
        for (Componente elemento : componentes) {
            if (elemento.getDescricao().toLowerCase().startsWith(inicioNome.toLowerCase())) {
                if (processador.addComponente(elemento, componente)) {
                    elemento.add(componente);
                } else {
                    throw new IllegalArgumentException("Nao foi possivel adicionar " + componente.getDescricao()
                            + " em " + this.getDescricao());
                }
            }
        }
    }

    @Override
    public void add(Componente componente) {

        if (componente == null) {
            throw new NullPointerException("Informe um componente valido!");
        }

        if (getDescricao().contains(componente.getDescricao())) {
            throw new IllegalArgumentException("Componente \"" + componente.getDescricao() + "\" já adicionado");
        }
        ProcessadorConfiguracaoComputador processador = new ProcessadorConfiguracaoComputador();
        if (processador.addComponente(this, componente)) {
            this.componentes.add(componente);
        } else {
            throw new IllegalArgumentException("Nao foi possivel adicionar " + componente.getDescricao()
                    + " em " + this.getDescricao());
        }
    }

    @Override
    public String getDescricao() {
        String descricaoTodo = descricao;
        for (Componente componente : componentes) {
            descricaoTodo = descricaoTodo + "\n" + componente.getDescricao();
        }
        return descricaoTodo;
    }

    @Override
    public double getPreco() {
        double precoTodo = this.preco;
        for (Componente componente : componentes) {
            precoTodo += componente.getPreco();

        }
        return precoTodo;
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
        this.preco = memento.getPreco();
        this.descricao = memento.getDescricao();
        componentes = new ArrayList<>(memento.getComponentes());
    }

    @Override
    public TodoMemento cria() {
        TodoMemento todo = new TodoMemento(preco, descricao);
        try {
            ArrayList<Componente> elementos = clonaColecao(componentes);
            todo.add(elementos);
        } catch (IOException | ClassNotFoundException ex) {
            throw new IllegalArgumentException("\nFalha ao criar memento: " + ex.getMessage());
        }
        return todo;
    }

    private ArrayList<Componente> clonaColecao(ArrayList<Componente> elementos) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream saidaArray = new ByteArrayOutputStream();
        ObjectOutputStream entradaObjeto = new ObjectOutputStream(saidaArray);
        entradaObjeto.writeObject(elementos);
        ObjectInputStream saidaObjeto = new ObjectInputStream(new ByteArrayInputStream(saidaArray.toByteArray()));
        return (ArrayList<Componente>) saidaObjeto.readObject();
    }

    @Override
    public String toString() {
        String descricaoTodo = descricao;
        for (Componente componente : componentes) {
            descricaoTodo = descricaoTodo + "\n" + componente.toString();
        }
        return descricaoTodo;
    }
}
