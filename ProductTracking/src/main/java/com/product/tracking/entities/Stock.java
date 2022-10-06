package com.product.tracking.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import com.product.tracking.dto.StockDto;

@Entity
@Table(name = "stocks")
public class Stock {

	@Id
	@Column(name = "stock_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long stockId;

	@NotNull(message = "Quantity cannot be empty!")
	@Column(nullable = false)
	private Long quantity;

	@NotNull(message = "Price cannot be empty!")
	@Column(nullable = false, precision = 2)
	private BigDecimal price;

	@OneToOne(optional = false)
	@JoinColumn(name = "pzn", referencedColumnName = "pzn")
	private Product product;

	public Stock() {

	}

	public Stock(StockDto stockDto, Product product) {
		BeanUtils.copyProperties(stockDto, this);
		this.product = product;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
