package com.nuoche.redirect.resolverB.interface1.mA;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nuoche.classroot.interface1.NuocheInOutFace;
import com.nuoche.classroot.interface1.NuocheInOutManager;
import com.nuoche.classroot.interface2.NuocheSqlMFace;
import com.nuoche.classroot.interface4.HelpManager;
import com.nuoche.classroot.interface4.JsonUtil;
import com.nuoche.classroot.interface4.JyLogDetect.DataType;
//import com.ssctrl.interface4.HelpManager;
import com.nuoche.redirect.resolverB.interface2.mA.NuocheSqlBoos_A_01178;




public class NuocheInoutBoos_A_01178  extends NuocheInOutManager implements
NuocheInOutFace {
	protected ArrayList<Map<String, Object>> list;
	protected ArrayList<Map<String, Object>> list1;
	protected ArrayList<Map<String, Object>> list2;
	protected String json = "";
	NuocheSqlMFace sqlmface = new NuocheSqlBoos_A_01178();

	public NuocheInoutBoos_A_01178(String[] arg, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			SQLException {
		super(arg, request, response);
	}

	@Override
	public void addface() throws SQLException, ServletException, IOException {
		switch (arg[1]) {
			
		
		}

	}
	

	@Override
	public void modface() throws SQLException, ServletException, IOException {
		switch (arg[1]) {
		
		case "time_slot":
			time_slot(arg);
			break;
		
		default:
			break;
		}

	}
	

	
	
	@Override
	public void deleteface() throws SQLException, ServletException, IOException {


	}
	@Override
	public void searchface() throws SQLException, ServletException, IOException {
		switch(arg[1]){
		
		case "shijian_search":
			shijian_search(arg);
			break;
			
		case "nuoche_to_details":
			nuoche_to_details(arg);
			break;
			
		case "nuoche_details":
			nuoche_details(arg);
			break;
			
		case "to_editshijian_search":
			to_editshijian_search(arg);
			break;
			
		}	
	}
	
	//查询后台的抢单和发单时间
	private void to_editshijian_search(String[] arg) throws SQLException, IOException,
	ServletException {
		String sql = "";
		sql = sqlmface.searchSqlface(0, arg);
		log.send(DataType.basicType, "--------01178--------", "-----抢单和发单时间sql-----:", sql);
		list = sqlUtil.get_list(sql);
		log.send(DataType.basicType, "--------01178--------", "-----抢单和发单时间list-----", list);
		
		inOutUtil.return_list(list, "/uiface/boss/shijian_edit_01178.jsp");
		//inOutUtil.return_list(list, "/uiface1/boss/shijian_edit_01178.jsp");shijian_set_01178.jsp
	}
	
	//查询后台设置的时间
	private void shijian_search(String[] arg) throws SQLException, IOException,
	ServletException {
		
		String sql = sqlmface.searchSqlface(0, arg);
		log.send(DataType.basicType, "--------01178--------", "-----设置的时间sql-----:", sql);
		
		list = sqlUtil.get_list(sql);
		log.send(DataType.basicType, "--------01178--------", "-----设置的时间str-----:", list);
		
		if("rushtime".equals(arg[2])){
			inOutUtil.return_list(list, "/uiface/boss/shijian_set_rush_01178.jsp");
		}else if("sendtime".equals(arg[2])){
			inOutUtil.return_list(list, "/uiface/boss/shijian_set_send_01178.jsp");
		}
		//inOutUtil.return_list(list, "/uiface/boss/shijian_set_01178.jsp");
		//inOutUtil.return_list(list, "/uiface1/boss/shijian_edit_01178.jsp");shijian_set_01178.jsp
	}
	
	// 抢单和发单的时间设置
	private void time_slot(String[] arg) throws SQLException, IOException, ServletException {
		String sql = "", jsonadd;
		
		/*if("rushtime".equals(arg[3])){
			sql = sqlmface.modSqlface(0, arg);
			log.send(DataType.basicType, "--------01178--------", "-----设置的时间0-----:", sql);
		}else if("sendtime".equals(arg[3])){
			sql = sqlmface.modSqlface(1, arg);
			log.send(DataType.basicType, "--------01178--------", "-----设置的时间1-----:", sql);
		}
		String str = sqlUtil.get_string(sql);
		log.send(DataType.basicType, "--------01178--------", "-----设置的时间str-----:", str);
		if("".equals(str)){
			//有个判断
			if("rushtime".equals(arg[3])){
				sql = sqlmface.modSqlface(2, arg);
				log.send(DataType.basicType, "--------01178--------", "-----设置的时间2-----:", sql);
			}else if("sendtime".equals(arg[3])){
				sql = sqlmface.modSqlface(3, arg);
				log.send(DataType.basicType, "--------01178--------", "-----设置的时间3-----:", sql);
			}
			
		}else{*/
		//有个判断
		if("rushtime".equals(arg[3])){
			sql = sqlmface.modSqlface(0, arg);
			log.send(DataType.basicType, "--------01178--------", "-----设置的时间0-----:", sql);
		}else if("sendtime".equals(arg[3])){
			sql = sqlmface.modSqlface(1, arg);
			log.send(DataType.basicType, "--------01178--------", "-----设置的时间1-----:", sql);
		}
		
		int json = sqlUtil.sql_exec(sql);
		log.send(DataType.basicType, "--------01178--------", "-----设置的时间json-----:", json);
		
		if(json == 1){
			jsonadd = "{\"success\":\"1\"}";
		}else{
			jsonadd = "{\"fail\":\"0\"}";
		}
		log.send(DataType.basicType, "--------01178--------", "-----设置的时间jsonadd-----:", jsonadd);
		
		inOutUtil.return_ajax(jsonadd);
	}
	
	//跳到挪车明细表
	private void nuoche_to_details(String[] arg) throws SQLException, IOException, ServletException {
		
		String sql;
		
		sql = sqlmface.searchSqlface(0, arg);
		log.send(DataType.basicType, "挪车明细-01178", "查询挪车明细记录数()-sql: ", sql);
		int total = sqlUtil.get_int(sql);
		log.send(DataType.basicType, "挪车明细-01178", "查询挪车明细记录数()-记录数: ", total);
		
		// 追加参数
		int[] pages = HelpManager.pages(arg[2], total);
		arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
		arg[arg.length - 2] = pages[2] + "";
		arg[arg.length - 1] = HelpManager.item + "";
		
		sql = sqlmface.searchSqlface(1, arg);
		log.send(DataType.basicType, "--------01178--------", "-----查询挪车明细-----:", sql);
		list = sqlUtil.get_list(sql);
		log.send(DataType.basicType, "--------01178--------", "-----查询挪车明细-----", list);
		
		if ("tojsp".equals(arg[3])) {
			/**************************
			 * 需要修改
			 *************************/
			//inOutUtil.return_list(list, "boss/nuoche_details_01178.jsp");
			inOutUtil.return_listpage(list, pages, "/uiface/boss/nuoche_details_list_01178.jsp");
		} else if ("tojson".equals(arg[3])) {
			inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
		}
		
	}
	
	//挪车明细表
	//p1=nuoche_details&p2="+pageIndex+"&p3=tojson&p4="+mil_id"+&p5="+startdate+"&p6="+enddate+"&p7="+pp+"&p8="+searchtype
	private void nuoche_details(String[] arg) throws SQLException, IOException, ServletException {
		
		String sql;
		
		sql = sqlmface.searchSqlface(0, arg);
		log.send(DataType.basicType, "挪车明细-01178", "查询挪车明细记录数()-sql: ", sql);
		int total = sqlUtil.get_int(sql);
		log.send(DataType.basicType, "挪车明细-01178", "查询挪车明细记录数()-记录数: ", total);
		
		// 追加参数
		int[] pages = HelpManager.pages(arg[2], total);
		arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
		arg[arg.length - 2] = pages[2] + "";
		arg[arg.length - 1] = HelpManager.item + "";
		
		if(arg[8].equals("年") || arg[8].equals("月")){
			if("".equals(arg[7])){
				sql = sqlmface.searchSqlface(1, arg);
			}else{
				sql = sqlmface.searchSqlface(2, arg);
			}
		}else if(arg[8].equals("时间段")){
			if("".equals(arg[5]) && "".equals(arg[6])){
				sql = sqlmface.searchSqlface(1, arg);
			}else if(!"".equals(arg[5]) && "".equals(arg[6])){	
				sql = sqlmface.searchSqlface(1, arg);
			}else if("".equals(arg[5]) && !"".equals(arg[6])){
				sql = sqlmface.searchSqlface(1, arg);
			}else if(!"".equals(arg[5]) && !"".equals(arg[6])){
				sql = sqlmface.searchSqlface(3, arg);
			}
		}
		
		/*if("0".equals(arg[4])){
			sql = sqlmface.searchSqlface(1, arg);
		}else{
			sql = sqlmface.searchSqlface(2, arg);
		}*/
		log.send(DataType.basicType, "--------01178--------", "-----查询挪车明细-----:", sql);
		list = sqlUtil.get_list(sql);
		log.send(DataType.basicType, "--------01178--------", "-----查询挪车明细-----", list);
		
		if ("tojsp".equals(arg[3])) {
			//inOutUtil.return_list(list, "boss/nuoche_details_01178.jsp");
			inOutUtil.return_listpage(list, pages, "/uiface/boss/nuoche_details_list_01178.jsp");
		} else if ("tojson".equals(arg[3])) {
			inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
		}
		
	}
	

}
