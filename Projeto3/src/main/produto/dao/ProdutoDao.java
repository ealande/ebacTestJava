package main.produto.dao;

import main.cliente.domain.Cliente;
import main.produto.domain.Produto;

import java.sql.SQLException;
import java.util.List;

public interface ProdutoDao {
    Integer cadastrar(Produto produto) throws SQLException;

    Integer atualizar(Produto produto) throws SQLException;

    Produto buscar(String codigo) throws SQLException;

    List<Produto> buscarTodos() throws SQLException;

    void excluir(Produto produto) throws SQLException;

}
