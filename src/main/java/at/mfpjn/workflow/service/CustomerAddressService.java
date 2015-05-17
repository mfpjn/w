package at.mfpjn.workflow.service;

import at.mfpjn.workflow.dao.CustomerAddressDao;
import at.mfpjn.workflow.model.CustomerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("customerAddressService")
public class CustomerAddressService {

	@Autowired
	private CustomerAddressDao customerAddressDao;

	public List<CustomerAddress> getCustomerAddresses() {
		return customerAddressDao.getCustomerAddresses();
	}

	public CustomerAddress getCustomerAddress(int idCustomerAddress) {
		return customerAddressDao.getCustomerAddress(idCustomerAddress);
	}
	
	public List<CustomerAddress> getCustomerAddressPerCustomer(int customerId) {
		return customerAddressDao.getCustomerAddressPerCustomer(customerId);
	}
	
	public void insertCustomerAddress(CustomerAddress customerAddress){
		customerAddressDao.insertCustomerAddress(customerAddress);
	}
	
	public void updateCustomerAddress(CustomerAddress customerAddress){
		customerAddressDao.updateCustomerAddress(customerAddress);
	}
	
	public CustomerAddress getCustomerAddressByCustomerId(int id){
		return customerAddressDao.getCustomerAddressByCustomerId(id);
	}
}
