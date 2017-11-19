package com.example.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dao.UserDAO;
import com.example.model.CookieToken;
import com.example.model.User;
import com.example.model.UserAuthentication;

@Service
public class UserHandler implements UserDetailsService{

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userDAO.getUserByName(username);
		UserAuthentication userAuthentication;
		if(user != null){
			Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(user.getRoleValue()));
			userAuthentication = new UserAuthentication(user, authorities);
		}
		else{
			userAuthentication = new UserAuthentication();
		}
		return userAuthentication;
	}
	
	public CookieToken authenticate(User user){
		UserAuthentication ua = (UserAuthentication) loadUserByUsername(user.getUserName());
		CookieToken ct = null;
		if(ua.getCredentials() != null){
			if(passwordEncoder.matches(user.getPassword(), ua.getCredentials().toString())){
				if(ua.getAuthorities() != null){
					if(!ua.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS"))){
						UUID token = null;
						token = UUID.randomUUID();
						ua.setToken(token.toString());
						ua.setUserId((Long)ua.getPrincipal());
						userDAO.saveToken(ua);
						SecurityContextHolder.getContext().setAuthentication(ua);
						ct = new CookieToken();
						ct.setToken(token.toString());
						ct.setUserId((Long)ua.getPrincipal());
					}
				}
				
			}
		}
		return ct;
	}
}
