package escalonamento_cpu;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Algoritmo de escalonamento da CPU Round-robin
 * @author Diego Winter
 *
 */
public class RoundRobin {
	
	ArrayList<Processo> processos = new ArrayList<Processo>();
	ArrayList<Processo> filaDeProcessos = new ArrayList<Processo>();
	
	public RoundRobin(ArrayList<Processo> processos) {
		this.processos = processos;
	}
	
	public String executar() {
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
					filaDeProcessos.remove(0);
				} else {
					Processo processo = filaDeProcessos.get(0);
					filaDeProcessos.remove(0);
					filaDeProcessos.add(processo);
				}
			}
			
			if(filaDeProcessos.size() == 0) {
				float tempoRetornoMedio = (float)tempoRetorno / (float)qtdeProcessos;
				float tempoRespostaMedio = (float)tempoResposta / (float)qtdeProcessos;
				float tempoEsperaMedio = (float)tempoEspera / (float)qtdeProcessos;
				
				DecimalFormatSymbols separador = new DecimalFormatSymbols(Locale.GERMAN);
				separador.setDecimalSeparator('.');
				DecimalFormat decimalFormat = new DecimalFormat("#.##", separador);
				
			    String resultado = decimalFormat.format(tempoRetornoMedio) + " "
			    		+ decimalFormat.format(tempoRespostaMedio) + " "
			    		+ decimalFormat.format(tempoEsperaMedio);
				
				return resultado;
			}
			
			tempo++;
		}
	}
}
