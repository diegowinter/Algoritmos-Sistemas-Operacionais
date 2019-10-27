package escalonamento_cpu;

import java.util.ArrayList;

public class RoundRobin {
	
	ArrayList<Processo> processos = new ArrayList<Processo>();
	ArrayList<Processo> filaDeProcessos = new ArrayList<Processo>();
	
	public RoundRobin(ArrayList<Processo> processos) {
		this.processos = processos;
	}
	
	public void executar() {
		int tempo = 0;
		int qtdeProcessos = processos.size();
		int tempoRetorno = 0;
		int tempoResposta = 0;
		int tempoEspera = 0;
		boolean pendente = false;
		
		int a = 0;
		for (Processo processo : processos) {
			processo.setPrioridade(a++);
		}
		
		while(true) {
			for(int i=0; i<processos.size(); i++) {
				if(processos.get(i).getInstante() == tempo) {
					filaDeProcessos.add(processos.get(i));
				}
			}
			
			for (Processo processo : filaDeProcessos) {
				System.out.println(processo.getEstado() + " - "
						+ processo.getDuracao() + " - "
						+ processo.getPrioridade());
			}
			System.out.println("========");
			
			if(filaDeProcessos.get(0).getPrimeiraExecucao() == -1) {
				filaDeProcessos.get(0).setPrimeiraExecucao(tempo);
			}
			
			if(pendente == false) {
				if(filaDeProcessos.get(0).getDuracao() > 1) {
					pendente = true;
					filaDeProcessos.get(0).setDuracao(filaDeProcessos.get(0).getDuracao() - 1);
					for(int i=1; i<filaDeProcessos.size(); i++) {
						filaDeProcessos.get(i).setTempoEmEspera(filaDeProcessos.get(i).getTempoEmEspera() + 1);
					}
				} else {
					filaDeProcessos.get(0).setDuracao(filaDeProcessos.get(0).getDuracao() - 1);
					for(int i=1; i<filaDeProcessos.size(); i++) {
						filaDeProcessos.get(i).setTempoEmEspera(filaDeProcessos.get(i).getTempoEmEspera() + 1);
					}
					if(filaDeProcessos.get(0).getDuracao() == 0) {
						tempoRetorno += (tempo + 1) - filaDeProcessos.get(0).getInstante();
						tempoResposta += filaDeProcessos.get(0).getPrimeiraExecucao()  - filaDeProcessos.get(0).getInstante();;
						tempoEspera += filaDeProcessos.get(0).getTempoEmEspera();
						System.out.println("nasci: "+ filaDeProcessos.get(0).getInstante() + " morri: " + (tempo+1));
						filaDeProcessos.remove(0);
					} else {
						Processo processo = filaDeProcessos.get(0);
						filaDeProcessos.remove(0);
						filaDeProcessos.add(processo);
					}
				}
			} else {
				filaDeProcessos.get(0).setDuracao(filaDeProcessos.get(0).getDuracao() - 1);
				for(int i=1; i<filaDeProcessos.size(); i++) {
					filaDeProcessos.get(i).setTempoEmEspera(filaDeProcessos.get(i).getTempoEmEspera() + 1);
				}
				pendente = false;
				if(filaDeProcessos.get(0).getDuracao() == 0) {
					tempoRetorno += (tempo + 1) - filaDeProcessos.get(0).getInstante();
					tempoResposta += filaDeProcessos.get(0).getPrimeiraExecucao()  - filaDeProcessos.get(0).getInstante();;
					tempoEspera += filaDeProcessos.get(0).getTempoEmEspera();
					System.out.println("nasci: "+ filaDeProcessos.get(0).getInstante() + " morri: " + (tempo+1));
					filaDeProcessos.remove(0);
				} else {
					Processo processo = filaDeProcessos.get(0);
					filaDeProcessos.remove(0);
					filaDeProcessos.add(processo);
				}
			}
			
			if(filaDeProcessos.size() == 0) {
				System.out.println("RoundRobin");
				System.out.println(tempoRetorno);
				float trtm = (float)tempoRetorno / (float)qtdeProcessos;
				float trsm = (float)tempoResposta / (float)qtdeProcessos;
				float tesm = (float)tempoEspera / (float)qtdeProcessos;
				System.out.println("TRetM = " + trtm);
				System.out.println("TResM = " + trsm);
				System.out.println("TEspM = " + tesm);
				break;
			}
			
			tempo++;
		}
	}
}
