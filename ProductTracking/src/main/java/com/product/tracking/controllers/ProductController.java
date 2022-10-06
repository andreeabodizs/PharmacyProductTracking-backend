package com.product.tracking.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.product.tracking.dto.ProductDto;
import com.product.tracking.dto.UserDtoForUpdate;
import com.product.tracking.entities.Product;
import com.product.tracking.exceptions.PznAlreadyExistsException;
import com.product.tracking.exceptions.UsernameAlreadyExistsException;
import com.product.tracking.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	// get all products from database
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDto> getAllProducts() {
		return productService.getAllProducts().stream().map(product -> new ProductDto(product))
				.collect(Collectors.toList());
	}

	// create one product in database
	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Product createProduct(@Valid @RequestBody Product product) throws PznAlreadyExistsException  {
			return productService.createProduct(product);
		
	}

	// update a product in database
	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Product updateProduct(@RequestBody Product product) {
		return productService.updateProduct(product);
	}

	// delete one product by its pzn from database
	@DeleteMapping(value = "/delete/{pzn}")
	public void deleteProduct(@PathVariable String pzn) {
		productService.deleteProduct(pzn);
	}

//	@GetMapping(value = "/{productName}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public List<ProductDto> getProductByProductName(@RequestParam String productName) {
//		return productService.getProductByProductName(productName).stream().map(product -> new ProductDto(product))
//				.collect(Collectors.toList());
//	}

	// get one product by pzn
	@GetMapping(value = "/{pzn}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductDto getProductByPzn(@PathVariable String pzn) {
		return new ProductDto(productService.getProductByPzn(pzn));
	}

}
