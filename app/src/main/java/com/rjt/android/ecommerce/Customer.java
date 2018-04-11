package com.rjt.android.ecommerce;

import java.util.Iterator;
import java.util.Map;

public class Customer {
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String address;
	private Cart cart;
	public Customer(String firstName, String lastName, String email, String phoneNumber, String address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.cart = new Cart();
	}
	
	public void buyProduct(Product product) {
		this.cart.addProduct(product);
	}
	
	public boolean removeProduct(Product product) {
		return this.cart.removeProduct(product);
	}
	
	public double checkOut() {
		this.cart.setIsCheckedOut(true);
		double bill = 0;
		Iterator<Map.Entry<Product, Integer>> mapIter = this.cart.getCartMap().entrySet().iterator();
		while(mapIter.hasNext()) {
			Map.Entry<Product, Integer> product = mapIter.next();
			Product p = product.getKey();
			int num = product.getValue();
			bill += p.getPrice() * num; 
		}
		return bill;
	}
}
