package br.com.ebac.service;

import java.util.List;

import br.com.ebac.domain.Produto;
import br.com.ebac.service.generic.IGenericService;

public interface IProdutoService extends IGenericService<Produto, String> {

	List<Produto> filtrarProdutos(String query);

}
