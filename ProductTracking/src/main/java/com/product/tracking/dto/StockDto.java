package com.product.tracking.dto;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;

import com.product.tracking.entities.Product;
import com.product.tracking.entities.Stock;

public class StockDto {

	private Long stockId;

	private Long quantity;

	private BigDecimal price;

	public StockDto() {
	}

	public StockDto(Stock stock) {
		BeanUtils.copyProperties(stock, this, "product");
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
