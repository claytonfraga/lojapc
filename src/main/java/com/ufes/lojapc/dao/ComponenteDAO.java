package com.ufes.lojapc.dao;

import com.ufes.lojapc.presenter.LinhaTabelaComponente;
import java.util.List;

public class ComponenteDAO implements IComponenteDAO {

    @Override
    public List<LinhaTabelaComponente> getTodosPorTipo(String tipo) {

        List<LinhaTabelaComponente> componentesRecuperados = FonteDadosStub.getInstance().getTodosPorTipo(tipo);

        if (componentesRecuperados != null) {
            return componentesRecuperados;
        }
        throw new IllegalArgumentException("Tipo " + tipo + " inexistente!");
    }

}
