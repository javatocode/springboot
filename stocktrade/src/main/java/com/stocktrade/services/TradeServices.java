package com.stocktrade.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import com.stocktrade.models.Trade;
import com.stocktrade.models.UserPortfolioTrades;

public interface TradeServices {
	
	// basic services of trade
	public ResponseEntity<String> createNewTrade(Trade trade);
	public void removeAllTrades();
	public ResponseEntity<Void> removeTradeById(int tradeid);
	public ResponseEntity<Trade> updateTrade(Trade trade , int tradeid);
	
	// Actions on trade
	
	public ResponseEntity<Trade> buytrade(Trade trade, int tradeid ); 
	public ResponseEntity<Trade> selltrade(Trade trade, int tradeid ); 
	
	// portfolio trade fetching
	public ResponseEntity<List<Trade>> FetchAllHoldTrades();
	
	// fetch portfolio trade
	public ResponseEntity<List<UserPortfolioTrades>> FetchAllTrade();
	
	// fetch return
	public ResponseEntity<Float> fetchreturn();
	
	
}
