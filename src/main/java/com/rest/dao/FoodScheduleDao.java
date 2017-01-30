package com.rest.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.rest.model.FoodSchedule;
import com.rest.model.SeedFood;
import com.rest.model.SeedFoodType;
import com.rest.util.ConnectionUtil;

public class FoodScheduleDao {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	public void save(FoodSchedule foodschedule) {

		String sql = "insert into food_schedule(id,foodtype_id,food_id,rem_qty) values(?,?,?,?)";
		Object[] params = { foodschedule.getId(), foodschedule.getSeedFoodType().getId(),
				foodschedule.getSeedFood().getId(), foodschedule.getRemQty() };
		int rows = jdbcTemplate.update(sql, params);
		System.out.println("No of rows inserted: " + rows);

	}

	public void update(FoodSchedule foodschedule) {

		String sql = "update food_schedule set rem_qty=? where foodtype_id=? and food_id=?";
		Object[] params = { foodschedule.getRemQty(), foodschedule.getSeedFoodType().getId(),
				foodschedule.getSeedFood().getId() };
		int rows = jdbcTemplate.update(sql, params);
		System.out.println("No of rows updated: " + rows);

	}

	public void delete(int id) {

		String sql = "delete from food_schedule where id=?";
		int rows = jdbcTemplate.update(sql, id);
		System.out.println("No of rows deleted: " + rows);

	}
	public List<FoodSchedule> list(){
		String sql="select * from food_schedule";
		
		return jdbcTemplate.query(sql, (rs,rowNum) ->{
			FoodSchedule foodschedule = new FoodSchedule();
			SeedFoodType seedfoodtype=new SeedFoodType();
			SeedFood seedfood=new SeedFood();
			foodschedule.setId(rs.getInt("id"));
			seedfoodtype.setId(rs.getInt("foodtype_id"));
			seedfood.setId(rs.getInt("food_id"));
			foodschedule.setRemQty(rs.getInt("rem_qty"));
			foodschedule.setSeedFoodType(seedfoodtype);
			foodschedule.setSeedFood(seedfood);
			return foodschedule;
		});
	}
}
