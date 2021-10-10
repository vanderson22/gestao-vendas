package com.vendas.gestaovendas.excecao;

public class Erro {

	private String mensagemUsuario;
	private String mensagemDev;

	public Erro() {

	}

	/**
	 * @param mensagemUsuario
	 * @param mensagemDev
	 */
	public Erro(String mensagemUsuario, String mensagemDev) {
		super();
		this.mensagemUsuario = mensagemUsuario;
		this.mensagemDev = mensagemDev;
	}

	/**
	 * @return the mensagemUsuario
	 */
	public String getMensagemUsuario() {
		return mensagemUsuario;
	}

	/**
	 * @param mensagemUsuario the mensagemUsuario to set
	 */
	public void setMensagemUsuario(String mensagemUsuario) {
		this.mensagemUsuario = mensagemUsuario;
	}

	/**
	 * @return the mensagemDev
	 */
	public String getMensagemDev() {
		return mensagemDev;
	}

	/**
	 * @param mensagemDev the mensagemDev to set
	 */
	public void setMensagemDev(String mensagemDev) {
		this.mensagemDev = mensagemDev;
	}

}
