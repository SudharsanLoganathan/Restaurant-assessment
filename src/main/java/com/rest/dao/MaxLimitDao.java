package com.rest.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.rest.model.MaxLimit;
import com.rest.util.ConnectionUtil;

public class MaxLimitDao {

	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	public void save(MaxLimit maxlimit) {

		String sql = "insert into max_limit(id,name,maximum) values(?,?,?)";
		Object[] params = { maxlimit.getId(), maxlimit.getName(), maxlimit.getMaximum() };
		int rows = jdbcTemplate.update(sql, params);
		System.out.println("No of rows inserted: " + rows);

	}

	public void update(MaxLimit maxlimit) {

		String sql = "update max_limit set maximum=? where id=?";
		Object[] params = { maxlimit.getMaximum(), maxlimit.getId() };
		int rows = jdbcTemplate.update(sql, params);
		System.out.println("No of rows updated: " + rows);

	}

	public void delete(int id) {

		String sql = "delete from max_limit where id=?";
		int rows = jdbcTemplate.update(sql, id);
		System.out.println("No of rows deleted: " + rows);

	}
	public List<MaxLimit> list(){
		String sql="select * from max_limit";
		return jdbcTemplate.query(sql, (rs,rowNum) ->{
			MaxLimit maxlimit = new MaxLimit();
			maxlimit.setId(rs.getInt("id"));
			maxlimit.setName(rs.getString("name"));
			maxlimit.setMaximum(rs.getInt("maximum"));
			return maxlimit;
		});
	}
	
}



