package edu.webonlineshop.entity;

public class WebForm {

//	Personal Info
	private String firstName;
	private String lastName;
	private String eMailAddress;
	private String password;

//	Address
	private double latitude;
	private double longitude;

//	Credit Card
	private long cardNumber;
	private double balance;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String geteMailAddress() {
		return eMailAddress;
	}
	public void seteMailAddress(String eMailAddress) {
		this.eMailAddress = eMailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "WebForm [firstName=" + firstName + ", lastName=" + lastName + ", eMailAddress=" + eMailAddress
				+ ", password=" + password + ", latitude=" + latitude + ", longitude=" + longitude + ", cardNumber="
				+ cardNumber + ", balance=" + balance + "]";
	}


	
}
