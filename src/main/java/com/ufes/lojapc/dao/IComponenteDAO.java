package com.ufes.lojapc.dao;

import com.ufes.lojapc.presenter.LinhaTabelaComponente;
import java.util.List;

public interface IComponenteDAO {

    List<LinhaTabelaComponente> getTodosPorTipo(String tipo);

}
