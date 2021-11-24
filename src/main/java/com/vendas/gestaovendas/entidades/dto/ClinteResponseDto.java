package com.vendas.gestaovendas.entidades.dto;

import com.vendas.gestaovendas.entidades.Cliente;
import com.vendas.gestaovendas.entidades.Endereco;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Cliente retorno Dto")
public class ClinteResponseDto {

	@ApiModelProperty(value = "Código")
	private Long codigo;

	@ApiModelProperty(value = "Nome")
	private String nome;

	@ApiModelProperty(value = "Telefone")
	private String telefone;

	@ApiModelProperty(value = "Ativo")
	private Boolean ativo;

	private EnderecoResponseDto enderecoResponseDto;

	public ClinteResponseDto() {

	}

	/**
	 * @param codigo
	 * @param nome
	 * @param telefone
	 * @param ativo
	 */

	public static ClinteResponseDto converterParaClienteResponseDto(Cliente cliente) {

		Endereco endereco = cliente.getEndereco();
		EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto(endereco.getNumero(),
				endereco.getLogradouro(), endereco.getEstado(), endereco.getCidade(), endereco.getCep(),
				endereco.getBairro());

		return new ClinteResponseDto(cliente.getCodigo(), cliente.getNome(), cliente.getTelefone(), cliente.getAtivo(),
				enderecoResponseDto);

	}

	/**
	 * @param codigo
	 * @param nome
	 * @param telefone
	 * @param ativo
	 * @param enderecoResponseDto
	 */
	public ClinteResponseDto(Long codigo, String nome, String telefone, Boolean ativo,
			EnderecoResponseDto enderecoResponseDto) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.telefone = telefone;
		this.ativo = ativo;
		this.enderecoResponseDto = enderecoResponseDto;
	}

	/**
	 * @return the codigo
	 */
	public Long getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * @param telefone the telefone to set
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * @return the ativo
	 */
	public Boolean getAtivo() {
		return ativo;
	}

	/**
	 * @param ativo the ativo to set
	 */
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	/**
	 * @return the enderecoResponseDto
	 */
	public EnderecoResponseDto getEnderecoResponseDto() {
		return enderecoResponseDto;
	}

	/**
	 * @param enderecoResponseDto the enderecoResponseDto to set
	 */
	public void setEnderecoResponseDto(EnderecoResponseDto enderecoResponseDto) {
		this.enderecoResponseDto = enderecoResponseDto;
	}

	
	
}
