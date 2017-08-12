package com.emusicstore.dao;

import java.util.List;

import com.emusicstore.model.Customer;

public interface CustomerDao {
	
	public void addCustomer(Customer customer);
	public void deleteCustomer(Customer customer);
	public void editCustomer(Customer customer);
	public List<Customer> getAllCustomer();
	public Customer getCustomerById(int customerId);
	public Customer getCustomerByUsername(String username);
}
