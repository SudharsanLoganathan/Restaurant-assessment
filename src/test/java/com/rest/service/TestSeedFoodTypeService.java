package com.rest.service;

import java.time.LocalTime;

import com.rest.exception.SeedFoodTypeException;
//import com.rest.exception.SeedFoodTypeException;
import com.rest.model.SeedFoodType;

public class TestSeedFoodTypeService {

	public static void main(String[] args) throws Exception {
		SeedFoodTypeService service=new SeedFoodTypeService();
		SeedFoodType seedfoodtype=new SeedFoodType();
		seedfoodtype.setId(0);
		seedfoodtype.setFoodType(" ");
		seedfoodtype.setStartTime(LocalTime.parse("12:00:00"));
		seedfoodtype.setEndTime(LocalTime.parse("17:00:00"));
		seedfoodtype.setQuantity(200);
		try{
		service.provideSave(seedfoodtype);
		}
		catch(SeedFoodTypeException e){
			e.printStackTrace();
		}
		
	}

}
