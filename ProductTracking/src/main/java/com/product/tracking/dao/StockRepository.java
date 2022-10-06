package com.product.tracking.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.tracking.entities.Product;
import com.product.tracking.entities.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

	void deleteByProduct(Product product);

	Stock findByProduct(Product product);

}
