package br.com.springmvc.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.springmvc.model.Product;

@Repository// Indicar que esta classe é um DAO.
public class ProductDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void save(Product poduct){
		manager.persist(poduct);	
	}
	
	public List<Product> list() {
		return manager.createQuery("select distinct p from Product p join fetch p.prices", 
				Product.class).getResultList();
	}
	
	public Product find(Integer id) {
		TypedQuery<Product> query = manager
				.createQuery(
						"select distinct(p) from Product p join fetch p.prices where p.id=:id",
						Product.class).setParameter("id", id);
		return query.getSingleResult();
	}
	
	public void delete(Product product){
		manager.remove(product);
	}
}
