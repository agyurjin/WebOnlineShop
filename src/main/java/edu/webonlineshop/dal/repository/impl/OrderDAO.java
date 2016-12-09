package edu.webonlineshop.dal.repository.impl;

import java.util.List;

import edu.webonlineshop.dal.entity.Order;

public interface OrderDAO {

	public void addOrder(Order order);
	public List<Order> searchByUserID(long userid);
	public List<Order> searchByStatus(String status);
	public void deleteOrder(long userid);
	public void updateOrderStatus(long id, String status);
}
