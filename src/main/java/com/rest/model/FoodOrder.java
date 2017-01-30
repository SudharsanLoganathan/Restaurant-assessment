package com.rest.model;

import lombok.Data;

@Data
public class FoodOrder {
private int id;
private int orderId;
private String foodName;
private int indQty;
private int price;
private String orderStatus;
}
