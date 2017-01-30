package com.rest.model;

import lombok.Data;

@Data
public class FoodSchedule {
	private int id;
	private SeedFoodType seedFoodType;
	private SeedFood seedFood;
	private int remQty;
}
