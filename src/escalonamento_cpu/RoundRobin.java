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
	
	/**
	 * Executa o algoritmo round-robin na entrada.
	 * @return Resultados da execução.
	 */
	public String executar() {
		int tempo = 0;
		int qtdeProcessos = processos.size();
		int tempoRetorno = 0;
		int tempoResposta = 0;
		int tempoEspera = 0;
		boolean pendente = false;
		boolean removido = false;
		
		int a = 0; // Neste algoritmo a prioridade serve para dar um número de "id" ao processo.
		for (Processo processo : processos) {
			processo.setPrioridade(a++);
		}
		// Adiciona processos do instante 0
		for(int i=0; i<processos.size(); i++) {
			if(processos.get(i).getInstante() == tempo) filaDeProcessos.add(processos.get(i));
		}
		
		tempo++;
		// Atribui prioridades para processos que não o primeiro, do instante 0
		for(int i=1; i<filaDeProcessos.size(); i++) {
			filaDeProcessos.get(i).setTempoEmEspera(filaDeProcessos.get(i).getTempoEmEspera() + 1);
		}
		// Adiciona processos do instante 1
		for(int i=0; i<processos.size(); i++) {
			if(processos.get(i).getInstante() == tempo) filaDeProcessos.add(processos.get(i));
		}
		
		while(true) {
			// Registra a primeira execução
			if(filaDeProcessos.get(0).getPrimeiraExecucao() == -1)
				filaDeProcessos.get(0).setPrimeiraExecucao(tempo-1);
			
			// Verifica se está ou não no meio de um quantum
			if(pendente==true) {
				filaDeProcessos.get(0).setDuracao(filaDeProcessos.get(0).getDuracao() - 1);
				pendente = false;
			} else {
				filaDeProcessos.get(0).setDuracao(filaDeProcessos.get(0).getDuracao() - 1);
				pendente = true;
			}
			
			// Se um processo tiver terminado, pega seus dados e o remove
			if(filaDeProcessos.get(0).getDuracao() == 0) {
				tempoRetorno += tempo - filaDeProcessos.get(0).getInstante();
				tempoResposta += filaDeProcessos.get(0).getPrimeiraExecucao()  - filaDeProcessos.get(0).getInstante();;
				tempoEspera += filaDeProcessos.get(0).getTempoEmEspera();
				filaDeProcessos.remove(0);
				removido = true;
			}
			
			// Se um quantum de um processo tiver terminado (ou seja, não é mais pendente),
			// o coloca no final da fila.
			if(filaDeProcessos.size() != 0) {
				if((pendente == false) && (filaDeProcessos.get(0).getDuracao() != 0)
						&& removido == false) {
					Processo processo = filaDeProcessos.get(0);
					filaDeProcessos.remove(0);
					filaDeProcessos.add(processo);
				}
			}
			removido = false;
			
			// Se todos os processos tiverem terminado, calcula as médias.
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
			
			//exibirFilaDeProcessos(filaDeProcessos);
			
			// Registra o tempo de espera de um processo
			for(int i=1; i<filaDeProcessos.size(); i++) {
				filaDeProcessos.get(i).setTempoEmEspera(filaDeProcessos.get(i).getTempoEmEspera() + 1);
			}
			
			tempo++; // Incrementa o tempo
			// Adiciona processos do instante <tempo>
			for(int i=0; i<processos.size(); i++) {
				if(processos.get(i).getInstante() == tempo) filaDeProcessos.add(processos.get(i));
			}
		}
	}
	
	/**
	 * Se chamado, mostra a fila de processos atual na tela.
	 * @param ArrayList da fila de processos atual.
	 */
	public void exibirFilaDeProcessos(ArrayList<Processo> processos) {
		for (Processo processo : processos) {
			System.out.println("Ins: " + processo.getInstante() + " Dur: " + processo.getDuracao()
			+ " Prior: " + processo.getPrioridade() + " Estado: " + processo.getEstado()
			+ " Prim exec: " + processo.getPrimeiraExecucao() + " Espera: " + processo.getTempoEmEspera());
		}
		System.out.println("================================================");
	}
}