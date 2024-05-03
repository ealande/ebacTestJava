package br.com.ebac.dao;

import java.util.List;

import br.com.ebac.dao.generic.IGenericDAO;
import br.com.ebac.domain.Cliente;
public interface IClienteDAO extends IGenericDAO<Cliente, Long>{

	List<Cliente> filtrarClientes(String query);

}
