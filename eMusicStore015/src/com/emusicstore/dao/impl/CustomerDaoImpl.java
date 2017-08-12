package com.emusicstore.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.emusicstore.dao.CustomerDao;
import com.emusicstore.model.Authorities;
import com.emusicstore.model.Cart;
import com.emusicstore.model.Customer;
import com.emusicstore.model.Users;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	 public Session session() {
		 return sessionFactory.getCurrentSession();
	 }
	
	@Override
	public void addCustomer(Customer customer) {
		customer.getBillingAddress().setCustomer(customer);
		customer.getShippingAddress().setCustomer(customer);
		
		session().saveOrUpdate(customer);
		session().saveOrUpdate(customer.getBillingAddress());
		session().saveOrUpdate(customer.getShippingAddress());

		Users newUser=new Users();
		newUser.setUserName(customer.getCustomerName());
		newUser.setPassword(customer.getPassword());
		newUser.setEnabled(true);
		newUser.setCustomerId(customer.getCustomerId());
		
		Authorities newAuthority =new Authorities();
		newAuthority.setUsername(customer.getCustomerName());
		newAuthority.setAuthority("ROLE_USER");
		session().saveOrUpdate(newUser);
		session().saveOrUpdate(newAuthority);
		
		Cart newCart=new Cart();
		newCart.setCustomer(customer);
		customer.setCart(newCart);
		session().saveOrUpdate(customer);
		session().saveOrUpdate(newCart);
		session().flush();
		
	}

	@Override
	public void deleteCustomer(Customer customer) {
		session().delete(customer.getBillingAddress());
		session().delete(customer.getShippingAddress());
		session().delete(customer.getCart());
		
		Users user=new Users();
		user.setUserName(customer.getCustomerName());
		user.setPassword(customer.getPassword());
		user.setEnabled(true);
		user.setCustomerId(customer.getCustomerId());
		Authorities authority =new Authorities();
		authority.setUsername(customer.getCustomerName());
		authority.setAuthority("ROLE_USER");
		session().delete(authority);
		session().delete(user);
		session().delete(customer);
		session().flush();
		
	}

	@Override
	public void editCustomer(Customer customer) {
		customer.getBillingAddress().setCustomer(customer);
		customer.getShippingAddress().setCustomer(customer);
		session().saveOrUpdate(customer);
		session().flush();
	}

	@Override
	public List<Customer> getAllCustomer() {
		Query query = session().createQuery("from Customer");
		@SuppressWarnings("unchecked")
		List<Customer> customerList = query.list();
		session().flush();
		return customerList;
	
	}

	@Override
	public Customer getCustomerById(int customerId) {
		return (Customer) session().get(Customer.class, customerId);
	}

	@Override
	public Customer getCustomerByUsername(String username) {
		Query query=session().createQuery("from Customer where username=?");
		query.setString(0,username);
		return (Customer) query.uniqueResult();
	}
	

}
