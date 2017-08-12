package com.emusicstore.service;

import java.util.List;

import com.emusicstore.model.Customer;

public interface CustomerService {
	
	public void addCustomer(Customer customer);
	public void deleteCustomer(Customer customer);
	public void editCustomer(Customer customer);
	public Customer getCustomerById(int customerId);
	public List<Customer> getAllCustomer();
	public Customer getCustomerByUsername(String username);
	
}
