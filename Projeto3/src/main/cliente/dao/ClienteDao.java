package main.cliente.dao;

import main.cliente.domain.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface ClienteDao {
    Integer cadastrar(Cliente cliente) throws SQLException;

    Integer atualizar(Cliente cliente) throws SQLException;

    Cliente buscar(String codigo) throws SQLException;

    List<Cliente> buscarTodos() throws SQLException;

    void excluir(Cliente cliente) throws SQLException;

}
