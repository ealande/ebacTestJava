package dao;

import main.java.br.com.rpires.dao.IClienteDAO;
import main.java.br.com.rpires.domain.Cliente;
import main.java.br.com.rpires.exceptions.DAOException;
import main.java.br.com.rpires.exceptions.TipoChaveNaoEncontradaException;

import java.util.Collection;

public class ClienteDaoMock implements IClienteDAO {

    @Override
    public Cliente cadastrar(Cliente entity) {
        return new Cliente();
    }

    @Override
    public void excluir(Cliente entity) throws DAOException {
        // TODO Auto-generated method stub
    }

    @Override
    public Cliente alterar(Cliente entity) throws TipoChaveNaoEncontradaException {
        return new Cliente();
    }

    @Override
    public Cliente consultar(Long valor) {
        Cliente cliente = new Cliente();
        cliente.setCpf(valor);
        return cliente;
    }

    @Override
    public Collection<Cliente> buscarTodos() {
        // TODO Auto-generated method stub
        return null;
    }


}
