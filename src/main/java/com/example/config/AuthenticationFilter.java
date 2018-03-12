package com.example.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.filter.GenericFilterBean;


public class AuthenticationFilter extends GenericFilterBean{

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	SecurityContextRepository repo;
	
	 public AuthenticationFilter(AuthenticationManager authenticationManager, SecurityContextRepository repo) {
	        this.authenticationManager = authenticationManager;
	        this.repo	=	repo;
	    }
	 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpRequestResponseHolder httpRequestResponseHolder = new HttpRequestResponseHolder(
				(HttpServletRequest)request, (HttpServletResponse)response);
		Cookie cookie[] = ((HttpServletRequest)request).getCookies();
		if (cookie != null && cookie.length > 0) {
			for (int i = 0; i < cookie.length; i++) {
				Cookie c = cookie[i];
				if (c.getName().equalsIgnoreCase("demo")) {
					SecurityContext sc = repo.loadContext(httpRequestResponseHolder);
					SecurityContextHolder.setContext(sc);
				}

			}

		}
		filter.doFilter(request, response);
	}

}
