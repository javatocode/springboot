package com.stocktrade.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stocktrade.models.UserPortfolioTrades;

@Repository
public interface UserPortfolioRepository  extends CrudRepository<UserPortfolioTrades, Integer>{ 
	
}
