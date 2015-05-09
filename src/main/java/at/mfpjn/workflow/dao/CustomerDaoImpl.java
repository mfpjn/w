package at.mfpjn.workflow.dao;

import at.mfpjn.workflow.model.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
@Component("customerDao")
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public void InsertCustomer(Customer customer) {
		session().save(customer);

	}

	public List<Customer> getCustomer() {
		return session().createQuery("from Customer").list();
	}

	public Customer getCustomer(int id) {
		return (Customer) session().createQuery("from Customer where id = :id")
				.setParameter("id", id).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public Customer getUser(String email) {
		List<Customer> userList = new ArrayList();
		Query query = session().createQuery(
				"from Customer c where c.email= :email");
		query.setParameter("email", email);
		try {
			userList = query.list();
		} catch (HibernateException e) {
			System.err.println("HibernateException in CustomerDaoImpl.getUser");
			return null;
		}

		if (userList.size() > 0)
			return userList.get(0);
		else
			return null;
	}

	@Transactional
	public void updateUser(Customer customer) {
		session().update(customer);
	}

}
