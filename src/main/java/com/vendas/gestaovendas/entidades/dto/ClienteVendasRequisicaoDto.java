package com.vendas.gestaovendas.entidades.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Cliente Venda Requisicao Dto")
public class ClienteVendasRequisicaoDto {

	@ApiModelProperty(value = "CodigoCliente")
	private Long codigoCliente;

	@ApiModelProperty(value = "Data")
	private LocalDate data;

	@ApiModelProperty(value = "ItensVendas")
	private List<ItemVendaRequisicaoDto> itens;

	@ApiModelProperty(value = "CodigoVenda")
	@JsonIgnore
	private Long codigoVenda;

	public ClienteVendasRequisicaoDto() {
	}

	/**
	 * @param codigoCliente
	 * @param data
	 * @param itens
	 */
	public ClienteVendasRequisicaoDto(Long codigoCliente, LocalDate data, List<ItemVendaRequisicaoDto> itens) {
		super();
		this.codigoCliente = codigoCliente;
		this.data = data;
		this.itens = itens;
	}

	/**
	 * @return the codigoCliente
	 */
	public Long getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(Long codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return the data
	 */
	public LocalDate getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(LocalDate data) {
		this.data = data;
	}

	/**
	 * @return the itens
	 */
	public List<ItemVendaRequisicaoDto> getItens() {
		return itens;
	}

	/**
	 * @param itens the itens to set
	 */
	public void setItens(List<ItemVendaRequisicaoDto> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigoCliente, data, itens);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteVendasRequisicaoDto other = (ClienteVendasRequisicaoDto) obj;
		return Objects.equals(codigoCliente, other.codigoCliente) && Objects.equals(data, other.data)
				&& Objects.equals(itens, other.itens);
	}

	public void setCodigoVenda(Long codigo) {
		this.codigoVenda = codigo;

	}

	public Long getCodigoVenda() {

		return codigoVenda;
	}

}
