package com.ufes.lojapc.dao;

import com.ufes.lojapc.presenter.LinhaTabelaComponente;
import java.util.ArrayList;

public interface IComponenteDAO {

    ArrayList<LinhaTabelaComponente> getTodosPorTipo(String tipo);

}
