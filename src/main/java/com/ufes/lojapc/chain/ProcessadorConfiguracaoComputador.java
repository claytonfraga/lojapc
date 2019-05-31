package com.ufes.lojapc.chain;

import com.ufes.lojapc.model.Componente;
import java.util.ArrayList;

public final class ProcessadorConfiguracaoComputador {

    private final ArrayList<AdicionaTratador> tratadores = new ArrayList<>();

    public ProcessadorConfiguracaoComputador() {

        AdicionaTratador computador = new AdicionaTratador("computador", "placa mãe, placamae, placa mae, monitor, teclado, mouse, gabinete");
        this.add(computador);

        AdicionaTratador computador1 = new AdicionaTratador("pc", "placa mãe, placamae, placa mae, monitor, teclado, mouse, gabinete");
        this.add(computador1);

        AdicionaTratador computador2 = new AdicionaTratador("desktop", "placa mãe, placamae, placa mae, monitor, teclado, mouse, gabinete");
        this.add(computador2);

        AdicionaTratador placaMaeTratador1 = new AdicionaTratador("placa mae", "placa de video, hd, ssd, sata, "
                + "hard disk, disco rigido, video, processador, memoria, ddr, gb");
        this.add(placaMaeTratador1);

        AdicionaTratador placaVideo = new AdicionaTratador("placa de video", "acelerador gráfico, acelerador, grafico");
        this.add(placaVideo);
        AdicionaTratador gabinete = new AdicionaTratador("gabinete", "fonte, placa mae, placamae");
        this.add(gabinete);

    }

    public boolean add(AdicionaTratador tratador) {
        return tratadores.add(tratador);
    }

    public boolean addComponente(Componente todo, Componente componente) {
        if (todo == null || componente == null) {
            throw new IllegalArgumentException("Adicione um Todo ou um Componente validos");
        }
        for (AdicionaTratador tratador : tratadores) {
            if (tratador.aceita(todo.getDescricao(), componente.getDescricao())) {
                return true;
            }
        }
        return false;
    }
}
