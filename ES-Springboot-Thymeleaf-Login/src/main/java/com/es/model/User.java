package com.es.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "USERS")
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotNull(message="username harus diisi")
	@Column(length=20, unique = true)
	private String username;
	
	@Email(message="format email")
	@Size(min=5, max=50, message = "email min 5 max 50")
	@Column(length=50)
	private String email;
	
	@NotNull(message="username harus diisi")
	@Column(length=60)
	@Size(min=5, max=60, message = "email min 5 max 60")
	private String password;
	@NotNull(message="username harus diisi")
	private boolean enabled;
	
	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
