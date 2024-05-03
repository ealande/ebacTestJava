package main.java.dao.generic;

import java.io.Serializable;
import java.util.Collection;

public interface GenericDao<T extends Persistente, E extends Serializable> {
    T cadastrar(T entity);
    void excluir(E valor);
    T consultar(E valor);

    Collection<T> buscarTodos();
}
