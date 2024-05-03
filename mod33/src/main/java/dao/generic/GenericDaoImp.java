package main.java.dao.generic;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

public abstract class GenericDaoImp<T extends Persistente, E extends Serializable> implements
        GenericDao<T, E> {
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;
    private final Class<T> persistenteClass;

    @SuppressWarnings("unchecked")
    public GenericDaoImp() {
        this.persistenteClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public T cadastrar(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
        return entity;
    }


    @Override
    public void excluir(E valor) {
        entityManager.getTransaction().begin();
        valor = entityManager.merge(valor);
        entityManager.remove(valor);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public T consultar(E valor) {
        T valorEncontrado = entityManager.find(persistenteClass, valor);
        entityManager.close();
        entityManagerFactory.close();
        return valorEncontrado;
    }

    @Override
    public Collection<T> buscarTodos() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(persistenteClass);
        Root<T> root = query.from(persistenteClass);
        query.select(root);

        TypedQuery<T> tpQuery =
                entityManager.createQuery(query);
        List<T> list = tpQuery.getResultList();

        entityManager.close();
        entityManagerFactory.close();
        return list;
    }


}
