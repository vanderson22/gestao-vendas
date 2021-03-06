package com.vendas.gestaovendas.entidades.dto;

import java.util.Objects;

import javax.persistence.Column;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Endereco Response Dto")
public class EnderecoRequisicaoDto {

	@ApiModelProperty(value = "Numero")
	private Long numero;

	@ApiModelProperty(value = "Logradouro")
	private String logradouro;

	@ApiModelProperty(value = "Estado")
	private String estado;

	@ApiModelProperty(value = "cidade")
	@Column(name = "cidade")
	private String cidade;

	@ApiModelProperty(value = "Cep")
	private String cep;

	@Column(name = "Bairro")
	private String bairro;

	public EnderecoRequisicaoDto() {
	}

	/**
	 * @param numero
	 * @param logradouro
	 * @param estado
	 * @param cidade
	 * @param cep
	 * @param bairro
	 */
	public EnderecoRequisicaoDto(Long numero, String logradouro, String estado, String cidade, String cep,
			String bairro) {
		this.numero = numero;
		this.logradouro = logradouro;
		this.estado = estado;
		this.cidade = cidade;
		this.cep = cep;
		this.bairro = bairro;
	}

	/**
	 * @return the numero
	 */
	public Long getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(Long numero) {
		this.numero = numero;
	}

	/**
	 * @return the logradouro
	 */
	public String getLogradouro() {
		return logradouro;
	}

	/**
	 * @param logradouro the logradouro to set
	 */
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the cidade
	 */
	public String getCidade() {
		return cidade;
	}

	/**
	 * @param cidade the cidade to set
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/**
	 * @return the cep
	 */
	public String getCep() {
		return cep;
	}

	/**
	 * @param cep the cep to set
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}

	/**
	 * @return the bairro
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * @param bairro the bairro to set
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bairro, cep, cidade, estado, logradouro, numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnderecoRequisicaoDto other = (EnderecoRequisicaoDto) obj;
		return Objects.equals(bairro, other.bairro) && Objects.equals(cep, other.cep)
				&& Objects.equals(cidade, other.cidade) && Objects.equals(estado, other.estado)
				&& Objects.equals(logradouro, other.logradouro) && Objects.equals(numero, other.numero);
	}

}
