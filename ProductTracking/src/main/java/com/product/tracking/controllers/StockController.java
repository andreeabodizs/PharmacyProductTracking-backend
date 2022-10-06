package com.product.tracking.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.tracking.dto.ProductDto;
import com.product.tracking.dto.StockDto;
import com.product.tracking.entities.Stock;
import com.product.tracking.services.StockService;

@RestController
@RequestMapping("/stock")
public class StockController {

	@Autowired
	private StockService stockService;

	// create one stock for a certain product
	@PostMapping(value = "/create/{pzn}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public StockDto createStock(@RequestBody StockDto stockDto, @PathVariable String pzn) {
		return new StockDto(stockService.persistStock(stockDto, pzn));
	}

	// get all stocks from database
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<StockDto> getAllStocks() {
		return stockService.getAllStocks().stream().map(stock -> new StockDto(stock)).collect(Collectors.toList());
	}

	// get a certain stock based on its id from database
	@GetMapping(value = "/getById/{stockId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Stock getStock(@PathVariable Long stockId) {
		return stockService.getStockById(stockId).get();
	}

	// get a certain stock based on product's pzn from database
	@GetMapping(value = "/{pzn}", produces = MediaType.APPLICATION_JSON_VALUE)
	public StockDto getStockByPzn(@PathVariable String pzn) {
		return new StockDto(stockService.getStockByPzn(pzn));
	}

	// update
	@PutMapping(value = "/update/{pzn}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public StockDto updateStock(@RequestBody StockDto stockDto, @PathVariable String pzn) {
		return new StockDto(stockService.persistStock(stockDto, pzn));
	}

}
