package escalonamento_cpu;

import java.util.ArrayList;
import java.util.Random;

public class Loteria {
	
	ArrayList<Processo> processos = new ArrayList<Processo>();
	ArrayList<Processo> filaDeProcessos = new ArrayList<Processo>();
	ArrayList<Integer> finalizados = new ArrayList<Integer>();
	
	public Loteria(ArrayList<Processo> processos) {
		this.processos = processos;
	}
	
	public void executar() {
		Random random = new Random();
		int tempo = 0;
		int qtdeProcessos = processos.size();
		int tempoRetorno = 0;
		int tempoResposta = 0;
		int tempoEspera = 0;
		
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
			System.out.println("=========");
			
			int sorteio = random.nextInt(filaDeProcessos.size());
			if(!finalizados.contains(sorteio)) {
				if(filaDeProcessos.get(sorteio).getPrimeiraExecucao() == -1) {
					filaDeProcessos.get(sorteio).setPrimeiraExecucao(tempo);
				}
				filaDeProcessos.get(sorteio).setDuracao(filaDeProcessos.get(sorteio).getDuracao() - 1);
				
				for(int i=0; i<filaDeProcessos.size(); i++) {
					if(i != sorteio) {
						filaDeProcessos.get(i).setTempoEmEspera(filaDeProcessos.get(i).getTempoEmEspera() + 1);
					}
				}
				
				if(filaDeProcessos.get(sorteio).getDuracao() == 0) {
					finalizados.add(sorteio);
					tempoRetorno += tempo - filaDeProcessos.get(sorteio).getInstante();
					tempoResposta += filaDeProcessos.get(sorteio).getPrimeiraExecucao() - filaDeProcessos.get(sorteio).getInstante();
					tempoEspera += filaDeProcessos.get(sorteio).getTempoEmEspera();
				}
			}
			
			if(filaDeProcessos.size() == finalizados.size()) {
				System.out.println("Loteria");
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
