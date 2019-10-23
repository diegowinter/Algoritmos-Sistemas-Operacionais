package escalonamento_braco_disco;

import java.util.ArrayList;

/**
 * Algoritmo FCFS (First Come, First Served)
 * @author Diego Winter
 */
public class FCFS {
	
	private ArrayList<Integer> requisicoes;
	
	public FCFS(ArrayList<Integer> requisicoes) {
		this.requisicoes = requisicoes;
	}
	
	/**
	 * Executa o algoritmo.
	 * @return A quantidade de cilindros percorridos.
	 */
	public int executar() {
		int cilindrosPercorridos = 0;
		
		for(int i=0; i<requisicoes.size()-1; i++) {
			int distancia = requisicoes.get(i) - requisicoes.get(i+1);
			if(distancia < 0) {
				distancia *= -1;
			}
			cilindrosPercorridos += distancia;
		}
		
		return cilindrosPercorridos;
	}
	
}
