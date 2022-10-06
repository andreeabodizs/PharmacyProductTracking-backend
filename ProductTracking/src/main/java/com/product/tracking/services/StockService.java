package com.product.tracking.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.product.tracking.dao.ProductRepository;
import com.product.tracking.dao.StockRepository;
import com.product.tracking.dto.StockDto;
import com.product.tracking.entities.Product;
import com.product.tracking.entities.Stock;

@Service
@Transactional
public class StockService {

	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);



//	@PostConstruct 
//	public void populateStockList() {
//		try {
//			if (stockRepository.count() == 0) {
//				Resource resource = new ClassPathResource("stocks.csv");
//				CsvSchema schema = CsvSchema.emptySchema().withHeader();
//				CsvMapper mapper = new CsvMapper();
//				MappingIterator<Stock> iterator = mapper.readerFor(Stock.class).with(schema)
//						.readValues(resource.getInputStream());
//				List<Stock> products = stockRepository.saveAll(iterator.readAll());
//			}
//		} catch (IOException e) {
//			logger.error("An error occurred while populating products.", e);
//		}
//	}

	public List<Stock> getAllStocks() {
		return stockRepository.findAll();
	}

	//
	public Stock persistStock(StockDto stockDto, String pzn) {
		return stockRepository.save(new Stock(stockDto, findProductByPzn(pzn)));
	}
	
	public Optional<Stock> getStockById(Long stockId) {
		return stockRepository.findById(stockId);
	}
	
	public void deleteStockByStockId(Long stockId) {
		stockRepository.deleteById(stockId);
	}
	
	public void deleteStockByPzn(String pzn) {
		stockRepository.deleteByProduct(findProductByPzn(pzn));
	}

	public Stock getStockByPzn(String pzn) {
		return stockRepository.findByProduct(findProductByPzn(pzn));
	}

	private Product findProductByPzn(String pzn) {
		return productRepository.findById(pzn).get();
	}

}
