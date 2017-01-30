package com.rest.dao;

import java.time.LocalTime;
import java.util.List;

import com.rest.model.FoodSchedule;
import com.rest.model.SeedFoodType;

public class TestSeedFoodTypeDAO {
	public static void main(String[] args) {
		SeedFoodType seedfoodtype = new SeedFoodType();
//		seedfoodtype.setId(5);
//		seedfoodtype.setFoodType("Supper");
//		seedfoodtype.setStartTime(LocalTime.parse("11:15:00"));
//		seedfoodtype.setEndTime(LocalTime.parse("01:00:00"));
//		seedfoodtype.setQuantity(500);
	
		SeedFoodTypeDao dao = new SeedFoodTypeDao();
		//dao.delete(5);
	List<SeedFoodType> list =dao.list();
		
		for(SeedFoodType a : list)
		{
			System.out.println(a.getId()+"\t"+a.getFoodType()+"\t"+a.getStartTime()+"\t"+a.getEndTime()+"\t"+a.getQuantity());
		}
	}
}
