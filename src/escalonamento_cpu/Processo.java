package escalonamento_cpu;

/**
 * Processo
 * @author Diego Winter
 */
public class Processo {
	
	private int instante;
	private int duracao;
	private int prioridade;
	private int estado; // 1 = executando, 2 = pronto
	private int primeiraExecucao;
	private int tempoEmEspera;
	
	public Processo(int instante, int duracao, int prioridade, int estado,
			int primeiraExecucao, int tempoEmEspera) {
		this.instante = instante;
		this.duracao = duracao;
		this.prioridade = prioridade;
		this.estado = estado;
		this.primeiraExecucao = primeiraExecucao;
		this.tempoEmEspera = tempoEmEspera;
	}

	public int getInstante() {
		return instante;
	}

	public void setInstante(int instante) {
		this.instante = instante;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getPrimeiraExecucao() {
		return primeiraExecucao;
	}

	public void setPrimeiraExecucao(int primeiraExecucao) {
		this.primeiraExecucao = primeiraExecucao;
	}

	public int getTempoEmEspera() {
		return tempoEmEspera;
	}

	public void setTempoEmEspera(int tempoEmEspera) {
		this.tempoEmEspera = tempoEmEspera;
	}

}
