package com.ufes.lojapc.presenter.stateassistente.todo;

import com.ufes.lojapc.memento.Zelador;
import com.ufes.lojapc.model.Componente;
import com.ufes.lojapc.model.Parte;
import com.ufes.lojapc.model.Todo;
import com.ufes.lojapc.presenter.AssistentePresenter;
import com.ufes.lojapc.presenter.stateassistente.AssistenteState;
import com.ufes.lojapc.presenter.stateassistente.parte.SelecaoParteProcessadorState;
import java.text.DecimalFormat;

public final class SelecaoTodoPlacaMaeState extends AssistenteState {

    public SelecaoTodoPlacaMaeState(AssistentePresenter presenter, Todo todo) {
        super(presenter, todo);

        presenter.getView().getLblNomeComponente().setText("Placa mãe");

        linhas = presenter.getDaoComponentes().getTodosPorTipo("placamae");

        Zelador.getInstancia().limparHistorico();

        presenter.carregaTableModel(linhas);
        if (todo != null) {
            presenter.preencheDescricao(this.todo);
        } else {
            presenter.getView().getLblPreco().setText("");
        }

        todo = null;

        presenter.getView().setVisible(true);
        presenter.getView().getBtnAnterior().setVisible(false);
    }

    @Override
    public void proximo() throws Exception {
        this.todo = new Todo(selecionado.getPreco(), selecionado.getDescricao());
        Zelador.getInstancia().add(todo.cria());
        presenter.setEstado(new SelecaoParteProcessadorState(presenter, this.todo));
    }

    @Override
    public void limparSelecao() throws Exception {
        todo = null;
        super.limparSelecao();
    }

    @Override
    protected void adicionaSelecionado(Componente selecionado) throws Exception {
        if (selecionado != null) {
            if (todo != null) {
                Todo novoTodo = new Todo(todo.getPreco(), todo.getDescricao());
                novoTodo.add(new Parte(selecionado.getPreco(), selecionado.getDescricao()));
                preencheDescricao(novoTodo);
            } else {
                this.preencheDescricao(null);
            }
        }
    }

    protected void preencheDescricao(Todo todo) {
        if (selecionado != null) {
            presenter.getView().getTaDescricao().selectAll();
            presenter.getView().getTaDescricao().replaceSelection("");

            DecimalFormat df = new DecimalFormat("#.00");

            presenter.getView().getTaDescricao().setText(selecionado.getDescricao());
            presenter.getView().getLblPreco().setText(df.format(selecionado.getPreco()));
        }

    }
}
