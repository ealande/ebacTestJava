/**
 *
 */
package main.java.br.com.rpires.dao;

import main.java.br.com.rpires.dao.generic.GenericDAO;
import main.java.br.com.rpires.domain.Venda;
import main.java.br.com.rpires.exceptions.DAOException;
import main.java.br.com.rpires.exceptions.TipoChaveNaoEncontradaException;

/**
 * @author rodrigo.pires
 */
public class VendaDAO extends GenericDAO<Venda, String> implements IVendaDAO {


    public VendaDAO() {
        super(Venda.class);
    }

    @Override
    public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException {
        super.alterar(venda);
    }

    @Override
    public void cancelarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException {
        super.alterar(venda);
    }
}
