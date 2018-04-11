package com.rjt.android.ecommerce;

public abstract class Product {
	private double price;
	private String productName;
	private int totalSale;
	private String description;
	private Boolean isOnSale;
	private Boolean isTopSeller;
	private String productId;
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getTotalSale() {
		return totalSale;
	}
	public void setTotalSale(int totalSale) {
		this.totalSale = totalSale;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getIsOnSale() {
		return isOnSale;
	}
	public void setIsOnSale(Boolean isOnSale) {
		this.isOnSale = isOnSale;
	}
	public Boolean getIsTopSeller() {
		return isTopSeller;
	}
	public void setIsTopSeller(Boolean isTopSeller) {
		this.isTopSeller = isTopSeller;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
}
