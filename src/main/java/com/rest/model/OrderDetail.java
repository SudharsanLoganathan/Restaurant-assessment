package com.rest.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderDetail {
	private int id;
	private int orderId;
	private SeedSeat seedSeat;
	private int qty;
	private int totalPrice;
	private String orderStatus;
	private LocalDateTime orderDate;
}
