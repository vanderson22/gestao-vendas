package com.vendas.gestaovendas.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.vendas.gestaovendas.entidades.Cliente;
import com.vendas.gestaovendas.entidades.ItemVenda;
import com.vendas.gestaovendas.entidades.Venda;
import com.vendas.gestaovendas.entidades.dto.ClienteVendaRespostaDto;
import com.vendas.gestaovendas.entidades.dto.ClienteVendasRequisicaoDto;
import com.vendas.gestaovendas.excecao.RegraNegocioException;
import com.vendas.gestaovendas.repositorio.ClienteRepositorio;
import com.vendas.gestaovendas.repositorio.ItemVendaRepositorio;
import com.vendas.gestaovendas.repositorio.VendaRepositorio;

@Service
public class VendaServico {

	@Autowired
	private VendaRepositorio venRepositorio;

	@Autowired
	private ItemVendaRepositorio itemVendaRepositorio;

	@Autowired
	private ClienteRepositorio cliRepositorio;

	public List<Venda> listar() {

		return venRepositorio.findAll();
	}

	public Page<Venda> listarTodosPaginado(PageRequest req) {

		return venRepositorio.findAll(req);
	}

	public ClienteVendaRespostaDto salvar(Long codigoCliente, ClienteVendasRequisicaoDto vendaDto) {
		Cliente cli = validarClienteExiste(codigoCliente);

		ClienteVendasRequisicaoDto vendaValidadaDto = validarVendaCliente(cli.getCodigo(), vendaDto);
		Venda venda = Venda.converterDtoToVenda(vendaValidadaDto);
		venda.setCliente(cli);

		Venda vendaSalva = venRepositorio.save(venda);
		vendaDto.setCodigoVenda(vendaSalva.getCodigo());

		List<ItemVenda> iv = ItemVenda.converterDtoParaItemVenda(vendaDto);
		List<ItemVenda> ivSalvo = itemVendaRepositorio.saveAll(iv);

		return new ClienteVendaRespostaDto(vendaSalva.getData(), vendaSalva.getCodigo(), cli.getNome(), cli.getCodigo(),
				ClienteVendaRespostaDto.converterItemVendaToDto(ivSalvo));
	}

	private ClienteVendasRequisicaoDto validarVendaCliente(Long codigoCliente, ClienteVendasRequisicaoDto vendaDto) {

		if (vendaDto.getCodigoCliente() == null)
			throw new RegraNegocioException("Código do cliente na venda não pode ser nulo");

		if (!codigoCliente.equals(vendaDto.getCodigoCliente()))
			throw new RegraNegocioException(
					String.format("Os códigos do cliente informados são diferentes  :  %s  - %s ", codigoCliente,
							vendaDto.getCodigoCliente()));

		return vendaDto;
	}

	private Venda validarVendaExiste(Long codigo) {

		if (codigo == null)
			throw new RegraNegocioException("Código da venda não pode ser nulo");

		Optional<Venda> optional = venRepositorio.findById(codigo);

		if (!optional.isPresent())
			throw new RegraNegocioException("Código da venda informado não existe");

		return optional.get();
	}

	private Cliente validarClienteExiste(Long codigoCliente) {

		if (codigoCliente == null)
			throw new RegraNegocioException("Código do cliente não pode ser nulo");
		Optional<Cliente> optional = cliRepositorio.findById(codigoCliente);

		if (!optional.isPresent())
			throw new RegraNegocioException("Cliente não foi encontrado");

		return optional.get();

	}

}
