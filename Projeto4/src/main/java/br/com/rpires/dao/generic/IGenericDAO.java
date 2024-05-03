package main.java.br.com.rpires.dao.generic;

import main.java.br.com.rpires.dao.Persistente;
import main.java.br.com.rpires.exceptions.DAOException;

import java.io.Serializable;
import java.util.Collection;

public interface IGenericDAO<T extends Persistente, E extends Serializable> {

    T cadastrar(T entity);

    public void excluir(T entity) throws DAOException;

    T alterar(T entity);

    T consultar(E valor);

    Collection<T> buscarTodos();
}
