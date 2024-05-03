/**
 * 
 */
package main.java.br.com.rpires.dao;

import main.java.br.com.rpires.dao.generic.GenericDAO;
import main.java.br.com.rpires.domain.Produto;

/**
 * @author rodrigo.pires
 *
 */
public class ProdutoDAO extends GenericDAO<Produto, String> implements IProdutoDAO {

	public ProdutoDAO() {
		super(Produto.class);
	}
}
