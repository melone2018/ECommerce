package com.rjt.android.ecommerce;

public class Book extends Product {

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(String name, double price, String description) {
		this.setProductName(name);
		this.setPrice(price);
		this.setDescription(description);
	}
	
}
