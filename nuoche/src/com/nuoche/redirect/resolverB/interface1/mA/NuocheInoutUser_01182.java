package com.nuoche.redirect.resolverB.interface1.mA;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
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

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
/*import org.codehaus.xfire.util.Base64;*/


import com.google.gson.Gson;
import com.nuoche.classroot.interface1.NuocheInOutFace;
import com.nuoche.classroot.interface1.NuocheInOutManager;
import com.nuoche.classroot.interface2.NuocheSqlMFace;
import com.nuoche.classroot.interface4.JsonUtil;
import com.nuoche.classroot.interface4.JyLogDetect.DataType;
import com.nuoche.redirect.resolverB.interface2.mA.NuocheSqlUser_01182;
import com.nuoche.redirect.resolverB.interface4.AES;
import com.nuoche.redirect.resolverB.interface4.GetOpenid;




public class NuocheInoutUser_01182  extends NuocheInOutManager implements
NuocheInOutFace {
	protected ArrayList<Map<String, Object>> list;
	protected ArrayList<Map<String, Object>> list1;
	protected ArrayList<Map<String, Object>> list2;
	protected String json = "";
	NuocheSqlMFace sqlmface = new NuocheSqlUser_01182();

	public NuocheInoutUser_01182(String[] arg, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			SQLException {
		super(arg, request, response);
	}

	@Override
	public void addface() throws SQLException, ServletException, IOException {
		switch (arg[1]) {
		case"fabudongtai":
			fabudongtai(arg);
			break;
			//动态评论
		case"dongtaipinglun":
			dongtaipinglun(arg);
			break;
		}
	
	}

	private void dongtaipinglun(String[] arg) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		String sql=sqlmface.addSqlface(0, arg);
		log.send(DataType.specialType, "01182", "添加评论是否成功—sql", sql);
		sqlUtil.sql_exec(sql);
		String sql2=sqlmface.addSqlface(1, arg);
		sqlUtil.sql_exec(sql2);
		String jsonadd="{\"success\":\"1\"}";
		inOutUtil.return_ajax(jsonadd);
	}

	@Override
	public void modface() throws SQLException, ServletException, IOException {
		switch (arg[1]) {
		//（动态）点赞(点击点赞事件)
		case"dianjidianzan":
			dianjidianzan(arg);
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
		/*case "pinglun_search":
			pinglun_search(arg);
			break;*/
		case "decode":
			decode(arg);
			break;
		
		}	
	}
	
	//点击点赞事件
	private void dianjidianzan(String[] arg) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		String sql=sqlmface.modSqlface(0, arg);
		log.send(DataType.basicType, "01182", "点赞后内容的点赞数量-sql", sql);
		int res=sqlUtil.get_int(sql);
		if(res>0){
			//res>0，删除点赞表数据
			String sql2=sqlmface.modSqlface(1, arg);
		} 
		else{
			//添加点赞数据
			String sql2=sqlmface.modSqlface(2, arg);
			
		}
		//点赞量添加到动态表中
		String sql2=sqlmface.modSqlface(3, arg);
		//int res2=sqlUtil.sql_exec(sql2);
		//查询点赞表中当前动态点赞数量
		String sql3=sqlmface.modSqlface(4, arg);
		String res3=sqlUtil.get_string(sql3);
		String jsonadd = "{\"success\":\"1\"}";
		inOutUtil.return_ajax(jsonadd);
	}

	//发布动态
	private void fabudongtai(String[] arg) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		String sql=sqlmface.addSqlface(1, arg);
		log.send(DataType.basicType, "01182", "提交发布动态-sql:", sql);
		sqlUtil.sql_exec(sql);
		String jsonadd = "{\"success\":\"1\"}";
		inOutUtil.return_ajax(jsonadd);
		
	}

	private void user_search(String[] arg) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

	/*private void decode(String[] arg) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		log.send(DataType.basicType, "01182", "提交发布动态-sql:", arg[2]+"和"+arg[3]+"和"+arg[4]);
		String encryptedData = arg[2];
		String iv = arg[3];
		String session_key = arg[4];
		String encryptedData = arg[2];
		String iv = arg[3];
		String sessionkey = arg[4];
		 JSONObject obj = new GetOpenid().getUserInfo(encryptedData, sessionkey,
	                iv);
	        Gson gson = new Gson();
	        String json = gson.toJson(obj);
	        log.send(DataType.basicType, "01182", "解密数据", json);
	        
		Map map = new HashMap();    
	       try {    
	               byte[] resultByte  = AES.decrypt(Base64.decode(encryptedData),    
	                       Base64.decode(session_key),  
	                       Base64.decode(iv));    
	                   if(null != resultByte && resultByte.length > 0){    
	                       String userInfo = new String(resultByte, "UTF-8");                   
	                       map.put("status", "   1");    
	                       map.put("msg", "解密成功");                   
	                       map.put("userInfo", userInfo);    
	                   }else{    
	                       map.put("status", "0");    
	                       map.put("msg", "解密失败");    
	                   }    
	           }catch (InvalidAlgorithmParameterException e) {    
	                   e.printStackTrace();    
	           } catch (UnsupportedEncodingException e) {    
	                   e.printStackTrace();    
	           }                  
	           Gson gson = new Gson();    
	           String decodeJSON = gson.toJson(map);    
	           //System.out.println(decodeJSON);   
	           log.send(DataType.basicType, "01182", "解密数据", decodeJSON);
	}*/
	private void decode(String[] arg) throws SQLException, IOException, ServletException {
		//log.send(DataType.basicType, "01168", "提交发布动态-sql:", arg[2]+"和"+arg[3]+"和"+arg[4]);
		/*String encryptedData = arg[2];
		String iv = arg[3];
		String session_key = arg[4];*/
		
		
		
		/*String encryptedData = "6WCBJvUzgsOulZepiEkVdxGOds4UasjOzblLZwW68FE6JeygiXvOXbrLjGSXRoRaz83HEUoQdyeRTYxwv2fxH/6mM1BvgXqE9xknWFLY5 9bXqi3qhAdgxohzpDP4Ut4oEw88yLgG03IIlsCyL2AMXAdZC6yaQ8vnGeYkkPT i2UbPVJQVM5W1zRM7RDZ5lihTfJoMe46eutFjivp4TA0w==";
		String iv = "qieo0zw4aNq/05rE0Ep16A==";
		String session_key = "5/QoBOLtWXlMtQZ3PNPdNg==";*/
		String encryptedData = "CiyLU1Aw2KjvrjMdj8YKliAjtP4gsMZMQmRzooG2xrDcvSnxIMXFufNstNGTyaGS9uT5geRa0W4oTOb1WT7fJlAC+oNPdbB+3hVbJSRgv+4lGOETKUQz6OYStslQ142dNCuabNPGBzlooOmB231qMM85d2/fV6ChevvXvQP8Hkue1poOFtnEtpyxVLW1zAo6/1Xx1COxFvrc2d7UL/lmHInNlxuacJXwu0fjpXfz/YqYzBIBzD6WUfTIF9GRHpOn/Hz7saL8xz+W//FRAUid1OksQaQx4CMs8LOddcQhULW4ucetDf96JcR3g0gfRK4PC7E/r7Z6xNrXd2UIeorGj5Ef7b1pJAYB6Y5anaHqZ9J6nKEBvB4DnNLIVWSgARns/8wR2SiRS7MNACwTyrGvt9ts8p12PKFdlqYTopNHR1Vf7XjfhQlVsAJdNiKdYmYVoKlaRv85IfVunYzO0IKXsyl7JCUjCpoG20f0a04COwfneQAGGwd5oa+T8yO5hzuyDb/XcxxmK01EpqOyuxINew==";
		String iv = "r7BXXKkLb8qrSNn05n0qiA==";
		String session_key = "tiihtNczf5v6AKRyjwEUhQ==";
		
		
		
		
		
		
		//错误的
		/*String iv = "5/QoBOLtWXlMtQZ3PNPdNg==";
		String session_key = "qieo0zw4aNq/05rE0Ep16A==";*/
		Map map = new HashMap();    
	       try {    
	    	   log.send(DataType.basicType, "01168", "解密-sql:", "decode1");
	               byte[] resultByte  = AES.decrypt(Base64.decodeBase64(encryptedData),
	                       Base64.decodeBase64(session_key),
	                       Base64.decodeBase64(iv));
	               log.send(DataType.basicType, "01168", "提交发布动态-sql:", "解密1");
	                   if(null != resultByte && resultByte.length > 0){    
	                	   log.send(DataType.basicType, "01182", "提交发布动态-sql:", "解密2");
	                       String userInfo = new String(resultByte, "UTF-8");                   
	                       map.put("status", "1");    
	                       map.put("msg", "解密成功");                   
	                       map.put("userInfo", userInfo);    
	                   }else{    
	                	   log.send(DataType.basicType, "01182", "提交发布动态-sql:", "解密3");
	                       map.put("status", "0");    
	                       map.put("msg", "解密失败");    
	                   }    
	           }catch (InvalidAlgorithmParameterException e) {   
	        	   log.send(DataType.basicType, "01168", "解密-sql:", "错误1");
	                   e.printStackTrace();    
	           } catch (UnsupportedEncodingException e) {    
	        	   log.send(DataType.basicType, "01168", "解密-sql:", "错误2");
	                   e.printStackTrace();    
	           }                  
	       	   log.send(DataType.basicType, "01168", "提交发布动态-sql:", "解密4");
	           Gson gson = new Gson();    
	           String decodeJSON = gson.toJson(map);    
	           
	           log.send(DataType.basicType, "01168", "解密最终数据:", decodeJSON);
	           inOutUtil.return_ajax(decodeJSON);
		
		
		
		
		
	}

}
