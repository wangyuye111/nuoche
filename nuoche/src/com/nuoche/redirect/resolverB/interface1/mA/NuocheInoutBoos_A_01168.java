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
import com.nuoche.redirect.resolverB.interface2.mA.NuocheSqlBoos_A_01168;
//import com.ssctrl.interface4.HelpManager;




public class NuocheInoutBoos_A_01168  extends NuocheInOutManager implements
NuocheInOutFace {
	protected ArrayList<Map<String, Object>> list;
	protected ArrayList<Map<String, Object>> list1;
	protected ArrayList<Map<String, Object>> list2;
	protected String json = "";
	NuocheSqlMFace sqlmface = new NuocheSqlBoos_A_01168();

	public NuocheInoutBoos_A_01168(String[] arg, HttpServletRequest request,
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
		
		//动态点击不通过,理由
		case "album_checknopass":
			album_checknopass(arg);
			break;
		//card_mod		挪车卡价格设置
		case "card_mod":
			card_mod(arg);
			break;
		case "mod_bili":
			mod_bili(arg);
			break;
		
		case "album_checkpass":
			album_checkpass(arg);
			break;
			
		default:
			break;
		}

	}
	
	/*private void album_checkpass(String[] arg) throws SQLException, IOException,
	ServletException {
		//String sql = sqlmface.searchSqlface(0, arg);
		String sql = sqlmface.modSqlface(0, arg);
		list = sqlUtil.get_list(sql);
		log.send(DataType.basicType, "01162", "相册图片--点击查看", sql);
		inOutUtil.return_list(list, "/uiface/boss/album_search_one1.jsp");
	}
	*/
	private void album_checkpass(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.modSqlface(0, arg);
		sqlUtil.sql_exec(sql);
		String jsonadd = "{\"success\":\"1\"}";
		inOutUtil.return_ajax(jsonadd);
	}
	
	
	// arg[2]:0、修改挪车卡价格，1、修改红包钱数，2、修改代理价格arg[3]:修改钱数
		private void mod_bili(String[] arg) throws SQLException, IOException, ServletException {
			// TODO Auto-generated method stub
			if(arg[2].endsWith("0")){
				String sql = sqlmface.modSqlface(0, arg);
				log.send(DataType.basicType, "01162", "修改提现抽成比例-sql", sql);	
				sqlUtil.sql_exec(sql);
				String jsonadd = "{\"success\":\"1\"}";
				inOutUtil.return_ajax(jsonadd);
			}else if(arg[2].endsWith("1")){
				String sql = sqlmface.modSqlface(1, arg);
				log.send(DataType.basicType, "01162", "修改逾期抽成比例-sql", sql);	
				sqlUtil.sql_exec(sql);
				String jsonadd = "{\"success\":\"1\"}";
				inOutUtil.return_ajax(jsonadd);
			}
		}
	
	
	// arg[2]:0、修改挪车卡价格，1、修改红包钱数，2、修改代理价格arg[3]:修改钱数
	private void card_mod(String[] arg) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		if(arg[2].endsWith("0")){
			String sql = sqlmface.modSqlface(0, arg);
			log.send(DataType.basicType, "01162", "修改挪车卡价格-sql", sql);	
			sqlUtil.sql_exec(sql);
			String jsonadd = "{\"success\":\"1\"}";
			inOutUtil.return_ajax(jsonadd);
		}else if(arg[2].endsWith("1")){
			String sql = sqlmface.modSqlface(1, arg);
			log.send(DataType.basicType, "01162", "修改红包钱数-sql", sql);	
			sqlUtil.sql_exec(sql);
			String jsonadd = "{\"success\":\"1\"}";
			inOutUtil.return_ajax(jsonadd);
		}else if(arg[2].endsWith("2")){
			String sql = sqlmface.modSqlface(2, arg);
			log.send(DataType.basicType, "01162", "修改代理价格-sql", sql);	
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
		//挪车卡(暂时没写jsp页面)
		case "nuoche_card":
			nuoche_card(arg);
			break;
		//申请挪车卡(邮寄)	
		case "apply_card":
			apply_card(arg);
			break;
			
		case "apply_card_youji":
			apply_card_youji(arg);
			break;	
			
			
		//发布信息	
		case "fabu":
			fabu(arg);
			break;
		//动态信息
		case "dynamic":
			dynamic(arg);
			break;
		//动态图片
		case "album_list":
			album_list(arg);
			break;
		
			
		//用户表信息	
		case "user_list":
			user_list(arg);
			break;
		//收入明细	
		case "shourumingxi":
			shourumingxi(arg);
		break;
		//支出明细
		case "zhichumingxi":
			zhichumingxi(arg);
			break;
		//价格设置
		case "jiage_seach":
			jiage_seach(arg);
			break;
		case "jiage_seach_a":
			jiage_seach_a(arg);
			break;
		case "jiage_seach_b":
			jiage_seach_b(arg);
			break;
		case "jiage_seach_c":
			jiage_seach_c(arg);
			break;
			
			
		//提现设置	
		case "tixian_seach":
			tixian_seach(arg);
			break;
		
		case "tixian_seach_a":
			tixian_seach_a(arg);
			break;
		case "tixian_seach_b":
			tixian_seach_b(arg);
			break;
			
		}	
	}
	public void tixian_seach_a(String[] arg) throws SQLException, IOException, ServletException {
		arg[1]="tixian_seach";
		log.send(DataType.basicType, "01168", "修改提现抽成比例-sql", "进入tixian_seach");
		String sql = sqlmface.searchSqlface(0, arg);
		log.send(DataType.basicType, "01168", "修改提现抽成比例-sql", "进入tixian_seachsql语句");
		list = sqlUtil.get_list(sql);
		log.send(DataType.basicType, "01168", "v币充值设置查询", sql);
		inOutUtil.return_list(list, "/uiface/boss/shezhi_tixian_choucheng_01168.jsp");
	}
	public void tixian_seach_b(String[] arg) throws SQLException, IOException, ServletException {
		arg[1]="tixian_seach";
		log.send(DataType.basicType, "01168", "修改提现抽成比例-sql", "进入tixian_seach");
		String sql = sqlmface.searchSqlface(0, arg);
		log.send(DataType.basicType, "01168", "修改提现抽成比例-sql", "进入tixian_seachsql语句");
		list = sqlUtil.get_list(sql);
		log.send(DataType.basicType, "01168", "v币充值设置查询", sql);
		inOutUtil.return_list(list, "/uiface/boss/shezhi_tixian_yuqi_01168.jsp");
	}
	
	//提现设置
	public void tixian_seach(String[] arg) throws SQLException, IOException, ServletException {
		
		log.send(DataType.basicType, "01168", "修改提现抽成比例-sql", "进入tixian_seach");
		String sql = sqlmface.searchSqlface(0, arg);
		log.send(DataType.basicType, "01168", "修改提现抽成比例-sql", "进入tixian_seachsql语句");
		list = sqlUtil.get_list(sql);
		log.send(DataType.basicType, "01168", "v币充值设置查询", sql);
		inOutUtil.return_list(list, "/uiface/boss/shezhi_tixian_01168.jsp");
	}
	
	
	//价格设置
	public void jiage_seach(String[] arg) throws SQLException, IOException, ServletException {
		arg[1]="tixian_seach";
		log.send(DataType.basicType, "01168", "修改提现抽成比例-sql", "进入jiage_seach");
		String sql = sqlmface.searchSqlface(0, arg);
		list = sqlUtil.get_list(sql);
		log.send(DataType.basicType, "01168", "v币充值设置查询", sql);
		inOutUtil.return_list(list, "/uiface/boss/shezhi_jiage_01168.jsp");
	}
	public void jiage_seach_a(String[] arg) throws SQLException, IOException, ServletException {
		arg[1]="tixian_seach";
		log.send(DataType.basicType, "01168", "修改提现抽成比例-sql", "进入jiage_seach");
		String sql = sqlmface.searchSqlface(0, arg);
		list = sqlUtil.get_list(sql);
		log.send(DataType.basicType, "01168", "v币充值设置查询", sql);
		inOutUtil.return_list(list, "/uiface/boss/shezhi_jiage_card_01168.jsp");
	}
	public void jiage_seach_b(String[] arg) throws SQLException, IOException, ServletException {
		arg[1]="tixian_seach";
		log.send(DataType.basicType, "01168", "修改提现抽成比例-sql", "进入jiage_seach");
		String sql = sqlmface.searchSqlface(0, arg);
		list = sqlUtil.get_list(sql);
		log.send(DataType.basicType, "01168", "v币充值设置查询", sql);
		inOutUtil.return_list(list, "/uiface/boss/shezhi_jiage_hongbao_01168.jsp");
		//shezhi_jiage_hongbao_01168
	}
	public void jiage_seach_c(String[] arg) throws SQLException, IOException, ServletException {
		arg[1]="tixian_seach";
		log.send(DataType.basicType, "01168", "修改提现抽成比例-sql", "进入jiage_seach");
		String sql = sqlmface.searchSqlface(0, arg);
		list = sqlUtil.get_list(sql);
		log.send(DataType.basicType, "01168", "v币充值设置查询", sql);
		inOutUtil.return_list(list, "/uiface/boss/shezhi_jiage_daili_01168.jsp");
	}
	
	private void zhichumingxi(String[] arg) throws SQLException,
	IOException, ServletException {
		if(arg[6].equals("")){
			String sql=sqlmface.searchSqlface(0, arg);
			log.send(DataType.basicType, "01168", "收入明细", sql);
			int total=sqlUtil.get_int(sql);
			pages=hm.pages(arg[2], total);
			arg[2]=pages[2]+"";
			sql=sqlmface.searchSqlface(1, arg);
			log.send(DataType.basicType, "01162", "提现明细", sql);
			list=sqlUtil.get_list(sql);
			sql=sqlmface.searchSqlface(2, arg);
			log.send(DataType.basicType, "01162", "提现明细", sql);
			String sum=sqlUtil.get_string(sql);
			log.send(DataType.basicType, "01162", "提现明细---总和", sum);
			//list.get(0).put("sum", sum);
			if(list.size()!=0){
				list.get(0).put("sum", sum);
			}
			if(arg[5].equals("tojsp")){
					inOutUtil.return_listpage(list, pages, "/uiface/boss/mingxi_zhichu_01168.jsp");
			}else {
				inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
			}
		}else{
			String sql=sqlmface.searchSqlface(3, arg);
			log.send(DataType.basicType, "01162", "提现明细", sql);
			int total=sqlUtil.get_int(sql);
			pages=hm.pages(arg[2], total);
			arg[2]=pages[2]+"";
			sql=sqlmface.searchSqlface(4, arg);
			log.send(DataType.basicType, "01162", "提现明细", sql);
			list=sqlUtil.get_list(sql);
			sql=sqlmface.searchSqlface(5, arg);
			log.send(DataType.basicType, "01162", "提现明细", sql);
			String sum=sqlUtil.get_string(sql);
			log.send(DataType.basicType, "01162", "提现明细---总和", sum);
			//list.get(0).put("sum", sum);
			if(list.size()!=0){
				list.get(0).put("sum", sum);
			}
			
			if(arg[5].equals("tojsp")){
				inOutUtil.return_listpage(list, pages, "/uiface/boss/mingxi_zhichu_01168.jsp");
			}else{
				inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
			}
		}
	}
	//收入明细
	private void shourumingxi(String[] arg) throws SQLException,
	IOException, ServletException {
		log.send(DataType.basicType, "01168", "修改提现抽成比例-sql", "进入shourmingxi");
		if(arg[6].equals("")){
			String sql=sqlmface.searchSqlface(0, arg);
			log.send(DataType.basicType, "01168", "收入明细", sql);
			int total=sqlUtil.get_int(sql);
			pages=hm.pages(arg[2], total);
			arg[2]=pages[2]+"";
			sql=sqlmface.searchSqlface(1, arg);
			log.send(DataType.basicType, "01162", "提现明细", sql);
			list=sqlUtil.get_list(sql);
			sql=sqlmface.searchSqlface(2, arg);
			log.send(DataType.basicType, "01162", "提现明细", sql);
			String sum=sqlUtil.get_string(sql);
			log.send(DataType.basicType, "01162", "提现明细---总和", sum);
			if(list.size()!=0){
				list.get(0).put("sum", sum);
			}
			
			if(arg[5].equals("tojsp")){
					inOutUtil.return_listpage(list, pages, "/uiface/boss/mingxi_shouru_01168.jsp");
			}else {
				inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
			}
		}else{
			String sql=sqlmface.searchSqlface(3, arg);
			log.send(DataType.basicType, "01162", "提现明细", sql);
			int total=sqlUtil.get_int(sql);
			pages=hm.pages(arg[2], total);
			arg[2]=pages[2]+"";
			sql=sqlmface.searchSqlface(4, arg);
			log.send(DataType.basicType, "01162", "提现明细", sql);
			list=sqlUtil.get_list(sql);
			sql=sqlmface.searchSqlface(5, arg);
			log.send(DataType.basicType, "01162", "提现明细", sql);
			String sum=sqlUtil.get_string(sql);
			log.send(DataType.basicType, "01162", "提现明细---总和", sum);
			//list.get(0).put("sum", sum);
			if(list.size()!=0){
				list.get(0).put("sum", sum);
			}
			if(arg[5].equals("tojsp")){
				inOutUtil.return_listpage(list, pages, "/uiface/boss/mingxi_shouru_01168.jsp");
			}else{
				inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
			}
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 返回到显示图片的弹框中
	 */
	//动态中的图片
	private void album_list(String[] arg) throws SQLException, IOException,
	ServletException {
		String sql = sqlmface.searchSqlface(0, arg);
		list = sqlUtil.get_list(sql);
		log.send(DataType.basicType, "01162", "相册图片--点击查看", sql);
		inOutUtil.return_list(list, "/uiface/boss/album_search_one1.jsp");
	}
	
	// 动态:审核不通过理由
	// arg[4]:拒绝理由	(暂时没有传递过来的arg[4])
	private void album_checknopass(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.modSqlface(0, arg);
		sqlUtil.sql_exec(sql);
		String jsonadd = "{\"success\":\"1\"}";
		inOutUtil.return_ajax(jsonadd);
	}
	
	//动态查询
	private void dynamic(String[] arg) throws SQLException, IOException,
	ServletException {
		//如果搜索条件为空
		if (arg[3].equals("")) {
			String sql = sqlmface.searchSqlface(0, arg);
			int total = sqlUtil.get_int(sql);
			pages = hm.pages(arg[2], total);
			arg[2] = pages[2] + "";
			sql = sqlmface.searchSqlface(1, arg);
			log.send(DataType.basicType, "01162", "相册图片审核-无条件搜索", sql);
			list = sqlUtil.get_list(sql);
			log.send(DataType.basicType, "01162", "相册图片审核-根据条件搜索", list);
			if (arg[4].equals("tojsp")) {
				log.send(DataType.basicType, "01162", "相册图片审核-根据条件搜索", "带数据进入jsp页面");
				inOutUtil.return_listpage(list, pages, "/uiface/boss/album_search1.jsp");
			} else {
				log.send(DataType.basicType, "01162", "相册图片审核-根据条件搜索", "数据");
				inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
			}
		} else {
			//搜索条件不为空
			String sql = sqlmface.searchSqlface(2, arg);
			int total = sqlUtil.get_int(sql);
			pages = hm.pages(arg[2], total);
			arg[2] = pages[2] + "";
			sql = sqlmface.searchSqlface(3, arg);
			log.send(DataType.basicType, "01162", "相册图片审核-根据条件搜索", sql);
			list = sqlUtil.get_list(sql);
			log.send(DataType.basicType, "01162", "相册图片审核-根据条件搜索", list);
			if (arg[4].equals("tojsp")) {
				log.send(DataType.basicType, "01162", "相册图片审核-根据条件搜索", "带数据进入jsp页面");
				inOutUtil.return_listpage(list, pages, "/uiface/boss/album_search1.jsp");
			} else {
				log.send(DataType.basicType, "01162", "相册图片审核-根据条件搜索", "数据");
				inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
			}
		}
		
		
	}
	
	
	
	//用户管理
	private void user_list(String[] arg) throws SQLException, IOException,
	ServletException {
		log.send(DataType.basicType, "nuoche_card-01168", "查询用户表()-sql:arg234 ", arg[2]+"和"+arg[3]+"和"+arg[4]);
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
				
				arg[0]=list.get(i).get("openid").toString();
				sql2 = sqlmface.searchSqlface(5, arg);
				ArrayList<Map<String, Object>> blist = sqlUtil.get_list(sql2);
				if(blist.size()>0){
					list.get(i).put("card_num", blist.get(0).get("card_num").toString());
					list.get(i).put("car_number", blist.get(0).get("car_number").toString());
					list.get(i).put("car_phone", blist.get(0).get("car_phone").toString());
					list.get(i).put("car_text", blist.get(0).get("car_text").toString());
				}else{
					list.get(i).put("card_num", "");
					list.get(i).put("car_number", "");
					list.get(i).put("car_phone", "");
					list.get(i).put("car_text", "");
				}
				
			}
			
			
			if ("tojsp".equals(arg[3])) {
				/**************************
				 * 需要修改
				 *************************/
				inOutUtil.return_listpage(list, pages, "/uiface/boss/user_list_01168.jsp");
			} else if ("tojson".equals(arg[3])) {
				inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
			}
		}else{
			String sql2 = sqlmface.searchSqlface(1, arg);
			log.send(DataType.basicType, "01156", "orderlist()-sql2: ", sql2);
			ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);
			log.send(DataType.basicType, "01156", "orderlist()-list: ", list);
			
            for(int i=0;i<list.size();i++){
				arg[0]=list.get(i).get("openid").toString();
				sql2 = sqlmface.searchSqlface(5, arg);
				ArrayList<Map<String, Object>> blist = sqlUtil.get_list(sql2);
				if(blist.size()>0){
					list.get(i).put("card_num", blist.get(0).get("card_num").toString());
					list.get(i).put("car_number", blist.get(0).get("car_number").toString());
					list.get(i).put("car_phone", blist.get(0).get("car_phone").toString());
					list.get(i).put("car_text", blist.get(0).get("car_text").toString());
				}else{
					list.get(i).put("card_num", "");
					list.get(i).put("car_number", "");
					list.get(i).put("car_phone", "");
					list.get(i).put("car_text", "");
				}
			}
			
			
			if ("tojsp".equals(arg[3])) {
				/**************************
				 * 需要修改
				 *************************/
				inOutUtil.return_listpage(list, pages, "/uiface/boss/user_list_01168.jsp");
			} else if ("tojson".equals(arg[3])) {
				inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * 查询所有充值卡记录
	 * arg[0]: A-boss-search 
	 * arg[1]: recharge_click_search 
	 * arg[2]: time 
	 * arg[3]: pageNum 页码(jsp页面翻页的页码) 
	 * arg[4]: tojsp: 返回jsp tojson: 返回json数据
	 * arg[5]: curIndex 页面首元素索引
	 * arg[6]: items 一页显示的元素个数
	 * 
	 */
	//发布信息
	private void fabu(String[] arg) throws SQLException, IOException,
	ServletException {
		String sql = sqlmface.searchSqlface(0, arg);
		log.send(DataType.basicType, "fabu-01168", "查询发布信息()-sql: ", sql);
		int total = sqlUtil.get_int(sql);
		log.send(DataType.basicType, "fabu-01168", "查询发布信息()-信息: ", total);
		
		// 追加参数
		int[] pages = HelpManager.pages(arg[2], total);
		arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
		arg[arg.length - 2] = pages[2] + "";
		arg[arg.length - 1] = HelpManager.item + "";
		
		String sql2 = sqlmface.searchSqlface(1, arg);
		log.send(DataType.basicType, "fabu-01168", "orderlist()-sql2: ", sql2);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);
		log.send(DataType.basicType, "fabu-01168", "orderlist()-list: ", list);
		
		if ("tojsp".equals(arg[3])) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/nuoche_release_list_01168.jsp");
		} else if ("tojson".equals(arg[3])) {
			inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
		}
	}
	
	private void apply_card_youji(String[] arg) throws SQLException, IOException,
	ServletException {
		String sql = sqlmface.searchSqlface(0, arg);
		log.send(DataType.basicType, "fabu-01168", "查询发布信息()-sql: ", sql);
		int total = sqlUtil.sql_exec(sql);
		log.send(DataType.basicType, "fabu-01168", "查询发布信息()-信息: ", total);
		inOutUtil.return_ajax(total+"");
	}
	
	
	
	/**
	 * 查询所有充值卡记录
	 * arg[0]: A-boss-search 
	 * arg[1]: recharge_click_search 
	 * arg[2]: time 
	 * arg[3]: pageNum 页码(jsp页面翻页的页码) 
	 * arg[4]: tojsp: 返回jsp tojson: 返回json数据
	 * arg[5]: curIndex 页面首元素索引
	 * arg[6]: items 一页显示的元素个数
	 * 
	 */
	//邮寄挪车卡
	private void apply_card(String[] arg) throws SQLException, IOException,
	ServletException {
		log.send(DataType.basicType, "nuoche_card-01168", "查询数量()-sql:arg[4] ", arg[4]);
		if(arg[4].equals("")){
			String sql = sqlmface.searchSqlface(2, arg);
			log.send(DataType.basicType, "nuoche_card-01168", "查询数量()-sql: ", sql);
			int total = sqlUtil.get_int(sql);
			log.send(DataType.basicType, "nuoche_card-01168", "查询数量()-信息: ", total);
			
			// 追加参数
			int[] pages = HelpManager.pages(arg[2], total);
			arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
			arg[arg.length - 2] = pages[2] + "";
			arg[arg.length - 1] = HelpManager.item + "";
			
			String sql2 = sqlmface.searchSqlface(3, arg);
			log.send(DataType.basicType, "apply_card-01168", "申请挪车卡()-sql2: ", sql2);
			ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);
			log.send(DataType.basicType, "apply_card-01168", "申请挪车卡()-list: ", list);
			
			if ("tojsp".equals(arg[3])) {
				/**************************
				 * 需要修改
				 *************************/
				inOutUtil.return_listpage(list, pages, "/uiface/boss/nuoche_card_apply_01182.jsp");
			} else if ("tojson".equals(arg[3])) {
				inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
			}
		}else{
		String sql = sqlmface.searchSqlface(0, arg);
		log.send(DataType.basicType, "nuoche_card-01168", "查询数量()-sql: ", sql);
		int total = sqlUtil.get_int(sql);
		log.send(DataType.basicType, "nuoche_card-01168", "查询数量()-信息: ", total);
		
		// 追加参数
		int[] pages = HelpManager.pages(arg[2], total);
		arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
		arg[arg.length - 2] = pages[2] + "";
		arg[arg.length - 1] = HelpManager.item + "";
		
		String sql2 = sqlmface.searchSqlface(1, arg);
		log.send(DataType.basicType, "apply_card-01168", "申请挪车卡()-sql2: ", sql2);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);
		log.send(DataType.basicType, "apply_card-01168", "申请挪车卡()-list: ", list);
		
		if ("tojsp".equals(arg[3])) {
			/**************************
			 * 需要修改
			 *************************/
			inOutUtil.return_listpage(list, pages, "/uiface/boss/nuoche_card_apply_01182.jsp");
		} else if ("tojson".equals(arg[3])) {
			inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
		}
		}
	}
	//recharge_click_search		在youhuigou 中 RechargeInOutBoss_01156
	/**
	 * 查询所有充值卡记录
	 * arg[0]: A-boss-search 
	 * arg[1]: recharge_click_search 
	 * arg[2]: time 
	 * arg[3]: pageNum 页码(jsp页面翻页的页码) 
	 * arg[4]: tojsp: 返回jsp tojson: 返回json数据
	 * arg[5]: curIndex 页面首元素索引
	 * arg[6]: items 一页显示的元素个数
	 * 
	 */
	//挪车卡信息(暂不需要)
	private void nuoche_card(String[] arg) throws SQLException, IOException,
	ServletException {
		log.send(DataType.basicType, "nuoche_card-01168", "查询数量()-sql: arg[4]的值", arg[4]);
		String sql = "";
		String sql2 = "";
		String a = arg[4];
		if(a.equals("")){
			sql = sqlmface.searchSqlface(0, arg);
		}
		else{
			sql = sqlmface.searchSqlface(2, arg);
		}
		
		log.send(DataType.basicType, "nuoche_card-01168", "查询数量()-sql: ", sql);
		int total = sqlUtil.get_int(sql);
		log.send(DataType.basicType, "nuoche_card-01168", "查询数量()-信息: ", total);
		
		// 追加参数
		int[] pages = HelpManager.pages(arg[2], total);
		arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
		arg[arg.length - 2] = pages[2] + "";
		arg[arg.length - 1] = HelpManager.item + "";
		if(a.equals("")){
			sql2= sqlmface.searchSqlface(1, arg);
		}else{
			sql2= sqlmface.searchSqlface(3, arg);
		}
		
		log.send(DataType.basicType, "nuoche_card-01168", "挪车卡()-sql2: ", sql2);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);
		log.send(DataType.basicType, "nuoche_card-01168", "挪车卡()-list: ", list);
		
		if ("tojsp".equals(arg[3])) {
			/**************************
			 * 需要修改
			 *************************/
			inOutUtil.return_listpage(list, pages, "/uiface/boss/nuoche_card_list_01168.jsp");
		} else if ("tojson".equals(arg[3])) {
			log.send(DataType.basicType, "nuoche_card-01168", "挪车卡()-list: ", "进入的tojson");
			inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
