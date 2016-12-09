package edu.webonlineshop.dal.entity;

public class Profile {

	private long userID;
	private String firstName;
	private String lastName;
	private double latitude;
	private double longitude;
	private String eMailAddress;
	private String password;
	private double balance;
	private long accountNumber;
	
	public Profile() {
	}
	public Profile(User user, Account account) {
		this.userID = user.getUserID();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.eMailAddress = user.geteMailAddress();
		this.password = user.getPassword();
		this.latitude = user.getLatitude();
		this.longitude = user.getLongitude();
		this.balance = account.getBalance();
		this.accountNumber = account.getAccountNumber();
	}
	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
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
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	
}
