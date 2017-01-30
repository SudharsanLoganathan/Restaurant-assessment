package com.rest.dao;


public class TestProcedureDAO {

	public static void main(String[] args) {
		ProcedureDao dao=new ProcedureDao();
		// TODO Auto-generated method stub
		
		/*String status = dao.PlaceOrder("s08","tea,coffee","3,2","message");
		         System.out.println(status);*/
		        /* String status = dao.CancelOrder(114,"tea,coffee","message");
		         System.out.println(status);*/
		/*String status = dao.ReqNextItem(114,"snacks",3,"message");
        System.out.println(status);*/
        String status = dao.PayBill(150,"s10","message");
        System.out.println(status);
	}

}
