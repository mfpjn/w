package at.mfpjn.workflow.dao;

import at.mfpjn.workflow.model.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
@Component("cityDao")
public class CityDaoImpl implements CityDao{

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	public List<City> getCities() {
		return session().createCriteria(City.class).list();
	}

	public City getCity(int id) {
		return (City) session().get(City.class, id);
	}
	
	public void insertCity(City city){
		session().save(city);
	}
	
	public void updateCity(City city){
		session().update(city);
	}
}
