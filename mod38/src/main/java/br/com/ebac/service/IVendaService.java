package br.com.ebac.service;

import br.com.ebac.dao.generic.IGenericDAO;
import br.com.ebac.domain.Venda;
import br.com.ebac.exceptions.DAOException;
import br.com.ebac.exceptions.TipoChaveNaoEncontradaException;

public interface IVendaService extends IGenericDAO<Venda, Long>{

	public void finalizarVenda(Venda venda);
	
	public void cancelarVenda(Venda venda);

	public Venda consultarComCollection(Long id);

}
