package com.rest.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.rest.model.SeedFoodType;
import com.rest.util.ConnectionUtil;

public class SeedFoodTypeDao {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	public void save(SeedFoodType seedfoodtype) {

		String sql = "insert into seed_foodtype(id,food_type,start_time,end_time,quantity) values(?,?,?,?,?)";
		Object[] params = { seedfoodtype.getId(), seedfoodtype.getFoodType(), seedfoodtype.getStartTime(),
				seedfoodtype.getEndTime(), seedfoodtype.getQuantity() };
		int rows = jdbcTemplate.update(sql, params);
		System.out.println("No of rows inserted: " + rows);

	}

	public void update(SeedFoodType seedfoodtype) {

		String sql = "update seed_foodtype set quantity=? where id=?";
		Object[] params = { seedfoodtype.getQuantity(), seedfoodtype.getId() };
		int rows = jdbcTemplate.update(sql, params);
		System.out.println("No of rows updated: " + rows);

	}

	public void delete(int id) {

		String sql = "delete from seed_foodtype where id=?";
		int rows = jdbcTemplate.update(sql, id);
		System.out.println("No of rows deleted: " + rows);

	}
	public List<SeedFoodType> list(){
		String sql="select * from seed_foodtype";
		
		return jdbcTemplate.query(sql, (rs,rowNum) ->{
		    SeedFoodType seedfoodtype=new SeedFoodType();
			
			seedfoodtype.setId(rs.getInt("id"));
			seedfoodtype.setFoodType(rs.getString("food_type"));
		    seedfoodtype.setStartTime(rs.getTime("start_time").toLocalTime());
		    seedfoodtype.setEndTime(rs.getTime("end_time").toLocalTime());
		    seedfoodtype.setQuantity(rs.getInt("quantity"));
			return seedfoodtype;
		});
	}

	
}
