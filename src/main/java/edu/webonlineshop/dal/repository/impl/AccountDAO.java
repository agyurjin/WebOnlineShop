package edu.webonlineshop.dal.repository.impl;

import edu.webonlineshop.dal.entity.Account;

public interface AccountDAO {

	public void addAccount(Account account);
	public Account searchByID(long id);
	public void deleteAccount(long userid);
	public void doPayment(long userid, double newBalance);
}
