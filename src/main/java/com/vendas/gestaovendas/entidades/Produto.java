package com.vendas.gestaovendas.entidades;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name = "descricao")
	@NotBlank(message = "descricao")
	@Length(min = 3, message = "Não pode ter menos de 3 caracteres ")
	private String descricao;

	@NotNull(message = "A quantidade não pode ser nula")
	@Column(name = "quantidade")
	private Integer quantidade;

	@NotNull(message = "precoCusto - O campo não pode ser nulo")
	@Column(name = "preco_curto")
	private BigDecimal precoCusto;

	@NotNull(message = "precoVenda - O campo não pode ser nulo")
	@Column(name = "preco_venda")
	private BigDecimal precoVenda;

	@NotNull(message = "observacao - O campo não pode ser nulo")
	@Column(name = "observacao")
	@Length(min = 3, message = "Não pode ter menos de 3 caracteres - %1")
	private String observacao;

	@ManyToOne
	@JoinColumn(name = "codigo_categoria", referencedColumnName = "codigo")
	@NotNull(message = "Categoria - O campo não pode ser nulo")
	private Categoria categoria;

	public Produto() {
	}

	/**
	 * @param codigo
	 * @param descricao
	 * @param quantidade
	 * @param precoCusto
	 * @param precoVenda
	 * @param observacao
	 * @param categoria
	 */
	public Produto(Long codigo, String descricao, Integer quantidade, BigDecimal precoCusto, BigDecimal precoVenda,
			String observacao) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.precoCusto = precoCusto;
		this.precoVenda = precoVenda;
		this.observacao = observacao;

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
	 * @return the precoCusto
	 */
	public BigDecimal getPrecoCusto() {
		return precoCusto;
	}

	/**
	 * @param precoCusto the precoCusto to set
	 */
	public void setPrecoCusto(BigDecimal precoCusto) {
		this.precoCusto = precoCusto;
	}

	/**
	 * @return the precoVenda
	 */
	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}

	/**
	 * @param precoVenda the precoVenda to set
	 */
	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}

	/**
	 * @return the observacao
	 */
	public String getObservacao() {
		return observacao;
	}

	/**
	 * @param observacao the observacao to set
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	/**
	 * @return the categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", descricao=" + descricao + ", quantidade=" + quantidade + ", precoCusto="
				+ precoCusto + ", precoVenda=" + precoVenda + ", observacao=" + observacao + ", categoria=" + categoria
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoria, codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(categoria, other.categoria) && codigo == other.codigo;
	}

}
