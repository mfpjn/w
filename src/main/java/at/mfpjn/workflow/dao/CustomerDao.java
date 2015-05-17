package at.mfpjn.workflow.dao;


import at.mfpjn.workflow.model.Customer;

public interface CustomerDao {


	
	public void InsertCustomer(Customer customer);
	
	public Customer getUser(String email);

	public Customer getCustomer(int Id);
	
	public void updateUser(Customer customer);

}
