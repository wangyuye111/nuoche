package com.nuoche.redirect.resolverB.interface1.mA;



import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.sql.SQLException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.nuoche.classroot.interface1.NuocheInOutFace;
import com.nuoche.classroot.interface1.NuocheInOutManager;
import com.nuoche.classroot.interface2.NuocheSqlMFace;
import com.nuoche.classroot.interface4.JsonUtil;
import com.nuoche.classroot.interface4.JyHelpManager;
import com.nuoche.classroot.interface4.JyLogDetect.DataType;
import com.nuoche.redirect.resolverB.interface2.mA.NuocheSqlUser_B;
import com.nuoche.redirect.resolverB.interface4.AES;
import com.nuoche.redirect.resolverB.interface4.GetOpenid;
import com.nuoche.redirect.resolverB.interface4.WxPKCS7Encoder;
import com.nuoche.redirect.resolverB.interface4.pay.Ordercreate;




public class NuocheInoutUser_B  extends NuocheInOutManager implements
NuocheInOutFace {
	protected ArrayList<Map<String, Object>> list;
	protected ArrayList<Map<String, Object>> list1;
	protected ArrayList<Map<String, Object>> list2;
	protected String json = "";
	NuocheSqlMFace sqlmface = new NuocheSqlUser_B();
	private static final String WATERMARK = "watermark";  
	   private static final String APPID = "appid";
	public NuocheInoutUser_B(String[] arg, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			SQLException {
		super(arg, request, response);
	}

	@Override
	public void addface() throws SQLException, ServletException, IOException {
		switch (arg[1]) {
		case "apply_move_code_submit":
			apply_move_code_submit(arg);
			break;
			
		case "apply_move_code_pay":
			apply_move_code_pay(arg);
			break;	
			
			
		case "say_art_submit":
			say_art_submit(arg);
			break;
			//1,NOW(),'山东临沂兰山银雀山街','10','职业占位','30.1','42.1'
		case "release_submit":
			release_submit(arg);
			break;
			
		
			
			//+ "(2,'鲁Q85462','18565396328','正在忙碌中，请拨打电话',NOW())";
		case "move_code_submit":
			move_code_submit(arg);
			break;
		case "regiest":
			regiest(arg);
			break;
		
		}

	}
	
	private void regiest(String[] arg) throws SQLException, IOException, ServletException {
		/*String sql = sqlmface.searchSqlface(0, arg);
		log.send(DataType.basicType, "01165", "申请邮寄id查询-sql:", sql);
		String apply_id = sqlUtil.get_string(sql);
		log.send(DataType.basicType, "01165", "申请邮寄id查询-apply_id:", apply_id);
		String jsonadd = "{\"success\":\""+apply_id+"\"}";
		inOutUtil.return_ajax(jsonadd);*/
		
		//String sql = sqlmface.a(0, arg);
		
		
		String sql = "";
		String jsonadd = "";
		sql = sqlmface.addSqlface(0, arg);
		log.send(DataType.basicType, "01168", "检查是否有用户openid:", sql);
		String is_id = sqlUtil.get_string(sql);
		log.send(DataType.basicType, "01168", "检查是否有用户openid:", is_id);
		if(is_id.equals("0")){
			//没有openid，执行插入语句
			sql = sqlmface.addSqlface(1, arg);
			log.send(DataType.basicType, "01168", "插入openid:", sql);
			sqlUtil.sql_exec(sql);
			//jsonadd = "{\"success\":\"1\"}";
			
			//inOutUtil.return_ajax(jsonadd);
		}/*else{
			//有openid,查询信息
			//jsonadd = "{\"success\":\"该用户已存在\"}";
			//inOutUtil.return_ajax(jsonadd);
			
		}*/
		sql = sqlmface.addSqlface(2, arg);
		log.send(DataType.basicType, "01168", "插入openid:", sql);
		list = sqlUtil.get_list(sql);
		inOutUtil.return_ajax(JsonUtil.listToJson(list));
		
	}
	
	/**
	 * 挪车卡生成
	 * arg[1] release_submit
	 * arg[2] User_id			用户id
	 * arg[3] Number_plate 		车牌号
	 * arg[4] Phone 			手机号
     * arg[5] Greetings 		问候语
	 * arg[6] move_code_id
	 * @param arg
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ServletException 
	 */
	private void move_code_submit(String[] arg) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		String sql=""; 
		int success2 =0;
		if(arg[6].equals("0")){
			//添加挪车卡
			sql = sqlmface.addSqlface(0, arg);
			log.send(DataType.basicType, "01165", "挪车卡生成-sql1:", sql);
			success2 = sqlUtil.sql_exec(sql);
		}else{
			//修改挪车卡
			sql = sqlmface.addSqlface(1, arg);
			log.send(DataType.basicType, "01165", "挪车卡修改-sql1:", sql);
			success2 = sqlUtil.sql_exec(sql);
		}
		
		if(success2==1){
			String jsonadd = "{\"success\":\""+arg[6]+"\"}";
			inOutUtil.return_ajax(jsonadd);
		}else{
			String jsonadd = "{\"success\":\"0\"}";
			inOutUtil.return_ajax(jsonadd);
		}
	}

	
	private void release_delete(String[] arg) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		
		log.send(DataType.basicType, "01165", "车位发布-arg:", arg);
		String sql = sqlmface.modSqlface(0, arg);
		log.send(DataType.basicType, "01165", "车位发布-sql1:", sql);
		int success = sqlUtil.sql_exec(sql);
		if(success==1){
			String jsonadd = "{\"success\":\"1\"}";
			inOutUtil.return_ajax(jsonadd);
		}else{
			String jsonadd = "{\"success\":\"0\"}";
			inOutUtil.return_ajax(jsonadd);
		}
	}
	
	
	/**
	 * 车位发布
	 * arg[1] release_submit
	 * arg[2] user_id
	 * arg[3] place
	 * arg[4] price
	 * arg[5] type
	 * arg[6] longitude
	 * arg[7] latitude
	 * 
	 * @param arg
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ServletException 
	 */
	/******************
	 * 问题
	 * 01168修改,有问题可修改或询问
	 * 1.当我点击发布时，从后台获取设置的那个时间(发单时间)，
	 * 增加到发布时间，作为它的结束时间
	 */
	private void release_submit(String[] arg) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		/*String send_time = "";
		
		//查询设置表,获取发布时间段
		String sql1 = sqlmface.addSqlface(1, arg);
		log.send(DataType.basicType, "01165", "车位发布-sql1:", sql1);
		
		send_time = sqlUtil.get_string(sql1);
		log.send(DataType.basicType, "01165", "获取的发布时间段-sql1:", send_time);
		//如果时间段不为空
		if(!send_time.equals("")){
			arg[0] = send_time;
		}else{
			//否则写死为10分钟
			arg[0] = "10";
		}*/
		String time =arg[4];
		if(!time.equals("")){
			arg[4] = time.replaceAll("/", "-");
		}
		if(!arg[9].equals("")){
			arg[0] = arg[9].replaceAll("/", "-");
		}
		arg = Arrays.copyOfRange(arg, 0, arg.length+1);
		
		Date currentTime = new Date();
		
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 ParsePosition pos = new ParsePosition(0);
		 Date strtodate = formatter.parse(arg[4], pos);
		 long interval = (strtodate.getTime() - currentTime.getTime())/1000;
		 System.out.println(interval);
		 log.send(DataType.basicType, "01168", "计算出发布车位的到期时间",interval);
		 //如果发布时间大于当前时间，说明不是立即发布，需要到时间自动发布
		 if(interval>8){
				arg[11]="-1";
			}else{
				arg[11]="0";
			}
		
		/********************
		 * 01168添加,有问题可修改或询问
		 */
		//获取发布的时间段
//		String sql10 = sqlmface.addSqlface(1, arg);
//		log.send(DataType.basicType, "01168", "获取发布的时间段",sql10);
//		String sendtime = sqlUtil.get_string(sql10);
//		log.send(DataType.basicType, "01168", "获取发布的时间段sendtime",sendtime);
//		arg[0] = sendtime;
		//在sql中计算增加时间
//		String sql11 = sqlmface.addSqlface(2, arg);
//		log.send(DataType.basicType, "01168", "计算出发布车位的到期时间",sql11);
//		//sqlUtil.sql_exec(sql11);
//		//点击发布车位后的结束时间
//		String end_timef = sqlUtil.get_string(sql11);
//		log.send(DataType.basicType, "01168", "end_timef",end_timef);
//		arg[0] = end_timef;
		
		
		log.send(DataType.basicType, "01165", "车位发布-arg:", arg);
		String sql = sqlmface.addSqlface(0, arg);
		log.send(DataType.basicType, "01165", "车位发布-sql1:", sql);
		int success = sqlUtil.sql_exec(sql);
		if(success==1){
			String jsonadd = "{\"success\":\"1\"}";
			inOutUtil.return_ajax(jsonadd);
		}else{
			String jsonadd = "{\"success\":\"0\"}";
			inOutUtil.return_ajax(jsonadd);
		}
	}
	/**********************
	 * 原发布代码
	 */
	/*private void release_submit(String[] arg) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		String sql = sqlmface.addSqlface(0, arg);
		log.send(DataType.basicType, "01165", "车位发布-sql1:", sql);
		int success = sqlUtil.sql_exec(sql);
		if(success==1){
			String jsonadd = "{\"success\":\"1\"}";
			inOutUtil.return_ajax(jsonadd);
		}else{
			String jsonadd = "{\"success\":\"0\"}";
			inOutUtil.return_ajax(jsonadd);
		}
	}*/

	/**
	 * 发布动态
	 * arg[1] say_art_submit
	 * arg[2] user_id
	 * arg[3] title
	 * arg[4] photo
	 * @param arg
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ServletException 
	 */
	//title 和photo 换一下 
	private void say_art_submit(String[] arg) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		String sql = sqlmface.addSqlface(0, arg);
		log.send(DataType.basicType, "01165", "发布动态-sql1:", sql);
		int success = sqlUtil.sql_exec(sql);
		if(success==1){
			String jsonadd = "{\"success\":\"1\"}";
			inOutUtil.return_ajax(jsonadd);
		}else{
			String jsonadd = "{\"success\":\"0\"}";
			inOutUtil.return_ajax(jsonadd);
		}
	}

	private void apply_move_code_pay(String[] arg) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		String sql = sqlmface.addSqlface(0, arg);
		log.send(DataType.basicType, "01165", "发布动态-sql1:", sql);
		list=sqlUtil.get_list(sql);
		
		String a=Ordercreate.order_creat(list.get(0).get("openid").toString(),0,list.get(0).get("price").toString(), list.get(0).get("order_num").toString(), request, response);
		inOutUtil.return_ajax(a);
		
	}
	
	
	
	/**
	 * 挪车卡申请邮寄提交
	 * arg[1] apply_move_code_submit
	 * arg[2] user_id---用户id
	 * arg[3] name---姓名
	 * arg[4] phone---电话
	 * arg[5] address---地址
	 * arg[6] number---申请挪车卡数量
	 * arg[7] move_code_id(挪车卡id)
	 * 
	 * 
	 * 2user_id	3code_id	4num	5name	6phone 7address
	 * user_id  code_id num name phone address
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ServletException 
	 */
	private void apply_move_code_submit(String[] arg) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		String sql;
		int success2 = 0;
//		if(arg[3].equals("0")){
//			sql = sqlmface.addSqlface(1, arg);
//			log.send(DataType.basicType, "01165", "挪车卡id为零-sql1:", sql);
//			success2 = sqlUtil.sql_exec(sql);
//			log.send(DataType.basicType, "01165", "挪车卡id为零-success:", success2);
//			
//		}
//		sql = sqlmface.addSqlface(2, arg);
//		log.send(DataType.basicType, "01165", "挪车卡id-sql1:", sql);
//		arg[3] = sqlUtil.get_string(sql);
//		log.send(DataType.basicType, "01165", "挪车卡id-:", arg[3]);
//		sql = sqlmface.addSqlface(0, arg);
//		log.send(DataType.basicType, "01165", "提交挪车卡申请-sql1:", sql);
//		int success = sqlUtil.sql_exec(sql);
		arg[0]=new JyHelpManager().order_create();
		
		sql = sqlmface.addSqlface(0, arg);
		log.send(DataType.basicType, "01165", "挪车卡id-sql1:", sql);
		int success1 = sqlUtil.sql_exec(sql);
		
		String money=String.valueOf(Integer.parseInt(arg[4])*0.01);
		
		sql = sqlmface.addSqlface(1, arg);
		log.send(DataType.basicType, "01165", "挪车卡id-sql1:", sql);
		String  openid = sqlUtil.get_string(sql);
		
		String a=Ordercreate.order_creat(openid,0,money, arg[0], request, response);
		inOutUtil.return_ajax(a);
		//
//		if(success1==1){
//			//sql = sqlmface.addSqlface(2, arg);
//			//int apply_id = sqlUtil.get_int(sql);
//			String jsonadd = "{\"success\":\""+arg[3]+"\"}";
//			inOutUtil.return_ajax(jsonadd);
//		}else{
//			String jsonadd = "{\"success\":\"0\"}";
//			inOutUtil.return_ajax(jsonadd);
//		}

	}
	/**********
	 * 原逻辑
	 */
	/*private void apply_move_code_submit(String[] arg) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		String sql;
		int success2 = 0;
		if(arg[7].equals("0")){
			sql = sqlmface.addSqlface(1, arg);
			log.send(DataType.basicType, "01165", "挪车卡id为零-sql1:", sql);
			success2 = sqlUtil.sql_exec(sql);
			log.send(DataType.basicType, "01165", "挪车卡id为零-success:", success2);
			
		}
		sql = sqlmface.addSqlface(2, arg);
		log.send(DataType.basicType, "01165", "挪车卡id-sql1:", sql);
		arg[7] = sqlUtil.get_string(sql);
		log.send(DataType.basicType, "01165", "挪车卡id-:", arg[7]);
		sql = sqlmface.addSqlface(0, arg);
		log.send(DataType.basicType, "01165", "提交挪车卡申请-sql1:", sql);
		int success = sqlUtil.sql_exec(sql);
		sql = sqlmface.addSqlface(1, arg);
		int success1 = sqlUtil.sql_exec(sql);
		//
		if(success==1&&success2==1){
			//sql = sqlmface.addSqlface(2, arg);
			//int apply_id = sqlUtil.get_int(sql);
			String jsonadd = "{\"success\":\""+arg[7]+"\"}";
			inOutUtil.return_ajax(jsonadd);
		}else{
			String jsonadd = "{\"success\":\"0\"}";
			inOutUtil.return_ajax(jsonadd);
		}

	}*/

	@Override
	public void modface() throws SQLException, ServletException, IOException {
		switch (arg[1]) {

		case "release_delete":
			release_delete(arg);
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

		case "user_search":
			user_search(arg);
			break;
		case "shourumingxi":
			shourumingxi(arg);
			break;

		case "zhichumingxi":
			zhichumingxi(arg);
			break;

		case "youjikalist":
			youjikalist(arg);
			break;
			
			
		case "nuochemingxi":
			nuochemingxi(arg);
			break;
		case "say_art_search":
			say_art_search(arg);
			break;
		case "apply_move_code_search":
			apply_move_code_search(arg);
			break;
		
		//删除超时的发布信息	需要进行查询设置表中时间段
		case "release_del":
			release_del(arg);
			break;
		//微信登录1	
		case "wxlogin_1":
      	  wxlogin_1(arg);
      	  break;
      	//逾期  
		case "yuqi":
			yuqi(arg);
			break;
		case "decodes":
			decodes(arg);
			break;
      	  
		}	
	}
	//逾期钱数计算
	private void yuqi(String[] arg) throws SQLException, IOException, ServletException{
		//获取逾期百分比
		String sql = sqlmface.searchSqlface(0, arg);
		log.send(DataType.basicType, "01165", "逾期百分比:", sql);
		String percent = sqlUtil.get_string(sql);
		//根据抢单id,获取钱数
		sql = sqlmface.searchSqlface(1, arg);
		String price = sqlUtil.get_string(sql);
		double d_percent = Double.parseDouble(percent);
		double d_price = Double.parseDouble(price);
		double overdue_mon = d_percent*d_price;
		double return_mon = d_price-overdue_mon;
		//扣除钱数  overdue_mon,返还钱数return_mon	
	}
	
	private void wxlogin_1(String[] arg) throws SQLException, IOException, ServletException{
		String sql=sqlmface.searchSqlface(0, arg);
		list=sqlUtil.get_list(sql);
		if(list.size()==0){
			String jsonadd = "{\"failed\":\"no such id\"}"; 
			 inOutUtil.return_ajax(jsonadd);
			 
			 
		}else{
			sql=sqlmface.searchSqlface(0, arg);
			list=sqlUtil.get_list(sql);
			inOutUtil.return_ajax(JsonUtil.listToJson(list));
		}
	}
	
	
	
	/**
	 * 申请邮寄id查询
	 * arg[1] apply_move_code_search
	 * arg[2] user_id
	 * arg[3] move_code_id
	 * @param arg
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ServletException 
	 */
	//申请邮寄查询：r
	private void apply_move_code_search(String[] arg) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		String sql = sqlmface.searchSqlface(0, arg);
		log.send(DataType.basicType, "01165", "申请邮寄id查询-sql:", sql);
		String apply_id = sqlUtil.get_string(sql);
		log.send(DataType.basicType, "01165", "申请邮寄id查询-apply_id:", apply_id);
		String jsonadd = "{\"success\":\""+apply_id+"\"}";
		inOutUtil.return_ajax(jsonadd);
	}

	/**
	 * 动态查询
	 * arg[1] say_art_search
	 * arg[2] user_id
	 * arg[3] pageno
	 * @param arg
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ServletException 
	 */
	private void say_art_search(String[] arg) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		String sql = sqlmface.searchSqlface(0, arg);
		int total=sqlUtil.get_int(sql);
		pages=hm.pages(arg[3], total);
		arg[3]=pages[2]+"";

		String ceshi3 = arg[3];
		log.send(DataType.basicType, "01152", "动态查询arg[3]里面的值",ceshi3);

		sql = sqlmface.searchSqlface(1, arg);
		log.send(DataType.basicType, "01152", "动态查询信息sql",sql);
		list=sqlUtil.get_list(sql);
		log.send(DataType.basicType, "01152", "动态查询信息-list",list);

		inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
	}

	//收入明细
	/**
	 * arg[1] shourumingxi
	 * arg[2] user_id
	 * arg[3] pageno
	 * @param arg
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	private void shourumingxi(String[] arg) throws SQLException, IOException,
	ServletException {

		String sql = sqlmface.searchSqlface(0, arg);
		int total=sqlUtil.get_int(sql);
		pages=hm.pages(arg[3], total);
		arg[3]=pages[2]+"";

		String ceshi3 = arg[3];
		log.send(DataType.basicType, "01152", "用户收入arg[3]里面的值",ceshi3);

		sql = sqlmface.searchSqlface(1, arg);
		log.send(DataType.basicType, "01152", "用户收入信息sql",sql);
		list=sqlUtil.get_list(sql);
		log.send(DataType.basicType, "01152", "用户收入信息-list",list);

		inOutUtil.return_ajax(JsonUtil.listToJson(list));

	}

	//支出明细
	/**
	 * arg[1] shourumingxi
	 * arg[2] user_id
	 * arg[3] pageno
	 * @param arg
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	private void zhichumingxi(String[] arg) throws SQLException, IOException,
	ServletException {

		String sql = sqlmface.searchSqlface(0, arg);
		int total=sqlUtil.get_int(sql);
		pages=hm.pages(arg[3], total);
		arg[3]=pages[2]+"";

		String ceshi3 = arg[3];
		log.send(DataType.basicType, "01152", "用户支出arg[3]里面的值",ceshi3);

		sql = sqlmface.searchSqlface(1, arg);
		log.send(DataType.basicType, "01152", "用户支出信息sql",sql);
		list=sqlUtil.get_list(sql);
		log.send(DataType.basicType, "01152", "用户支出信息-list",list);
		inOutUtil.return_ajax(JsonUtil.listToJson(list));
	}

	
	private void youjikalist(String[] arg) throws SQLException, IOException,
	ServletException {

		String sql = sqlmface.searchSqlface(0, arg);
		int total=sqlUtil.get_int(sql);
		pages=hm.pages(arg[3], total);
		arg[3]=pages[2]+"";

		String ceshi3 = arg[3];
		log.send(DataType.basicType, "01152", "用户支出arg[3]里面的值",ceshi3);

		sql = sqlmface.searchSqlface(1, arg);
		log.send(DataType.basicType, "01152", "用户支出信息sql",sql);
		list=sqlUtil.get_list(sql);
		
		for(int i=0;i<list.size();i++){
			
			if(list.get(i).get("is_pay").toString().equals("0")){
				list.get(i).put("is_pay", "支付");
			}else if(list.get(i).get("is_pay").toString().equals("1") && !list.get(i).get("send_number").toString().equals("")){
				list.get(i).put("is_pay", "运单号:"+list.get(i).get("send_number").toString());
			}else if(list.get(i).get("is_pay").toString().equals("1") && list.get(i).get("send_number").toString().equals("")){
				list.get(i).put("is_pay", "待邮寄");
			}
		}
		
		log.send(DataType.basicType, "01152", "用户支出信息-list",list);
		inOutUtil.return_ajax(JsonUtil.listToJson(list));
	}
	
	
	//挪车明细
	/**
	 * arg[1] shourumingxi
	 * arg[2] user_id
	 * arg[3] pageno
	 * @param arg
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	private void nuochemingxi(String[] arg) throws SQLException, IOException,
	ServletException {

		String sql = sqlmface.searchSqlface(0, arg);
		int total=sqlUtil.get_int(sql);
		pages=hm.pages(arg[3], total);
		arg[3]=pages[2]+"";

		String ceshi3 = arg[3];
		log.send(DataType.basicType, "01152", "用户挪车arg[3]里面的值",ceshi3);

		sql = sqlmface.searchSqlface(1, arg);
		log.send(DataType.basicType, "01152", "用户挪车信息sql",sql);
		list=sqlUtil.get_list(sql);
		
		
		
		log.send(DataType.basicType, "01152", "用户挪车信息-list",list);
		inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));	
	}
	private void user_search(String[] arg) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		String sql = sqlmface.searchSqlface(0, arg);
		log.send(DataType.noType, "01107", "测试()-sql: ", sql);
		list = sqlUtil.get_list(sql);
		log.send(DataType.noType, "01107", "测试()-list: ", list);

		inOutUtil.return_ajax(JsonUtil.listToJson(list));
	}
	//车位发布时间计算
	public void updateDengji(String user_id,String release_id) throws SQLException, IOException {
		
		String[] arg = {"","updatedengji",user_id,""};
		
		
	}
	
	/*****
	 * 删除发布时间
	 * 
	 */
	private void release_del(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.searchSqlface(0, arg);
		log.send(DataType.noType, "01168", "查询设置表中信息", sql);
		String a = sqlUtil.get_string(sql);
		arg[0] = a;
		sql = sqlmface.searchSqlface(1, arg);
		list = sqlUtil.get_list(sql);
		for(int i=0;i<list.size();i++){
			list.get(i).get("time");
			
			
		}
		
		
	}
	
	private void decodes(String[] arg) throws SQLException, IOException, ServletException {
		
		
		String appId="wx97698cafd0f86482";
        String iv = arg[3];
        String encryptedData = arg[2];
        String session_key = arg[4];
        String result="";
        try {  
        	String b=encryptedData.replace(" ", "+");
        	iv = iv.replace(" ", "+");
        	session_key = session_key.replace(" ", "+");
            AES aes = new AES();    
            byte[] resultByte = aes.decrypt(Base64.decodeBase64(b), Base64.decodeBase64(session_key), Base64.decodeBase64(iv));    
            log.send(DataType.basicType, "01168", "解密最终数据0:----解密后byte数组内容", resultByte);
            if(null != resultByte && resultByte.length > 0){    
                result = new String(WxPKCS7Encoder.decode(resultByte)); 
                //System.out.println(result);
                log.send(DataType.basicType, "01168", "解密最终数据1:", result);
                JSONObject jsonObject = JSONObject.fromObject(result);
                String decryptAppid = jsonObject.getJSONObject(WATERMARK).getString(APPID);  
                if(!appId.equals(decryptAppid)){  
                    result = "";  
                }  
                log.send(DataType.basicType, "01168", "解密最终数据2:", result);
               // System.out.println(result);
            }    
        } catch (Exception e) {  
            result = "";  
            e.printStackTrace();  
        }  
        log.send(DataType.basicType, "01168", "解密最终数据3:", result);
        inOutUtil.return_ajax(result);
	}
	
	
	
	

}
