/**
 * 
 */
package br.com.ebac.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.ebac.dao.IClienteDAO;
import br.com.ebac.domain.Cliente;
import br.com.ebac.exceptions.DAOException;
import br.com.ebac.exceptions.MaisDeUmRegistroException;
import br.com.ebac.exceptions.TableException;
import br.com.ebac.service.generic.GenericService;

/**
 * @author rodrigo.pires
 *
 */
@Stateless
public class ClienteService extends GenericService<Cliente, Long> implements IClienteService {
	
	private IClienteDAO clienteDAO;
	
	@Inject
	public ClienteService(IClienteDAO clienteDAO) {
		super(clienteDAO);
		this.clienteDAO = clienteDAO;
	}

	@Override
	public Cliente buscarPorCPF(Long cpf){
		try {
			return this.dao.consultar(cpf);
		} catch (MaisDeUmRegistroException | TableException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Cliente> filtrarClientes(String query) {
		return clienteDAO.filtrarClientes(query);
	}

}
