package com.rest.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;


import com.rest.model.SeedSeat;
import com.rest.util.ConnectionUtil;

public class SeedSeatDao {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	

	public void save(SeedSeat seedseat) {

		String sql = "insert into seed_seat(id,seat_no,isactive) values(?,?,?)";
		Object[] params = { seedseat.getId(), seedseat.getSeatNo(),seedseat.getIsActive() };
		int rows = jdbcTemplate.update(sql, params);
		System.out.println("No of rows inserted: " + rows);

	}

	public void update(SeedSeat seedseat) {

		String sql = "update seed_seat set isactive=? where id=?";
		Object[] params = { seedseat.getIsActive(), seedseat.getId() };
		int rows = jdbcTemplate.update(sql, params);
		System.out.println("No of rows updated: " + rows);

	}

	public void delete(int id) {

		String sql = "delete from seed_seat where id=?";
		int rows = jdbcTemplate.update(sql, id);
		System.out.println("No of rows deleted: " + rows);

	}
	public List<SeedSeat> list(){
		String sql="select * from seed_seat";
		
		return jdbcTemplate.query(sql, (rs,rowNum) ->{
			SeedSeat seedseat = new SeedSeat();
		
			seedseat.setId(rs.getInt("id"));
			seedseat.setSeatNo(rs.getString("seat_no"));
			seedseat.setIsActive(rs.getInt("isactive"));
			return seedseat;
		});
	}

}
