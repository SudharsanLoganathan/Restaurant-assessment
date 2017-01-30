package com.rest.dao;

import java.sql.Types;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.rest.util.ConnectionUtil;

public class ProcedureDao {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();


	public String PlaceOrder(String seatno,String food,String qty,String error) {
		SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PR_PLACE_ORDER");
		call.declareParameters(
				new SqlParameter("SEAT_NO", Types.VARCHAR), new SqlParameter("ITEM_LIST", Types.VARCHAR),
				new SqlParameter("QTY_LIST",Types.VARCHAR),new SqlOutParameter("ERR",Types.VARCHAR));
		call.setAccessCallParameterMetaData(false);
		SqlParameterSource in = new MapSqlParameterSource().addValue("SEAT_NO", seatno).addValue("ITEM_LIST",food).addValue("QTY_LIST", qty)
			.addValue("ERR",error);

		Map<String, Object> execute = call.execute(in);
		String status = (String) execute.get("ERR");
		return status;

	}
	public String CancelOrder(int orderNo,String item,String error) {
		SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PR_CANCEL_MULTI_ITEM");
		call.declareParameters(
				new SqlParameter("ORDER_ID", Types.INTEGER), new SqlParameter("ITEM_LIST", Types.VARCHAR)
				,new SqlOutParameter("err",Types.VARCHAR));
		call.setAccessCallParameterMetaData(false);
		SqlParameterSource in = new MapSqlParameterSource().addValue("ORDER_ID",orderNo).addValue("ITEM_LIST",item)
			.addValue("err",error);

		Map<String, Object> execute = call.execute(in);
		String status = (String) execute.get("err");
		return status;

	}
	public String ReqNextItem(int orderNo,String food,int qty,String error) {
		SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PR_REQ_NEXT_ITEM");
		call.declareParameters(
				new SqlParameter("ORDER_ID", Types.INTEGER), new SqlParameter("ITEM", Types.VARCHAR),
				new SqlParameter("QTY",Types.VARCHAR),new SqlOutParameter("err",Types.VARCHAR));
		call.setAccessCallParameterMetaData(false);
		SqlParameterSource in = new MapSqlParameterSource().addValue("ORDER_ID", orderNo).addValue("ITEM",food).addValue("QTY", qty)
			.addValue("err",error);

		Map<String, Object> execute = call.execute(in);
		String status = (String) execute.get("err");
		return status;

	}
	public String PayBill(int orderNo,String seatId,String error) {
		SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PR_PAY_BILL");
		call.declareParameters(
				new SqlParameter("ORDER_NO", Types.INTEGER), new SqlParameter("SEAT_ID", Types.VARCHAR)
				,new SqlOutParameter("err",Types.VARCHAR));
		call.setAccessCallParameterMetaData(false);
		SqlParameterSource in = new MapSqlParameterSource().addValue("ORDER_NO",orderNo).addValue("SEAT_ID",seatId)
			.addValue("err",error);

		Map<String, Object> execute = call.execute(in);
		String status = (String) execute.get("err");
		return status;

	}
	
}
