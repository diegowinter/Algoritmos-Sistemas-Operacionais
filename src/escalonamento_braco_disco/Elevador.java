package escalonamento_braco_disco;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Algoritmo do elevador (Scan)
 * @author Diego Winter
 */
public class Elevador {
	
	private ArrayList<Integer> requisicoes;
	
	public Elevador(ArrayList<Integer> requisicoes) {
		this.requisicoes = requisicoes;
	}

	/**
	 * Executa o algoritmo.
	 * @return A quantidade de cilindros percorridos.
	 */
	@SuppressWarnings("unchecked")
	public int executar() {
		ArrayList<Integer> requisicoesOrdenadas = (ArrayList<Integer>) requisicoes.clone();
		int posicaoAtual = 0;
		int cilindrosPercorridos = 0;
		
		Collections.sort(requisicoesOrdenadas);

		for(int i=0; i<requisicoes.size(); i++) {
			if(requisicoesOrdenadas.get(i).equals(requisicoes.get(0))) {
				posicaoAtual = i;
			}
		}
		
		for(int i=posicaoAtual; i<requisicoesOrdenadas.size()-1; i++) {
			cilindrosPercorridos += requisicoesOrdenadas.get(i+1) - requisicoesOrdenadas.get(i);
		}
		
		// Pega a distância do último da subida com o primeiro da descida
		cilindrosPercorridos += requisicoesOrdenadas.get(requisicoesOrdenadas.size()-1) - requisicoesOrdenadas.get(posicaoAtual-1);
		
		for(int i=posicaoAtual-1; i>0; i--) {
			cilindrosPercorridos += requisicoesOrdenadas.get(i) - requisicoesOrdenadas.get(i-1);
		}
		
		return cilindrosPercorridos;
	}
}
