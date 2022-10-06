package com.product.tracking.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.tracking.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

	public List<Product> findByProductName(String productName);
	
	public Product findByPzn(String pzn);
}
