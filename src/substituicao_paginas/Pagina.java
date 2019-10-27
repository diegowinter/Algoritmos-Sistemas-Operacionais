package substituicao_paginas;

public class Pagina {
	
	private int bitR;
	private int referencia;
	
	public Pagina(int bitR, int referencia) {
		this.bitR = bitR;
		this.referencia = referencia;
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

}
