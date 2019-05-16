package com.ufes.lojapc.presenter.stateassistente.parte;

import com.ufes.lojapc.memento.Zelador;
import com.ufes.lojapc.model.Componente;
import com.ufes.lojapc.model.Parte;
import com.ufes.lojapc.model.Todo;
import com.ufes.lojapc.presenter.AssistentePresenter;
import com.ufes.lojapc.presenter.stateassistente.AssistenteState;

public final class SelecaoParteTecladoState extends AssistenteState {

    public SelecaoParteTecladoState(AssistentePresenter presenter, Todo todo) throws Exception {
        super(presenter, todo);
        presenter.getView().getLblNomeComponente().setText("Teclado");

        linhas = presenter.getDaoComponentes().getTodosPorTipo("teclado");

        presenter.carregaTableModel(linhas);

        presenter.preencheDescricao(todo);
        presenter.getView().getBtnAnterior().setVisible(true);
        presenter.getView().getBtnAnterior().setVisible(true);
    }

    @Override
    public void anterior() throws Exception {
        this.todo.restaura(Zelador.getInstancia().get());
        presenter.setEstado(new SelecaoParteMouseState(presenter, todo));
    }

    /*
    @Override
    public void proximo() throws Exception {
        if (selecionado != null) {
            Parte parte = new Parte(selecionado.getPreco(), selecionado.getDescricao());
            todo.add(parte);
            Zelador.getInstancia().add(todo.cria());
            presenter.setEstado(new SelecaoParteFonteEnergiaState(presenter, todo));
        }
    }
     */
    @Override
    protected void adicionaSelecionado(Componente selecionado) throws Exception {
        if (selecionado != null) {
            Todo novoTodo = new Todo(todo.getPreco(), todo.getDescricao());
            novoTodo.add(new Parte(selecionado.getPreco(), selecionado.getDescricao()));
            // Computador
            presenter.setComputador(novoTodo);
            presenter.preencheDescricao(novoTodo);
        }

    }
}
