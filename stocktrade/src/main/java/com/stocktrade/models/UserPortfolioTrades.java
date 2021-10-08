package com.stocktrade.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component(value="userportfolio")
@Entity
@Table(name="portfolio_trade")
public class UserPortfolioTrades {
   
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private  int id;
	
	@Column(name = "Action")
	@NotNull                       
	private String action ;
	
	@Column(name = "Quantity")
	@NotNull
	private int quantity;
	
	@Column(name = "Price")
	@NotNull
	private float price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public UserPortfolioTrades(int id, String action, int quantity, float price) {
		super();
		this.id = id;
		this.action = action;
		this.quantity = quantity;
		this.price = price;
	}

	public UserPortfolioTrades() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserPortfolioTrades [id=" + id + ", action=" + action + ", quantity=" + quantity + ", price=" + price
				+ "]";
	}

}
