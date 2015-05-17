package at.mfpjn.workflow.dao;

import at.mfpjn.workflow.model.State;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
@Component("stateDao")
public class StateDaoImpl implements StateDao{

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	public List<State> getStates() {
		return session().createQuery("from State").list();
	}

	public State getState(int id) {
		return (State) session()
				.createQuery("from State where id = :id")
				.setParameter("id", id).uniqueResult();
	}
	
	public State getStateByName(String name){
		return (State)session().createQuery("from State where name = :name").setParameter("name", name).uniqueResult();				
	}
}
