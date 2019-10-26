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
		
		ArrayList<Processo> processos = new ArrayList<Processo>();
		
		File arquivoEntrada = new File(args[0]);
	    try {
	        Scanner sr = new Scanner(arquivoEntrada);
	        while (sr.hasNextLine()) {
	        	String i = sr.nextLine();
	        		Processo processo = new Processo(
	        				Integer.parseInt(i.split(" ")[0]),
	        				Integer.parseInt(i.split(" ")[1]), 0, 2, -1, 0);
	        		processos.add(processo);
	        }
	        sr.close();
	    } 
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
		
	    PrioridadesDinamicas pd = new PrioridadesDinamicas(processos);
	    pd.executar();
		//SSTF sstf = new SSTF (ultimoCilindro, requisicoes);
		//Elevador elevador = new Elevador (requisicoes);
		
		//exibirResultados(fcfs.executar(), sstf.executar(), elevador.executar());
	}
	
	private static void exibirResultados(int fcfs, int sstf, int elevador) {
		System.out.println("PRI " + fcfs);
		System.out.println("LOT " + sstf);
		System.out.println("RR " + elevador);
	}

}
