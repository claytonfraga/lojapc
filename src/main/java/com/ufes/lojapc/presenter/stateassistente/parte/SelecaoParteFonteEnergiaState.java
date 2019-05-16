package com.ufes.lojapc.presenter.stateassistente.parte;

import com.ufes.lojapc.memento.Zelador;
import com.ufes.lojapc.model.Componente;
import com.ufes.lojapc.model.Parte;
import com.ufes.lojapc.model.Todo;
import com.ufes.lojapc.presenter.AssistentePresenter;
import com.ufes.lojapc.presenter.stateassistente.AssistenteState;
import com.ufes.lojapc.presenter.stateassistente.todo.SelecaoTodoGabineteState;

public final class SelecaoParteFonteEnergiaState extends AssistenteState {

    public SelecaoParteFonteEnergiaState(AssistentePresenter presenter, Todo todo) throws Exception {
        super(presenter, todo);

        presenter.getView().getLblNomeComponente().setText("Fonte de Energia");

        linhas = presenter.getDaoComponentes().getTodosPorTipo("fonte");

        presenter.carregaTableModel(linhas);

        presenter.preencheDescricao(this.todo);
        presenter.getView().getBtnAnterior().setVisible(true);
    }

    @Override
    public void anterior() throws Exception {
        todo.restaura(Zelador.getInstancia().get());
        presenter.setEstado(new SelecaoTodoGabineteState(presenter, todo));
    }

    @Override
    public void proximo() throws Exception {
        if (selecionado != null) {
            Parte parte = new Parte(selecionado.getPreco(), selecionado.getDescricao());
            todo.add("gabinete", parte);
            Zelador.getInstancia().add(todo.cria());
            presenter.setEstado(new SelecaoParteMonitorState(presenter, todo));
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