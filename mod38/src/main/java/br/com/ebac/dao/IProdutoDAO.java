package br.com.ebac.dao;

import java.util.List;

import br.com.ebac.dao.generic.IGenericDAO;
import br.com.ebac.domain.Produto;
public interface IProdutoDAO extends IGenericDAO<Produto, String>{

	List<Produto> filtrarProdutos(String query);

}
