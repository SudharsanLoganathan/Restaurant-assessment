package com.rest.dao;

import com.rest.model.FoodOrder;

public class TestFoodOrderDAO {

	public static void main(String[] args) {
		FoodOrder foodorder=new FoodOrder();
//		foodorder.setPrice(10);
//		foodorder.setOrderId(131);
//		foodorder.setFoodName("tea");
        
		FoodOrderDao dao=new FoodOrderDao();
		System.out.println(dao.list());
		//dao.update(foodorder);
	}

}
