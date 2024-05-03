/**
 *
 */
package main.java.br.com.rpires.dao;

import main.java.br.com.rpires.dao.generic.GenericDAO;
import main.java.br.com.rpires.domain.Cliente;

/**
 * @author rodrigo.pires
 *
 */
public class ClienteDAO extends GenericDAO<Cliente, Long> implements IClienteDAO {

    public ClienteDAO() {
        super(Cliente.class);
    }
}
