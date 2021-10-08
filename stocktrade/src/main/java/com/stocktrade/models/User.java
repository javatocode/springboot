package com.stocktrade.models;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class User {
	
	@Id
	@Column(name = "Userid")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int user_id;
	
	@Column(name = "Username")
	@NotNull
	private String name;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User(int user_id, String name) {
		super();
		this.user_id = user_id;
		this.name = name;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", name=" + name + "]";
	}	
}
