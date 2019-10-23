package escalonamento_braco_disco;

import java.util.ArrayList;

public class SSTF {
	
	private int ultimoCilindro;
	//private int posicaoInicial;
	private ArrayList<Integer> requisicoes;
	
	public SSTF(int ultimoCilindro, /*int posicaoInicial,*/
			ArrayList<Integer> requisicoes) {
		this.ultimoCilindro = ultimoCilindro;
		//this.posicaoInicial = posicaoInicial;
		this.requisicoes = requisicoes;
	}
	
	public int executar() {
		ArrayList<Integer> requisicoesAtendidas = new ArrayList<Integer>();
		
		requisicoesAtendidas.add(0); // Posição incial já é prontamente atendida.
		int posicaoAtual = 0; // Posição incial é a inicial.
		int tempPosicaoAtual = 0;
		int cilindrosPercorridos = 0;
		while(true) {
			int distancia = ultimoCilindro; // Inicia com o maior valor possível.
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
			System.out.println(posicaoAtual);

			if(requisicoesAtendidas.size() == requisicoes.size()) {
				break;
			}
		}

		return cilindrosPercorridos;
	}

}
