/**
 *
 */
package main.java.services.generic;

import main.java.dao.generic.GenericDao;
import main.java.dao.generic.Persistente;
import main.java.exceptions.MaisDeUmRegistroException;
import main.java.exceptions.TableException;

import java.io.Serializable;
import java.util.Collection;

public abstract class GenericServiceImp<T extends Persistente, E extends Serializable>
        implements GenericService<T, E> {

    protected GenericDao<T, E> dao;

    public GenericServiceImp(GenericDao<T, E> dao) {
        this.dao = dao;
    }

    @Override
    public T cadastrar(T entity) {
        return this.dao.cadastrar(entity);
    }

    @Override
    public void excluir(E valor) {
        this.dao.excluir(valor);
    }

    @Override
	public T consultar(E valor) {
        try {
            return this.dao.consultar(valor);
        } catch (MaisDeUmRegistroException | TableException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<T> buscarTodos() {
        return this.dao.buscarTodos();
    }

}
