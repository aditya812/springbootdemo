package com.example.daoimpl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.AbstractDAO;
import com.example.dao.UserDAO;
import com.example.model.User;
import com.example.model.UserAuthentication;

@Repository
@Transactional
public class UserDAOImpl extends AbstractDAO implements UserDAO{

	@Override
	public User getUserByName(String userName) {
		// TODO Auto-generated method stub
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq(User.USER_NAME, userName));
		return (User) criteria.uniqueResult();
	}

	@Override
	public void saveToken(UserAuthentication userAuthentication) {
		// TODO Auto-generated method stub
		getSession().persist(userAuthentication);
	}

	@Override
	public UserAuthentication getUserAuthentication(String token) {
		// TODO Auto-generated method stub
		Criteria criteria = getSession().createCriteria(UserAuthentication.class);
		criteria.add(Restrictions.eq("token", token));
		return (UserAuthentication) criteria.uniqueResult();
	}

}
