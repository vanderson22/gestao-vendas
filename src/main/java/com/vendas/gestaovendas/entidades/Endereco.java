package com.vendas.gestaovendas.entidades;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;
import com.vendas.gestaovendas.entidades.dto.EnderecoRequisicaoDto;

@Embeddable
//atenção, não é uma entidade
public class Endereco {

	@NotNull()
	@Column(name = "numero")
	private Long numero;

	@Length(max = 60, message = "Logradouro")
	@NotNull
	@Column(name = "logradouro")
	private String logradouro;

	@NotNull
	@Pattern(regexp = "[A-Z]{2}", message = "Estado")
	@Column(name = "estado")
	private String estado;

	@NotNull
	@Column(name = "cidade")
	private String cidade;

	@NotNull
	@Pattern(regexp = "[\\d]{5}-[\\d]{3}", message = "Cep")
	@Column(name = "cep")
	private String cep;

	@NotNull
	@Column(name = "bairro")
	private String bairro;

	public Endereco() {
	}

	/**
	 * @param numero
	 * @param logradouro
	 * @param estado
	 * @param cidade
	 * @param cep
	 * @param bairro
	 */
	public Endereco(Long numero, String logradouro, String estado, String cidade, String cep, String bairro) {
		this.numero = numero;
		this.logradouro = logradouro;
		this.estado = estado;
		this.cidade = cidade;
		this.cep = cep;
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
		Endereco other = (Endereco) obj;
		return Objects.equals(bairro, other.bairro) && Objects.equals(cep, other.cep)
				&& Objects.equals(cidade, other.cidade) && Objects.equals(estado, other.estado)
				&& Objects.equals(logradouro, other.logradouro) && Objects.equals(numero, other.numero);
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

	/**
	 * @param enderecoReqDto 
	 * @return converte a entrada do dto para um novo objeto de endereço
	 */
	public static Endereco converterDtoParaEndereco(EnderecoRequisicaoDto enderecoReqDto) {

		return new Endereco(enderecoReqDto.getNumero(), enderecoReqDto.getLogradouro(), enderecoReqDto.getEstado(),
				enderecoReqDto.getCidade(), enderecoReqDto.getCep(), enderecoReqDto.getBairro());

	}

}
