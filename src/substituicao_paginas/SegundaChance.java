package substituicao_paginas;

import java.util.ArrayList;

public class SegundaChance {
	
	ArrayList<Pagina> referencias = new ArrayList<Pagina>();
	ArrayList<Pagina> quadros = new ArrayList<Pagina>();
	int quadrosDisponiveis;
	
	public SegundaChance(ArrayList<Pagina> referencias, int quadrosDisponiveis) {
		this.referencias = referencias;
		this.quadrosDisponiveis = quadrosDisponiveis;
	}
	
	
	
	int faltasDePagina = 0;
	int contReferencias = 0;
	public int executar() {

		boolean troca = false;
		for(int i=0; i<referencias.size(); i++) {
			if(contReferencias == 4) {
				for (Pagina pagina : quadros) {
					pagina.setBitR(0);
				}
				contReferencias = 0;
			}
			
			if(quadros.size() < quadrosDisponiveis) {
				quadros.add(referencias.get(i));
			} else {
				for (Pagina pagina : quadros) {
					if(pagina.getReferencia() == referencias.get(i).getReferencia()) {
						pagina.setBitR(1);
						troca = true;
					}
				}
				if(troca == false) {
					while(true) {
						if(quadros.get(0).getBitR() == 1) {
							Pagina pagina = new Pagina(0, 0, 0);
							pagina.setReferencia(quadros.get(0).getReferencia());
							pagina.setBitR(0);
							quadros.remove(0);
							quadros.add(pagina);
							faltasDePagina++;
						} else {
							quadros.get(0).setReferencia(referencias.get(i).getReferencia());
							quadros.get(0).setBitR(1);
							faltasDePagina++;
							break;
						}
					}
				}
				troca = false;	
			}
			
			contReferencias++;
		}
		
		return faltasDePagina;
	}

}
