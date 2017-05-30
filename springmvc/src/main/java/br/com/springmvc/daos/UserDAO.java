package br.com.springmvc.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.springmvc.model.SystemUser;


@Repository
public class UserDAO implements UserDetailsService{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	//o nome do m�todo � loadUserByUsername, justamente	o que a interface nos obriga implementar.
	//Ele nos for�a a retornar um objeto que implementa a interface UserDetails.
	//Voc� simplesmente busca pelo login e o Spring Security vai verificar se a senha cadastrada bate com a senha com que foi enviada pelo formul�rio.
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String jpql = "select u from SystemUser u where u.login = :login";
		List<SystemUser> users = em.createQuery(jpql,SystemUser.class).setParameter("login", username).getResultList();
		if(users.isEmpty()){
			throw new UsernameNotFoundException("O usuario "+username+" n�o existe");
		}
		return users.get(0);
	}

}