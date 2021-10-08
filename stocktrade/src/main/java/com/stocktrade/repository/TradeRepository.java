package com.stocktrade.repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stocktrade.models.Trade;

import com.stocktrade.models.UserPortfolioTrades;


@Repository
public interface TradeRepository  extends CrudRepository<Trade, Integer>{
	
	// fetch all holding portfolio = JPQL
	@Query("select t from Trade t")
	public List<Trade>  getallholdTrade();
	
	// fetching all trade portfolio = JPQL
	@Query("select u from Trade t , UserPortfolioTrades  u  where t.userPortfolioTrades = u")
	public List<UserPortfolioTrades> gettradePortfolio();
	
	
	
	
	
}
