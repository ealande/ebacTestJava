package br.com.ebac.dao;

import br.com.ebac.dao.generic.IGenericDAO;
import br.com.ebac.domain.Venda;
import br.com.ebac.exceptions.DAOException;
import br.com.ebac.exceptions.TipoChaveNaoEncontradaException;

public interface IVendaDAO extends IGenericDAO<Venda, Long>{

	void finalizarVenda(Venda venda);
	
	void cancelarVenda(Venda venda);

	Venda consultarComCollection(Long id);

}
