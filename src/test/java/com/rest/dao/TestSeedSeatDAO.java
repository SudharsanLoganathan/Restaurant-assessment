package com.rest.dao;

import java.util.List;

import com.rest.model.SeedFood;
import com.rest.model.SeedSeat;

public class TestSeedSeatDAO {

	public static void main(String[] args) {
		SeedSeat seedseat = new SeedSeat();
		/*
		 * seedseat.setId(11); //to insert seedseat.setSeatNo("s11");
		 * seedseat.setIsActive(0);
		 */
		/*
		 * seedseat.setId(1); //to update seedseat.setIsActive(0);
		 */

		SeedSeatDao dao = new SeedSeatDao();

		//dao.delete(11);
		List<SeedSeat> list = dao.list();

		for (SeedSeat a : list) {
			System.out.println(a.getId() + "\t" + a.getSeatNo()+"\t"+a.getIsActive());
		}
	}
	}


