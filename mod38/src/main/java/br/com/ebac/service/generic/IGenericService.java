package br.com.ebac.service.generic;

import java.io.Serializable;
import java.util.Collection;

import br.com.ebac.domain.Persistente;
import br.com.ebac.exceptions.DAOException;
import br.com.ebac.exceptions.MaisDeUmRegistroException;
import br.com.ebac.exceptions.TableException;
import br.com.ebac.exceptions.TipoChaveNaoEncontradaException;

public interface IGenericService <T extends Persistente, E extends Serializable> {

    T cadastrar(T entity);

    void excluir(T entity);

    T alterar(T entity);

    T consultar(E valor);

    Collection<T> buscarTodos();

}
