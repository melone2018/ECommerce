package com.rjt.android.ecommerce;

public class Shoes extends Product {

	public Shoes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Shoes(String name, double price, String description) {
		this.setProductName(name);
		this.setPrice(price);
		this.setDescription(description);
	}
}
