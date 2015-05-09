package at.mfpjn.workflow.dao;

import at.mfpjn.workflow.dao.AuthoritiesDao;
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
	public void insertAuthority(at.mfpjn.workflow.model.Authorities authority) {
		session().save(authority);
	}
	
	@Transactional
	public void updateAuthority(at.mfpjn.workflow.model.Authorities authority) {
		session().update(authority);
	}
	
	@SuppressWarnings("unchecked")
	public at.mfpjn.workflow.model.Authorities getByUsername(String username){
		return (at.mfpjn.workflow.model.Authorities)session().createQuery("from Authorities where Username = :username").setParameter("username", username).uniqueResult();
	}

}
