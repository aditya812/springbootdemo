package com.example.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.handler.UserHandler;
import com.example.model.CookieToken;
import com.example.model.User;

@RestController
@RequestMapping("api/v1/user")
public class UserService {

	@Autowired
	UserHandler userHandler;
	
	@Transactional
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public CookieToken login(@RequestBody User user){
		 return userHandler.authenticate(user);
	}
	
	@RequestMapping(value = "login12", method = RequestMethod.GET)
	public CookieToken login(@RequestParam(value="id") long id){
		System.out.println("hyj");
		return null;
	}
	
	 @RequestMapping("/")
	    public String index() {
	        return "Greetings from Spring Boot!";
	    }
}
