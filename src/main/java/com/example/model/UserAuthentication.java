package com.example.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.TableGenerator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="userauthentication")
public class UserAuthentication implements Authentication, UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1737376655183629576L;

	@GeneratedValue(generator = "tableGen", strategy = GenerationType.TABLE)
	@GenericGenerator(name = "tableGen", strategy = "org.hibernate.id.enhanced.TableGenerator", parameters = {
	            @Parameter(name = TableGenerator.CONFIG_PREFER_SEGMENT_PER_ENTITY, value = "true"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
	    })
	
	@Id
	@Column(name = "id")
	private long id;
	
	@Column(name = "token")	
	private String token;
	
	@Column(name = "name")	
	private String name;
	
	@Column(name = "password")	
	private String password;
	
	@Column(name = "username")	
	private String username;
	
	@Column(name = "accountnonexpired")	
	private boolean accountNonExpired = true;
	
	@Column(name = "accountnonlocked")	
	private boolean accountNonLocked = true;
	
	@Column(name = "credentialsnonexpired")	
	private boolean credentialsNonExpired = true;
	
	@Column(name = "enabled")	
	private boolean enabled;
	
	//@Column(name = "name")	
	@Transient
	private Collection<? extends GrantedAuthority> authorities;
	
	@Column(name = "credentials")	
	private String credentials;
	
	@Column(name = "details")	
	private String details;
	
	@Column(name = "principal")	
	private long principal;
	
	@Column(name = "authenticated")	
	private boolean authenticated = true;//cre, deta, prin
	
	@Column(name = "userid")	
	private long userId;
	
	@Column(name = "status")	
	private int status;
	
	public UserAuthentication(){
		
	}

	public UserAuthentication(User user , Collection<SimpleGrantedAuthority> authorities) {
		// TODO Auto-generated constructor stub
		this.username = user.getUserName();
		this.name = user.getName();
		this.password = user.getPassword();
		this.authorities = authorities;
		this.enabled = user.isActive();
		this.principal = user.getId();
		this.credentials = user.getPassword();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return credentials;
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return details;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return principal;
	}

	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		authenticated = isAuthenticated;
		
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setAuthorities(Collection<SimpleGrantedAuthority> authorities) {
		this.authorities = authorities;
	}


}
