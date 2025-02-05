package escalonamento_braco_disco;

import java.util.ArrayList;

/**
 * Algoritmo SSTF (Short Seek Time First)
 * @author Diego Winter
 */
public class SSTF {
	
	private int ultimoCilindro;
	private ArrayList<Integer> requisicoes;
	
	public SSTF(int ultimoCilindro, ArrayList<Integer> requisicoes) {
		this.ultimoCilindro = ultimoCilindro;
		this.requisicoes = requisicoes;
	}
	
	/**
	 * Executa o algoritmo.
	 * @return A quantidade de cilindros percorridos.
	 */
	public int executar() {
		ArrayList<Integer> requisicoesAtendidas = new ArrayList<Integer>();
		
		requisicoesAtendidas.add(0); // Posi��o incial j� � prontamente atendida.
		int posicaoAtual = 0; // Posi��o incial � a inicial.
		int tempPosicaoAtual = 0;
		int cilindrosPercorridos = 0;
		while(true) {
			int distancia = ultimoCilindro; // Inicia com o maior valor poss�vel.
			for(int i=0; i<requisicoes.size(); i++) {
				if(!requisicoesAtendidas.contains(i)) {
					int tmpDistancia = requisicoes.get(i) - requisicoes.get(posicaoAtual);
					if(tmpDistancia < 0) {
						tmpDistancia *= -1;
					}
					if(tmpDistancia < distancia) {
						distancia = tmpDistancia;
						tempPosicaoAtual = i;
					}
				}
			}
			posicaoAtual = tempPosicaoAtual;
			cilindrosPercorridos += distancia;
			requisicoesAtendidas.add(posicaoAtual);

			if(requisicoesAtendidas.size() == requisicoes.size()) {
				break;
			}
		}

		return cilindrosPercorridos;
	}

}
