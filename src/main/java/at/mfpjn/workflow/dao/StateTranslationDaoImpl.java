package at.mfpjn.workflow.dao;

import at.mfpjn.workflow.model.State;
import at.mfpjn.workflow.model.StateTranslation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@Component("stateTranslationDao")
public class StateTranslationDaoImpl implements StateTranslationDao{

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	public List<StateTranslation> getStates(String language) {
		return session()
				.createQuery("from StateTranslation where language = :language")
				.setParameter("language", language).list();
	}

	public StateTranslation getState(int id) {
		return (StateTranslation) session()
				.createQuery("from StateTranslation where id = :id")
				.setParameter("id", id).uniqueResult();
	}
	public StateTranslation getStateByLanguageAndId(String language, State state_fk) {
		return (StateTranslation) session()
				.createQuery("from StateTranslation where language = :language and state_fk=:state_fk")
				.setParameter("language", language).setParameter("state_fk", state_fk).uniqueResult();
	}
}
