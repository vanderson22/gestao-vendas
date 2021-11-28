package com.vendas.gestaovendas.entidades.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Item Venda requisicao dto")
public class ItemVendaRequisicaoDto {

	@ApiModelProperty(name = "Quantidade")
	private Integer quantidade;

	@ApiModelProperty(name = "precoVendido")
	private BigDecimal precoVendido;

	@ApiModelProperty(name = "CodigoProduto", notes = "Código do produto")
	private Long codigoProduto;

	public ItemVendaRequisicaoDto() {
	}

	/**
	 * @param quantidade
	 * @param precoVendido
	 * @param codigoProduto
	 * @param codigoVenda
	 */
	public ItemVendaRequisicaoDto(Integer quantidade, BigDecimal precoVendido, Long codigoProduto) {
		super();
		this.quantidade = quantidade;
		this.precoVendido = precoVendido;
		this.codigoProduto = codigoProduto;

	}

	/**
	 * @return the quantidade
	 */
	public Integer getQuantidade() {
		return quantidade;
	}

	/**
	 * @param quantidade the quantidade to set
	 */
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * @return the precoVendido
	 */
	public BigDecimal getPrecoVendido() {
		return precoVendido;
	}

	/**
	 * @param precoVendido the precoVendido to set
	 */
	public void setPrecoVendido(BigDecimal precoVendido) {
		this.precoVendido = precoVendido;
	}

	/**
	 * @return the codigoProduto
	 */
	public Long getCodigoProduto() {
		return codigoProduto;
	}

	/**
	 * @param codigoProduto the codigoProduto to set
	 */
	public void setCodigoProduto(Long codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

}
