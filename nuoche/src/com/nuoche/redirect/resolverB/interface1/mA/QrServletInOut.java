package com.nuoche.redirect.resolverB.interface1.mA;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




















import net.coobird.thumbnailator.Thumbnails;
import net.sf.json.JSONObject;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.nuoche.classroot.interface1.NuocheInOutFace;
import com.nuoche.classroot.interface4.JsonUtil;
import com.nuoche.classroot.interface4.JyGlobalConstant;
import com.nuoche.classroot.interface4.JyLogDetect;
import com.nuoche.classroot.interface4.SqlUtil;
import com.nuoche.classroot.interface4.JyLogDetect.DataType;
@WebServlet("/qrcode")
public class QrServletInOut extends HttpServlet {

	

	public QrServletInOut() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String[] arg = null;
		JyLogDetect log = new JyLogDetect(request);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		arg = JsonUtil.jsonReceive(request);
		
		log.send(DataType.specialType, "01115", "头信息:   ", arg);
		
		SqlUtil sqlUtil = new SqlUtil(JyGlobalConstant.getDbBaseName());
		String sql="select * from batchcard_info where  card_num='"+arg[0]+"'";
		ArrayList<Map<String, Object>> list = null;
		try {
			list = sqlUtil.get_list(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		if(list.size()>0){
		   if(list.get(0).get("card_status").toString().equals("0")){
			   
			    JSONObject jsonObj0 = new JSONObject();
				jsonObj0.put("result", "fail");
				
				sql="select * from batchcard_info where  user_openid='"+arg[1]+"'";
				ArrayList<Map<String, Object>> list2 = null;
				try {
					list2 = sqlUtil.get_list(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(list2.size()>0){
					jsonObj0.put("card_num", list2.get(0).get("card_num").toString());
					jsonObj0.put("phonenum", list2.get(0).get("car_phone").toString());
					jsonObj0.put("chepaihao", list2.get(0).get("car_number").toString());
					jsonObj0.put("wenhouyu", list2.get(0).get("car_text").toString());
				}else{
					jsonObj0.put("card_num", "0");
				}
				
				PrintWriter out = response.getWriter();
				out.write(jsonObj0.toString());
			   //response.sendRedirect("https://vipz.top/uiface/editqrcode/card_num="+arg[0]);
		   }else{
			   JSONObject jsonObj0 = new JSONObject();
			   if(list.get(0).get("user_openid").toString().equals(arg[1])){
				   jsonObj0.put("result", "self");
			   }else{
				   
				   sql="select * from batchcard_info   where  user_openid='"+arg[1]+"'";
				   ArrayList<Map<String, Object>> list3 = null;
				   try {
					   list3 = sqlUtil.get_list(sql);
				   } catch (SQLException e) {
						// TODO Auto-generated catch block
				  	   e.printStackTrace();
				   }
				   if(list3.size()>0){
					   jsonObj0.put("userphone", list3.get(0).get("car_phone").toString());
				   }else{
					   jsonObj0.put("userphone", "");
				   }
				   jsonObj0.put("result", "success");
			   }
			   jsonObj0.put("phonenum", list.get(0).get("car_phone").toString());
			   jsonObj0.put("chepaihao", list.get(0).get("car_number").toString());
			   jsonObj0.put("wenhouyu", list.get(0).get("car_text").toString());
			   PrintWriter out = response.getWriter();
			   out.write(jsonObj0.toString());
			   //response.sendRedirect("https://vipz.top/uiface/user1/phonenum="+list.get(0).get("car_phone").toString()+"&chepaihao='"+list.get(0).get("car_number").toString());
		   }	
		}
		
	}

	
}
