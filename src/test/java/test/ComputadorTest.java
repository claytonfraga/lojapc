package test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.ufes.lojapc.memento.Zelador;
import com.ufes.lojapc.model.Parte;
import com.ufes.lojapc.model.Todo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Clayton
 */
public class ComputadorTest {

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
        computador.add(placaMae);

        Parte memoria = new Parte(100, "Memoria DDR4 16GB");
        placaMae.add(memoria);

        System.out.println("\n" + computador);
    }

    @Test
    public void computadorComMemento() throws Exception {
        Todo computador = new Todo(100, "Computador gamer");
        Parte monitor = new Parte(700, "monitor Samsung 23\"");
        computador.add(monitor);

        // Zelador guarda a copia
        //Zelador.getInstancia().add(computador.cria());
        System.out.println("Copia 1 " + computador.getPreco() + "\n");

        Todo placaMae = new Todo(430, "Placa mae");
        Parte hd = new Parte(530, "SSD 240Gb");
        placaMae.add(hd);
        computador.add(placaMae);

        // para falhar
        //computador.add(hd);
        computador.setPreco(160.0);

        System.out.println(computador);

        //Zelador guarda a copia
       // Zelador.getInstancia().add(computador.cria());
        System.out.println("Copia 2 " + computador.getPreco() + "\n");

        Parte memoria = new Parte(100, "Memoria DDR4 16GB");
        placaMae.add(memoria);

        System.out.println("Memoria adicionada, sem copia!");
        System.out.println(computador);

        //Restauracao
       // computador.restaura(Zelador.getInstancia().get());

        System.out.println("\nApos a restauracao 1:\n");

        System.out.println(computador);

        //Restauracao
     //   computador.restaura(Zelador.getInstancia().get());

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

}
