package edu.webonlineshop.dal.entity;

public class Product {
	private long productID;
	private String name;
	private int quantity;
	private double price;
	
	public long getProductID() {
		return productID;
	}
	public void setProductID(long productID) {
		this.productID = productID;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Product [productID=" + productID + ", name=" + name + ", quantity="
				+ quantity + ", price=" + price + "]";
	}
}
