package com.ufes.lojapc.dao;

import com.ufes.lojapc.presenter.LinhaTabelaComponente;
import java.util.ArrayList;

public class ComponenteDAO implements IComponenteDAO {

    @Override
    public ArrayList<LinhaTabelaComponente> getTodosPorTipo(String tipo) {

        ArrayList<LinhaTabelaComponente> componentesRecuperados = FonteDadosStub.getInstance().getTodosPorTipo(tipo);

        if (componentesRecuperados != null) {
            return componentesRecuperados;
        }
        throw new IllegalArgumentException("Tipo " + tipo + " inexistente!");
    }

}
