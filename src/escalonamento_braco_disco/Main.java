package escalonamento_braco_disco;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Algoritmo de escalonamento do braço do disco
 * @author Diego Winter
 */
public class Main {
	
	public static void main(String[] args) {
		
		boolean leitura = true;
		int ultimoCilindro = 0;
		ArrayList<Integer> requisicoes = new ArrayList<Integer>();
		
		File arquivoEntrada = new File(args[0]);
	    try {
	        Scanner sr = new Scanner(arquivoEntrada);
	        while (sr.hasNextLine()) {
	        	String i = sr.next();
	        	if(leitura == true) {
		    		ultimoCilindro = Integer.parseInt(i);
		    		leitura = false;
		    	} else {
		    		requisicoes.add(Integer.parseInt(i));
		    	}
	        }
	        sr.close();
	    } 
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
		
		FCFS fcfs = new FCFS (requisicoes);
		SSTF sstf = new SSTF (ultimoCilindro, requisicoes);
		Elevador elevador = new Elevador (requisicoes);
		
		exibirResultados(fcfs.executar(), sstf.executar(), elevador.executar());
	}
	
	private static void exibirResultados(int fcfs, int sstf, int elevador) {
		System.out.println("FCFS " + fcfs);
		System.out.println("SSTF " + sstf);
		System.out.println("ELEVADOR " + elevador);
	}

}
