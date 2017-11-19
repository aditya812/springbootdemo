package com.example.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractDAO {

	@Autowired
	public SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public Criteria createCustomCriteria(Class<?> pojo){
		Criteria criteria = getSession().createCriteria(pojo);
		return criteria;
	}
}
