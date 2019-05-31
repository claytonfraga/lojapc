package com.ufes.lojapc.presenter;

import com.ufes.lojapc.dao.IComponenteDAO;
import com.ufes.lojapc.model.Componente;
import com.ufes.lojapc.model.Todo;
import com.ufes.lojapc.presenter.stateassistente.AssistenteState;
import com.ufes.lojapc.presenter.stateassistente.todo.SelecaoTodoPlacaMaeState;
import com.ufes.lojapc.view.AssistenteView;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class AssistentePresenter {

    private AssistenteView view;
    private AssistenteState estadoAtual;
    private DefaultTableModel tm;
    private IComponenteDAO daoComponentes;
    private Componente computador;

    public AssistentePresenter(IComponenteDAO daoComponentes) {
        this.daoComponentes = daoComponentes;

        view = new AssistenteView();
        view.setVisible(false);

        view.getBtnAnterior().addActionListener((ActionEvent e) -> {
            try {
                anterior();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Falha ao voltar para a tela anterior:\n " + ex.getMessage());
            }
        });

        view.getBtnProximo().addActionListener((ActionEvent e) -> {
            try {
                proximo();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Falha ao ir para a próxima tela:\n" + ex.getMessage());
            }
        });

        view.getBtnLimparSelecao().addActionListener((ActionEvent e) -> {
            try {
                limparSelecao();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Falha ao limpar seleção:\n" + ex.getMessage());
            }
        });

        view.getTaDescricao().setFont(new Font("Tahoma", Font.PLAIN, 12));
        view.getTaDescricao().setLineWrap(true);
        view.getTaDescricao().setWrapStyleWord(true);

        estadoAtual = new SelecaoTodoPlacaMaeState(this, null);
    }

    private void updateRowHeights() {
        for (int row = 0; row < view.getTblComponente().getRowCount(); row++) {
            int rowHeight = view.getTblComponente().getRowHeight();

            for (int column = 0; column < view.getTblComponente().getColumnCount(); column++) {
                Component comp = view.getTblComponente().prepareRenderer(view.getTblComponente().getCellRenderer(row, column), row, column);
                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
            }

            view.getTblComponente().setRowHeight(row, rowHeight);
        }
    }

    private void configuraTableModel() {
        tm = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Nome", "Preço (R$)"}) {

            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        view.getTblComponente().setModel(tm);

        view.getTblComponente().getColumnModel().getColumn(0).setPreferredWidth(420);
        view.getTblComponente().getColumnModel().getColumn(1).setPreferredWidth(90);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        view.getTblComponente().getColumnModel().getColumn(1).setCellRenderer(rightRenderer);

    }

    public void carregaTableModel(List<LinhaTabelaComponente> linhas) {
        configuraTableModel();

        Iterator<LinhaTabelaComponente> it = linhas.iterator();
        while (it.hasNext()) {
            LinhaTabelaComponente p = it.next();
            String linha = p.toString();
            String[] campos = linha.split("#");
            tm.addRow(new Object[]{campos[0], campos[1]});
        }

        view.getTblComponente().getColumnModel().getColumn(0).setCellRenderer(new MultilineTableCell());
        updateRowHeights();
    }

    public void preencheDescricao(Todo todo) {

        view.getTaDescricao().selectAll();
        view.getTaDescricao().replaceSelection("");

        if (todo == null) {
            view.getLblPreco().setText("");
        } else {
            DecimalFormat df = new DecimalFormat("#.00");

            view.getTaDescricao().setText(todo.getDescricao());
            view.getLblPreco().setText("R$ " + df.format(todo.getPreco()));
        }
        view.repaint();
    }

    public void setDAO(IComponenteDAO componenteDao) {
        this.daoComponentes = componenteDao;
        tm.setNumRows(0);
    }

    public IComponenteDAO getDaoComponentes() {
        return daoComponentes;
    }

    private void proximo() {
        estadoAtual.proximo();
    }

    private void anterior() {
        estadoAtual.anterior();
    }

    public Componente getComputador() {
        return computador;
    }

    public void setComputador(Componente computador) {
        this.computador = computador;
    }

    private void limparSelecao() {
        estadoAtual.limparSelecao();
    }

    public AssistenteView getView() {
        return view;
    }

    public void setEstado(AssistenteState estado) {
        this.estadoAtual = estado;
    }

}
