package com.vendas.gestaovendas.entidades.dto;

import com.vendas.gestaovendas.entidades.Cliente;
import com.vendas.gestaovendas.entidades.Endereco;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Cliente Requisicao Dto")
public class ClienteRequisicaoDto {

	@ApiModelProperty(value = "Nome")
	private String nome;

	@ApiModelProperty(value = "Telefone")
	private String telefone;

	@ApiModelProperty(value = "Ativo")
	private Boolean ativo;

	private EnderecoRequisicaoDto enderecoRequisicaoDto;

	public ClienteRequisicaoDto() {

	}

	/**
	 * @param codigo
	 * @param nome
	 * @param telefone
	 * @param ativo
	 */

	public static Cliente converterParaCliente(ClienteRequisicaoDto dto) {

		EnderecoRequisicaoDto enderecoReqDto = dto.getEnderecoRequisicaoDto();
		Endereco endereco = Endereco.converterDtoParaEndereco(enderecoReqDto);

		return new Cliente(null, dto.getNome(), dto.getTelefone(), dto.getAtivo(), endereco);

	}

	/**
	 * @param codigo
	 * @param nome
	 * @param telefone
	 * @param ativo
	 * @param enderecoResponseDto
	 */
	public ClienteRequisicaoDto(String nome, String telefone, Boolean ativo,
			EnderecoRequisicaoDto enderecoRequisicaoDto) {
		this.nome = nome;
		this.telefone = telefone;
		this.ativo = ativo;
		this.enderecoRequisicaoDto = enderecoRequisicaoDto;
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

	public EnderecoRequisicaoDto getEnderecoRequisicaoDto() {
		return enderecoRequisicaoDto;
	}

	public void setEnderecoRequisicaoDto(EnderecoRequisicaoDto enderecoRequisicaoDto) {
		this.enderecoRequisicaoDto = enderecoRequisicaoDto;
	}

}
