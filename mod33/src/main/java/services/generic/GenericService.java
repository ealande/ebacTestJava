/**
 *
 */
package main.java.services.generic;

import main.java.dao.generic.Persistente;

import java.io.Serializable;
import java.util.Collection;

public interface GenericService<T extends Persistente, E extends Serializable> {

    T cadastrar(T entity);

    void excluir(E valor);

    T consultar(E valor);

    Collection<T> buscarTodos();

}
