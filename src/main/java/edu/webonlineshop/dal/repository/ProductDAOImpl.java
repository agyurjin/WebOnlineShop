package edu.webonlineshop.dal.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import edu.webonlineshop.dal.entity.Product;
import edu.webonlineshop.dal.repository.impl.ProductDAO;

@Repository
public class ProductDAOImpl implements ProductDAO{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public void addProduct(Product product) {
		final String sqlInsert = "INSERT INTO products (name, quantity, price) values(?, ?, ?)";
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		
		getJdbcTemplate().update(new PreparedStatementCreator() {

		public PreparedStatement createPreparedStatement(
				Connection connection) throws SQLException {
			PreparedStatement ps = connection.prepareStatement(sqlInsert,
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, product.getName());
			ps.setInt(2, product.getQuantity());
			ps.setDouble(3, product.getPrice());
			return ps;
		}

		}, keyHolder);
		product.setProductID(keyHolder.getKey().longValue());
		
	}

	@Override
	public Product searchByID(long id) {
		String sqlSearch = "SELECT * FROM products WHERE productid = ?";
		List<Product> productlist = getJdbcTemplate().query(sqlSearch, new ProductRowMapper(), id);
		if(productlist.isEmpty()) {
			return new Product();
		}
		return productlist.get(0);
	}

	@Override
	public List<Product> allProducts() {
		String sqlAll = "SELECT * FROM products";
		List<Product> productlist = getJdbcTemplate().query(sqlAll, new ProductRowMapper());
		if(productlist.isEmpty()) {
			return new ArrayList<Product>();
		}
			
		return productlist;
	}

	@Override
	public void reduceNumber(long productid, int quantity) {
		String query = "UPDATE products SET quantity = ? where productid = ?";
		getJdbcTemplate().update(query, quantity, productid);
	}
}

class ProductRowMapper implements RowMapper<Product> {

	public Product mapRow(ResultSet result, int rowNum) throws SQLException {
		Product product = new Product();
		product.setProductID(result.getLong("productid"));
		product.setName(result.getString("name"));
		product.setQuantity(result.getInt("quantity"));
		product.setPrice(result.getDouble("price"));
		
		return product;
	}
	
}