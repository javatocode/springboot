package com.stocktrade.models;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="Trade")
public class Trade {
		
	@Id
	@Column(name = "Tradeid")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int trade_id;
	
	@Column(name = "Ticker")
	@NotNull
	private String symbol;
	
	@Column(name = "StockPrice")
	@NotNull
	private float stock_price;
	
	@Column(name = "Currency")
	@NotNull
	private String currency ;
	
	@Column(name = "AvgPrice")
	private float avg_price;
	
	@Column(name = "Shares")
	@NotNull
	private int share;
	
	@Autowired
	@OneToOne(cascade = {CascadeType.ALL})
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	
	@Autowired
	@OneToOne(cascade = {CascadeType.ALL})
	@OnDelete(action = OnDeleteAction.CASCADE)
	private UserPortfolioTrades   userPortfolioTrades;

	public int getTrade_id() {
		return trade_id;
	}

	public void setTrade_id(int trade_id) {
		this.trade_id = trade_id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public float getStock_price() {
		return stock_price;
	}

	public void setStock_price(float stock_price) {
		this.stock_price = stock_price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public float getAvg_price() {
		return avg_price;
	}

	public void setAvg_price(float avg_price) {
		this.avg_price = avg_price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

	public int getShare() {
		return share;
	}

	public void setShare(int share) {
		this.share = share;
	}

	

	public UserPortfolioTrades getUserPortfolioTrades() {
		return userPortfolioTrades;
	}

	public void setUserPortfolioTrades(UserPortfolioTrades userPortfolioTrades) {
		this.userPortfolioTrades = userPortfolioTrades;
	}


	

	public Trade(int trade_id, String symbol, float stock_price, String currency, float avg_price, int share, User user,
			UserPortfolioTrades userPortfolioTrades) {
		super();
		this.trade_id = trade_id;
		this.symbol = symbol;
		this.stock_price = stock_price;
		this.currency = currency;
		this.avg_price = avg_price;
		this.share = share;
		this.user = user;
		this.userPortfolioTrades = userPortfolioTrades;
	}

	public Trade() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Trade [trade_id=" + trade_id + ", symbol=" + symbol + ", stock_price=" + stock_price + ", currency="
				+ currency + ", avg_price=" + avg_price + ", share=" + share + ", user=" + user
				+ ", userPortfolioTrades=" + userPortfolioTrades + "]";
	}
	
}
