/**
 * 
 */
package main.java.br.com.rpires.services.generic;

import main.java.br.com.rpires.dao.Persistente;
import main.java.br.com.rpires.dao.generic.IGenericDAO;
import main.java.br.com.rpires.exceptions.MaisDeUmRegistroException;
import main.java.br.com.rpires.exceptions.TableException;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author rodrigo.pires
 *
 */
public abstract class GenericService<T extends Persistente, E extends Serializable>
	implements IGenericService<T, E> {
	
	protected IGenericDAO<T,E> dao;
	
	public GenericService(IGenericDAO<T,E> dao) {
		this.dao = dao;
	}

	@Override
	public Boolean cadastrar(T entity){
		return this.dao.cadastrar(entity);
	}

	@Override
	public void excluir(E valor){
		this.dao.excluir(valor);
	}

	@Override
	public void alterar(T entity){
		this.dao.alterar(entity);
	}

	@Override
	public T consultar(E valor){
		try {
			return this.dao.consultar(valor);
		} catch (MaisDeUmRegistroException | TableException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Collection<T> buscarTodos(){
		return this.dao.buscarTodos();
	}

}
