package com.product.tracking.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
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
import com.product.tracking.dto.ProductDto;
import com.product.tracking.entities.Product;
import com.product.tracking.entities.Stock;
import com.product.tracking.exceptions.PznAlreadyExistsException;

@Service
@Transactional
public class ProductService {
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StockRepository stockRepository;

	@PostConstruct
	public void populateProductList() {
		try {
			if (productRepository.count() == 0) {
				Resource resource = new ClassPathResource("products.csv");
				CsvSchema schema = CsvSchema.emptySchema().withHeader();
				CsvMapper mapper = new CsvMapper();
				MappingIterator<Product> iterator = mapper.readerFor(Product.class).with(schema)
						.readValues(resource.getInputStream());
				List<Product> products = productRepository.saveAll(iterator.readAll());
			}
		} catch (IOException e) {
			logger.error("An error occurred while populating products.", e);
		}
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product createProduct(Product product) throws PznAlreadyExistsException {
		final String pzn = product.getPzn();
		if (!!productRepository.existsById(pzn)) {
			throw new PznAlreadyExistsException(pzn);
		} else {
			return productRepository.save(product);
		}

	}
	
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	public void deleteProduct(String pzn) {
		productRepository.deleteById(pzn);
	}

	public List<Product> getProductByProductName(String productName) {
		return productRepository.findByProductName(productName);
	}

	public Product getProductByPzn(String pzn) {
		return productRepository.findByPzn(pzn);
	}

//	@PostConstruct
//	private void test() {
//		List<Product> listOfProducts = productRepository.findAll();
//		listOfProducts.forEach(product -> {
//			Stock stock = new Stock();
//			stock.setPrice(BigDecimal.valueOf(1.1));
//			stock.setQuantity(14L);
//			stock.setProduct(product);
//			stockRepository.save(stock);
//		});
//	}

}
