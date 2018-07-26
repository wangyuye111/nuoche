package com.nuoche.redirect.resolverB.interface1.mA;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.nuoche.classroot.interface1.NuocheInOutFace;
import com.nuoche.classroot.interface1.NuocheInOutManager;
import com.nuoche.classroot.interface2.NuocheSqlMFace;
import com.nuoche.classroot.interface4.HelpManager;
import com.nuoche.classroot.interface4.JsonUtil;
import com.nuoche.classroot.interface4.JyLogDetect.DataType;
import com.nuoche.redirect.resolverB.interface2.mA.NuocheSqlBoss_152;
import com.nuoche.redirect.resolverB.interface2.mA.NuocheSqlUser_152;




public class NuocheInoutBoss_152  extends NuocheInOutManager implements
NuocheInOutFace {
	protected ArrayList<Map<String, Object>> list;
	protected ArrayList<Map<String, Object>> list1;
	protected ArrayList<Map<String, Object>> list2;
	protected String json = "";
	NuocheSqlMFace sqlmface = new NuocheSqlBoss_152();

	public NuocheInoutBoss_152(String[] arg, HttpServletRequest request,
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
		case "mod_duanxin":
			mod_duanxin(arg);
			break;


		
		}

	}

	

	private void mod_duanxin(String[] arg) throws SQLException, IOException, ServletException {
		if(arg[2].endsWith("0")){
			String sql = sqlmface.modSqlface(0, arg);
			log.send(DataType.basicType, "01152", "修改短信语句例-sql", sql);	
			sqlUtil.sql_exec(sql);
			String jsonadd = "{\"success\":\"1\"}";
			inOutUtil.return_ajax(jsonadd);
		}else if(arg[2].endsWith("1")){
			String sql = sqlmface.modSqlface(1, arg);
			log.send(DataType.basicType, "01152", "修改短信语句例-sql", sql);	
			sqlUtil.sql_exec(sql);
			String jsonadd = "{\"success\":\"1\"}";
			inOutUtil.return_ajax(jsonadd);
		}else if(arg[2].endsWith("2")){
			String sql = sqlmface.modSqlface(2, arg);
			log.send(DataType.basicType, "01152", "修改短信语句例-sql", sql);	
			sqlUtil.sql_exec(sql);
			String jsonadd = "{\"success\":\"1\"}";
			inOutUtil.return_ajax(jsonadd);
		}else if(arg[2].endsWith("3")){
			String sql = sqlmface.modSqlface(3, arg);
			log.send(DataType.basicType, "01152", "修改短信语句例-sql", sql);	
			sqlUtil.sql_exec(sql);
			String jsonadd = "{\"success\":\"1\"}";
			inOutUtil.return_ajax(jsonadd);
		}
		
	}

	@Override
	public void deleteface() throws SQLException, ServletException, IOException {

		
		}
	
	
		


	@Override
	public void searchface() throws SQLException, ServletException, IOException {
		switch(arg[1]){
		case "duanxin_seach":
			duanxin_seach(arg);
		break;
		
		case "duanxin_seach_a":
			duanxin_seach_a(arg);
		break;
		
		case "duanxin_seach_b":
			duanxin_seach_b(arg);
		break;
		
		case "duanxin_seach_c":
			duanxin_seach_c(arg);
		break;
		
		case "duanxin_seach_d":
			duanxin_seach_d(arg);
		break;
		
		case"sefabunuoche":
			sefabunuoche(arg);
			break;
		case "qiangcheweimingxi":
			qiangcheweimingxi(arg);
		break;
		
		}	
	}
	
	
	//抢车位明细
	private void qiangcheweimingxi(String[] arg) throws SQLException,
	IOException, ServletException {
		if(arg[6].equals("")){
			String sql=sqlmface.searchSqlface(0, arg);
			log.send(DataType.basicType, "01152", "抢车位明细", sql);
			int total=sqlUtil.get_int(sql);
			pages=hm.pages(arg[2], total);
			arg[2]=pages[2]+"";
			sql=sqlmface.searchSqlface(1, arg);
			log.send(DataType.basicType, "01152", "抢车位明细", sql);
			list=sqlUtil.get_list(sql);
			
			/*String name = list.get(0).get("nicknames").toString();
			list.get(0).put("name", name);*/
			
			sql=sqlmface.searchSqlface(2, arg);
			log.send(DataType.basicType, "01152", "抢车位明细", sql);
			String sum=sqlUtil.get_string(sql);
			log.send(DataType.basicType, "01152", "抢车位明细---总和", sum);
			if(list.size()!=0){
				list.get(0).put("sum", sum);
			}/*else{
				int a=0;
				//list.add("sum", a);
				HashMap<String, Object> map = new HashMap<String, Object>();
	             map.put("sum", a);
	             list.add(map);
				//list.get(0).put("sum", a);
			}*/
			
			log.send(DataType.basicType, "01152", "查看list-01152", list);
			if(arg[5].equals("tojsp")){
					inOutUtil.return_listpage(list, pages, "/uiface/boss/qiangchewei_01152.jsp");
			}else {
				inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
			}
		}else{
			String sql=sqlmface.searchSqlface(3, arg);
			log.send(DataType.basicType, "01152", "抢车位明细", sql);
			int total=sqlUtil.get_int(sql);
			pages=hm.pages(arg[2], total);
			arg[2]=pages[2]+"";
			sql=sqlmface.searchSqlface(4, arg);
			log.send(DataType.basicType, "01152", "抢车位明细", sql);
			list=sqlUtil.get_list(sql);
			
			
			/*String name = list.get(0).get("nicknames").toString();
			list.get(0).put("name", name);*/
			
			sql=sqlmface.searchSqlface(5, arg);
			log.send(DataType.basicType, "01152", "抢车位明细", sql);
			String sum=sqlUtil.get_string(sql);
			log.send(DataType.basicType, "01152", "抢车位明细---总和", sum);
			/*list.get(0).put("sum", sum);*/
			if(list.size()!=0){
				list.get(0).put("sum", sum);
			}/*else{
				int a=0;
				//list.add("sum", a);
				HashMap<String, Object> map = new HashMap<String, Object>();
	             map.put("sum", a);
	             list.add(map);
				//list.get(0).put("sum", a);
			}*/
			
			log.send(DataType.basicType, "01152", "查看list-01152BBB", list);
			if(arg[5].equals("tojsp")){
				inOutUtil.return_listpage(list, pages, "/uiface/boss/qiangchewei_01152.jsp");
			}else{
				inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
			}
		}
	}
	
	
	
	
	private void sefabunuoche(String[] arg) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		
		String a = arg[4];
		String sql ="";
		if(a.equals("0")){
			sql= sqlmface.searchSqlface(2, arg);
		}else{
			sql= sqlmface.searchSqlface(0, arg);
		}
		log.send(DataType.basicType, "nuoche_card-01168", "查询用户表()-sql: ", sql);
		int total = sqlUtil.get_int(sql);
		log.send(DataType.basicType, "nuoche_card-01168", "查询用户表()-信息: ", total);
		
		// 追加参数
		int[] pages = HelpManager.pages(arg[2], total);
		arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
		arg[arg.length - 2] = pages[2] + "";
		arg[arg.length - 1] = HelpManager.item + "";
		log.send(DataType.basicType, "nuoche_card-01168", "查询用户表()-sql:arg56 ", arg[5]+"和"+arg[6]);
		if(a.equals("0")){
			String sql2 = sqlmface.searchSqlface(3, arg);
			log.send(DataType.basicType, "01156", "orderlist()-sql2: ", sql2);
			ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);
			log.send(DataType.basicType, "01156", "orderlist()-list: ", list);
			
			for(int i=0;i<list.size();i++){
				
				if(list.get(i).get("paymoney").toString().equals("")){
					list.get(i).put("paymoney", "0");
				}
				
				if(list.get(i).get("cw_states").toString().equals("-1")){
					list.get(i).put("cw_states", "等待发布");
				}else if(list.get(i).get("cw_states").toString().equals("1")&&!list.get(i).get("res_msg").toString().equals("success")){
					list.get(i).put("cw_states", "逾期取消发布");
				}else if(list.get(i).get("cw_states").toString().equals("1")&&list.get(i).get("res_msg").toString().equals("success")&&list.get(i).get("refuseres_msg").toString().equals("OK")){
					list.get(i).put("cw_states", "取消订单已扣除50%");
				}else if(list.get(i).get("cw_states").toString().equals("1")&&list.get(i).get("res_msg").toString().equals("success")&&list.get(i).get("refuseres_msg").toString().equals("")){
					list.get(i).put("cw_states", "交易成功");
				}else if(list.get(i).get("cw_states").toString().equals("0")&&!list.get(i).get("res_msg").toString().equals("success")){
					list.get(i).put("cw_states", "发布中");
				}else if(list.get(i).get("cw_states").toString().equals("0")&&list.get(i).get("res_msg").toString().equals("success")&&list.get(i).get("refuseres_msg").toString().equals("OK")){
					list.get(i).put("cw_states", "取消订单已扣除50%");
				}else if(list.get(i).get("cw_states").toString().equals("0")&&list.get(i).get("res_msg").toString().equals("success")&&list.get(i).get("refuseres_msg").toString().equals("")){
					list.get(i).put("cw_states", "交易成功");
				}
				
			}
			if ("tojsp".equals(arg[3])) {
				/**************************
				 * 需要修改
				 *************************/
				inOutUtil.return_listpage(list, pages, "/uiface/boss/nuoche_fabu_list_01182.jsp");
			} else if ("tojson".equals(arg[3])) {
				inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
			}
		}else{
			String sql2 = sqlmface.searchSqlface(1, arg);
			log.send(DataType.basicType, "01156", "orderlist()-sql2: ", sql2);
			ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);
			log.send(DataType.basicType, "01156", "orderlist()-list: ", list);
			
			for(int i=0;i<list.size();i++){
				
				if(list.get(i).get("paymoney").toString().equals("")){
					list.get(i).put("paymoney", "0");
				}
				
				if(list.get(i).get("cw_states").toString().equals("-1")){
					list.get(i).put("cw_states", "等待发布");
				}else if(list.get(i).get("cw_states").toString().equals("1")&&!list.get(i).get("res_msg").toString().equals("success")){
					list.get(i).put("cw_states", "逾期取消发布");
				}else if(list.get(i).get("cw_states").toString().equals("1")&&list.get(i).get("res_msg").toString().equals("success")&&list.get(i).get("refuseres_msg").toString().equals("OK")){
					list.get(i).put("cw_states", "取消订单已扣除50%");
				}else if(list.get(i).get("cw_states").toString().equals("1")&&list.get(i).get("res_msg").toString().equals("success")&&list.get(i).get("refuseres_msg").toString().equals("")){
					list.get(i).put("cw_states", "交易成功");
				}else if(list.get(i).get("cw_states").toString().equals("0")&&!list.get(i).get("res_msg").toString().equals("success")){
					list.get(i).put("cw_states", "发布中");
				}else if(list.get(i).get("cw_states").toString().equals("0")&&list.get(i).get("res_msg").toString().equals("success")&&list.get(i).get("refuseres_msg").toString().equals("OK")){
					list.get(i).put("cw_states", "取消订单已扣除50%");
				}else if(list.get(i).get("cw_states").toString().equals("0")&&list.get(i).get("res_msg").toString().equals("success")&&list.get(i).get("refuseres_msg").toString().equals("")){
					list.get(i).put("cw_states", "交易成功");
				}
				
			}
			
			if ("tojsp".equals(arg[3])) {
				/**************************
				 * 需要修改
				 *************************/
				inOutUtil.return_listpage(list, pages, "/uiface/boss/nuoche_fabu_list_01182.jsp");
			} else if ("tojson".equals(arg[3])) {
				inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
			}
		}
		
		/*int pagenum=10;
		if(arg[4]!=""){
		String sql=sqlmface.searchSqlface(0, arg);
		log.send(DataType.basicType, "01182", "通过id查询发布车位信息——sql", sql);
		int total=sqlUtil.get_int(sql);
		pages=hm.pages(arg[2], total);
		arg[2]=pages[2]+"";
		String ceshi=arg[2];
		log.send(DataType.basicType, "01182", "页数", ceshi);
		sql=sqlmface.searchSqlface(2, arg);
		log.send(DataType.basicType, "01182", "车位信息集合 -sql", sql);
		list=sqlUtil.get_list(sql);
		log.send(DataType.basicType, "01182", "车位信息集合 -list", list);
		inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
		}
		else{
		String sql=sqlmface.searchSqlface(2, arg);
		log.send(DataType.basicType, "01182", "查询发布车位信息——sql", sql);
		int total=sqlUtil.get_int(sql);
		pages=hm.pages(arg[2], total);
		arg[2]=pages[2]+"";
		String ceshi=arg[2];
		log.send(DataType.basicType, "01182", "页数", ceshi);
		sql=sqlmface.searchSqlface(3, arg);
		log.send(DataType.basicType, "01182", "车位信息集合 -sql", sql);
		list=sqlUtil.get_list(sql);
		log.send(DataType.basicType, "01182", "车位信息集合 -list", list);
		inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
		}*/
	}
	
	
	
	
	//短信设置
	public void duanxin_seach(String[] arg) throws SQLException, IOException, ServletException {
		log.send(DataType.basicType, "--------01152--------", "-----1111-----", list);
		String sql = sqlmface.searchSqlface(0, arg);
		log.send(DataType.basicType, "--------01152--------", "-----2222-----", list);
		list = sqlUtil.get_list(sql);
		log.send(DataType.basicType, "01152", "短信修改设置", sql);
		inOutUtil.return_list(list, "/uiface/boss/shezhi_duanxin_01152.jsp");
	}

	//短信设置
	public void duanxin_seach_a(String[] arg) throws SQLException, IOException, ServletException {
		arg[1] = "duanxin_seach";
		String sql = sqlmface.searchSqlface(0, arg);
		list = sqlUtil.get_list(sql);
		log.send(DataType.basicType, "01152", "短信修改查询", sql);
		inOutUtil.return_list(list, "/uiface/boss/shezhi_Amodtixian_01152.jsp");
	}
	
	//短信设置
	public void duanxin_seach_b(String[] arg) throws SQLException, IOException, ServletException {
		arg[1] = "duanxin_seach";
		String sql = sqlmface.searchSqlface(0, arg);
		list = sqlUtil.get_list(sql);
		log.send(DataType.basicType, "01152", "短信修改查询", sql);
		inOutUtil.return_list(list, "/uiface/boss/shezhi_Bmodtixian_01152.jsp");
	}
	
	//短信设置
	public void duanxin_seach_c(String[] arg) throws SQLException, IOException, ServletException {
		arg[1] = "duanxin_seach";
		String sql = sqlmface.searchSqlface(0, arg);
		list = sqlUtil.get_list(sql);
		log.send(DataType.basicType, "01152", "短信修改查询", sql);
		inOutUtil.return_list(list, "/uiface/boss/shezhi_Cmodtixian_01152.jsp");
	}
	//短信设置
	public void duanxin_seach_d(String[] arg) throws SQLException, IOException, ServletException {
		arg[1] = "duanxin_seach";
		String sql = sqlmface.searchSqlface(0, arg);
		list = sqlUtil.get_list(sql);
		log.send(DataType.basicType, "01152", "短信修改查询", sql);
		inOutUtil.return_list(list, "/uiface/boss/shezhi_Dmodtixian_01152.jsp");
	}
	
}
