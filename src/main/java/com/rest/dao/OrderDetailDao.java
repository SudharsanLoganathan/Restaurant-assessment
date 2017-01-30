package com.rest.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.rest.model.OrderDetail;
import com.rest.model.SeedSeat;
import com.rest.util.ConnectionUtil;

public class OrderDetailDao {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	public void save(OrderDetail orderdetail) {

		String sql = "insert into order_details(id,order_id,seat_id,quantity,total_price,order_status,order_date) values(?,?,?,?,?,?,?)";
		Object[] params = { orderdetail.getId(), orderdetail.getOrderId(), orderdetail.getSeedSeat().getSeatNo(),
				orderdetail.getQty(), orderdetail.getTotalPrice(), orderdetail.getOrderStatus(),
				orderdetail.getOrderDate() };
		int rows = jdbcTemplate.update(sql, params);
		System.out.println("No of rows inserted: " + rows);

	}

	public void update(OrderDetail orderdetail) {

		String sql = "update order_details set total_price=? where order_id=? and seat_id=?";
		Object[] params = { orderdetail.getTotalPrice(), orderdetail.getOrderId(),
				orderdetail.getSeedSeat().getSeatNo() };
		int rows = jdbcTemplate.update(sql, params);
		System.out.println("No of rows updated: " + rows);

	}

	public void delete(int id) {

		String sql = "delete from order_details where id=?";
		int rows = jdbcTemplate.update(sql, id);
		System.out.println("No of rows deleted: " + rows);

	}
	public List<OrderDetail> list(){
		String sql="select * from order_details";
		
		return jdbcTemplate.query(sql, (rs,rowNum) ->{
			OrderDetail orderdetail = new OrderDetail();
			SeedSeat seedseat=new SeedSeat();
			seedseat.setSeatNo(rs.getString("seat_id"));
			orderdetail.setId(rs.getInt("id"));
			orderdetail.setOrderId(rs.getInt("order_id"));
			orderdetail.setSeedSeat(seedseat);
			orderdetail.setQty(rs.getInt("quantity"));
			orderdetail.setTotalPrice(rs.getInt("total_price"));
		    orderdetail.setOrderStatus(rs.getString("order_status"));
		    orderdetail.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());
			return orderdetail;
		});
	}
}
