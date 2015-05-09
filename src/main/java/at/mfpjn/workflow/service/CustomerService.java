package at.mfpjn.workflow.service;


import at.mfpjn.workflow.dao.CustomerDao;
import at.mfpjn.workflow.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("customerService")
public class CustomerService {


	@Autowired
	private CustomerDao customerDao;
	
	public void insertCustomer(Customer customer) {
		customerDao.InsertCustomer(customer);
	}
	
	public Customer getUser(String username){
		return customerDao.getUser(username);
	}
	
	public void updateUser(Customer customer){
		customerDao.updateUser(customer);
	}
	
	public Customer getCustomer(int customerid) {
		return customerDao.getCustomer(customerid);
	}
}
