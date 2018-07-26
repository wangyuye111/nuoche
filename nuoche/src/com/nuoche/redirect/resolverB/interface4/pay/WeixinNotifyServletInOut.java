package com.nuoche.redirect.resolverB.interface4.pay;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import com.nuoche.classroot.interface4.JyGlobalConstant;
import com.nuoche.classroot.interface4.JyLogDetect;
import com.nuoche.classroot.interface4.SqlUtil;
import com.nuoche.redirect.resolverB.interface4.pay.util.RequestHandler;






@WebServlet("/wxpay/notify")
public class WeixinNotifyServletInOut extends HttpServlet {
	
	JyLogDetect log=null;

	
	public WeixinNotifyServletInOut() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("UTF-8");
		log=new JyLogDetect();
		log.send("01115", "支付成功的回调：", "支付成功的回调");
		String out_trade_no=null;
		String return_code =null;
		String attach =null;
		try {
			InputStream inStream = request.getInputStream();
			ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inStream.read(buffer)) != -1) {
				outSteam.write(buffer, 0, len);
			}
			outSteam.close();
			inStream.close();
			String resultStr  = new String(outSteam.toByteArray(),"utf-8");
			log.send("01115", "", "支付成功的回调："+resultStr);
			//logger.info("支付成功的回调："+resultStr);
			Map<String, Object> resultMap = parseXmlToList(resultStr);
			String result_code = (String) resultMap.get("result_code");
			String is_subscribe = (String) resultMap.get("is_subscribe");
			String transaction_id = (String) resultMap.get("transaction_id");
			String sign = (String) resultMap.get("sign");
			String time_end = (String) resultMap.get("time_end");
			String bank_type = (String) resultMap.get("bank_type");
			out_trade_no = (String) resultMap.get("out_trade_no");
			return_code = (String) resultMap.get("return_code");
			attach =(String) resultMap.get("attach");
			request.setAttribute("out_trade_no", out_trade_no);
			log.send("01115", "支付成功的回调：", ""+out_trade_no);
			log.send("01115", "支付成功的回调：", ""+attach);
			log.send("01115", "支付成功的回调：", ""+return_code);
			//通知微信.异步确认成功.必写.不然微信会一直通知后台.八次之后就认为交易失败了.
			response.getWriter().write(RequestHandler.setXML("SUCCESS", ""));
		}  catch (Exception e) {
			//logger.error("微信回调接口出现错误：",e);
			try {
				response.getWriter().write(RequestHandler.setXML("FAIL", "error"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		log.send("01115", "支付成功的回调：", "处理订单"+return_code);
		if(return_code.equals("SUCCESS")){
			try {
				log.send("01115", "支付成功的业务逻辑：", "处理订单"+return_code);
				//String out_trade_no=request.getParameter("out_trade_no").toString();
				log.send("01115", "支付成功的回调out_trade_no：", out_trade_no);
				SqlUtil sqlUtil = new SqlUtil(JyGlobalConstant.getDbBaseName());
				String sql="";
				if(attach.equals("0")){
					sql="update apply_move_code_table set return_msg='success',is_pay=1 where order_num='"+out_trade_no+"'";
					log.send("01115", "支付成功的回调：", sql);
					sqlUtil.sql_exec(sql);
				}else{
					sql="update release_table set res_msg='success',cw_states=0 where order_num='"+out_trade_no+"'";
					log.send("01115", "支付成功的回调：", sql);
					sqlUtil.sql_exec(sql);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			log.send("01115", "支付失败的业务逻辑：", "处理订单"+return_code);
			//支付失败的业务逻辑
		}
	}
	/*public  void sendAndroidPush(String sellerid,String orderid)  {
		log.send("01115", "支付成功的回调：", sellerid);
		final String appKey ="05aa32a7f711e77ad2e1b80e";
		final String masterSecret = "0623578f51c9b2fa1f4b207d";
		ClientConfig config = ClientConfig.getInstance();
        // Setup the custom hostname
        config.setPushHostName("https://api.jpush.cn");
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, config);
        //JPushClient jpushClient = new JPushClient(masterSecret, appKey);
		// 解析消息类型
        String msgType = "msg_type_img";
        String message = "新订单："+orderid;
        log.send("01115", "支付成功的回调：", message);
        //com.tencent.xinge.Message message1 = new com.tencent.xinge.Message();
        PushPayload payload = null;
        switch(msgType) {
        case "msg_type_img":
        	//message1.setType(com.tencent.xinge.Message.TYPE_MESSAGE);
        	payload = PushPayload.newBuilder()
                    .setPlatform(Platform.android())
                    //.setAudience(Audience.tag("seller"))
                    .setAudience(Audience.tag(sellerid))
                    .setNotification(Notification.newBuilder()
                    		.setAlert(message)
                    		.addPlatformNotification(AndroidNotification.newBuilder()
                    				.setTitle("订单消息").build())
                            .addPlatformNotification(IosNotification.newBuilder()
      	                          .setBadge(1)
      	                          .setSound("happy")
      	                          .addExtra("from", "JPush")
      	                          .build())
      	                      .build())
                    .setMessage(cn.jpush.api.push.model.Message.content(message))
                    .setOptions(Options.newBuilder()
                         .setApnsProduction(true)
                         .build())
                    .build();
        	break;
        }
     // 开始极光推送
		try {
        	if(payload != null) {
        		PushResult result = jpushClient.sendPush(payload);
        		log.send("01115", "支付成功的回调：", "message: "+message+", result: "+result);
        	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}*/
	/**
	 * description: 解析微信通知xml
	 * 
	 * @param xml
	 * @return
	 * @author ex_yangxiaoyi
	 * @see
	 */
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	private static Map parseXmlToList(String xml) {
		Map retMap = new HashMap();
		try {
			StringReader read = new StringReader(xml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			SAXBuilder sb = new SAXBuilder();
			// 通过输入源构造一个Document
			Document doc = (Document) sb.build(source);
			Element root = doc.getRootElement();// 指向根节点
			List<Element> es = root.getChildren();
			if (es != null && es.size() != 0) {
				for (Element element : es) {
					retMap.put(element.getName(), element.getValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}

}
