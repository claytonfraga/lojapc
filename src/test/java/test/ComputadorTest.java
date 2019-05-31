package test;

import com.ufes.lojapc.chain.ProcessadorConfiguracaoComputador;
import com.ufes.lojapc.memento.Zelador;
import com.ufes.lojapc.model.Parte;
import com.ufes.lojapc.model.Todo;
import com.ufes.lojapc.model.TodoMemento;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class ComputadorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    public ComputadorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void computador1() {
        Todo computador = new Todo(102.98, "Computador gamer");
        Parte monitor = new Parte(700, "monitor Samsung 23\"");
        computador.add(monitor);

        Todo placaMae = new Todo(432.67, "Placa mae");
        Parte hd = new Parte(530, "SSD 240Gb");
        Parte processador = new Parte(930, "Processador I7");
        placaMae.add(hd);
        placaMae.add(processador);
        Parte memoria = new Parte(100, "Memoria DDR4 16GB");
        placaMae.add(memoria);
        computador.add(placaMae);

        assertTrue(computador.getDescricao().contains("Memoria"));
        assertEquals(2795.65, computador.getPreco(), 0.01);
    }

    @Test
    public void computadorComMemento() throws Exception {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Componente \"SSD 240Gb\" já adicionado");

        Todo computador = new Todo(100, "Computador gamer");
        Parte monitor = new Parte(700, "monitor Samsung 23\"");
        computador.add(monitor);

        // Zelador guarda a copia
        Zelador.getInstancia().add(computador.cria());
        System.out.println("Copia 1 " + computador.getPreco() + "\n");

        Todo placaMae = new Todo(430, "Placa mae");
        Parte hd = new Parte(530, "SSD 240Gb");
        placaMae.add(hd);
        computador.add(placaMae);

        // para falhar
        computador.add(hd);

        System.out.println(computador);

        //Zelador guarda a copia
        Zelador.getInstancia().add(computador.cria());
        System.out.println("Copia 2 " + computador.getPreco() + "\n");

        Parte memoria = new Parte(100, "Memoria DDR4 16GB");
        placaMae.add(memoria);

        System.out.println("Memoria adicionada, sem copia!");
        System.out.println(computador);

        //Restauracao
        computador.restaura(Zelador.getInstancia().get());
        System.out.println("\nApos a restauracao 1:\n");

        System.out.println(computador);

        //Restauracao
        computador.restaura(Zelador.getInstancia().get());
        System.out.println("\nApos a restauracao 2:\n");

        System.out.println(computador);

        //Voltamos para adicionar uma nova configuraÃ§Ã£o
        Todo placaMae1 = new Todo(930, "Placa mae XPTO TOP");
        Parte hd1 = new Parte(530, "SSD 480Gb");
        placaMae1.add(hd1);
        computador.add(placaMae1);

        System.out.println("\nApos retornar e fazer uma nova configuracao:\n");

        System.out.println(computador.getDescricao());
        System.out.println("Preco total: " + computador.getPreco());
    }

    @Test
    public void computadorComMemento2() throws Exception {

        Todo computador = new Todo(100, "Computador gamer");
        Parte monitor = new Parte(700, "monitor Samsung 23\"");
        computador.add(monitor);

        // Zelador guarda a copia
        Zelador.getInstancia().add(computador.cria());
        System.out.println("Copia 1 " + computador.getPreco() + "\n");

        Todo placaMae = new Todo(430, "Placa mae");
        Parte hd = new Parte(530, "SSD 240Gb");
        placaMae.add(hd);
        computador.add(placaMae);

        System.out.println(computador);

        //Zelador guarda a copia
        Zelador.getInstancia().add(computador.cria());
        System.out.println("Copia 2 " + computador.getPreco() + "\n");

        Parte memoria = new Parte(100, "Memoria DDR4 16GB");
        placaMae.add(memoria);

        System.out.println("Memoria adicionada, sem copia!");
        System.out.println(computador);

        //Restauracao
        computador.restaura(Zelador.getInstancia().get());
        System.out.println("\nApos a restauracao 1:\n");

        System.out.println(computador);

        //Restauracao
        computador.restaura(Zelador.getInstancia().get());
        System.out.println("\nApos a restauracao 2:\n");

        System.out.println(computador);

        //Voltamos para adicionar uma nova configuraÃ§Ã£o
        Todo placaMae1 = new Todo(930, "Placa mae XPTO TOP");
        Parte hd1 = new Parte(530, "SSD 480Gb");
        placaMae1.add(hd1);
        computador.add(placaMae1);

        System.out.println("\nApos retornar e fazer uma nova configuracao:\n");

        String descricaoEsperada = "Computador gamer\n"
                + "monitor Samsung 23\"\n"
                + "Placa mae XPTO TOP\n"
                + "SSD 480Gb";

        double precoEsperado = 2260.0;

        assertEquals(descricaoEsperada, computador.getDescricao());
        assertEquals(precoEsperado, computador.getPreco(), 0.001);
    }

    @Test
    public void restauracaoParte() {

        thrown.expect(UnsupportedOperationException.class);
        thrown.expectMessage("Nao disponivel na parte");

        Parte parte = new Parte(12, "Memoria");

        Zelador.getInstancia().add(parte.cria());

    }

    @Test
    public void addParte() {

        thrown.expect(UnsupportedOperationException.class);
        thrown.expectMessage("Nao disponivel na parte");

        Parte parte = new Parte(12, "Memoria");
        parte.add(new Parte(123, "Teclado"));

    }

    @Test
    public void addParte2() {

        thrown.expect(UnsupportedOperationException.class);
        thrown.expectMessage("Nao disponivel na parte");

        Parte parte = new Parte(12, "Memoria");
        parte.add("", new Parte(123, "Teclado"));

    }

    @Test
    public void addTodo() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Informe uma descricao valida!");

        Todo todo = new Todo(500, null);
        todo.add("", null);

    }

    @Test
    public void addTodo1() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Informe um preco valido!");

        Todo todo = new Todo(-0.01, null);
    }

    @Test
    public void addTodo2() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Informe um preco valido!");

        Todo todo = new Todo(Double.NaN, null);
    }

    @Test
    public void addTodo3() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Informe uma descricao valida!");

        Todo todo = new Todo(100, null);
    }

    @Test
    public void addTodo4() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Informe uma descricao valida!");

        Todo todo = new Todo(100, "");
    }

    @Test
    public void addTodo5() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Informe uma descricao valida!");

        Todo todo = new Todo(100, " ");
    }

    @Test
    public void addComponenteProcessadorConfiguracaoComputador() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Adicione um Todo ou um Componente validos");

        ProcessadorConfiguracaoComputador processador = new ProcessadorConfiguracaoComputador();
        processador.addComponente(null, null);

    }

    @Test
    public void addComponenteProcessadorConfiguracaoComputador1() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Adicione um Todo ou um Componente validos");

        ProcessadorConfiguracaoComputador processador = new ProcessadorConfiguracaoComputador();
        processador.addComponente(null, new Parte(250, "Memoria"));

    }
    @Test
    public void addComponenteProcessadorConfiguracaoComputador2() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Adicione um Todo ou um Componente validos");

        ProcessadorConfiguracaoComputador processador = new ProcessadorConfiguracaoComputador();
        processador.addComponente(new Todo(250, "Memoria"), null);

    }

    @Test
    public void zeladorVazio() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Não há estados salvos");
        Zelador.getInstancia().get();
    }

    @Test
    public void zeladorLimpaHistorico() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Não há estados salvos");

        Todo pc = new Todo(123, "PC");
        Zelador.getInstancia().add(pc.cria());

        Zelador.getInstancia().limparHistorico();

        Zelador.getInstancia().get();
    }

}
