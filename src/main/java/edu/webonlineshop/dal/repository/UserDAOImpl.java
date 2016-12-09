package edu.webonlineshop.dal.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import edu.webonlineshop.dal.entity.User;
import edu.webonlineshop.dal.repository.impl.UserDAO;

@Repository
public class UserDAOImpl implements UserDAO{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public void addUser(final User user) {
		final String sqlInsert = "INSERT INTO users (firstname, lastname, emailaddress, password, latitude, longitude) values(?, ?, ?, ?, ?, ?)";
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		
		getJdbcTemplate().update(new PreparedStatementCreator() {

		public PreparedStatement createPreparedStatement(
				Connection connection) throws SQLException {
			PreparedStatement ps = connection.prepareStatement(sqlInsert,
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.geteMailAddress());
			ps.setString(4, user.getPassword());
			ps.setDouble(5, user.getLatitude());
			ps.setDouble(6, user.getLongitude());
			return ps;
		}

		}, keyHolder);
		user.setUserID(keyHolder.getKey().longValue());
		
	}

	@Override
	public User searchByEmail(String email) {
		String sqlSearch = "SELECT * FROM users WHERE emailaddress = ?";
		List<User> user = getJdbcTemplate().query(sqlSearch, new UserRowMapper(), email);
		
		if(user.isEmpty()) {
			return new User();
		}
	    return user.get(0);
	}

	@Override
	public void deleteUser(long userid) {
		String sqlDelete = "DELETE FROM users WHERE userid = ?";
		int rows = getJdbcTemplate().update(sqlDelete, userid);

	}
	
	@Override
	public User searchById(long id) {
		String query = "SELECT * FROM users where UserID = ?";
        List<User> user = getJdbcTemplate().query(query, new UserRowMapper(), id);
        
        if(user.size() == 0) {
        	System.out.println("User id is not correct!");
        	return new User();
        }

        return user.get(0);
	}

}

class UserRowMapper implements RowMapper<User> {
	 
    public User mapRow(ResultSet result, int rowNum) throws SQLException {
    	User user = new User();
    	user.setUserID(result.getLong("userid"));
    	user.setFirstName(result.getString("firstname"));
    	user.setLastName(result.getString("lastname"));
    	user.seteMailAddress(result.getString("emailaddress"));
    	user.setPassword(result.getString("password"));
    	user.setLatitude(result.getDouble("latitude"));
    	user.setLongitude(result.getDouble("longitude"));

    	return user;
    }     
}
