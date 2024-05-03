package main.java.dao;

import main.java.domain.Produto;

import java.util.List;

public interface ProdutoDao {

    public Produto cadastrar(Produto produto);

    public void excluir(Produto produto);

    public List<Produto> buscarTodos();

    public Produto buscarPorId(Produto produto);
}
