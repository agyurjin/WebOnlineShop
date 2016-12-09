package edu.webonlineshop.dal.entity;

public class Order {

	private long orderID;
	private long userID;
	private long productID;
	private int productNumber;
	private String status;
	
	public long getOrderID() {
		return orderID;
	}
	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}
	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
	public long getProductID() {
		return productID;
	}
	public void setProductID(long productID) {
		this.productID = productID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getProductNumber() {
		return productNumber;
	}
	public void setProductNumber(int productNumber) {
		this.productNumber = productNumber;
	}
	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", userID=" + userID + ", productID=" + productID + ", status=" + status
				+ "]";
	}
}
