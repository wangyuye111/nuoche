package com.nuoche.redirect.resolverB.interface1.mA;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.KeyStore;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.impl.JsonReadContext;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.nuoche.classroot.interface1.NuocheInOutFace;
import com.nuoche.classroot.interface1.NuocheInOutManager;
import com.nuoche.classroot.interface2.NuocheSqlMFace;
import com.nuoche.classroot.interface4.JsonUtil;
import com.nuoche.classroot.interface4.JyLogDetect.DataType;
import com.nuoche.classroot.interface4.OkHttp;
import com.nuoche.classroot.interface4.ctrlclass.CtrlClass;
import com.nuoche.redirect.resolverB.interface2.mA.JySqlUser;
import com.nuoche.redirect.resolverB.interface4.pay.GetWxOrderno;
import com.nuoche.redirect.resolverB.interface4.pay.Ordercreate;
import com.nuoche.redirect.resolverB.interface4.pay.Ordercreate.MD5;
import com.nuoche.redirect.resolverB.interface4.pay.util.RequestHandler;
import com.nuoche.redirect.resolverB.interface4.pay.util.http.HttpClientConnectionManager;

public class JyInOutUser extends NuocheInOutManager implements NuocheInOutFace {
	protected ArrayList<Map<String, Object>> list;
	protected ArrayList<Map<String, Object>> list1;
	protected String json = "";
	int totle;
	int[] current;
	NuocheSqlMFace sqlmface = new JySqlUser();
	//JyHelpManager helpmanager=new JyHelpManager();
	HttpSession session = request.getSession();
	public int[] getCurrent() {
		return current;
	}

	public void setCurrent(int[] current) {
		this.current = current;
	}
	public JyInOutUser(String[] arg, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
	SQLException {
		super(arg, request, response);
	}

	@Override
	public void addface() throws SQLException, ServletException, IOException {
		String sql1="";
		int i=0;
		switch (arg[1]) {
		case "agentadd":
			sql1 = sqlmface.addSqlface(0, arg);
			log.send(DataType.noType, "01139", "agentadd-sql: ", sql1);
			i=sqlUtil.sql_exec(sql1);
			inOutUtil.return_ajax(""+i);

			break;	



		}
	}

	@Override
	public void deleteface() throws SQLException, ServletException, IOException {

		switch (arg[1]) {

		}
	}

	@Override
	public void modface() throws SQLException, ServletException, IOException, Exception {
		String sql1="";
		int i=0;
		switch (arg[1]) {
		case "batchcard_mod":

			sql1 = sqlmface.modSqlface(2, arg);
			log.send(DataType.noType, "01139", "agentadd-sql: ", sql1);
			arg[0]=sqlUtil.get_string(sql1);


			StringBuffer a4 = new StringBuffer(arg[3]);  
			arg[3] = a4.toString().toUpperCase(); 
			sql1 = sqlmface.modSqlface(0, arg);
			log.send(DataType.noType, "01139", "agentadd-sql: ", sql1);
			i=sqlUtil.sql_exec(sql1);

			sql1 = sqlmface.modSqlface(1, arg);
			log.send(DataType.noType, "01139", "agentadd-sql: ", sql1);
			i=sqlUtil.sql_exec(sql1);


			inOutUtil.return_ajax(""+i);

			break;

		case "batchcard_delete":

			sql1 = sqlmface.modSqlface(2, arg);
			log.send(DataType.noType, "01139", "agentadd-sql: ", sql1);
			arg[0]=sqlUtil.get_string(sql1);


			sql1 = sqlmface.modSqlface(0, arg);
			log.send(DataType.noType, "01139", "agentadd-sql: ", sql1);
			i=sqlUtil.sql_exec(sql1);

			if(!arg[0].equals("")){
				sql1 = sqlmface.modSqlface(1, arg);
				log.send(DataType.noType, "01139", "agentadd-sql: ", sql1);
				i=sqlUtil.sql_exec(sql1);
			}
			inOutUtil.return_ajax(""+i);

			break;

		case "qchewei_shifang":

			sql1 = sqlmface.modSqlface(0, arg);
			log.send(DataType.noType, "01139", "agentadd-sql: ", sql1);
			i=sqlUtil.sql_exec(sql1);
			inOutUtil.return_ajax(""+i);

			break;

		case "qchewei_tuikuan":
			sql1 = sqlmface.modSqlface(0, arg);
			log.send(DataType.noType, "01139", "agentadd-sql: ", sql1);
			ArrayList<Map<String,Object>> list = sqlUtil.get_list(sql1);	
			String path = CtrlClass.class.getClassLoader().getResource("/").getFile();
			File file1 = new File(path.substring(1, path.length()-1));
			File a = new File(new File(file1.getParent()).getParent());

			//File a=CtrlClass.file;
			String filePath=a + File.separator +"zs"+File.separator+"apiclient_cert.p12";
			//String filePath = "E:\\证书\\apiclient_cert.p12"; //退款需要提供证书数据，所以需要根据证书路径读取证书
			//需要退款的商户订单号，对应提交订单中的out_trade_no
			log.send(DataType.specialType, "01066", "活动检测", filePath);
			//----------  1.生成预支付订单需要的的package数据-----------
			String GZHID="wx97698cafd0f86482";
			String GZHSecret="44b0dbe4e3b8a6efd9543cf59dfeacee";
			String SHHID= "1505521711";
			String SHHKEY= "YEWe15X12TGTc7YmyIHj9pE8PUQrqUvk";
			String nonce_str = MD5.getMessageDigest(String.valueOf(new Random().nextInt(10000)).getBytes());
			SortedMap<String, String> packageParams = new TreeMap<String, String>();
			packageParams.put("appid", GZHID);
			packageParams.put("mch_id", SHHID);//微信支付分配的商户号
			packageParams.put("nonce_str", nonce_str);//随机字符串，不长于32位
			//packageParams.put("op_user_id", SHHID);//操作员帐号, 默认为商户号
			//out_refund_no只能含有数字、字母和字符_-|*@
			packageParams.put("out_refund_no", list.get(0).get("refuseorder_num").toString());//商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
			packageParams.put("refund_fee", Ordercreate.getMoney(Double.parseDouble(list.get(0).get("paymoney").toString())/2+""));
			packageParams.put("total_fee", Ordercreate.getMoney(list.get(0).get("paymoney").toString()));
			packageParams.put("out_trade_no", list.get(0).get("order_num").toString());

			log.send(DataType.specialType, "01066", "活动检测", packageParams);
			//------2.根据package生成签名sign------- 
			RequestHandler reqHandler = new RequestHandler(request, response);
			reqHandler.init(GZHID, GZHSecret, SHHKEY);
			String sign = reqHandler.createSign(packageParams);
			log.send(DataType.specialType, "01066", "活动检测", sign);

			//------3.拼装需要提交到微信的数据xml------- 
			String xml="<xml>"
					+"<appid><![CDATA["+GZHID+"]]></appid>"
					+"<mch_id><![CDATA["+SHHID+"]]></mch_id>"
					+"<nonce_str><![CDATA["+nonce_str+"]]></nonce_str>"
					+"<out_refund_no><![CDATA["+list.get(0).get("refuseorder_num").toString()+"]]></out_refund_no>"
					+"<out_trade_no><![CDATA["+list.get(0).get("order_num").toString()+"]]></out_trade_no>"
					+"<refund_fee><![CDATA["+Ordercreate.getMoney(Double.parseDouble(list.get(0).get("paymoney").toString())/2+"")+"]]></refund_fee>"
					+"<total_fee><![CDATA["+Ordercreate.getMoney(Double.parseDouble(list.get(0).get("paymoney").toString())+"")+"]]></total_fee>"
					+"<sign><![CDATA["+sign+"]]></sign>"
					+"</xml>";
			try {
				//--------4.读取证书文件,这一段是直接从微信支付平台提供的demo中copy的，所以一般不需要修改------
				KeyStore keyStore  = KeyStore.getInstance("PKCS12");
				log.send(DataType.specialType, "01066", "活动检测", filePath);
				FileInputStream instream = new FileInputStream(new File(filePath));
				try {
					keyStore.load(instream, SHHID.toCharArray());
				} finally {
					instream.close();
				}
				// Trust own CA and all self-signed certs
				SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, SHHID.toCharArray()).build();
				// Allow TLSv1 protocol only
				SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext,new String[] { "TLSv1" },null,
						SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
				CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
				//------5.发送数据到微信的红包接口------- 
				String url="https://api.mch.weixin.qq.com/secapi/pay/refund";
				HttpPost httpost= HttpClientConnectionManager.getPostMethod(url);
				httpost.setEntity(new StringEntity(xml, "UTF-8"));
				HttpResponse weixinResponse = httpClient.execute(httpost);
				String jsonStr = EntityUtils.toString(weixinResponse.getEntity(), "UTF-8");
				//logger.info(jsonStr);

				Map map = GetWxOrderno.doXMLParse(jsonStr);
				log.send(DataType.specialType, "01066", "活动检测", map);
				if("SUCCESS".equalsIgnoreCase((String) map.get("result_code"))){
					log.send(DataType.specialType, "01066", "活动检测", (String) map.get("result_code"));
					String sql="update release_table set paymoney=paymoney/2,refuseres_msg='"+(String) map.get("return_msg")+"'  where id='"+arg[2]+"'";
					log.send(DataType.specialType, "01066", "活动检测", sql);
					sqlUtil.sql_exec(sql);
					inOutUtil.return_ajax("订单已取消,返还50%费用！");
				}else{
					log.send(DataType.specialType, "01066", "活动检测", (String) map.get("result_code"));
					String sql="update release_table set refuseres_msg='"+(String) map.get("err_code")+"'  where id='"+arg[2]+"' and refuseres_msg=''";
					log.send(DataType.specialType, "01066", "活动检测",sql);
					sqlUtil.sql_exec(sql);
					inOutUtil.return_ajax(map.get("err_code_des").toString());
				}


			} catch (Exception e) {
				//logger.info("退款失败");
			}

			break;


		case "isbangding":
			String sql4 = sqlmface.searchSqlface(0, arg);
			log.send(DataType.noType, "01139", "search_article_info-sql: ", sql4);
			ArrayList<Map<String,Object>> list4 = sqlUtil.get_list(sql4);	
			if(list4.size()>0){
				JSONObject jsonObj0 = new JSONObject();
				jsonObj0.put("card_num", list4.get(0).get("card_num").toString());
				jsonObj0.put("phonenum", list4.get(0).get("car_phone").toString());
				jsonObj0.put("chepaihao", list4.get(0).get("car_number").toString());
				jsonObj0.put("wenhouyu", list4.get(0).get("car_text").toString());
				jsonObj0.put("head_photo", list4.get(0).get("head_photo").toString());
				jsonObj0.put("nickname", list4.get(0).get("nickname").toString());
				//				list4.get(0).put("card_num", list4.get(0).get("card_num").toString());
				//				list4.get(0).put("phonenum", list4.get(0).get("car_phone").toString());
				//				list4.get(0).put("chepaihao", list4.get(0).get("car_number").toString());
				//				list4.get(0).put("wenhouyu", list4.get(0).get("car_text").toString());
				inOutUtil.return_ajax(jsonObj0.toString());
			}else{
				inOutUtil.return_ajax("");
			}


			//			inOutUtil.return_ajax(JsonUtil.listToJson(list4));
			break;

		case "userinfo":
			String sql5 = sqlmface.searchSqlface(0, arg);
			log.send(DataType.noType, "01139", "search_article_info-sql: ", sql5);
			ArrayList<Map<String,Object>> list5 = sqlUtil.get_list(sql5);	
			if(list5.size()>0){
				JSONObject jsonObj0 = new JSONObject();
				jsonObj0.put("card_num", list5.get(0).get("card_num").toString());
				jsonObj0.put("phonenum", list5.get(0).get("car_phone").toString());
				jsonObj0.put("chepaihao", list5.get(0).get("car_number").toString());
				jsonObj0.put("wenhouyu", list5.get(0).get("car_text").toString());
				jsonObj0.put("head_photo", list5.get(0).get("head_photo").toString());
				jsonObj0.put("nickname", list5.get(0).get("nickname").toString());
				//    				list4.get(0).put("card_num", list4.get(0).get("card_num").toString());
				//    				list4.get(0).put("phonenum", list4.get(0).get("car_phone").toString());
				//    				list4.get(0).put("chepaihao", list4.get(0).get("car_number").toString());
				//    				list4.get(0).put("wenhouyu", list4.get(0).get("car_text").toString());
				inOutUtil.return_ajax(jsonObj0.toString());
			}else{
				sql5 = sqlmface.searchSqlface(1, arg);
				log.send(DataType.noType, "01139", "search_article_info-sql: ", sql5);
				ArrayList<Map<String,Object>> list6 = sqlUtil.get_list(sql5);	
				JSONObject jsonObj0 = new JSONObject();
				jsonObj0.put("card_num", "");
				jsonObj0.put("phonenum", "");
				jsonObj0.put("chepaihao","");
				jsonObj0.put("wenhouyu", "");
				jsonObj0.put("head_photo", list6.get(0).get("head_photo").toString());
				jsonObj0.put("nickname", list6.get(0).get("nickname").toString());
				inOutUtil.return_ajax(jsonObj0.toString());
			}


			//    			inOutUtil.return_ajax(JsonUtil.listToJson(list4));
			break;
		case "yiyuding":
			sql1 = sqlmface.modSqlface(0, arg);
			ArrayList<Map<String,Object>> list2 = sqlUtil.get_list(sql1);
			list2 = sqlUtil.get_list(sql1);
			inOutUtil.return_ajax(JsonUtil.listToJson(list2));
			break;
		case "qchewei_mod":
			sql1 = sqlmface.modSqlface(1, arg);
			log.send(DataType.noType, "01139", "agentadd-sql: ", sql1);
			System.out.println(sql1);
			int num=sqlUtil.get_countint(sql1);
			JSONObject jsonObj0 = new JSONObject();
			if(num<1){
				sql1 = sqlmface.modSqlface(2, arg);
				log.send(DataType.noType, "01139", "agentadd-sql: ", sql1);
				list2 = sqlUtil.get_list(sql1);	
				if(list2.size()>0){
					String click=list2.get(0).get("click").toString();
					if(click.equals("1")){
						jsonObj0.put("result", "此车位已被抢！");  
						inOutUtil.return_ajax(jsonObj0.toString());
					}else{
						sql1 = sqlmface.modSqlface(0, arg);
						log.send(DataType.noType, "01139", "qchewei_mod-sql: ", sql1);
						Date d=new Date();//获取时间
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//转换格式
						String time = sdf.format(d);
						i=sqlUtil.sql_exec(sql1);
						arg[0]=time;
						sql1 = sqlmface.modSqlface(4, arg);
						i=sqlUtil.sql_exec(sql1);
						sql1 = sqlmface.modSqlface(3, arg);
						list2 = sqlUtil.get_list(sql1);
						for(Map<String, Object> m : list2) {
							for (String k : m.keySet())  
							{  
								System.out.println(k + " : " + m.get(k));  
							} 
						}
						jsonObj0.put("result","预定成功，请尽快到达！");
						jsonObj0.put("nowphone", list2.get(0).get("nowphone"));
						jsonObj0.put("confirmthetime", time);
						jsonObj0.put("place", list2.get(0).get("place"));
						jsonObj0.put("head_photo", list2.get(0).get("head_photo"));
						inOutUtil.return_ajax(jsonObj0.toString());

					}
				}else{
					jsonObj0.put("result", "此车位已过期！");  
					inOutUtil.return_ajax(jsonObj0.toString());
				}
			} else{
				jsonObj0.put("result", "您还有未付款的车位!");  
				inOutUtil.return_ajax(jsonObj0.toString());
			}

			break;
		case "changqi":
			sql1 = sqlmface.modSqlface(0, arg);
			log.send(DataType.noType, "01139", "agentadd-sql: ", sql1);
			num=sqlUtil.get_countint(sql1);
			jsonObj0 = new JSONObject();
			if(num<=1) {
				String sql2 = sqlmface.modSqlface(2, arg);
				log.send(DataType.noType, "01139", "agentadd-sql: ", sql2);
				list2 = sqlUtil.get_list(sql2);
				inOutUtil.return_ajax(JsonUtil.listToJson(list2));

			} else{
				jsonObj0.put("result", "您还有未付款的车位!");  
				inOutUtil.return_ajax(jsonObj0.toString());
			}

			break;
		case "quxiao":
			jsonObj0 = new JSONObject();
			sql1 = sqlmface.modSqlface(1, arg);
			num = sqlUtil.get_countint(sql1);
			if(num>=1) {
				sql1 = sqlmface.modSqlface(0, arg);
				log.send(DataType.noType, "01139", "agentadd-sql: ", sql1);
				i=sqlUtil.sql_exec(sql1);
				jsonObj0.put("result", "取消成功！");
				inOutUtil.return_ajax(jsonObj0.toString());
			}
			jsonObj0.put("result", "没有订单！");
			inOutUtil.return_ajax(jsonObj0.toString());
			break;
		case "fukuan_wancheng":
			jsonObj0 = new JSONObject();
			sql1 = sqlmface.modSqlface(0, arg);
			System.out.println(sql1);
			log.send(DataType.noType, "01139", "agentadd-sql: ", sql1);
			i=sqlUtil.sql_exec(sql1);
			jsonObj0.put("result", "取消成功！");
			break;
		case "fukuan":
			sql1 = sqlmface.modSqlface(1, arg);
			log.send(DataType.noType, "01139", "agentadd-sql: ", sql1);
			num=sqlUtil.get_countint(sql1);
			jsonObj0 = new JSONObject();
			if(num==1){
				String sql2 = sqlmface.modSqlface(2, arg);
				log.send(DataType.noType, "01139", "agentadd-sql: ", sql2);
				list2 = sqlUtil.get_list(sql2);
				if(list2.size()>0){
					String click=list2.get(0).get("click").toString();
					if(click.equals("1")){
						jsonObj0.put("result", "此车位已被抢！");  
						inOutUtil.return_ajax(jsonObj0.toString());

					}else{
						Date currentTime = new Date();
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						ParsePosition pos = new ParsePosition(0);
						Date strtodate = formatter.parse(list2.get(0).get("end_timef").toString(), pos);
						String times = arg[4];
						Date time = formatter.parse(times);
						log.send(DataType.noType, "01139", "agentadd-sql: ", strtodate.getTime()+"---"+currentTime.getTime()+"---"+(strtodate.getTime() - currentTime.getTime()));
						long hm=time.getTime() - currentTime.getTime()-300000;
						//long interval = (hm/(1000*60*60));
						//log.send(DataType.noType, "01139", "agentadd-sql: ", interval);
						double hour = 0;  
						double min = (double)hm/(1000*60*60*24); 
						hour =(double) Math.ceil(min);  
						log.send(DataType.noType, "01139", "agentadd-sql: ", hour+"  "+min);
						double a1=hour;
						log.send(DataType.noType, "01139", "agentadd-sql: ", a1);
						double paymoney=a1*Double.parseDouble(list2.get(0).get("price").toString());
						System.out.println(paymoney);
						//BigDecimal bg = new BigDecimal(paymoney).setScale(2, RoundingMode.UP);
						//arg[0]=bg.doubleValue()+"";
						arg[0]=paymoney+"";
						log.send(DataType.noType, "01139", "agentadd-sql: ", arg);
						sql1 = sqlmface.modSqlface(0, arg);
						log.send(DataType.noType, "01139", "qchewei_mod-sql: ", sql1);
						i=sqlUtil.sql_exec(sql1);
						jsonObj0.put("result", "确认成功！");  
						jsonObj0.put("paymoney",arg[0]);
						jsonObj0.put("天", hour);
						inOutUtil.return_ajax(jsonObj0.toString());
					}
				}else{
					jsonObj0.put("result", "此车位已过期！");  
					//inOutUtil.return_ajax(jsonObj0.toString());
				}
			} else{
				jsonObj0.put("result", "您还有未付款的车位!");  
				inOutUtil.return_ajax(jsonObj0.toString());
			}
			break;
		case "linshi":
			jsonObj0 = new JSONObject();
			String sql2 = sqlmface.modSqlface(2, arg);
			log.send(DataType.noType, "01139", "agentadd-sql: ", sql2);
			ArrayList<Map<String,Object>> list3 = sqlUtil.get_list(sql2);
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ParsePosition pos1 = new ParsePosition(0);
			Date strtodate = formatter.parse(list3.get(0).get("starting_time").toString(), pos1);
			log.send(DataType.noType, "01139", "agentadd-sql: ", strtodate.getTime()+"---"+currentTime.getTime()+"---"+(strtodate.getTime() - currentTime.getTime()));
			long hm=currentTime.getTime() - strtodate.getTime();
			if(hm>300000) {
				double hour =hm/1000/60;
				double hour1 = hour/15;
				long day = hm/1000/60/60;
				double m = Math.ceil(hour1);
				double paymoney=m*Double.parseDouble(list3.get(0).get("price").toString());
				arg[0]=paymoney+"";
				log.send(DataType.noType, "01139", "agentadd-sql: ", arg);
				sql1 = sqlmface.modSqlface(0, arg);
				log.send(DataType.noType, "01139", "qchewei_mod-sql: ", sql1);
				//i=sqlUtil.sql_exec(sql1);
				jsonObj0.put("result", "请付款");  
				jsonObj0.put("paymoney",arg[0]);
				jsonObj0.put("hour", day);
				jsonObj0.put("m", hour);
				inOutUtil.return_ajax(jsonObj0.toString());
			}
			break;
		}
	}


	@Override
	public void searchface() throws SQLException, ServletException, IOException {

		switch (arg[1]) {
		// 根据分类查询文章列表
		case "search_article_list":
			if(arg[2].equals("0")){
				arg[2]="首页";
			}else{
				arg[2]="其他";
			}
			String sql = sqlmface.searchSqlface(0, arg);
			log.send(DataType.noType, "01139", "search_article_list-sql: ", sql);
			ArrayList<Map<String,Object>> list = sqlUtil.get_list(sql);	
			inOutUtil.return_ajax(JsonUtil.listToJson(list));
			break;

		case "search_article_info":
			String sql1 = sqlmface.searchSqlface(0, arg);
			log.send(DataType.noType, "01139", "search_article_info-sql: ", sql1);
			ArrayList<Map<String,Object>> list1 = sqlUtil.get_list(sql1);	
			inOutUtil.return_list(list1,"user/article_info.jsp");
			break;	
		case "ad_show":
			String sql2 = sqlmface.searchSqlface(0, arg);
			log.send(DataType.noType, "01139", "search_article_info-sql: ", sql2);
			String isshow = sqlUtil.get_string(sql2);	
			inOutUtil.return_ajax(isshow);
			break;

		case "chewei_pay":
			String sql3 = sqlmface.searchSqlface(0, arg);
			log.send(DataType.noType, "01139", "search_article_info-sql: ", sql3);
			ArrayList<Map<String,Object>> list3 = sqlUtil.get_list(sql3);	
			String a=Ordercreate.order_creat(list3.get(0).get("openid").toString(),1,list3.get(0).get("paymoney").toString(), list3.get(0).get("order_num").toString(), request, response);
			inOutUtil.return_ajax(a);
			break;

		case "withdraw_up":
			String sqlwp = sqlmface.searchSqlface(0, arg);
			log.send(DataType.noType, "01139", "search_article_info-sql: ", sqlwp);
			ArrayList<Map<String,Object>> listwp = sqlUtil.get_list(sqlwp);	
			if(listwp.size()>0){
				if(listwp.get(0).get("num").toString().equals("0")){
					inOutUtil.return_ajax("没有可提现的订单！");
				}else{
					inOutUtil.return_ajax("可提现"+listwp.get(0).get("num").toString()+"笔订单，合计"+listwp.get(0).get("totlemoney").toString()+"元！");
				}
			}else{
				inOutUtil.return_ajax("没有可提现的订单！");
			}
			break;


		case "withdraw_apply":
			String sqlapply = sqlmface.searchSqlface(0, arg);
			log.send(DataType.noType, "01139", "search_article_info-sql: ", sqlapply);
			ArrayList<Map<String,Object>> listapple = sqlUtil.get_list(sqlapply);	
			if(listapple.size()>0){
				if(listapple.get(0).get("num").toString().equals("0")){
					inOutUtil.return_ajax("没有可提现的订单！");
				}else{

					arg[0]=listapple.get(0).get("num").toString();
					arg[3]=listapple.get(0).get("totlemoney").toString();
					sqlapply = sqlmface.searchSqlface(2, arg);
					int apl=sqlUtil.sql_exec(sqlapply);

					sqlapply = sqlmface.searchSqlface(3, arg);
					arg[0]=sqlUtil.get_string(sqlapply);

					sqlapply = sqlmface.searchSqlface(1, arg);
					apl=sqlUtil.sql_exec(sqlapply);

					inOutUtil.return_ajax("申请成功，等待审核！");
				}
			}else{
				inOutUtil.return_ajax("没有可提现的订单！");
			}
			break;


		case "getsession_key":

			String json = new OkHttp().requestPostBySyn("https://api.weixin.qq.com/sns/jscode2session?appid=wx97698cafd0f86482&secret=5b51e65d0f0c5c8619445972a834ebec&js_code="+arg[2]+"&grant_type=authorization_code");
			inOutUtil.return_ajax(json);
			//			String sql3 = sqlmface.searchSqlface(0, arg);
			//			log.send(DataType.noType, "01139", "search_article_info-sql: ", sql3);
			//			ArrayList<Map<String,Object>> list3 = sqlUtil.get_list(sql3);	
			//			
			//			String a=Ordercreate.order_creat(list3.get(0).get("openid").toString(),1,list3.get(0).get("price").toString(), list3.get(0).get("order_num").toString(), request, response);
			//			inOutUtil.return_ajax(a);
			break;


		}
	}


}
