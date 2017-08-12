package com.emusicstore.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.emusicstore.dao.CustomerOrderDao;
import com.emusicstore.model.CustomerOrder;

@Repository
@Transactional
public class CustomerOrderDaoImpl implements CustomerOrderDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void addCustomerOrder(CustomerOrder customerOrder) {
		session().saveOrUpdate(customerOrder);
		session().flush();
	}
}
