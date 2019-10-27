package escalonamento_cpu;

import java.util.ArrayList;

public class RoundRobin {
	
	ArrayList<Processo> processos = new ArrayList<Processo>();
	
	public RoundRobin(ArrayList<Processo> processos) {
		this.processos = processos;
	}
	
	public void executar() {
		/*
		 * Pendente: saber se considera o instante de chegada ou não.
		 */
		
		int tempo = 0;
		int qtdeProcessos = processos.size();
		int tempoRetorno = 0;
		int tempoResposta = 0;
		int tempoEspera = 0;
		while(true) {
			for (Processo processo : processos) {
				System.out.println(processo.getEstado() + " - "
						+ processo.getDuracao() + " - "
						+ processo.getPrioridade());
			}
			System.out.println("========");
			
			if(processos.get(0).getPrimeiraExecucao() == -1) {
				processos.get(0).setPrimeiraExecucao(tempo);
			}
			if(processos.get(0).getDuracao() > 1) {
				processos.get(0).setDuracao(processos.get(0).getDuracao() - 2);
				tempo+=2;
				for(int i=1; i<processos.size(); i++) {
					processos.get(i).setTempoEmEspera(processos.get(i).getTempoEmEspera() + 2);
				}
			} else {
				processos.get(0).setDuracao(processos.get(0).getDuracao() - 1);
				tempo++;
				for(int i=1; i<processos.size(); i++) {
					processos.get(i).setTempoEmEspera(processos.get(i).getTempoEmEspera() + 1);
				}
			}
			
			if(processos.get(0).getDuracao() == 0) {
				tempoRetorno += tempo;
				tempoResposta += processos.get(0).getPrimeiraExecucao();
				tempoEspera += processos.get(0).getTempoEmEspera();
				
				System.out.println("Morri: " + tempo);
				processos.remove(0);
				
			} else {
				Processo proc = processos.get(0);
				processos.remove(0);
				processos.add(proc);
			}
			
			if(processos.size() == 0) {
				System.out.println("RoundRobin");
				float trtm = (float)tempoRetorno / (float)qtdeProcessos;
				float trsm = (float)tempoResposta / (float)qtdeProcessos;
				float tesm = (float)tempoEspera / (float)qtdeProcessos;
				System.out.println("TRetM = " + trtm);
				System.out.println("TResM = " + trsm);
				System.out.println("TEspM = " + tesm);
				break;
			}
		}
	}
}
