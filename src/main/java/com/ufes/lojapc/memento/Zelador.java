package com.ufes.lojapc.memento;

import com.ufes.lojapc.model.TodoMemento;
import java.util.Stack;

public class Zelador {

    private final Stack<TodoMemento> elementos;
    private static Zelador instancia;

    private Zelador() {
        this.elementos = new Stack<>();
    }

    public static Zelador getInstancia() {
        if (instancia == null) {
            instancia = new Zelador();
        }
        return instancia;
    }

    public void add(TodoMemento todo) throws Exception {
        this.elementos.push(todo);

    }

    public TodoMemento get() throws Exception {
        if (!elementos.isEmpty()) {
            return elementos.pop();
        }
        //return null;
        throw new Exception("Não há estados salvos");
    }

    public void limparHistorico() {
        instancia = null;
    }

}
