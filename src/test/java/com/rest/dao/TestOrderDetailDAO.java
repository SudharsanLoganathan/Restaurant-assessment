package com.rest.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.rest.model.FoodSchedule;
import com.rest.model.OrderDetail;
import com.rest.model.SeedSeat;

public class TestOrderDetailDAO {

	public static void main(String[] args) {
		// SeedSeat seedseat = new SeedSeat();
		OrderDetail orderdetail = new OrderDetail();
		// orderdetail.setId(2);
		// orderdetail.setOrderId(131);
		// orderdetail.setSeedSeat(seedseat);
		// seedseat.setSeatNo("s02");
		// orderdetail.setQty(12);
		// orderdetail.setTotalPrice(130);
		// orderdetail.setOrderStatus("Ordered");
		// orderdetail.setOrderDate(LocalDateTime.now());
		// ;

		OrderDetailDao dao = new OrderDetailDao();
		// dao.save(orderdetail);
		List<OrderDetail> list = dao.list();

		for (OrderDetail a : list) {
			System.out.println(a.getId() + "\t" + a.getOrderId() + "\t" + a.getSeedSeat().getSeatNo() + "\t"
					+ a.getQty() + "\t" + a.getTotalPrice() + "\t" + a.getOrderStatus() + "\t" + a.getOrderDate());
		}
	}

}
