package edu.webonlineshop.dal.entity;

public class Account {

		private long userID;
		private double balance;
		private long accountNumber;

		public long getUserID() {
			return userID;
		}
		public void setUserID(long userID) {
			this.userID = userID;
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

		@Override
		public String toString() {
			return "Account [userID=" + userID + ", balance=" + balance + ", accountNumber=" + accountNumber
					+ "]";
		}

		
	}
