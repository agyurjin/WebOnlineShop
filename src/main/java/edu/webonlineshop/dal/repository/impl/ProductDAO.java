package edu.webonlineshop.dal.repository.impl;

import java.util.List;

import edu.webonlineshop.dal.entity.Product;

public interface ProductDAO {

	public void addProduct(Product product);
	public Product searchByID(long id);
	public List<Product> allProducts();
	public void reduceNumber(long productid, int quantity);
}
