package com.rest.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import com.rest.model.SeedFood;
import com.rest.util.ConnectionUtil;

public class SeedFoodDao {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	public void save(SeedFood seedfood) {

		String sql = "insert into seed_food(id,item,price) values(?,?,?)";
		Object[] params = { seedfood.getId(), seedfood.getItem(), seedfood.getPrice() };
		int rows = jdbcTemplate.update(sql, params);
		System.out.println("No of rows inserted: " + rows);

	}

	public void update(SeedFood seedfood) {

		String sql = "update seed_food set price=? where id=?";
		Object[] params = { seedfood.getPrice(), seedfood.getId() };
		int rows = jdbcTemplate.update(sql, params);
		System.out.println("No of rows updated: " + rows);

	}

	public void delete(int id) {

		String sql = "delete from seed_food where id=?";
		int rows = jdbcTemplate.update(sql, id);
		System.out.println("No of rows deleted: " + rows);

	}
	public List<SeedFood> list(){
		String sql="select * from seed_food";
		
		return jdbcTemplate.query(sql, (rs,rowNum) ->{
			SeedFood seedfood = new SeedFood();
		
			seedfood.setId(rs.getInt("id"));
			seedfood.setItem(rs.getString("item"));
			seedfood.setPrice(rs.getInt("price"));
			return seedfood;
		});
	}
}
