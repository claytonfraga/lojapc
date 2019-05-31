package com.ufes.lojapc.presenter.stateassistente.parte;

import com.ufes.lojapc.memento.Zelador;
import com.ufes.lojapc.model.Componente;
import com.ufes.lojapc.model.Parte;
import com.ufes.lojapc.model.Todo;
import com.ufes.lojapc.presenter.AssistentePresenter;
import com.ufes.lojapc.presenter.stateassistente.AssistenteState;

public final class SelecaoParteMouseState extends AssistenteState {

    public SelecaoParteMouseState(AssistentePresenter presenter, Todo todo) throws IllegalArgumentException {
        super(presenter, todo);
        presenter.getView().getLblNomeComponente().setText("Mouse");

        linhas = presenter.getDaoComponentes().getTodosPorTipo("mouse");

        presenter.carregaTableModel(linhas);

        presenter.preencheDescricao(todo);
        presenter.getView().getBtnAnterior().setVisible(true);
        presenter.getView().getBtnProximo().setVisible(true);
    }

    @Override
    public void anterior() throws Exception {
        this.todo.restaura(Zelador.getInstancia().get());
        presenter.setEstado(new SelecaoParteMonitorState(presenter, todo));
    }

    @Override
    public void proximo() throws Exception {
        if (selecionado != null) {
            Parte parte = new Parte(selecionado.getPreco(), selecionado.getDescricao());
            todo.add(parte);
            Zelador.getInstancia().add(todo.cria());
            presenter.setEstado(new SelecaoParteTecladoState(presenter, todo));
        }
    }

    @Override
    protected void adicionaSelecionado(Componente selecionado) throws Exception {
        if (selecionado != null) {
            Todo novoTodo = new Todo(todo.getPreco(), todo.getDescricao());
            novoTodo.add(new Parte(selecionado.getPreco(), selecionado.getDescricao()));
            presenter.preencheDescricao(novoTodo);
        }

    }
}
