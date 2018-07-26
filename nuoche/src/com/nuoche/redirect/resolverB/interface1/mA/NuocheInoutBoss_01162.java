package com.nuoche.redirect.resolverB.interface1.mA;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.media.jai.operator.LogDescriptor;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alicom.mns.tools.DefaultAlicomMessagePuller;
import com.alicom.mns.tools.MessageListener;
import com.aliyun.mns.model.Message;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxbRequest;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxbResponse;
import com.aliyuncs.dyplsapi.model.v20170525.UnbindSubscriptionRequest;
import com.aliyuncs.dyplsapi.model.v20170525.UnbindSubscriptionResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.google.gson.Gson;
import com.nuoche.classroot.interface1.NuocheInOutFace;
import com.nuoche.classroot.interface1.NuocheInOutManager;
import com.nuoche.classroot.interface2.NuocheSqlMFace;
import com.nuoche.classroot.interface4.HelpManager;
import com.nuoche.classroot.interface4.JsonUtil;
import com.nuoche.classroot.interface4.JyLogDetect;
import com.nuoche.classroot.interface4.JyLogDetect.DataType;
import com.nuoche.redirect.resolverB.interface2.mA.NuocheSqlBoss_01162;
import com.nuoche.redirect.resolverB.interface4.QrImage;




public class NuocheInoutBoss_01162  extends NuocheInOutManager implements
NuocheInOutFace {
	protected ArrayList<Map<String, Object>> list;
	protected ArrayList<Map<String, Object>> list1;
	protected ArrayList<Map<String, Object>> list2;
	   //产品名称:云通信隐私保护产品,开发者无需替换
    static final String product = "Dyplsapi";
    //产品域名,开发者无需替换
    static final String domain = "dyplsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAI5KZKkVbeh4sp";
    static final String accessKeySecret = "rEyoFPSbzFPquhH0zToRa1f8kqdmD1";
	protected String json = "";
	NuocheSqlMFace sqlmface = new NuocheSqlBoss_01162();
	public NuocheInoutBoss_01162(String[] arg, HttpServletRequest request,
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
		case "getqrcode":
			getqrcode(arg);
			break;
		case "private_number":
			private_number(arg);
			break;
		case "send_message":
			send_message(arg);
			break;
			
		case "getcode":
			getcode(arg);
			break;
			
		case "admin_login":
			admin_login(arg);
			break;	
			
		}	
	}
	
	
	
	/**
	 * 管理员登录
	 * arg[1] admin_login
	 * arg[2] 暂时无用
	 * arg[3] 用户名	
	 * arg[4] 密码			
	 * @param arg
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ServletException 
	 */
	private void admin_login(String[] arg) throws SQLException, IOException,
	ServletException {
		String sql1 = sqlmface.searchSqlface(0, arg);
		log.send(DataType.basicType, "01156", "admin_login()-sql1: ", sql1);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql1);
		log.send(DataType.basicType, "01156", "admin_login()-list: ", list);
		HttpSession session = request.getSession();
		session.setAttribute("admin", "success");
		session.setMaxInactiveInterval(60 * 60 * 10);
		if (list.size() == 1) {
			inOutUtil.return_listpage(list, pages,
					"/uiface/boss/admincenter.jsp");
		} else {
			// request.getSession().setAttribute("adminLoginStatus", "err");
			inOutUtil.return_only("http://47.106.140.170:8090/uiface/boss/adminLogin.jsp");
		}
   }
	
	private void getcode(String[] arg) throws SQLException, IOException, ServletException {
		int num = (int) (Math.random()*9000+1000);
		//String jsonadd = "{\"random\":\"" + num + "\"}";
		// 产品名称:云通信短信API产品,开发者无需替换
		String product = "Dysmsapi";
		// 产品域名,开发者无需替换
		String domain = "dysmsapi.aliyuncs.com";

		// TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻�?
		//accessKeyId = "LTAI6N7qVbZ01jwL";
		//accessKeySecret = "ffgZPSCeMOU483QsZVr1Fmxu4KlUY5";

		// 可自助调整超时时�?
		System.setProperty("sun.net.client.defaultConnectTimeout", "60000");
		System.setProperty("sun.net.client.defaultReadTimeout", "60000");

		// 初始化acsClient,暂不支持region�?
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
				accessKeyId, accessKeySecret);
		SendSmsResponse sendSmsResponse = null;
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product,
					domain);
			IAcsClient acsClient = new DefaultAcsClient(profile);
			// 组装请求对象-具体描述见控制台-文档部分内容
			SendSmsRequest request = new SendSmsRequest();
			// 必填:待发送手机号
			request.setPhoneNumbers(arg[2]);
			// 必填:短信签名-可在短信控制台中找到
			request.setSignName("铼摩共享挪车");
			// 必填:短信模板-可在短信控制台中找到
			request.setTemplateCode("SMS_138520013");
			// 可�?模板中的变量替换JSON�?如模板内容为"亲爱�?{name},您的验证码为${code}"�?此处的值为
			request.setTemplateParam("{\"code\":\"" + num +"\"}");
			// 选填-上行短信扩展�?无特殊需求用户请忽略此字�?
			// request.setSmsUpExtendCode("90997");

			// 可�?outId为提供给业务方扩展字�?最终在短信回执消息中将此值带回给调用�?
			// request.setOutId("yourOutId");

			// hint 此处可能会抛出异常，注意catch

			sendSmsResponse = acsClient.getAcsResponse(request);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.send(DataType.basicType, "01160", "发送消息信息", sendSmsResponse.getCode());
		if (sendSmsResponse.getCode()!= null
				&& sendSmsResponse.getCode().equals("OK")) {
			// 请求成功
			inOutUtil.return_ajax(num+"");
		}		
	}
	
	
	private void send_message(String[] arg) throws SQLException, IOException, ServletException {
		//int num = (int) (Math.random() * 10000);
		//String jsonadd = "{\"random\":\"" + num + "\"}";
		// 产品名称:云通信短信API产品,开发者无需替换
		String product = "Dysmsapi";
		// 产品域名,开发者无需替换
		String domain = "dysmsapi.aliyuncs.com";

		// TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻�?
		//accessKeyId = "LTAI6N7qVbZ01jwL";
		//accessKeySecret = "ffgZPSCeMOU483QsZVr1Fmxu4KlUY5";

		// 可自助调整超时时�?
		System.setProperty("sun.net.client.defaultConnectTimeout", "60000");
		System.setProperty("sun.net.client.defaultReadTimeout", "60000");

		// 初始化acsClient,暂不支持region�?
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
				accessKeyId, accessKeySecret);
		SendSmsResponse sendSmsResponse = null;
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product,
					domain);
			IAcsClient acsClient = new DefaultAcsClient(profile);
			// 组装请求对象-具体描述见控制台-文档部分内容
			SendSmsRequest request = new SendSmsRequest();
			// 必填:待发送手机号
			request.setPhoneNumbers(arg[2]);
			// 必填:短信签名-可在短信控制台中找到
			request.setSignName("铼摩共享挪车");
			// 必填:短信模板-可在短信控制台中找到
			request.setTemplateCode("SMS_138067207");
			// 可�?模板中的变量替换JSON�?如模板内容为"亲爱�?{name},您的验证码为${code}"�?此处的值为
			request.setTemplateParam("{\"code\":\"" + arg[3] + "\",\"address\":\""+arg[4]+ "\",\"reason\":\""+arg[5]+"\"}");
			// 选填-上行短信扩展�?无特殊需求用户请忽略此字�?
			// request.setSmsUpExtendCode("90997");

			// 可�?outId为提供给业务方扩展字�?最终在短信回执消息中将此值带回给调用�?
			// request.setOutId("yourOutId");

			// hint 此处可能会抛出异常，注意catch

			sendSmsResponse = acsClient.getAcsResponse(request);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.send(DataType.basicType, "01160", "发送消息信息", sendSmsResponse.getCode());
		if (sendSmsResponse.getCode()!= null
				&& sendSmsResponse.getCode().equals("OK")) {
			// 请求成功
			inOutUtil.return_ajax("ok");
		}		
	}
	
	
	
	private void private_number(String[] arg) throws SQLException, IOException,
	ServletException {
		 //AXB绑定
        BindAxbResponse axbResponse;
		try {
			axbResponse = bindAxb(arg);
			 log.send("01162"," 01162", "AXB绑定接口返回的结果----------------");
		        String axbSubsId = axbResponse.getSecretBindDTO() == null ?  null : axbResponse.getSecretBindDTO().getSubsId();
		        String axbSecretNo = axbResponse.getSecretBindDTO() == null ?  null : axbResponse.getSecretBindDTO().getSecretNo();
                  log.send("01162"," 01162", "Code=" + axbResponse.getCode());
                  log.send("01162"," 01162", "Code=" + axbResponse.getMessage());
                  log.send("01162"," 01162", "Code=" + axbResponse.getRequestId());
                  log.send("01162"," 01162", "subsId=" + axbSubsId);
                  log.send("01162"," 01162", "secretNo=" + axbSecretNo);
                  String jsonadd="{\"success\":\"1\"}";
                  inOutUtil.return_ajax(jsonadd);
          		
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.send("01162"," 01162----异常", e);
		} 
	}
	
	/**
	 * 生成二维码海报图片
	 * @param arg
	 * 
	 * arg[0]: A-user-search
	 * arg[1]: getqrcode
	 * arg[2]: userid
	 * @throws IOException 
	 * @throws SQLException 
	 */
	private void getqrcode(String[] arg) throws SQLException, IOException {
		response.setContentType("image/jpeg");
		
		String sql=sqlmface.searchSqlface(0, arg);
		log.send(DataType.basicType, "01156", "getqrcode: ", sql);
		int code=sqlUtil.get_int(sql);
		code++;
		arg[0]=code+"";
		sql=sqlmface.searchSqlface(1, arg);
		log.send(DataType.basicType, "01156", "getqrcode: ", sql);
		int a=sqlUtil.sql_exec(sql);
		
		sql=sqlmface.searchSqlface(2, arg);
		log.send(DataType.basicType, "01156", "getqrcode: ", sql);
		a=sqlUtil.sql_exec(sql);
		
		//int a=(int)((Math.random()*9+1)*100000);
		log.send(DataType.basicType, "01156", "getqrcode: ", "start");
		try {
			// 生成二维码
			//http://12nm.cn/uiface/user/invitation.jsp?userid=${json}
			//String qrcodeUrl = "https://vipz.top/uiface/user1/ff?phonenum="+arg[2]+"&chepaihao="+arg[3]+"";
			String qrcodeUrl = "https://vipz.top/uiface/editqrcode/card_num="+code;
            //
			//String qrcodeUrl = request.getScheme()+"://"+request.getServerName()+"/uiface/login?a=A-user-search&b=shareqrcode&c="+arg[2];
			log.send(DataType.basicType, "01156", "qrcodeUrl: ", qrcodeUrl);
			BufferedImage bi = QrImage.createCodeStream(qrcodeUrl);	
			String fullPath = "img/qrcodeimg";
			String tmppath = request.getSession().getServletContext()
            .getRealPath("/");
			String filedir = tmppath + fullPath;
			File fullDir = new File(tmppath + fullPath);
			if (!fullDir.exists()) {
				fullDir.mkdirs();
			}
			String fileName = code+".jpg";
			String filepath = filedir + File.separator + fileName;
			log.send(DataType.basicType, "01150", "filepath========", filepath);
			File file = new File(filepath);
			ImageIO.write(bi, "JPEG", file);
			String b="https://vipz.top/img/qrcodeimg/"+code+".jpg";
			String json="{\"success\":\""+b+"\"}";
			log.send(DataType.basicType, "01158", "register_add",json);
			inOutUtil.return_ajax(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.send(DataType.exceptionType, "01156", "getqrcode: ", "exception: "+e);
		}
	}
	
	

	
	
	
	
	 /**
     * AXB绑定示例
     * @return
     * @throws ClientException
     */
    public static BindAxbResponse bindAxb(String []arg) throws ClientException {
    	JyLogDetect log=new JyLogDetect();
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象-具体描述见控制台-文档部分内容
        BindAxbRequest request = new BindAxbRequest();
        //必填:对应的号池Key
        request.setPoolKey("FC100000035840039");
        //必填:AXB关系中的A号码
        request.setPhoneNoA(arg[2]);
        //必填:AXB关系中的B号码
        request.setPhoneNoB(arg[3]);
        
        request.setPhoneNoX("17150873143");
        //必填:绑定关系对应的失效时间-不能早于当前系统时间
        log.send(DataType.basicType, "01158", "两分钟之后的时间",addDateMinut(4));
        request.setExpiration(addDateMinut(4));
        //可选:是否需要录制音频-默认是false
        request.setIsRecordingEnabled(false);
        //外部业务自定义ID属性
        //request.setOutId("yourOutId");
        //hint 此处可能会抛出异常，注意catch
        BindAxbResponse response = acsClient.getAcsResponse(request);
        return response;
    }
    public static UnbindSubscriptionResponse unbind(String subsId, String secretNo) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        UnbindSubscriptionRequest request = new UnbindSubscriptionRequest();
        //必填:对应的号池Key
        request.setPoolKey("FC100000035840039");
        //必填-分配的X小号-对应到绑定接口中返回的secretNo;
        request.setSecretNo(secretNo);
        //可选-绑定关系对应的ID-对应到绑定接口中返回的subsId;
        request.setSubsId(subsId);
        UnbindSubscriptionResponse response = acsClient.getAcsResponse(request);

        return response;
    }
    public static String addDateMinut( int x)//返回的是字符串型的时间，输入的
   {   
          SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24小时制  
 
          Date date = null;  
          date = new Date();   
          //System.out.println("front:" + format.format(date)); //显示输入的日期  
          Calendar cal = Calendar.getInstance();   
          cal.setTime(date);   
          cal.add(Calendar.MINUTE, x);// 24小时制   
          date = cal.getTime();   
          System.out.println("after:" + format.format(date));  //显示更新后的日期 
          cal = null;   
          return format.format(date);   
      }  

	

}
