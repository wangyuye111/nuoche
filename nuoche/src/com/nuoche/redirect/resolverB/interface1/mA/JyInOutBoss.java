package com.nuoche.redirect.resolverB.interface1.mA;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;



















import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

import com.nuoche.classroot.interface1.NuocheInOutFace;
import com.nuoche.classroot.interface1.NuocheInOutManager;
import com.nuoche.classroot.interface2.NuocheSqlMFace;
import com.nuoche.classroot.interface4.HelpManager;
import com.nuoche.classroot.interface4.JsonUtil;
import com.nuoche.classroot.interface4.JyLogDetect;
import com.nuoche.classroot.interface4.SqlUtil;
import com.nuoche.classroot.interface4.JyLogDetect.DataType;
import com.nuoche.classroot.interface4.ctrlclass.CtrlClass;
import com.nuoche.redirect.resolverB.interface2.mA.JySqlBoss;
import com.nuoche.redirect.resolverB.interface4.ExcelUtil;
import com.nuoche.redirect.resolverB.interface4.QrImage;
import com.nuoche.redirect.resolverB.interface4.pay.GetWxOrderno;
import com.nuoche.redirect.resolverB.interface4.pay.Ordercreate;
import com.nuoche.redirect.resolverB.interface4.pay.Ordercreate.MD5;
import com.nuoche.redirect.resolverB.interface4.pay.util.RequestHandler;
import com.nuoche.redirect.resolverB.interface4.pay.util.http.HttpClientConnectionManager;


public class JyInOutBoss extends NuocheInOutManager implements
NuocheInOutFace {
	protected ArrayList<Map<String, Object>> list;
	protected ArrayList<Map<String, Object>> list1;
	protected String json = "";
	NuocheSqlMFace sqlmface = new JySqlBoss();
	
	int[] current;
	int totle;
	public int[] getCurrent() {
		return current;
	}

	public void setCurrent(int[] current) {
		this.current = current;
	}
	public JyInOutBoss(String[] arg, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException, SQLException {
		super(arg, request, response);
	}
	
	
	@Override
	public void addface() throws SQLException, ServletException, IOException {

		switch (arg[1]) {
		case "ArticleAdd":
			ArticleAdd();
		break;
		
		case "batchcard_add":
			batchcard_add();
		break;
		
		
		}
	}


/*		文章增加                    
	Arg[0]: A-boss-add
	Arg[1]: ArticleAdd
	Arg[2]: name
	Arg[3]: type
	Arg[4]: status
	Arg[5]: reading
	Arg[6]: price
	Arg[7]: img
	
	Arg[8]: arttype
	Arg[9]: descs
	Arg[10]: video
	*/


	private void ArticleAdd()throws SQLException, IOException, ServletException  {
		String sql="";
		arg[4] = arg[4].replaceAll("'", "\\\\'");
		sql = sqlmface.addSqlface(0, arg);
		log.send(DataType.noType, "01139", "ArticleAdd-sql: ", sql);
		int a = sqlUtil.sql_exec(sql);
		if(a == 0){
			json = "{\"result\":\"添加失败\"}";
			inOutUtil.return_ajax(json);
		}else{
			json = "{\"result\":\"添加成功\"}";
			inOutUtil.return_ajax(json);
		}
	}

	/*		
	Arg[0]: A-boss-add
	Arg[1]: batchcard_add
	Arg[2]: 批号
	Arg[3]: 开始
	Arg[4]: 个数
	Arg[5]: 结束
	Arg[6]: agentid
	*/


	private void batchcard_add()throws SQLException, IOException, ServletException  {
		arg[2]=hm.order_create();
		String sql="";
		sql = sqlmface.addSqlface(0, arg);
		log.send(DataType.noType, "01139", "ArticleAdd-sql: ", sql);
		int a = sqlUtil.sql_exec(sql);
		
		
		String path = this.getClass().getResource("/").getFile();
		log.send(DataType.noType, "01139", "ArticleAdd-sql: ", path);
		File file1 = new File(path.substring(1, path.length()-1));
		File file2 = new File(new File(file1.getParent()).getParent());
		log.send(DataType.noType, "01139", "ArticleAdd-sql: ", file2.getAbsolutePath());
		String imgpath=file2.getAbsolutePath();
		int start=Integer.parseInt(arg[3]);
		int sum=Integer.parseInt(arg[4]);
		
		for(int i=0;i<sum;i++){
			
			if(i%500==0){
				sqlUtil = new SqlUtil(dbName);
			}
			sql = "insert into batchcard_info (batchcard_num,card_num) values ('"+ arg[2]
					+ "','"
					+ start
					+ "')";
			int b = sqlUtil.sql_exec(sql);
			
			getqrcode(imgpath,start);
			
			start++;
		}
		
		if(a == 0){
			json = "{\"result\":\"添加失败\"}";
			inOutUtil.return_ajax(json);
		}else{
			json = "{\"result\":\"添加成功\"}";
			inOutUtil.return_ajax(json);
		}
		
	}

	public void getqrcode(final String imgpaht,final int code){
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				log=new JyLogDetect();
				try {
					// 生成二维码
					//http://12nm.cn/uiface/user/invitation.jsp?userid=${json}
					//String qrcodeUrl = "https://vipz.top/qrcode?id="+code;
					String qrcodeUrl = "https://vipz.top/uiface/editqrcode/card_num="+code;
		            //
					//String qrcodeUrl = request.getScheme()+"://"+request.getServerName()+"/uiface/login?a=A-user-search&b=shareqrcode&c="+arg[2];
					log.send(DataType.basicType, "01156", "qrcodeUrl: ", qrcodeUrl);
					
					BufferedImage bi = QrImage.createCodeStream(qrcodeUrl);	
					String fullPath = "img/qrcodeimg";
//					String tmppath = request.'().getServletContext()
//		            .getRealPath("/");
					String filedir = imgpaht  + File.separator + fullPath;
					File fullDir = new File(imgpaht  + File.separator + fullPath);
					if (!fullDir.exists()) {
						fullDir.mkdirs();
					}
					String fileName = code+".jpg";
					String filepath = filedir + File.separator + fileName;
					log.send(DataType.basicType, "01150", "filepath========", filepath);
					File file = new File(filepath);
					ImageIO.write(bi, "JPEG", file);
					String b="https://vipz.top/img/qrcodeimg/"+code+".jpg";
					String json="{\"success\":"+b+"}";
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.send(DataType.exceptionType, "01156", "getqrcode: ", "exception: "+e);
				}
			}
		}).start();
		
	}
	

	@Override
	public void deleteface() throws SQLException, ServletException, IOException {

		switch (arg[1]) {
		
		case "del_article":
			del_article(arg);
			break;
		case "del_article_one":
			del_article_one(arg);
			break;
				
		}
	}
	
	// 删除文章一个arg[2]:page arg[3]:id
	private void del_article_one(String[] arg) throws SQLException, ServletException, IOException{
		String[] argNew=new String[3];
		argNew[0]=arg[0];
		argNew[1]="del_article";
		argNew[2]=arg[3];
		String sql = sqlmface.deleteSqlface(argNew);
		log.send(DataType.noType, "01139", "删除文章一个", sql);
		int i=sqlUtil.sql_exec(sql);
		
		//uiface/JyServletInOut?mode=A-boss-search&amp;mode2=ArticleSearch&amp;empty=&amp;page=1
		response.sendRedirect("http://47.106.140.170:8090/uiface/JyServletInOut?mode=A-boss-search&amp;mode2=ArticleSearch&amp;empty=&amp;page=1" );
		//inOutUtil.return_only("JyServletInOut?mode=A-boss-search&mode2=ArticleSearch&empty=&page="+arg[2]+"");
	}	
	// 删除文章  arg[2]:page arg[3]:id
	private void del_article(String[] arg) throws SQLException, ServletException, IOException{
		String[] arg1 = request.getParameterValues("selectFlag");
		String[] argNew=new String[arg1.length+2];
		argNew[0]=arg[0];
		argNew[1]=arg[1];
		System.arraycopy(arg1, 0, argNew, 2, arg1.length);
		String sql = sqlmface.deleteSqlface(argNew);
		int i=sqlUtil.sql_exec(sql);
		log.send(DataType.noType, "01139", "删除文章", sql);
		inOutUtil.return_only("JyServletInOut?mode=A-boss-search&mode2=ArticleSearch&empty=&page="+arg[2]+"");		
	}
	
	@Override
	public void modface() throws SQLException, ServletException, IOException {
		
		switch (arg[1]) {
		case "ArticleMove":
			ArticleMove(arg);
			break;
		
			
		}	
	}
	
	
	/*文章修改                        
	Arg[0]: A-boss-mod
	Arg[1]: ArticleMove
	Arg[2]: name
	Arg[3]: type
	Arg[4]: status
	Arg[5]: reading
	Arg[6]: price
	Arg[7]: img
	
	Arg[8]: arttype
	Arg[9]: descs
	Arg[10]: video
	Arg[11]: id
*/
	

	private void ArticleMove(String[] arg) throws SQLException, IOException, ServletException  {
		String sql="";
		arg[4] = arg[4].replaceAll("'", "\\\\'");
		sql = sqlmface.modSqlface(0, arg);
		log.send(DataType.noType, "01139", "AdminLogin-sql: ", sql);
		int a = sqlUtil.sql_exec(sql);
		log.send(DataType.noType, "01139", "AdminLogin-sql: ", a);
		if(a == 0){
			json = "{\"1\":\"修改失败\"}";
			inOutUtil.return_ajax(json);
		}else{
			//inOutUtil.return_only("JyServletInOut?a=A-boss-search&b=ArticleSearch&empty=&page=1");
		}
		
	}


	@Override
	public void searchface() throws SQLException, ServletException, IOException {

		switch (arg[1]) {
		
		//查询文章	
		case "ArticleSearch":
			ArticleSearch(arg);
			break;
		
			//修改文章查询
		case "ArticleIdSearch":
			ArticleIdSearch(arg);
			break;
		
			// 文章添加页面
		case "article_add":
			article_add(arg);
			break;
			//挪车卡添加页面
		case "batch_addbf":
			batch_addbf(arg);
			break;	
		case "batchcard_down":
			batchcard_down(arg);
			break;		
			
			
			// 代理商列表
		case "agent_list":
			agent_list(arg);
			break;
		case "batchcardinfo":
			batchcardinfo(arg);
			break;
			
			
		case "batchcard_list":
			batchcard_list(arg);
			break;
			
		case "withdraw_list":
			withdraw_list(arg);
			break;
			
		case "withdraw_info":
			withdraw_info(arg);
			break;
		case "withdraw_down":
			withdraw_down(arg);
			break;	
		}
	} 
	
private void withdraw_down(String[] arg) throws SQLException, IOException, ServletException {
		
		
		String sql="select * from withdraw_list  as a ,user_data as b  where a.user_id=b.id and a.id="+arg[2];
		log.send(DataType.specialType, "01066", "活动检测", sql);
		ArrayList<Map<String, Object>> list = null;
		try {
			list = sqlUtil.get_list(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			log.send(DataType.specialType, "01066", "活动检测", e1);
		}
		log.send(DataType.specialType, "01066", "活动检测", list);
		String mch_appid = null,mchid = null,nonce_str = null,partner_trade_no = null,openid = null,amount = null,key = null;
		if(list.size()==0){
			arg=new String[5];
			inOutUtil.return_ajax("FAIL");
		}else{
			
		
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
	    	
	    	mch_appid=GZHID;
			mchid=SHHID;
			nonce_str = MD5.getMessageDigest(String.valueOf(new Random().nextInt(10000)).getBytes());
			log.send(DataType.specialType, "01066", "活动检测", nonce_str);
			partner_trade_no=new HelpManager().order_create();
			openid=list.get(0).get("openid").toString();
			amount=Ordercreate.getMoney(list.get(0).get("withdraw_money").toString());
			key=SHHKEY;
			
			//----------  1.生成预支付订单需要的的package数据-----------
			SortedMap<String, String> packageParams = new TreeMap<String, String>();
			packageParams.put("mch_appid", mch_appid);  
			packageParams.put("mchid", mchid);  
			packageParams.put("nonce_str", nonce_str);   
			packageParams.put("partner_trade_no", partner_trade_no);  
			packageParams.put("openid", openid);  
			packageParams.put("check_name", "NO_CHECK"); 
			packageParams.put("desc","车位订单提现");  
			packageParams.put("amount",amount);  
			packageParams.put("spbill_create_ip","47.106.140.170");  
			
			log.send(DataType.specialType, "01066", "活动检测", packageParams);
			//------2.根据package生成签名sign------- 
			RequestHandler reqHandler = new RequestHandler(request, response);
			reqHandler.init(mch_appid, list.get(0).get("appsecret").toString(), key);
			String sign = reqHandler.createSign(packageParams);
			log.send(DataType.specialType, "01066", "活动检测", sign);
			//------3.拼装需要提交到微信的数据xml------- 
			String xml="<xml>"
					+"<amount><![CDATA["+amount+"]]></amount>"
					+"<check_name><![CDATA[NO_CHECK]]></check_name>"
					+"<desc><![CDATA[车位订单提现]]></desc>"
					+"<mchid><![CDATA["+mchid+"]]></mchid>"
					+"<mch_appid><![CDATA["+mch_appid+"]]></mch_appid>"
					+"<nonce_str><![CDATA["+nonce_str+"]]></nonce_str>"
					+"<openid><![CDATA["+openid+"]]></openid>"
					+"<partner_trade_no><![CDATA["+partner_trade_no+"]]></partner_trade_no>"
					+"<spbill_create_ip><![CDATA[47.106.140.170]]></spbill_create_ip>"
					+"<sign><![CDATA["+sign+"]]></sign>"
					+"</xml>";
			try {
				 //--------4.读取证书文件,这一段是直接从微信支付平台提供的demo中copy的，所以一般不需要修改------
				 KeyStore keyStore  = KeyStore.getInstance("PKCS12");
				 log.send(DataType.specialType, "01066", "活动检测", filePath);
				 FileInputStream instream = new FileInputStream(new File(filePath));
				 try {
					 keyStore.load(instream, mchid.toCharArray());
				 } finally {
					 instream.close();
				 }
				 // Trust own CA and all self-signed certs
				 SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, mchid.toCharArray()).build();
				 // Allow TLSv1 protocol only
				 SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext,new String[] { "TLSv1" },null,
						 SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
				 CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
				 //------5.发送数据到微信的红包接口------- 
				 String url="https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
				 HttpPost httpost= HttpClientConnectionManager.getPostMethod(url);
				 httpost.setEntity(new StringEntity(xml, "UTF-8"));
				 HttpResponse weixinResponse = httpClient.execute(httpost);
				 String jsonStr = EntityUtils.toString(weixinResponse.getEntity(), "UTF-8");
				 //logger.info(jsonStr);
				 
				 Map map = GetWxOrderno.doXMLParse(jsonStr);
				 log.send(DataType.specialType, "01066", "活动检测", map);
				 if("SUCCESS".equalsIgnoreCase((String) map.get("result_code"))){
					 log.send(DataType.specialType, "01066", "活动检测", (String) map.get("result_code"));
					// sql="update rp_infolist set mch_billno='"+(String) map.get("partner_trade_no")+"',send_listid='"+(String) map.get("payment_no")+"',return_msg='"+(String) map.get("return_msg")+"',user_id='"+user_id+"' where code_id='"+code_id+"'";
					// sqlUtil.sql_exec(sql);
					sql = "update withdraw_list set withdraw_status='提现成功',withdraw_res_msg='"+(String) map.get("return_msg")+"',withdraw_num='"+(String) map.get("partner_trade_no")+"' where id="+arg[2];
					log.send(DataType.basicType, "01107", "modcommission()-sql: ", sql);
					sqlUtil.sql_exec(sql);
					
					sql = "update release_table set withdraw_status='提现成功' where  withdraw_id="+arg[2];
					log.send(DataType.basicType, "01107", "modcommission()-sql: ", sql);
					sqlUtil.sql_exec(sql);
					inOutUtil.return_ajax("提现成功");
				 }else{
					 log.send(DataType.specialType, "01066", "活动检测", (String) map.get("result_code"));
					 //sql="update rp_infolist set mch_billno='"+partner_trade_no+"',send_listid='',return_msg='"+map.toString()+"' where code_id='"+code_id+"'";
					 //sqlUtil.sql_exec(sql);
					 //String[] arg=new String[5];
					 //InOutUtil inOutUtil = new InOutUtil(arg, request, response);
					 /*String a1="";
					 if(map.get("err_code").toString().equals("V2_ACCOUNT_SIMPLE_BAN")){
						 a1="未实名需再扫！";
					 }
					 if(map.get("err_code").toString().equals("NOTENOUGH")){
						 a1="商户余额不足！";
					 }*/
					 log.send(DataType.specialType, "01066", "失败", map.get("err_code").toString());
					 inOutUtil.return_ajax(map.get("err_code").toString());
				 }
				 
			
			} catch (Exception e) {
				//logger.info("退款失败");
			}
		}
   }
	
	
	
	private void withdraw_list(String[] arg) throws SQLException, IOException, ServletException {
		String sql0 = sqlmface.searchSqlface(-1, arg);
		int totle= sqlUtil.get_int(sql0);
		pages = hm.pages(arg, totle);
		num = pages[2];
		String sql = sqlmface.searchSqlface(num, arg);
		log.send(DataType.noType, "01139", "AdminLogin-sql: ", sql);
		ArrayList<Map<String,Object>> list = sqlUtil.get_list(sql);
		//log.send(DataType.noType, "01139", "AdminLogin-sql: ", list);
		if(arg[4].equals("json")){
			inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
		}else{
			inOutUtil.return_listpage(list,pages,"boss/withdraw_list.jsp");
		}
	}
	
	private void withdraw_info(String[] arg) throws SQLException, IOException, ServletException {
		String sql0 = sqlmface.searchSqlface(-1, arg);
		int totle= sqlUtil.get_int(sql0);
		pages = hm.pages(arg, totle);
		num = pages[2];
		String sql = sqlmface.searchSqlface(num, arg);
		log.send(DataType.noType, "01139", "AdminLogin-sql: ", sql);
		ArrayList<Map<String,Object>> list = sqlUtil.get_list(sql);
		//log.send(DataType.noType, "01139", "AdminLogin-sql: ", list);
		if(arg[4].equals("json")){
			inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
		}else{
			inOutUtil.return_listpage(list,pages,"boss/withdraw_info.jsp");
		}
	}
	
	
	private void batchcard_list(String[] arg) throws SQLException, IOException, ServletException {
		String sql0 = sqlmface.searchSqlface(-1, arg);
		int totle= sqlUtil.get_int(sql0);
		pages = hm.pages(arg, totle);
		num = pages[2];
		String sql = sqlmface.searchSqlface(num, arg);
		log.send(DataType.noType, "01139", "AdminLogin-sql: ", sql);
		ArrayList<Map<String,Object>> list = sqlUtil.get_list(sql);
		//log.send(DataType.noType, "01139", "AdminLogin-sql: ", list);
		if(arg[4].equals("json")){
			inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
		}else{
			inOutUtil.return_listpage(list,pages,"boss/batchcard_list.jsp");
		}
	}
	
	
	private void agent_list(String[] arg) throws SQLException, IOException, ServletException {
		String sql0 = sqlmface.searchSqlface(-1, arg);
		int totle= sqlUtil.get_int(sql0);
		pages = hm.pages(arg, totle);
		num = pages[2];
		String sql = sqlmface.searchSqlface(num, arg);
		log.send(DataType.noType, "01139", "AdminLogin-sql: ", sql);
		ArrayList<Map<String,Object>> list = sqlUtil.get_list(sql);
		//log.send(DataType.noType, "01139", "AdminLogin-sql: ", list);
		if(arg[4].equals("json")){
			inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
		}else{
			inOutUtil.return_listpage(list,pages,"boss/agent_list.jsp");
		}
	}
	
	private void batchcardinfo(String[] arg) throws SQLException, IOException, ServletException {
		String sql0 = sqlmface.searchSqlface(-1, arg);
		int totle= sqlUtil.get_int(sql0);
		pages = hm.pages(arg, totle);
		num = pages[2];
		String sql = sqlmface.searchSqlface(num, arg);
		log.send(DataType.noType, "01139", "AdminLogin-sql: ", sql);
		ArrayList<Map<String,Object>> list = sqlUtil.get_list(sql);
		//log.send(DataType.noType, "01139", "AdminLogin-sql: ", list);
		if(arg[4].equals("json")){
			inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
		}else{
			inOutUtil.return_listpage(list,pages,"boss/batchcardinfo_list.jsp");
		}
	}
	
	
	
	// 文章添加页面 
	private void article_add(String[] arg) throws ServletException, IOException {
		// TODO Auto-generated method stub
		inOutUtil.return_str( "","boss/article_add.jsp");
	}
	
	private void batch_addbf(String[] arg) throws SQLException, IOException, ServletException  {
		// TODO Auto-generated method stub
		String sql = sqlmface.searchSqlface(0, arg);
		log.send(DataType.noType, "01139", "batch_addbf-sql: ", sql);
		String cardnum=sqlUtil.get_string(sql);
		if(cardnum.equals("")){
			cardnum="10000000";
		}else{
			cardnum=String.valueOf(Integer.parseInt(cardnum)+1);
		}
		inOutUtil.return_str( cardnum,"boss/batchcard_add.jsp");
	}
	
	
	private void batchcard_down(String[] arg) throws SQLException, IOException, ServletException  {
		// TODO Auto-generated method stub
		String sql = sqlmface.searchSqlface(0, arg);
		log.send(DataType.noType, "01139", "batch_addbf-sql: ", sql);
		ArrayList<Map<String,Object>> list=sqlUtil.get_list(sql);
		
		// 项目路径
		String path = this.getClass().getResource("/").getFile();
		
		File file1 = new File(path.substring(1, path.length()-1));
		File file2 = new File(new File(file1.getParent()).getParent());
		
		File filesDir = new File(file2,"files");
		if(!filesDir.exists()) {
			filesDir.mkdirs();
		}
		
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		//File excelFile = new File(filesDir,sdf.format(new Date())+".xls");
		File excelFile = new File(filesDir,arg[2]+".xls");
		log.send(DataType.basicType, "01107", "exportexcel()-excelFile: ", excelFile.getAbsolutePath());
		ExcelUtil.appendRow(list, excelFile.getPath(),file2,log);
		inOutUtil.return_ajax(excelFile.getName());
		
		
	}
	
	
	
	/*	修改文章查询
	Arg[0]: A-boss-search
	Arg[1]: ArticleIdSearch
	Arg[2]: Id
	
	
	
	*/
	
	
	private void ArticleIdSearch(String[] arg) throws SQLException, IOException, ServletException  {
		String sql = sqlmface.searchSqlface(0, arg);
		log.send(DataType.noType, "01139", "AdminLogin-sql: ", sql);
		ArrayList<Map<String,Object>> list = sqlUtil.get_list(sql);
		//log.send(DataType.noType, "01139", "AdminLogin-sql: ", list);
		// 测试
		/*sql = sqlmface.searchSqlface(1, arg);
		int i = sqlUtil.sql_exec(sql);
		log.send(DataType.noType, "01139", "i: ", i);*/
		
		inOutUtil.return_list(list,"boss/article_modify.jsp");
		
	}

	
	/*	查询文章
	Arg[0]: A-boss-search
	Arg[1]: ArticleSearch
	Arg[2]: empty
	Arg[3]: page
	
	
	*/
	private void ArticleSearch(String[] arg) throws SQLException, IOException, ServletException {
		String sql0 = sqlmface.searchSqlface(-1, arg);
		int totle= sqlUtil.get_int(sql0);
		pages = hm.pages(arg, totle);
		num = pages[2];
		String sql = sqlmface.searchSqlface(num, arg);
		log.send(DataType.noType, "01139", "AdminLogin-sql: ", sql);
		ArrayList<Map<String,Object>> list = sqlUtil.get_list(sql);
		//log.send(DataType.noType, "01139", "AdminLogin-sql: ", list);
		
		inOutUtil.return_listpage(list,pages,"boss/article_list.jsp");
		
	}

}



	

