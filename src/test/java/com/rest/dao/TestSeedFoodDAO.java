package com.rest.dao;

import java.util.List;

import com.rest.model.OrderDetail;
import com.rest.model.SeedFood;

public class TestSeedFoodDAO {

	public static void main(String[] args) {
		SeedFood seedfood = new SeedFood();
		/*seedfood.setId(15);
		seedfood.setItem("Special coffee");
		seedfood.setPrice(20);*/
		SeedFoodDao dao = new SeedFoodDao();
		//dao.delete(15);
		List<SeedFood> list = dao.list();

		for (SeedFood a : list) {
			System.out.println(a.getId() + "\t" + a.getItem()+"\t"+a.getPrice());
		}
	}

		
	}


