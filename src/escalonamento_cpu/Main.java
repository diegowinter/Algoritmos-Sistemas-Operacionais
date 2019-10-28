package escalonamento_cpu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Algoritmo de escalonamento de CPU
 * @author Diego Winter
 */
public class Main {
	
	public static void main(String[] args) {
		
		ArrayList<Processo> processos1 = new ArrayList<Processo>();
		ArrayList<Processo> processos2 = new ArrayList<Processo>();
		ArrayList<Processo> processos3 = new ArrayList<Processo>();
		
		File arquivoEntrada = new File(args[0]);
	    try {
	        Scanner sr = new Scanner(arquivoEntrada);
	        while (sr.hasNextLine()) {
	        	String i = sr.nextLine();
	        		Processo processo1 = new Processo(
	        				Integer.parseInt(i.split(" ")[0]),
	        				Integer.parseInt(i.split(" ")[1]), 0, 2, -1, 0);
	        		processos1.add(processo1);
	        		Processo processo2 = new Processo(
	        				Integer.parseInt(i.split(" ")[0]),
	        				Integer.parseInt(i.split(" ")[1]), 0, 2, -1, 0);
	        		processos2.add(processo2);
	        		Processo processo3 = new Processo(
	        				Integer.parseInt(i.split(" ")[0]),
	        				Integer.parseInt(i.split(" ")[1]), 0, 2, -1, 0);
	        		processos3.add(processo3);
	        }
	        sr.close();
	    } 
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	    
	    PrioridadesDinamicas pd = new PrioridadesDinamicas(processos1);
	    Loteria loteria = new Loteria(processos2);
		RoundRobin roundRobin = new RoundRobin(processos3);
		
		exibirResultados(pd.executar(), loteria.executar(), roundRobin.executar());
	}
	
	private static void exibirResultados(String pd, String lot, String rr) {
		System.out.println("PRI " + pd);
		System.out.println("LOT " + lot);
		System.out.println("RR " + rr);
	}

}
