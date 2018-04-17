package com.rjt.android.ecommerce;

import java.util.Iterator;
import java.util.Map;

public class Customer {
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String password;
	private StringBuilder infor;
	public void setPassword(String password) {
		this.password = password;
	}

	public Customer(String firstName, String lastName, String email, String phoneNumber, String address, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.password = password;
		infor = new StringBuilder(firstName + "/" + lastName + "/" + email + "/" + phoneNumber + "/" + address + "/"+ password);
	}

	public void setAddress(String address) {
		this.address = address;
	}

	private String address;

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String apiKey;
	private String id;
	private Cart cart;



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
			bill += Double.valueOf(p.getPrice()) * num;
		}
		return bill;
	}

    public StringBuilder getInfor(){
      return infor;
    }
}
