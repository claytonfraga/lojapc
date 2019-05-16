package com.ufes.lojapc.presenter.stateassistente.todo;

import com.ufes.lojapc.memento.Zelador;
import com.ufes.lojapc.model.Componente;
import com.ufes.lojapc.model.Todo;
import com.ufes.lojapc.presenter.AssistentePresenter;
import com.ufes.lojapc.presenter.stateassistente.AssistenteState;
import com.ufes.lojapc.presenter.stateassistente.parte.SelecaoParteFonteEnergiaState;
import com.ufes.lojapc.presenter.stateassistente.parte.SelecaoParteHDState;

public final class SelecaoTodoGabineteState extends AssistenteState {

    public SelecaoTodoGabineteState(AssistentePresenter presenter, Todo todo) {
        super(presenter, todo);

        presenter.getView().getLblNomeComponente().setText("Gabinete");

        linhas = presenter.getDaoComponentes().getTodosPorTipo("gabinete");

        presenter.carregaTableModel(linhas);

        presenter.preencheDescricao(todo);
        presenter.getView().getBtnAnterior().setVisible(true);
    }

    @Override
    public void anterior() throws Exception {
        todo.restaura(Zelador.getInstancia().get());
        presenter.setEstado(new SelecaoParteHDState(presenter, todo));
    }

    @Override
    public void proximo() throws Exception {
        if (selecionado != null) {
            Todo gabinete = new Todo(selecionado.getPreco(), selecionado.getDescricao());
            todo.add(gabinete);
            Zelador.getInstancia().add(todo.cria());
            presenter.setEstado(new SelecaoParteFonteEnergiaState(presenter, todo));
        }
    }

    @Override
    protected void adicionaSelecionado(Componente selecionado) throws Exception {
        if (selecionado != null) {
            Todo novoTodo = new Todo(todo.getPreco(), todo.getDescricao());
            novoTodo.add(new Todo(selecionado.getPreco(), selecionado.getDescricao()));
            presenter.preencheDescricao(novoTodo);
        }
    }

}
