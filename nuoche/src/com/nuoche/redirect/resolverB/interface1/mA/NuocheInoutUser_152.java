package com.nuoche.redirect.resolverB.interface1.mA;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
import com.nuoche.redirect.resolverB.interface2.mA.NuocheSqlUser_152;




public class NuocheInoutUser_152  extends NuocheInOutManager implements
NuocheInOutFace {
	protected ArrayList<Map<String, Object>> list;
	protected ArrayList<Map<String, Object>> list1;
	protected ArrayList<Map<String, Object>> list2;
	protected String json = "";
	NuocheSqlMFace sqlmface = new NuocheSqlUser_152();

	public NuocheInoutUser_152(String[] arg, HttpServletRequest request,
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
		case "fukuanfail":
			fukuanfail(arg);
			break;



		}

	}

	//付款失败，把信息返回到列表
	private void fukuanfail(String[] arg) throws SQLException, ServletException, IOException {
		String sql = sqlmface.modSqlface(0, arg);
		log.send(DataType.specialType, "01152", "修改发布信息", sql);
		int good = sqlUtil.sql_exec(sql);
		if (good == 1) {
			String jsonadd = "{\"success\":\"1\"}";
			inOutUtil.return_ajax(jsonadd);
		} else {
			String jsonadd = "{\"success\":\"0\"}";
			inOutUtil.return_ajax(jsonadd);
		}

	}

	@Override
	public void deleteface() throws SQLException, ServletException, IOException {

		switch (arg[1]) {
		case "fukuansuccess":
			fukuansuccess(arg);
			break;
		}
	}

	//付款成功，删除信息
	private void fukuansuccess(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.deleteSqlface(arg);
		log.send(DataType.specialType, "01152", "删除发布信息", sql);
		int good = sqlUtil.sql_exec(sql);
		if (good == 1) {
			String jsonadd = "{\"success\":\"1\"}";
			inOutUtil.return_ajax(jsonadd);
		} else {
			String jsonadd = "{\"success\":\"0\"}";
			inOutUtil.return_ajax(jsonadd);
		}

	}

	@Override
	public void searchface() throws SQLException, ServletException, IOException {
		switch(arg[1]){
		case "shourumingxi":
			shourumingxi(arg);
			break;

		case "zhichumingxi":
			zhichumingxi(arg);
			break;

		case "nuochemingxi":
			nuochemingxi(arg);
			break;

		case "qiangchewei":
			qiangchewei(arg);
			break;

		case"jiqiuchewei":
			jiqiuchewei(arg);
			break;
		case "code_search":
			code_search(arg);
			break;
		case "user_move_search":
			user_move_search(arg);
			break;
		case "jiqiuchewei_01168":
			jiqiuchewei_01168(arg);
			break;

		}	
	}

	private void user_move_search(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.searchSqlface(0, arg);
		log.send(DataType.basicType, "01152", "需求车位信息sql",sql);
		list=sqlUtil.get_list(sql);
		log.send(DataType.specialType, "01168", "扫描二维码出现的数据", list);
		inOutUtil.return_ajax(JsonUtil.listToJson(list));
	}


	private void code_search(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.searchSqlface(0, arg);
		log.send(DataType.basicType, "01152", "需求车位信息sql",sql);
		list=sqlUtil.get_list(sql);
		log.send(DataType.specialType, "01168", "扫描二维码出现的数据", list);
		inOutUtil.return_ajax(JsonUtil.listToJson(list));
		System.out.println(sql);
	}


	//急需车位
	/*********
	 * 问题
	 * 2.当我点击急求车位时，如果当前时间大于结束时间，删除这条信息
	 * @throws ParseException 
	 */

	private void jiqiuchewei(String[] arg) throws SQLException, IOException, ServletException{
		String sql="";
		if(arg[4].equals("")||arg[5].equals("")){
			sql = sqlmface.searchSqlface(2, arg);
			log.send(DataType.basicType, "01152", "需求车位信息sql-",sql);
			list = sqlUtil.get_list(sql);
			arg[4]=list.get(0).get("user_latitude").toString();
			arg[5]=list.get(0).get("user_longitude").toString();
		}
		arg = Arrays.copyOfRange(arg, 0, arg.length+8);
		double aa[]=getAround(Double.parseDouble(arg[4]), Double.parseDouble(arg[5]), 5000);
		arg[10]=aa[0]+"";  //纬度最大值
		arg[11]=aa[2]+"";  //纬度最大值
		arg[12]=aa[1]+"";  //经度最小值
		arg[13]=aa[3]+"";  //经度最大值
		System.out.println("------------------------aa[0]"+aa[0]);
		System.out.println("------------------------aa[1]"+aa[1]);
		System.out.println("------------------------aa[2]"+aa[2]);
		System.out.println("------------------------aa[3]"+aa[3]);
		sql = sqlmface.searchSqlface(0, arg);
		System.out.println("计算经纬度----------------------"+sql);
		System.out.println(sql);
		log.send(DataType.basicType, "01152", "需求车位信息sql-",sql);
		int total=sqlUtil.get_int(sql);
		System.out.println(total);
		pages=hm.pages(arg[3], total);
		arg[3]=pages[2]+"";
		String testnuoche=arg[3];
		log.send(DataType.basicType, "01152", "需求车位arg[3]数据",testnuoche);
		String sql2 = sqlmface.searchSqlface(1, arg);
		System.out.println(sql2);
		log.send(DataType.basicType, "01152", "需求车位信息sql",sql2);
		list1 = sqlUtil.get_list(sql2);
		for(int i=0;i<list1.size();i++){
			String times="";
			System.out.println(times);
			times = time_cals(list1.get(i).get("end_timef").toString());
			BigDecimal b = new BigDecimal(Double.parseDouble(list1.get(i).get("juli").toString()));
			double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out.println("f1------------------------"+f1);
			list1.get(i).put("juli", f1);
			list1.get(i).put("times", times);
		}

		log.send(DataType.basicType, "01152", "需求车位信息-list",list1);
		inOutUtil.return_ajax(JsonUtil.listToJson(list1));
		//inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
	}
	
	private static final double PI = 3.14159265;
	public static double[] getAround(double lat,double lon,int raidus){  
		//计算经纬度范围
		Double latitude = lat;  
		Double longitude = lon;  
		Double degree = (24901*1609)/360.0;  
		double raidusMile = raidus;  
		Double dpmLat = 1/degree;  
		Double radiusLat = dpmLat*raidusMile;  
		Double minLat = latitude - radiusLat;  
		Double maxLat = latitude + radiusLat;  

		Double mpdLng = degree*Math.cos(latitude * (PI/180));  
		Double dpmLng = 1 / mpdLng;  
		Double radiusLng = dpmLng*raidusMile;  
		Double minLng = longitude - radiusLng;  
		Double maxLng = longitude + radiusLng;  
		//System.out.println("["+minLat+","+minLng+","+maxLat+","+maxLng+"]");  
		return new double[]{minLat,minLng,maxLat,maxLng};  
	} 



	//点击抢车位
	/****************
	 * 问题
	 * 3.当我点击抢车位时，从后台获取设置的那个时间(抢单时间),
	 * 增加到结束时间，重新改变结束时间
	 */
	private void qiangchewei(String[] arg) throws SQLException, IOException, ServletException {

		/********************
		 * 01168添加,有问题可修改或询问
		 */
		//获取抢单的时间段
		String sql10 = sqlmface.searchSqlface(10, arg);
		log.send(DataType.basicType, "01168", "获取抢单的时间段",sql10);
		String rush_time = sqlUtil.get_string(sql10);
		System.out.println("sql10"+sql10);
		arg[0] = rush_time;
		//在sql中计算增加时间
		String sql11 = sqlmface.searchSqlface(11, arg);
		log.send(DataType.basicType, "01168", "计算出抢车位的到期时间",sql10);
		System.out.println("sql11"+sql10);
		//sqlUtil.sql_exec(sql11);
		//点击抢车位后的结束时间
		String end_timeq = sqlUtil.get_string(sql11);
		arg[0] = end_timeq;
		System.out.println(arg[0]);





		log.send(DataType.basicType, "01152", "车位发布信息",arg[2]+"和"+arg[3]);
		String sql = sqlmface.searchSqlface(0, arg);
		log.send(DataType.basicType, "01152", "车位发布信息",sql);
		list = sqlUtil.get_list(sql);
		log.send(DataType.basicType, "01152", "用户发布信息-list",list);

		String zhiding = list.get(0).get("set_top").toString();
		String dianji = list.get(0).get("click").toString();
		log.send(DataType.basicType, "01152", "状态",zhiding);
		if(dianji.equals("0")){
			String sql1 = sqlmface.searchSqlface(1, arg);
			sqlUtil.sql_exec(sql1);
			String sql2 = sqlmface.searchSqlface(2, arg);
			sqlUtil.sql_exec(sql2);
			//对数据进行重新查询
			String sql3 = sqlmface.searchSqlface(0, arg);
			log.send(DataType.basicType, "01152", "车位发布信息",sql3);
			list1 = sqlUtil.get_list(sql3);
			log.send(DataType.basicType, "01152", "用户发布信息-list",list1);
		}else{
			String sql4 = sqlmface.searchSqlface(0, arg);
			log.send(DataType.basicType, "01152", "车位发布信息",sql4);
			list1 = sqlUtil.get_list(sql4);
			log.send(DataType.basicType, "01152", "用户发布信息-list",list1);
		}

		inOutUtil.return_ajax(JsonUtil.listToJson(list));
	}





	//收入明细
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

		inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));

	}

	//支出明细
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
		inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));	
	}


	//挪车明细
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






	private void jiqiuchewei_01168(String[] arg) throws SQLException, IOException, ServletException{

		//String send_time = "11";		//时间段
		String send_time = "";
		String times = "";
		//String fabu_time = "2018-06-03 16:33:00";		//发布时间
		String fabu_time = "";
		String sql11=sqlmface.searchSqlface(1, arg);
		log.send(DataType.basicType, "01152", "需求车位信息sql",sql11);
		send_time = sqlUtil.get_string(sql11);
		log.send(DataType.basicType, "01152", "需求车位信息sqlsend_time",send_time);
		String sql12 = sqlmface.searchSqlface(2, arg);
		log.send(DataType.basicType, "01152", "fabu_time发布sql12",sql12);
		list2 = sqlUtil.get_list(sql12);
		log.send(DataType.basicType, "01152", "fabu_time发布list2",list2);
		for(int i=0;i<list2.size();i++){
			fabu_time = list2.get(i).get("time").toString();
			log.send(DataType.basicType, "01152", "fabu_time发布",fabu_time);
			times = time_cal(fabu_time,send_time);
			list2.get(i).put("times", times);
		}
		//String times = "";
		//times = time_cal(fabu_time,send_time);



		/*String times = "";
		//获取发布时间段
    	String sql11=sqlmface.searchSqlface(1, arg);
    	log.send(DataType.basicType, "01152", "需求车位信息sql",sql11);
    	//list=sqlUtil.get_list(sql1);
    	send_time = sqlUtil.get_string(sql11);
    	//send_time 为11分钟


    	String sql12 = sqlmface.searchSqlface(2, arg);
    	//获取发布表中信息
    	list2 = sqlUtil.get_list(sql12);
    	for(int i=0;i<list2.size();i++){
    		fabu_time = list2.get(i).get("time").toString();
    		times = time_cal(fabu_time,send_time);
    		list2.get(i).put("times", times);
    	}*/


		log.send(DataType.basicType, "01168abcd", "计算的时间",times);
		inOutUtil.return_ajax(JsonUtil.listToJson(list2));
		/*
		 double jingdu = 0.0;	//地点的经度
		 double weidu = 0.0;	//地点我的纬度
		 double myjingdu = 0.0;	//我的经度
		 double myweidu = 0.0;	//我的纬度
		 String sql = sqlmface.searchSqlface(0, arg);
		 int total=sqlUtil.get_int(sql);
		 pages=hm.pages(arg[3], total);
		 arg[3]=pages[2]+"";
		 String testnuoche=arg[3];
		 log.send(DataType.basicType, "01152", "需求车位arg[3]数据",testnuoche);
		 String sql2 = sqlmface.searchSqlface(2, arg);
		 log.send(DataType.basicType, "01152", "需求车位信息sql",sql2);
		 list1 = sqlUtil.get_list(sql2);

		 myjingdu = Double.valueOf(list1.get(0).get("user_longitude").toString());
		 myweidu = Double.valueOf(list1.get(0).get("user_latitude").toString());

		for(int i=0;i<list.size();i++){
			jingdu = Double.valueOf(list.get(i).get("longitude").toString());	//地点的经度
			weidu = Double.valueOf(list.get(i).get("latitude").toString());	//地点的纬度
			int juli1 = (int)computeDistance(myjingdu,myweidu,jingdu,weidu);
			list.get(i).put("juli", juli1);
		}

		 log.send(DataType.basicType, "01152", "需求车位信息-list",list);
		 inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));*/
	}



	//给出结束时间
	//结束时间与当前系统时间进行计算
	public String time_cals(String send_time){
		String times = "";
		//当前时间
		Date day=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		df.format(day);
		String dateTime = df.format(day);

		Calendar calendar = Calendar.getInstance();
		//转换成秒
		try {
			//当前系统时间转换
			calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTime));		//系统时间
			//calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-06-03 16:05:00"));		//系统时间

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String a = calendar.getTimeInMillis()+"";//当前时间						时间1

		Calendar calendar2 = Calendar.getInstance();
		//转换成秒
		try {
			//当前系统时间转换
			calendar2.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(send_time));		//结束时间
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String b = calendar2.getTimeInMillis()+"";//当前时间						时间1
		//a系统时间、b结束时间
		long c = 	Long.parseLong(a);	
		long d = 	Long.parseLong(b);	

		if(d<c){
			times = "0";
		}else{
			//计算终止时间
			long e = d-c;
			e = e/1000; 
			long h=e/3600;			//时
			long m=(e%3600)/60;		//分
			long s=(e%3600)%60;		//秒
			String H = h+"";
			String M = m+"";
			String S = s+"";
			if(H.length()<=1){
				H= "0"+H;
			}
			if(M.length()<=1){
				M= "0"+M;
			}
			if(S.length()<=1){
				S= "0"+S;
			}
			times = H+":"+M+":"+S;


		}




		return times;
	}





	//发布时间、时间段
	public String time_cal(String time1,String send_time){

		String times = "";

		//当前时间
		Date day=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		df.format(day);
		String dateTime = df.format(day);

		Calendar calendar = Calendar.getInstance();
		//转换成秒
		try {
			//当前系统时间转换
			calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTime));		//系统时间
			//calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-06-03 16:05:00"));		//系统时间

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String a = calendar.getTimeInMillis()+"";//当前时间						时间1

		Calendar calendar1 = Calendar.getInstance();

		try {
			//发布时间
			calendar1.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time1));			//给出时间
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//时间段
		String b = calendar1.getTimeInMillis()+"";//发布时间						时间2
		/* try {
		    	//时间段
				calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(send_time));		//时间段
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String n = calendar.getTimeInMillis()+"";//发布时间						时间3		
		 */



		//系统时间	
		long c = 	Long.parseLong(a);	
		log.send(DataType.specialType, "01152", "01168-剩余系统时间", c);
		//发布时间
		long d = 	Long.parseLong(b); 	
		log.send(DataType.specialType, "01152", "01168-剩余发布时间", d);
		long fb = 	Long.parseLong(send_time)*60*1000;			//发布时间段
		long q = c-d;							//过去的时间=系统时间-发布时间
		log.send(DataType.specialType, "01152", "01168-剩余时间", "系统时间:"+c+"给出时间:"+d+"fb:"+fb+"q:"+q);
		if(q>fb){
			times = "0";
		}else{
			long t = fb-q;
			t = t/1000; 
			String aaaa = t+"";
			log.send(DataType.specialType, "01152", "剩余时间", aaaa);
			//long n1  = Long.parseLong(n)*60-t;
			long h=t/3600;
			long m=(t%3600)/60;
			long s=(t%3600)%60;
			/*String H = h+"";
				if(H.length()==1){

				}*/
			if(h==0){
				times = "00"+":"+m+":"+s+"";
			}else{
				times = h+":"+m+":"+s+"";
			}
		}



		return times;
	}





	//根据经纬度计算距离
	public  double computeDistance(double lat1, double lon1,
			double lat2, double lon2) {
		// Based on http://www.ngs.noaa.gov/PUBS_LIB/inverse.pdf
		// using the "Inverse Formula" (section 4)

		int MAXITERS = 20;
		// Convert lat/long to radians
		lat1 *= Math.PI / 180.0;
		lat2 *= Math.PI / 180.0;
		lon1 *= Math.PI / 180.0;
		lon2 *= Math.PI / 180.0;

		double a = 6378137.0; // WGS84 major axis
		double b = 6356752.3142; // WGS84 semi-major axis
		double f = (a - b) / a;
		double aSqMinusBSqOverBSq = (a * a - b * b) / (b * b);

		double L = lon2 - lon1;
		double A = 0.0;
		double U1 = Math.atan((1.0 - f) * Math.tan(lat1));
		double U2 = Math.atan((1.0 - f) * Math.tan(lat2));

		double cosU1 = Math.cos(U1);
		double cosU2 = Math.cos(U2);
		double sinU1 = Math.sin(U1);
		double sinU2 = Math.sin(U2);
		double cosU1cosU2 = cosU1 * cosU2;
		double sinU1sinU2 = sinU1 * sinU2;

		double sigma = 0.0;
		double deltaSigma = 0.0;
		double cosSqAlpha = 0.0;
		double cos2SM = 0.0;
		double cosSigma = 0.0;
		double sinSigma = 0.0;
		double cosLambda = 0.0;
		double sinLambda = 0.0;

		double lambda = L; // initial guess
		for (int iter = 0; iter < MAXITERS; iter++) {
			double lambdaOrig = lambda;
			cosLambda = Math.cos(lambda);
			sinLambda = Math.sin(lambda);
			double t1 = cosU2 * sinLambda;
			double t2 = cosU1 * sinU2 - sinU1 * cosU2 * cosLambda;
			double sinSqSigma = t1 * t1 + t2 * t2; // (14)
			sinSigma = Math.sqrt(sinSqSigma);
			cosSigma = sinU1sinU2 + cosU1cosU2 * cosLambda; // (15)
			sigma = Math.atan2(sinSigma, cosSigma); // (16)
			double sinAlpha = (sinSigma == 0) ? 0.0 :
				cosU1cosU2 * sinLambda / sinSigma; // (17)
			cosSqAlpha = 1.0 - sinAlpha * sinAlpha;
			cos2SM = (cosSqAlpha == 0) ? 0.0 :
				cosSigma - 2.0 * sinU1sinU2 / cosSqAlpha; // (18)

			double uSquared = cosSqAlpha * aSqMinusBSqOverBSq; // defn
			A = 1 + (uSquared / 16384.0) * // (3)
					(4096.0 + uSquared *
							(-768 + uSquared * (320.0 - 175.0 * uSquared)));
			double B = (uSquared / 1024.0) * // (4)
					(256.0 + uSquared *
							(-128.0 + uSquared * (74.0 - 47.0 * uSquared)));
			double C = (f / 16.0) *
					cosSqAlpha *
					(4.0 + f * (4.0 - 3.0 * cosSqAlpha)); // (10)
			double cos2SMSq = cos2SM * cos2SM;
			deltaSigma = B * sinSigma * // (6)
					(cos2SM + (B / 4.0) *
							(cosSigma * (-1.0 + 2.0 * cos2SMSq) -
									(B / 6.0) * cos2SM *
									(-3.0 + 4.0 * sinSigma * sinSigma) *
									(-3.0 + 4.0 * cos2SMSq)));

			lambda = L +
					(1.0 - C) * f * sinAlpha *
					(sigma + C * sinSigma *
							(cos2SM + C * cosSigma *
									(-1.0 + 2.0 * cos2SM * cos2SM))); // (11)

			double delta = (lambda - lambdaOrig) / lambda;
			if (Math.abs(delta) < 1.0e-12) {
				break;
			}
		}

		return  b * A * (sigma - deltaSigma);
	}




}
