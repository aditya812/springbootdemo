package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.TableGenerator;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "user")
public class User {

	public static final String USER_NAME = "userName";
	
	@GeneratedValue(generator = "tableGen", strategy = GenerationType.TABLE)
	@GenericGenerator(name = "tableGen", strategy = "org.hibernate.id.enhanced.TableGenerator", parameters = {
	            @Parameter(name = TableGenerator.CONFIG_PREFER_SEGMENT_PER_ENTITY, value = "true"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
	    })
	
	@Id
	@Column(name = "id")
	private long id;
	
	@Column(name = "username")
	private String userName;

	@Column
	private String password;

	@Column(name = "name")
	private String name;

	@Column
	private String email;

	@Column(name = "contactno")
	private String contactNo;

	@Column(name = "role")
	private int role;

	@Column(name = "isactive")
	private boolean isActive;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public String getRoleValue() {
		return getRole() == 2 ? "ROLE_AGENT"
				: getRole() == 1 ? "ROLE_ADMIN" : "ROLE_ANONYMOUS";

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
