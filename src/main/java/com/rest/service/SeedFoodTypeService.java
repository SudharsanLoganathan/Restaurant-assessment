package com.rest.service;

import com.rest.dao.SeedFoodTypeDao;
import com.rest.exception.SeedFoodTypeException;
import com.rest.model.SeedFoodType;
import com.rest.validator.SeedFoodTypeValidator;

public class SeedFoodTypeService {
public void provideSave(SeedFoodType seedfoodtype) throws SeedFoodTypeException{
	SeedFoodTypeValidator validate=new SeedFoodTypeValidator();
	SeedFoodTypeDao dao=new SeedFoodTypeDao();
	try
	{
	validate.validateSave(seedfoodtype);
	dao.save(seedfoodtype);
	}
	catch(SeedFoodTypeException e){
		throw new SeedFoodTypeException("invalid value",e);
	}
}
}
