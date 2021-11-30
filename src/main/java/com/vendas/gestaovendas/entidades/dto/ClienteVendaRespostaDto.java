package com.vendas.gestaovendas.entidades.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.vendas.gestaovendas.entidades.ItemVenda;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Cliente Venda resposta dto")
public class ClienteVendaRespostaDto {

	private LocalDate data;
	private Long codigoVenda;
	private String nome;
	private Long codigoCliente;

	private List<ItensVendaDto> itensVendaDto;

	public ClienteVendaRespostaDto() {
	}

	/**
	 * @param data
	 * @param codigoVenda
	 * @param nome
	 * @param codigoCliente
	 * @param itensVendaDto
	 */
	public ClienteVendaRespostaDto(LocalDate data, Long codigoVenda, String nome, Long codigoCliente,
			List<ItensVendaDto> itensVendaDto) {
		super();
		this.data = data;
		this.codigoVenda = codigoVenda;
		this.nome = nome;
		this.codigoCliente = codigoCliente;
		this.itensVendaDto = itensVendaDto;
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
	 * @return the codigoVenda
	 */
	public Long getCodigoVenda() {
		return codigoVenda;
	}

	/**
	 * @param codigoVenda the codigoVenda to set
	 */
	public void setCodigoVenda(Long codigoVenda) {
		this.codigoVenda = codigoVenda;
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
	 * @return the itensVendaDto
	 */
	public List<ItensVendaDto> getItensVendaDto() {
		return itensVendaDto;
	}

	/**
	 * @param itensVendaDto the itensVendaDto to set
	 */
	public void setItensVendaDto(List<ItensVendaDto> itensVendaDto) {
		this.itensVendaDto = itensVendaDto;
	}

	public static List<ItensVendaDto> converterItemVendaToDto(List<ItemVenda> ivSalvo) {

		return ivSalvo.stream().map(p -> new ItensVendaDto(p.getCodigo(), p.getProduto().getCodigo(), p.getQuantidade(),
				p.getPrecoVendido(), p.getProduto().getDescricao())).collect(Collectors.toList());

	}

}
