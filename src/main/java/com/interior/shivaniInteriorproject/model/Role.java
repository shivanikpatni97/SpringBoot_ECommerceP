package com.interior.shivaniInteriorproject.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
@Table(name = "roles")
public class Role {

	public Role() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer role_id;
	
	@Column(nullable = false, unique =true)
	@NotEmpty
	private String name;

	@ManyToMany(mappedBy = "roles")
	private List<User> users;
	
	public Integer getId() {
		return role_id;
	}

	public Role(String name) {
		super();
		this.name = name;
	}

	public void setId(Integer role_id) {
		this.role_id = role_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
