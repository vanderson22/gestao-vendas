package com.vendas.gestaovendas.entidades;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vendas.gestaovendas.entidades.dto.ClienteVendasRequisicaoDto;

@Entity
@Table(name = "venda")
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	private Long codigo;

	@Column(name = "data")
	private LocalDate data;

//	1 venda = 1 cliente
//  1 cliente = N vendas    
	@ManyToOne()
	@JoinColumn(name = "codigo_cliente", referencedColumnName = "codigo")
	private Cliente cliente;

	public Venda() {
	}

	/**
	 * @param codigo
	 * @param data
	 * @param cliente
	 */
	public Venda(Long codigo, LocalDate data, Cliente cliente) {
		super();
		this.codigo = codigo;
		this.data = data;
		this.cliente = cliente;
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
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public static Venda converterDtoToVenda(ClienteVendasRequisicaoDto vendaDto) {

		return new Venda(null, vendaDto.getData(), new Cliente(vendaDto.getCodigoCliente(), null, null, null, null));

	}

}
