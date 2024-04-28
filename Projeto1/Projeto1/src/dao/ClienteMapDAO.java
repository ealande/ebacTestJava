package dao;

import domain.Cliente;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;


public class ClienteMapDAO implements IClienteDAO {
    private final Map<Long, Cliente> map;


    public ClienteMapDAO() {
        map = new TreeMap<>();
    }


    @Override
    public Boolean cadastrar(Cliente cliente) {
        if (this.map.containsKey(cliente.getCpf())) {
            return false;
        }

        this.map.put(cliente.getCpf(), cliente);
        return true;
    }

    @Override
    public void excluir(long cpf) {
        Cliente clienteCadastrado = this.map.get(cpf);
        this.map.remove(clienteCadastrado.getCpf(), clienteCadastrado);
    }

    @Override
    public void alterar(Cliente cliente) {
        this.excluir(cliente.getCpf());
        this.cadastrar(cliente);
    }

    @Override
    public Cliente consultar(long cpf) {
        return this.map.get(cpf);
    }

    @Override
    public Collection<Cliente> buscarTodos() {
        return this.map.values();
    }
}
