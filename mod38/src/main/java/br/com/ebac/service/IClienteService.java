package br.com.ebac.service;

import java.util.List;

import br.com.ebac.domain.Cliente;
import br.com.ebac.exceptions.DAOException;
import br.com.ebac.service.generic.IGenericService;

public interface IClienteService extends IGenericService<Cliente, Long> {

	Cliente buscarPorCPF(Long cpf) throws DAOException;

	List<Cliente> filtrarClientes(String query);

}
