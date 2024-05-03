package br.com.ebac.dao.generic;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.ebac.domain.Persistente;
import br.com.ebac.exceptions.DAOException;
import br.com.ebac.exceptions.MaisDeUmRegistroException;
import br.com.ebac.exceptions.TableException;
import br.com.ebac.exceptions.TipoChaveNaoEncontradaException;

public class GenericDAO <T extends Persistente, E extends Serializable> implements IGenericDAO <T,E> {

	protected Class<T> persistenteClass;
	
	@PersistenceContext
    protected EntityManager entityManager;
	
	public GenericDAO(Class<T> persistenteClass) {
		this.persistenteClass = persistenteClass;
	}
	
	@Override
	public T cadastrar(T entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public void excluir(T entity){
		if (entityManager.contains(entity)) {
            entityManager.remove(entity);
        } else {
            T managedCustomer = entityManager.find(this.persistenteClass, entity.getId());
            if (managedCustomer != null) {
                entityManager.remove(managedCustomer);
            }
        }
		
	}

	@Override
	public T alterar(T entity) {
		entity = entityManager.merge(entity);
		return entity;
	}

	@Override
	public T consultar(E valor) {
		return entityManager.find(this.persistenteClass, valor);
	}

	@Override
	public Collection<T> buscarTodos() {
		return entityManager.createQuery(getSelectSql(), this.persistenteClass).getResultList();
	}
	
	private String getSelectSql() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT obj FROM ");
		sb.append(this.persistenteClass.getSimpleName());
		sb.append(" obj");
		return sb.toString();
	}
}
