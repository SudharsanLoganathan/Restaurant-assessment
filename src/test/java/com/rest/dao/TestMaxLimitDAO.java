package com.rest.dao;

import com.rest.model.MaxLimit;

public class TestMaxLimitDAO {

	public static void main(String[] args) {
		MaxLimit maxlimit=new MaxLimit();
		/*maxlimit.setId(2);
		maxlimit.setName("LIM");
		maxlimit.setMaximum(2);*/
		
		MaxLimitDao dao=new MaxLimitDao();
		//dao.delete(2);
		
		System.out.println(dao.list());
		
	}

}
