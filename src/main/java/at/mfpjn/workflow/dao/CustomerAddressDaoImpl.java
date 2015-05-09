package at.mfpjn.workflow.dao;

import at.mfpjn.workflow.model.CustomerAddress;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
@Component("customerAddressDao")
public class CustomerAddressDaoImpl implements CustomerAddressDao{

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<CustomerAddress> getCustomerAddresses() {
		return session().createCriteria(CustomerAddress.class).list();
	}

	public CustomerAddress getCustomerAddress(int id) {
		return (CustomerAddress) session().get(CustomerAddress.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<CustomerAddress> getCustomerAddressPerCustomer(int customerId) {
//		Criteria criteria = session().createCriteria(Product.class);
//		return (List<CustomerAddress>)criteria.add(Restrictions.eq("customerFK", customerId)).list();
		return (List<CustomerAddress>)session().createQuery("from CustomerAddress where customerFK.id = :customerId")
		.setParameter("customerId", customerId).list();
	}
	
	public void updateCustomerAddress(CustomerAddress customerAddress){
		session().update(customerAddress);
	}
	
}
