package com.sapient.creditcard;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import com.sapient.creditcard.model.CreditCardModel;
import com.sapient.creditcard.utils.DBQueries;

@Service
public class CreditCardManagementService {
    
	 static JdbcTemplate jdbcTemplate;

    
	public List<CreditCardModel> findAll() {
		return jdbcTemplate.query(DBQueries.GET_ALL, new CreditCardRowMapper());
	}
	
	public void saveCreditCard (CreditCardModel card) {
		jdbcTemplate.update(DBQueries.SAVE_QUERY,
	    		new Object[] { card.getcardHolder(), card.getNumber(), card.getLimit(), 0 });
	}
	
	private class CreditCardRowMapper implements RowMapper<CreditCardModel> {
		public CreditCardModel mapRow(ResultSet rs, int rowNum) throws SQLException {
			CreditCardModel card= new CreditCardModel();
			card.setcardHolder(rs.getString("name"));
			card.setNumber(rs.getString("number"));
			card.setLimit(rs.getInt("card_limit"));
			card.setBalance(rs.getInt("balance"));
			return card;
		}
	}
	
	public static void init() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DBQueries.JDBC_DRIVER);
		dataSource.setUrl(DBQueries.DB_URL);
		dataSource.setUsername(DBQueries.USER);
		dataSource.setPassword(DBQueries.PASS);

		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute(DBQueries.CREATE_TABLE_QUERY);
	}
	
	
}
