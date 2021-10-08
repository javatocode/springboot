package com.stocktrade.tradescontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stocktrade.models.Trade;
import com.stocktrade.models.UserPortfolioTrades;
import com.stocktrade.services.TradeServices;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api")
public class TradesController {
	
	@Autowired
	private TradeServices tradeService;

	@Operation(summary = "This is used to add trades in portfolio")
	@PostMapping("/addtrades")
	private ResponseEntity<String> addtrades(@RequestBody @Valid  Trade trade ){
		try {
		return tradeService.createNewTrade(trade);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DeleteMapping("/removetrades")
	private ResponseEntity<String> deletAllTrades() {
		try {
		 tradeService.removeAllTrades();
		 return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DeleteMapping("/removetrade/{tradeid}")
	private ResponseEntity<String> deletAllTrades(@Valid @PathVariable(value="tradeid") int tradeid) {
		try {
		 tradeService.removeTradeById(tradeid);
		 return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/updatetrade/{tradeid}")
	public ResponseEntity<Trade> updateTrade(@Valid @RequestBody Trade trade , @PathVariable("tradeid") int tradeid) {
		try {
		this.tradeService.updateTrade(trade, tradeid);
		return ResponseEntity.ok().body(trade);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/buytrade/{tradeid}")
	public ResponseEntity<Trade> buyTrade(@Valid @RequestBody Trade trade , @PathVariable("tradeid") int tradeid) {
		try {
		this.tradeService.buytrade(trade, tradeid);
		return ResponseEntity.ok().body(trade);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/selltrade/{tradeid}")
	public ResponseEntity<Trade> sellTrade(@Valid @RequestBody Trade trade , @PathVariable("tradeid") int tradeid) {
		try {
		this.tradeService.selltrade(trade, tradeid);
		return ResponseEntity.ok().body(trade);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
    @GetMapping("/fetchholdtrade")
	private ResponseEntity<List<Trade>> fetchholdTrades() {
    	try {
		 return tradeService.FetchAllHoldTrades();
    	}
    	catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
    
    @GetMapping("/fetchtrade")
  	private ResponseEntity<List<UserPortfolioTrades>> fecthTrades() {
      	try {
  		 return tradeService.FetchAllTrade();
      	}
      	catch(Exception e) {
  			e.printStackTrace();
  			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  		}
  	}
    
    @GetMapping("/fetchreturn")
	private ResponseEntity<Float> fetchreturn(){
		try {
			return  tradeService.fetchreturn();
		}
		catch(Exception e) {
  			e.printStackTrace();
  			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  		}
	}
}
