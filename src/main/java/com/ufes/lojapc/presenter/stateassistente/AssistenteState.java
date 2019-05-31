package com.ufes.lojapc.presenter.stateassistente;

import com.ufes.lojapc.model.Componente;
import com.ufes.lojapc.model.Parte;
import com.ufes.lojapc.model.Todo;
import com.ufes.lojapc.presenter.AssistentePresenter;
import com.ufes.lojapc.presenter.LinhaTabelaComponente;
import java.util.List;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;

public abstract class AssistenteState {

    protected AssistentePresenter presenter;
    protected List<LinhaTabelaComponente> linhas;
    protected Parte selecionado;
    protected Todo todo;
    protected Todo todoTemp;

    public AssistenteState(AssistentePresenter presenter, Todo todo) {
        this.presenter = presenter;
        this.todo = todo;

        presenter.getView().getTblComponente().setSelectionModel(new DefaultListSelectionModel());
        this.configuraListener();

        presenter.getView().getBtnProximo().setEnabled(false);
        presenter.getView().getTblComponente().setEnabled(true);
        presenter.getView().requestFocus();
    }

    private void configuraListener() {
        presenter.getView().getTblComponente().getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                try {

                    if (presenter.getView().getTblComponente().getSelectedRow() >= 0) {
                        String nome = presenter.getView().getTblComponente().getValueAt(presenter.getView().getTblComponente().getSelectedRow(), 0).toString();
                        double preco = Double.parseDouble(presenter.getView().getTblComponente().getValueAt(presenter.getView().getTblComponente().getSelectedRow(), 1).toString());
                        selecionado = new Parte(preco, nome);
                        presenter.getView().getTblComponente().setEnabled(false);
                        presenter.getView().getBtnLimparSelecao().setEnabled(true);
                        adicionaSelecionado(selecionado);
                        presenter.getView().getBtnProximo().setEnabled(true);
                    }
                } catch (NumberFormatException exx) {
                    presenter.getView().getBtnLimparSelecao().setEnabled(false);
                    presenter.getView().getTblComponente().setEnabled(true);
                    selecionado = null;
                    JOptionPane.showMessageDialog(presenter.getView(), "Falha ao selecionar item:\n" + exx.getMessage());
                }

            }
        });
    }

    protected abstract void adicionaSelecionado(Componente selecionado);

    public void limparSelecao()  {
        presenter.getView().getTblComponente().setEnabled(true);
        presenter.getView().getBtnLimparSelecao().setEnabled(false);

        presenter.getView().getTaDescricao().selectAll();
        presenter.getView().getTaDescricao().replaceSelection("");

        presenter.getView().getLblPreco().setText("");
        if (todo != null) {
            presenter.preencheDescricao(todo);
        }

    }

    public void proximo() {
        throw new IllegalStateException("Não é possível executar o comando \"Próximo\" nesta tela");
    }

    public void anterior() {
        throw new IllegalStateException("Não é possível executar o comando \"Anterior\" nesta tela");
    }

}
