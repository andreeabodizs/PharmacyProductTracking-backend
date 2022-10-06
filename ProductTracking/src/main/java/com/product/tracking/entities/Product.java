package com.product.tracking.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@Size(min = 8, max = 8, message = "Pzn should have maximum 8 digits.")
	private String pzn;
	
	@Column(length = 100)
	@Size(max = 100)
	private String supplier;
	
	@NotBlank(message = "Product name cannot be empty!")
	@Column(name = "product_name", nullable = false, length = 100)
	@Size(max = 100)
	private String productName;
	
	@NotBlank(message = "Strength cannot be empty!")
	@Column(nullable = false, length = 100)
	@Size(max = 100)
	private String strength;
	
	@NotBlank(message = "Package size cannot be empty!")
	@Column(name = "package_size", nullable = false, length = 20)
	@Size(max = 20)
	private String packageSize;
	
	
	@NotBlank(message = "Unit cannot be empty!")
	@Column(nullable = false, length = 2)
	@Size(max = 2)
	private String unit;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.REMOVE)
	private Stock stock;

	public String getPzn() {
		return pzn;
	}

	public void setPzn(String pzn) {
		this.pzn = pzn;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public String getPackageSize() {
		return packageSize;
	}

	public void setPackageSize(String packageSize) {
		this.packageSize = packageSize;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
}
