package at.mfpjn.workflow.dao;

import at.mfpjn.workflow.model.Authorities;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("authoritiesDao")
public class AuthoritiesDaoImpl implements AuthoritiesDao {

	@Autowired
	private SessionFactory sessionFactoryMain;

	public Session session() {
		return sessionFactoryMain.getCurrentSession();
	}
	
	@Transactional
	public void insertAuthority(Authorities authority) {
		session().save(authority);
	}
	
	@Transactional
	public void updateAuthority(Authorities authority) {
		session().update(authority);
	}
	
	@SuppressWarnings("unchecked")
	public Authorities getByUsername(String username){
		return (Authorities)session().createQuery("from Authorities where Username = :username").setParameter("username", username).uniqueResult();
	}

}
