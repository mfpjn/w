package at.mfpjn.workflow.dao;


import at.mfpjn.workflow.model.Customer;

public interface CustomerDao {


	
	void InsertCustomer(Customer customer);
	
	Customer getUser(String email);

	Customer getCustomer(int Id);
	
	void updateUser(Customer customer);

}
