package com.stocktrade.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.stocktrade.models.Trade;
import com.stocktrade.models.UserPortfolioTrades;
import com.stocktrade.repository.TradeRepository;
import com.stocktrade.repository.UserPortfolioRepository;
import com.stocktrade.repository.UserRepository;

@Service
public class TradeServicesImpl implements TradeServices {
	
	@Autowired
	private TradeRepository tradeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserPortfolioRepository userPortfolioRepository;
	
	@Override
	public void removeAllTrades() {
		tradeRepository.deleteAll();
	}


@Override
public ResponseEntity<String> createNewTrade(Trade trade) {
	try {
	Optional<Trade> fetchedTrade = tradeRepository.findById(trade.getTrade_id());
	if (!fetchedTrade.isPresent()) {
		trade.setAvg_price(trade.getStock_price());
		userPortfolioRepository.save(trade.getUserPortfolioTrades());
		userRepository.save(trade.getUser());
		tradeRepository.save(trade);
		return ResponseEntity.status(HttpStatus.CREATED).build();

	} else
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	catch(Exception e){
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}


@Override
public ResponseEntity<Void> removeTradeById(int tradeid) {
	try {
		if (tradeRepository.findById(tradeid).isPresent()) {
			tradeRepository.deleteById(tradeid);
	          return ResponseEntity.ok().build();
		}
		else {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	catch(Exception e){
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}


@Override
public ResponseEntity<Trade> updateTrade(Trade trade, int tradeid) {
	try {
		if (tradeRepository.findById(tradeid).isPresent()) {
			trade.setTrade_id(tradeid);
			tradeRepository.save(trade);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		else {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	catch(Exception e){
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}


@Override
public ResponseEntity<Trade> buytrade(Trade trade, int tradeid) {
	try {
	if(trade.getUserPortfolioTrades().getPrice() == 0) {
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
	}
	else {
		trade.setTrade_id(tradeid);
		int total_share = (trade.getShare() + trade.getUserPortfolioTrades().getQuantity());
		float cal = (trade.getStock_price()*trade.getShare()+trade.getUserPortfolioTrades().getPrice()*trade.getUserPortfolioTrades().getQuantity())/total_share;
		trade.setAvg_price(cal);
		trade.setShare(total_share);
		tradeRepository.save(trade);
		userPortfolioRepository.save(trade.getUserPortfolioTrades());
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	}
	catch(Exception e){
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}


@Override
public ResponseEntity<Trade> selltrade(Trade trade, int tradeid) {
	try {
		if(trade.getShare() == 0) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
		else {
			trade.setTrade_id(tradeid);
			int total_share = trade.getShare() - trade.getUserPortfolioTrades().getQuantity();
			trade.setAvg_price(trade.getStock_price());
			trade.setShare(total_share);
			tradeRepository.save(trade);
			userPortfolioRepository.save(trade.getUserPortfolioTrades());
			return ResponseEntity.status(HttpStatus.OK).build();
		}
	}
	catch(Exception e){
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}


@Override
public ResponseEntity<List<Trade>> FetchAllHoldTrades() {
	try {
		return new ResponseEntity<>(tradeRepository.getallholdTrade(), HttpStatus.OK);
	}
	catch(Exception e){
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}


@Override
public ResponseEntity<List<UserPortfolioTrades>> FetchAllTrade() {
	try {
		return new ResponseEntity<>(tradeRepository.gettradePortfolio(), HttpStatus.OK);
	}
	catch(Exception e){
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}


@Override
public ResponseEntity<Float> fetchreturn() {
	try {
			float sum = 0.0f;
			for(int i = 0; i < tradeRepository.getallholdTrade().size(); i++ ) {
				float a = tradeRepository.getallholdTrade().get(i).getStock_price() ;
				float b = tradeRepository.getallholdTrade().get(i).getAvg_price();
				float c = tradeRepository.getallholdTrade().get(i).getShare();
				sum = sum + ((a-b)*c);
           }
			return ResponseEntity.status(HttpStatus.OK).body(sum);
	}
	catch(Exception e){
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

}
