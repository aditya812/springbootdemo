package com.example.config;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;

import com.example.dao.UserDAO;
import com.example.model.UserAuthentication;

@Component
public class SessionRepository implements SecurityContextRepository{

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	UserDAO userDAO;
	
	@Transactional
	private UserAuthentication getUserAuthentication(String token){
		Criteria criteria = getSession().createCriteria(UserAuthentication.class);
		criteria.add(Restrictions.eq("token", token));
		return (UserAuthentication) criteria.uniqueResult();
	}
	
	public Session getSession(){
		try{
		return sessionFactory.getCurrentSession();
		}
		catch(Exception e){
			return sessionFactory.openSession();
		}
	}
	
	@Override
	public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
		// TODO Auto-generated method stub
		System.out.println("*******");
		HttpServletRequest request = requestResponseHolder.getRequest();
		String iiphToken = "";
		Cookie cookie[] = request.getCookies();
		SecurityContext context = null;
		if (cookie != null && cookie.length > 0) {
			for (int i = 0; i < cookie.length; i++) {
				Cookie cookiee = cookie[i];
				if (cookiee.getName().equalsIgnoreCase("demo")) {
					Cookie requestCookie = cookie[i];
					iiphToken = requestCookie.getValue();
					UserAuthentication userAuthentication = getUserAuthentication(iiphToken);
					Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
					authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
					userAuthentication.setAuthorities(authorities);
					context = new SecurityContextImpl();
					context.setAuthentication(userAuthentication);
				}
				else{
					context = SecurityContextHolder.createEmptyContext();
				}
			}
		}
		else{
			context = SecurityContextHolder.createEmptyContext();
		}
		return context;
	}

	@Override
	public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean containsContext(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String iiphToken = "";
		Cookie cookie[] = request.getCookies();
		if (cookie != null && cookie.length > 0) {
			for (int i = 0; i < cookie.length; i++) {
				Cookie cookiee = cookie[i];
				if (cookiee.getName().equalsIgnoreCase("demo")) {
					Cookie requestCookie = cookie[i];
					iiphToken = requestCookie.getValue();
					UserAuthentication userAuthentication = userDAO.getUserAuthentication(iiphToken);
					if(userAuthentication != null)
						return true;
				}
			}
		}
		return false;
	}

}
