package edu.webonlineshop.dal.repository.impl;

import edu.webonlineshop.dal.entity.User;

public interface UserDAO {

	public void addUser(User user);
	public User searchByEmail(String email);
	public void deleteUser(long userid);
	public User searchById(long id);
	
}
