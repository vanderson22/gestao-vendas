package com.vendas.gestaovendas.entidades.dto;

import java.math.BigDecimal;
import java.util.Objects;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ItensVendaDto {
	@ApiModelProperty(name = "Codigo")
	private Long codigo;
	@ApiModelProperty(name = "CodigoProduto")
	private Long codigoProduto;

	@ApiModelProperty(name = "Quantidade")
	private Integer quantidade;
	@ApiModelProperty(name = "PrecoVendido")
	private BigDecimal precoVendido;

	@ApiModelProperty(name = "Descricao")
	private String descricao;

	public ItensVendaDto() {
	}

	/**
	 * @param codigo
	 * @param codigoProduto
	 * @param quantidade
	 * @param precoVendido
	 * @param descricao
	 */
	public ItensVendaDto(Long codigo, Long codigoProduto, Integer quantidade, BigDecimal precoVendido,
			String descricao) {
		super();
		this.codigo = codigo;
		this.codigoProduto = codigoProduto;
		this.quantidade = quantidade;
		this.precoVendido = precoVendido;
		this.descricao = descricao;
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
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, codigoProduto, descricao, precoVendido, quantidade);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItensVendaDto other = (ItensVendaDto) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(codigoProduto, other.codigoProduto)
				&& Objects.equals(descricao, other.descricao) && Objects.equals(precoVendido, other.precoVendido)
				&& Objects.equals(quantidade, other.quantidade);
	}

}
