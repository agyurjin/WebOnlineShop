package edu.webonlineshop.dal.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.webonlineshop.dal.entity.Account;
import edu.webonlineshop.dal.repository.impl.AccountDAO;

@Repository
public class AccountDAOImpl implements AccountDAO{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public void addAccount(Account account) {
		String sqlInsert = "INSERT INTO accounts (userid, balance, accountnumber) VALUES(?, ?, ?)";
		getJdbcTemplate().update(sqlInsert, account.getUserID(), account.getBalance(), account.getAccountNumber());	}

	@Override
	public Account searchByID(long id) {
		String sqlSearch = "SELECT * FROM accounts WHERE userid = ?";
		List<Account> account = getJdbcTemplate().query(sqlSearch, new AccountRowMapper(), id);
		
		if(account.isEmpty()) {
			return new Account();
		}
	    return account.get(0);
	}

	@Override
	public void deleteAccount(long userid) {
		String sqlDelete = "DELETE FROM accounts WHERE userid = ?";
		int rows = getJdbcTemplate().update(sqlDelete, userid);
		
	}

	@Override
	public void doPayment(long userid, double newBalance) {
		String query = "UPDATE accounts SET balance = ? where userid = ?";
		getJdbcTemplate().update(query, newBalance, userid);
		
	}
	
}

class AccountRowMapper implements RowMapper<Account> {
	 
    public Account mapRow(ResultSet result, int rowNum) throws SQLException {
    	Account account = new Account();
    	account.setUserID(result.getLong("userid"));
    	account.setBalance(result.getDouble("balance"));
    	account.setAccountNumber(result.getLong("accountnumber"));
    	        
        return account;
    }     
}