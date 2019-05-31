 package com.ufes.lojapc.presenter.stateassistente.parte;

import com.ufes.lojapc.presenter.stateassistente.todo.SelecaoTodoGabineteState;
import com.ufes.lojapc.memento.Zelador;
import com.ufes.lojapc.model.Componente;
import com.ufes.lojapc.model.Parte;
import com.ufes.lojapc.model.Todo;
import com.ufes.lojapc.presenter.AssistentePresenter;
import com.ufes.lojapc.presenter.stateassistente.AssistenteState;
import javax.swing.JOptionPane;

public final class SelecaoParteHDState extends AssistenteState {

    public SelecaoParteHDState(AssistentePresenter presenter, Todo todo) {
        super(presenter, todo);
        presenter.getView().getLblNomeComponente().setText("HD ou SSD");

        linhas = presenter.getDaoComponentes().getTodosPorTipo("hd");

        presenter.carregaTableModel(linhas);

        presenter.preencheDescricao(todo);
        presenter.getView().getBtnAnterior().setVisible(true);
        presenter.getView().getBtnProximo().setVisible(true);
    }

    @Override
    public void anterior() throws Exception {
        while (todo.getDescricao().toLowerCase().contains("ssd") || todo.getDescricao().toLowerCase().contains("hd")) {
            todo.restaura(Zelador.getInstancia().get());
        }
        presenter.setEstado(new SelecaoParteMemoriaState(presenter, todo));
    }

    @Override
    public void proximo() throws Exception {

        if (selecionado != null) {
            Parte parte = new Parte(selecionado.getPreco(), selecionado.getDescricao());
            todo.add(parte);
            Zelador.getInstancia().add(todo.cria());

            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Deseja adicionar outro HD (ou SDD) ?", "Disco", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {
                presenter.setEstado(new SelecaoParteHDState(presenter, todo));
            } else {
                presenter.setComputador(todo);
                presenter.setEstado(new SelecaoTodoGabineteState(presenter, todo));

            }
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
