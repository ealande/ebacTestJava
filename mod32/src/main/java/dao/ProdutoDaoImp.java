package main.java.dao;

import main.java.domain.Produto;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.List;

public class ProdutoDaoImp implements ProdutoDao {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public ProdutoDaoImp() {
        this.entityManagerFactory =
                Persistence.createEntityManagerFactory("ExemploJPA");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Produto cadastrar(Produto produto) {

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
        return produto;
    }

    @Override
    public void excluir(Produto produto) {

        entityManager.getTransaction().begin();
        produto = entityManager.merge(produto);
        entityManager.remove(produto);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public List<Produto> buscarTodos() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
        Root<Produto> root = query.from(Produto.class);
        query.select(root);

        TypedQuery<Produto> tpQuery =
                entityManager.createQuery(query);
        List<Produto> list = tpQuery.getResultList();

        entityManager.close();
        entityManagerFactory.close();
        return list;
    }

    @Override
    public Produto buscarPorId(Produto produto) {
        Produto produtoEncontrado = entityManager.find(Produto.class, produto);
        entityManager.close();
        entityManagerFactory.close();
        return produtoEncontrado;
    }
}
