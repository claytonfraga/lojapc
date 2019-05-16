package com.ufes.lojapc.chain;

import com.ufes.lojapc.utils.Util;
import java.util.AbstractMap;
import java.util.Map.Entry;

public class AdicionaTratador implements ITratador {

    private Entry<String, String> tabelaPadrao;

    public AdicionaTratador(String todo, String partes) {
        tabelaPadrao = new AbstractMap.SimpleEntry<>(todo, partes);

    }

    @Override
    public boolean aceita(String todo, String componente) {
        String[] descricaoTodo = todo.split(",");
        String[] componentes = componente.split(" ");

        for (String strParte : componentes) {
            for (String strTodo : descricaoTodo) {
                String[] strNomeTodo = strTodo.split(" ");
                for (String str : strNomeTodo) {
                    String parte = tabelaPadrao.getValue();
                    if (parte != null) {
                        return parte.toLowerCase().contains(Util.removeAcentos(strParte.toLowerCase()));
                    }
                }
                String chave = tabelaPadrao.getKey();
                if (Util.removeAcentos(todo.toLowerCase()).contains(Util.removeAcentos(chave.toLowerCase()))) {
                    strTodo = chave;
                }
                String parte = tabelaPadrao.getValue();
                if (parte != null) {
                    return parte.toLowerCase().contains(Util.removeAcentos(strParte.toLowerCase()));
                }
            }
        }
        return false;

    }

}
