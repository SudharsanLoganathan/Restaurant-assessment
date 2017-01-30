package com.rest.model;

import java.time.LocalTime;

import lombok.Data;
@Data
public class SeedFoodType {
	private int id;
	private String foodType;
	private LocalTime startTime;
	private LocalTime endTime;
	private int quantity;

}
