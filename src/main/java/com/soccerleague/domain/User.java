package com.soccerleague.domain;

import javax.persistence.*;

import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
@Embeddable
@Table(name="user")
public class User {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private boolean active;
	@Column
	private String roles;
	@Column
	private int tryquantityallowed;
	@Column
	private int tryquantitydone;
	
	public int getTryquantityallowed() {
		return tryquantityallowed;
	}
	public void setTryquantityallowed(int tryquantityallowed) {
		this.tryquantityallowed = tryquantityallowed;
	}
	public int getTryquantitydone() {
		return tryquantitydone;
	}
	public void setTryquantitydone(int tryquantitydone) {
		this.tryquantitydone = tryquantitydone;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getUsername() {
		return username;
	}
	public void setUsuario(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public User(String username, String password, boolean active, String roles, int tryquantityallowed, int tryquantitydone) {
		this.username = username;
		this.password = password;
		this.active = active;
		this.roles = roles;
		this.tryquantityallowed = tryquantityallowed;
		this.tryquantitydone = tryquantitydone;
	}	
	public User() {}
}