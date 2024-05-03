package br.com.ebac.dao;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.ebac.dao.generic.GenericDAO;
import br.com.ebac.domain.Cliente;
import br.com.ebac.domain.Produto;
import br.com.ebac.domain.Venda;
import br.com.ebac.exceptions.DAOException;

public class VendaDAO extends GenericDAO<Venda, Long> implements IVendaDAO {

	public VendaDAO() {
		super(Venda.class);
	}

	@Override
	public void finalizarVenda(Venda venda){
		super.alterar(venda);
	}

	@Override
	public void cancelarVenda(Venda venda){
		super.alterar(venda);
	}

	@Override
	public void excluir(Venda entity){
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
	}

	@Override
	public Venda cadastrar(Venda entity){
		try {
			entity.getProdutos().forEach(prod -> {
				Produto prodJpa = entityManager.merge(prod.getProduto());
				prod.setProduto(prodJpa);
			});
			Cliente cliente = entityManager.merge(entity.getCliente());
			entity.setCliente(cliente);
			entityManager.persist(entity);
			return entity;
		} catch (Exception e) {
			throw new DAOException("ERRO SALVANDO VENDA ", e);
		}
		
	}

	@Override
	public Venda consultarComCollection(Long id) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Venda> query = builder.createQuery(Venda.class);
		Root<Venda> root = query.from(Venda.class);
		root.fetch("cliente");
		root.fetch("produtos");
		query.select(root).where(builder.equal(root.get("id"), id));
		TypedQuery<Venda> tpQuery = 
				entityManager.createQuery(query);
		return tpQuery.getSingleResult();
	}
}
