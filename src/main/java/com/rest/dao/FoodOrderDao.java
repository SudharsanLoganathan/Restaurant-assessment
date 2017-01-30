package com.rest.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.rest.model.FoodOrder;
import com.rest.util.ConnectionUtil;

public class FoodOrderDao {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	public void save(FoodOrder foodorder) {

		String sql = "insert into food_order(id,order_id,food_name,ind_qty,price,order_status) values(?,?,?,?,?,?)";
		Object[] params = { foodorder.getId(), foodorder.getOrderId(), foodorder.getFoodName(), foodorder.getIndQty(),
				foodorder.getPrice(), foodorder.getOrderStatus() };
		int rows = jdbcTemplate.update(sql, params);
		System.out.println("No of rows inserted: " + rows);

	}

	public void update(FoodOrder foodorder) {

		String sql = "update food_order set price=? where order_id=? and food_name=?";
		Object[] params = { foodorder.getPrice(), foodorder.getOrderId(), foodorder.getFoodName() };
		int rows = jdbcTemplate.update(sql, params);
		System.out.println("No of rows updated: " + rows);

	}

	public void delete(int id) {

		String sql = "delete from food_order where id=?";
		int rows = jdbcTemplate.update(sql, id);
		System.out.println("No of rows deleted: " + rows);

	}
	public List<FoodOrder> list(){
		String sql="select * from food_order";
		
		return jdbcTemplate.query(sql, (rs,rowNum) ->{
			FoodOrder foodorder = new FoodOrder();
			
			foodorder.setId(rs.getInt("id"));
			foodorder.setOrderId(rs.getInt("order_id"));
		    foodorder.setFoodName(rs.getString("food_name"));
			foodorder.setIndQty(rs.getInt("ind_qty"));
			foodorder.setPrice(rs.getInt("price"));
			foodorder.setOrderStatus(rs.getString("order_status"));
			return foodorder;
		});
	}

}
