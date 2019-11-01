package substituicao_paginas;

public class Pagina {
	
	private int bitR;
	private int referencia;
	private int ultimoUso;
	
	public Pagina(int bitR, int referencia, int ultimoUso) {
		this.bitR = bitR;
		this.referencia = referencia;
		this.ultimoUso = ultimoUso;
	}
	
	public int getBitR() {
		return bitR;
	}
	
	public void setBitR(int bitR) {
		this.bitR = bitR;
	}
	
	public int getReferencia() {
		return referencia;
	}
	
	public void setReferencia(int referencia) {
		this.referencia = referencia;
	}

	public int getUltimoUso() {
		return ultimoUso;
	}

	public void setUltimoUso(int ultimoUso) {
		this.ultimoUso = ultimoUso;
	}

}
