package com.rest.validator;

import com.rest.exception.SeedFoodTypeException;
import com.rest.model.SeedFoodType;

public class SeedFoodTypeValidator {
public void validateSave(SeedFoodType seedfoodtype) throws SeedFoodTypeException{
	if(seedfoodtype.getId()<=0 || seedfoodtype.getFoodType()==null || "".equals(seedfoodtype.getFoodType().trim()) || 
			"".equals(seedfoodtype.getStartTime().toString()) ||"".equals(seedfoodtype.getEndTime().toString())){
		throw new SeedFoodTypeException("invalid");
	}
}
}
