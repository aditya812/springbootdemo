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

@Entity
@Table(name="rolebasedata")
public class RoleBaseData {

	@GeneratedValue(generator = "tableGen", strategy = GenerationType.TABLE)
	@GenericGenerator(name = "tableGen", strategy = "org.hibernate.id.enhanced.TableGenerator", parameters = {
	            @Parameter(name = TableGenerator.CONFIG_PREFER_SEGMENT_PER_ENTITY, value = "true"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
	    })
	
	@Id
	@Column(name = "id")
	private long id;
	
	@Column
	private String name;
	
	@Column
	private int role;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
}
