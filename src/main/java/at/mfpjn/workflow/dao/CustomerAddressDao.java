package at.mfpjn.workflow.dao;

import at.mfpjn.workflow.model.CustomerAddress;

import java.util.List;


public interface CustomerAddressDao {
	
	List<CustomerAddress> getCustomerAddresses();
	
	CustomerAddress getCustomerAddress(int id);
	
	public List<CustomerAddress> getCustomerAddressPerCustomer(int customerId);

	void updateCustomerAddress(CustomerAddress customerAddress);

	void insertCustomerAddress(CustomerAddress customerAddress);
	
	CustomerAddress getCustomerAddressByCustomerId(int id);

}
