package escalonamento_braco_disco;

import java.util.ArrayList;

public class FCFS {
	
	private int ultimoCilindro;
	//private int posicaoInicial;
	private ArrayList<Integer> requisicoes;
	
	public FCFS(int ultimoCilindro, /*int posicaoInicial,*/
			ArrayList<Integer> requisicoes) {
		this.ultimoCilindro = ultimoCilindro;
		//this.posicaoInicial = posicaoInicial;
		this.requisicoes = requisicoes;
	}
	
	public int executar() {
		int cilindrosPercorridos = 0;
		
		for(int i=0; i<requisicoes.size()-1; i++) {
			int distancia = requisicoes.get(i) - requisicoes.get(i+1);
			
			if(distancia < 0)
				distancia *= -1;
			
			cilindrosPercorridos += distancia;
		}
		
		return cilindrosPercorridos;
	}
	
}
