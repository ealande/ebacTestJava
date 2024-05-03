package br.com.ebac.dao.generic;

import java.io.Serializable;
import java.util.Collection;

import br.com.ebac.domain.Persistente;
import br.com.ebac.exceptions.DAOException;
import br.com.ebac.exceptions.MaisDeUmRegistroException;
import br.com.ebac.exceptions.TableException;
import br.com.ebac.exceptions.TipoChaveNaoEncontradaException;

public interface IGenericDAO <T extends Persistente, E extends Serializable> {

    public T cadastrar(T entity);

    public void excluir(T entity);

    public T alterar(T entity);

    public T consultar(E id);

    public Collection<T> buscarTodos();
}
