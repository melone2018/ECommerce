package com.rjt.android.ecommerce;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
	private Boolean isCheckedOut;
	private Map<Product, Integer> cartMap;
	public Map<Product, Integer> getCartMap() {
		return cartMap;
	}
	public Cart() {
		this.isCheckedOut = false;
		this.cartMap = new HashMap<Product, Integer>();
	}
	public void addProduct(Product product) {
		this.cartMap.put(product, this.cartMap.getOrDefault(product, 0) + 1);
	}
	public boolean removeProduct(Product product) {
		if(this.cartMap.containsKey(product)) {
			if(this.cartMap.get(product).intValue() > 1) {
				this.cartMap.put(product, this.cartMap.get(product)-1);
			}else {
				this.cartMap.remove(product);
			}
			return true;
		}
		return false;
	}
	public Boolean getIsCheckedOut() {
		return isCheckedOut;
	}
	public void setIsCheckedOut(Boolean isCheckedOut) {
		this.isCheckedOut = isCheckedOut;
	}
}
