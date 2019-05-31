package com.ufes.lojapc.memento;

import com.ufes.lojapc.model.TodoMemento;
import java.util.ArrayDeque;
import java.util.Deque;

public class Zelador {

    private final Deque<TodoMemento> elementos;
    private static Zelador instancia;

    private Zelador() {
        this.elementos = new ArrayDeque<>();
    }

    public static Zelador getInstancia() {
        if (instancia == null) {
            instancia = new Zelador();
        }
        return instancia;
    }

    public void add(TodoMemento todo) throws IllegalArgumentException {
        this.elementos.push(todo);

    }

    public TodoMemento get() throws IllegalArgumentException {
        if (!elementos.isEmpty()) {
            return elementos.pop();
        }
        throw new IllegalArgumentException("Não há estados salvos");
    }

    public void limparHistorico() {
        instancia = null;
    }

}
