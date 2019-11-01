package substituicao_paginas;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Algoritmo de substituição de páginas
 * @author Diego Winter
 */
public class Main {
	
	public static void main(String[] args) {
		boolean leitura = true;
		ArrayList<Pagina> referencias = new ArrayList<Pagina>();
		ArrayList<Pagina> referencias2 = new ArrayList<Pagina>();
		int quadrosDisponiveis = 0;
		File arquivoEntrada = new File(args[0]);
	    try {
	        Scanner sr = new Scanner(arquivoEntrada);
	        while (sr.hasNextLine()) {
	        	String i = sr.nextLine();
	        	if(leitura == true) {
	        		quadrosDisponiveis = Integer.parseInt(i);
		    		leitura = false;
		    	} else {
		    		Pagina pagina = new Pagina(0, 1, 0);
		        	pagina.setReferencia(Integer.parseInt(i));
		        	pagina.setBitR(1);
		        	referencias.add(pagina);
		        	referencias2.add(pagina);
		    	}
	        }
	        sr.close();
	    } 
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	    
	    SegundaChance segundaChance = new SegundaChance(referencias, quadrosDisponiveis);
	    Otimo otimo = new Otimo(referencias, quadrosDisponiveis);
	    
	    exibirResultados(segundaChance.executar(), otimo.executar(), 0);
	}
	
	private static void exibirResultados(int sc, int otm, int ct) {
		System.out.println("SC " + sc);
		System.out.println("OTM " + otm);
		System.out.println("CT " + ct);
	}
	
}
