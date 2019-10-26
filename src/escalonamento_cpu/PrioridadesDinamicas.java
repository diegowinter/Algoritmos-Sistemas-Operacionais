package escalonamento_cpu;

import java.util.ArrayList;

public class PrioridadesDinamicas {
	
	ArrayList<Processo> processos = new ArrayList<Processo>();
	ArrayList<Processo> filaDeProcessos = new ArrayList<Processo>();
	
	public PrioridadesDinamicas(ArrayList<Processo> processos) {
		this.processos = processos;
	}
	
	//@SuppressWarnings("unused")
	public void executar() {
		for (int i=0; i<processos.size(); i++) {
			processos.get(i).setPrioridade(5);
		}
		int qtdeProcessos = processos.size();
		System.out.println(qtdeProcessos);
		int tempoRetorno = 0;
		int tempoResposta = 0;
		int tempoEspera = 0;
		int tempo = 0;
		int novoProcesso = 0;
		while(true) {
			for(int i=0; i<processos.size(); i++) {
				if(processos.get(i).getInstante() == tempo) {
					filaDeProcessos.add(processos.get(i));
				}
			}
			
			/*
			for (Processo processo : filaDeProcessos) {
				System.out.println(processo.getEstado() + " - "
						+ processo.getDuracao() + " - "
						+ processo.getPrioridade());
			}
			System.out.println("========");
			*/
			
			/*
			 *  -> Verifica se tem algum executando.
			 *  -> Se sim, diminui seu tempo.
			 *  -> Diminui sua prioridade.
			 *  -> Verifica se é o seu fim.
			 */
			for(int i=0; i<filaDeProcessos.size(); i++) {
				if(filaDeProcessos.get(i).getEstado() == 1) {
					filaDeProcessos.get(i).setDuracao(filaDeProcessos.get(i).getDuracao() - 1);
					filaDeProcessos.get(i).setPrioridade(filaDeProcessos.get(i).getPrioridade() - 1);
					if(filaDeProcessos.get(i).getDuracao() == 0) {
						tempoRetorno += tempo - filaDeProcessos.get(i).getInstante();
						tempoResposta += filaDeProcessos.get(i).getPrimeiraExecucao() - filaDeProcessos.get(i).getInstante();
						tempoEspera += filaDeProcessos.get(i).getTempoEmEspera();
						System.out.println("Nasci: " + filaDeProcessos.get(i).getInstante()
								+ " e morri: " + tempo);
						filaDeProcessos.remove(i);
					} else {
						filaDeProcessos.get(i).setEstado(2);
					}
				}
			}
			
			if(filaDeProcessos.size() == 0) {
				float trtm = (float)tempoRetorno / (float)qtdeProcessos;
				float trsm = (float)tempoResposta / (float)qtdeProcessos;
				float tesm = (float)tempoEspera / (float)qtdeProcessos;
				System.out.println("TRetM = " + trtm);
				System.out.println("TResM = " + trsm);
				System.out.println("TEspM = " + tesm);
				break;
			}

			novoProcesso = escolherProcesso(tempo);
			if(filaDeProcessos.get(novoProcesso).getPrimeiraExecucao() == -1) {
				filaDeProcessos.get(novoProcesso).setPrimeiraExecucao(tempo);
			}
			filaDeProcessos.get(novoProcesso).setEstado(1);
			
			/*
			 * - Aumenta a prioridade de todos no estado pronto e que já chegaram
			 */
			for(int i=0; i<filaDeProcessos.size(); i++) {
				if(filaDeProcessos.get(i).getEstado() == 2) {
					filaDeProcessos.get(i).setPrioridade(filaDeProcessos.get(i).getPrioridade() + 1);
				}
			}
			
			for (Processo processo2 : filaDeProcessos) {
				if(processo2.getEstado() == 2) {
					processo2.setTempoEmEspera(processo2.getTempoEmEspera() + 1);
				}
			}
			
			tempo++;
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int escolherProcesso(int tempo) {
		int posMaior = 0;
		int prioridadeMaior = filaDeProcessos.get(0).getPrioridade();
		for(int i=0; i<filaDeProcessos.size(); i++) {
			if(filaDeProcessos.get(i).getPrioridade() > prioridadeMaior) {
				prioridadeMaior = filaDeProcessos.get(i).getPrioridade();
				posMaior = i;
			}
		}
		return posMaior;
	}
	
}
