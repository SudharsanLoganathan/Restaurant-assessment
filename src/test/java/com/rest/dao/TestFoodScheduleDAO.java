package com.rest.dao;

import java.util.Iterator;
import java.util.List;

import com.rest.model.FoodSchedule;
import com.rest.model.SeedFood;
import com.rest.model.SeedFoodType;

public class TestFoodScheduleDAO {

	public static void main(String[] args) {
//		FoodSchedule foodschedule = new FoodSchedule();
//		SeedFoodType seedfoodtype = new SeedFoodType();
//		SeedFood seedfood = new SeedFood();
//		seedfoodtype.setId(1);
//		seedfood.setId(1);
//		foodschedule.setRemQty(200);
//		foodschedule.setSeedFoodType(seedfoodtype);
//		foodschedule.setSeedFood(seedfood);
//	
		FoodScheduleDao dao = new FoodScheduleDao();
		List<FoodSchedule> list =dao.list();
		
		for(FoodSchedule a : list)
		{
			System.out.println(a.getId()+"\t"+a.getSeedFoodType().getId()+"\t"+a.getSeedFood().getId()+"\t"+a.getRemQty());
		}
//		Iterator<FoodSchedule> i=list.iterator();
//		
//				while(i.hasNext())
//				{
//					FoodSchedule foodSchedule=i.next();
//					System.out.println(foodSchedule.getId());
//					System.out.println(foodSchedule.getSeedFoodType().getId());
//					System.out.println(foodSchedule.getSeedFood().getId());
//					System.out.println(foodSchedule.getRemQty());
//					
//				}
//		
	}

}
