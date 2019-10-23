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
		/*
		 * Enviar pras classes os valores separados:
		 * Últ. cilindro / cilindro atual / array de requisições
		 */
		int leitura = 1;
		int ultimoCilindro = 0;
		//int posicaoInicial = 0;
		ArrayList<Integer> requisicoes = new ArrayList<Integer>();
		
		File arquivoEntrada = new File(args[0]);
	    try {
	        Scanner sr = new Scanner(arquivoEntrada);
	        while (sr.hasNextLine()) {
	        	String i = sr.next();
	        	if(leitura == 1) {
		    		ultimoCilindro = Integer.parseInt(i);
		    	//} else if (leitura == 2) {
		    		//posicaoInicial = Integer.parseInt(i);
		    	} else {
		    		requisicoes.add(Integer.parseInt(i));
		    	}
	        	leitura++;
	        }
	        sr.close();
	    } 
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
		
		FCFS fcfs = new FCFS (requisicoes);
		SSTF sstf = new SSTF (ultimoCilindro,/* posicaoInicial,*/ requisicoes);
		//Elevador elevador = new Elevador (ultimoCilindro, /*posicaoInicial,*/ requisicoes);
		
		exibirResultados(fcfs.executar(), sstf.executar()/*, elevador.executar()*/);
	}
	
	private static String exibirResultados(int fcfs, int sstf/*, int elevador*/) {
		return null;
	}

}
