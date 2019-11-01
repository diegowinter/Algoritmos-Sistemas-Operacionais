package substituicao_paginas;

import java.util.ArrayList;

public class Otimo {
	
	ArrayList<Pagina> referencias = new ArrayList<Pagina>();
	ArrayList<Integer> quadros = new ArrayList<Integer>();
	ArrayList<Integer> existentes = new ArrayList<Integer>();
	
	int quadrosDisponiveis;
	
	public Otimo(ArrayList<Pagina> referencias, int quadrosDisponiveis) {
		this.referencias = referencias;
		this.quadrosDisponiveis = quadrosDisponiveis;
	}
	
	int referenciaDistante;
	int posReferenciaDistante;
	int contQuadros = 0;
	int faltaDePagina = 0;
	
	public int executar() {
		for(int i=0; i<referencias.size(); i++) {
			if(quadros.size() < quadrosDisponiveis) {
				faltaDePagina++;
				quadros.add(referencias.get(i).getReferencia());
			} else {
				if(!quadros.contains(referencias.get(i).getReferencia())) {
					for(int j=0; j<quadros.size(); j++) {
						for(int k=(i+1); k<referencias.size(); k++) {
							if(quadros.contains(referencias.get(k).getReferencia())) {
								referenciaDistante = referencias.get(k).getReferencia();
								posReferenciaDistante = j;
								contQuadros++;
								existentes.add(referencias.get(k).getReferencia());
							}
						}
					}
					if(existentes.size() < quadrosDisponiveis) {
						for(int j=0; j<quadros.size(); j++) {
							if(!existentes.contains(quadros.get(j))) {
								faltaDePagina++;
								quadros.set(j, referencias.get(i).getReferencia());
								j = quadros.size();
							}
						}
					} else {
						faltaDePagina++;
						quadros.set(posReferenciaDistante, referenciaDistante);
					}
				}
			}
		}
		
		return faltaDePagina;
	}

}
