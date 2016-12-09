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

import edu.webonlineshop.dal.entity.Order;
import edu.webonlineshop.dal.repository.impl.OrderDAO;

@Repository
public class OrderDAOImpl implements OrderDAO{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public void addOrder(final Order order) {
		final String sqlInsert = "INSERT INTO orders (userid, productid, productnumber, status) values(?, ?, ?, ?)";
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		
		getJdbcTemplate().update(new PreparedStatementCreator() {

		public PreparedStatement createPreparedStatement(
				Connection connection) throws SQLException {
			PreparedStatement ps = connection.prepareStatement(sqlInsert,
					Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, order.getUserID());
			ps.setLong(2, order.getProductID());
			ps.setInt(3, order.getProductNumber());
			ps.setString(4, order.getStatus());
			return ps;
		}

		}, keyHolder);
		order.setOrderID(keyHolder.getKey().longValue());
	}

	@Override
	public List<Order> searchByUserID(long userid) {
		String sqlSearch = "SELECT * FROM orders WHERE userid = ?";
		List<Order> orderlist = getJdbcTemplate().query(sqlSearch, new OrderRowMapper(), userid);

		
		if(orderlist.size() == 0){
			return new ArrayList<Order>();
		}
		return orderlist;
	}

	@Override
	public void deleteOrder(long userid) {
		String sqlDelete = "DELETE FROM orders WHERE userid = ?";
		int rows = getJdbcTemplate().update(sqlDelete, userid);
	}

	@Override
	public List<Order> searchByStatus(String status) {
		String sqlSearch = "SELECT * FROM orders WHERE status = ?";
		List<Order> orderlist = getJdbcTemplate().query(sqlSearch, new OrderRowMapper(), status);

		
		if(orderlist.size() == 0){
			return new ArrayList<Order>();
		}
		return orderlist;
	}
	
	@Override
	public void updateOrderStatus(long id, String status) {
		String query = "UPDATE orders SET Status = ? where OrderID = ?";
		getJdbcTemplate().update(query, status, id);
	}

}
class OrderRowMapper implements RowMapper<Order> {
	
	public Order mapRow(ResultSet result, int rowNum) throws SQLException {
		Order order = new Order();
		order.setOrderID(result.getLong("orderid"));
		order.setUserID(result.getLong("userid"));
		order.setProductID(result.getLong("productid"));
		order.setProductNumber(result.getInt("productnumber"));
		order.setStatus(result.getString("status"));
		return order;
	}	
}