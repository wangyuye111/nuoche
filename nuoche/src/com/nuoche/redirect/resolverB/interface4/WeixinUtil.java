package com.nuoche.redirect.resolverB.interface4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;















import com.nuoche.classroot.interface4.JyGlobalConstant;
import com.nuoche.classroot.interface4.JyInOutUtil;
import com.nuoche.classroot.interface4.JyLogDetect;
import com.nuoche.classroot.interface4.JyLogDetect.DataType;
import com.nuoche.classroot.interface4.SqlUtil;

import net.sf.json.JSONObject;


public class WeixinUtil {
	
	private static long timestamp;
	private static String accessToken;
	private static String jsApiTicket;
	private static String randomString;
	private static String signature;
	/**
	 * 鍚戞寚瀹歎RL鍙戦�丟ET鏂规硶鐨勮姹�
	 * @param url
	 * 鍙戦�佽姹傜殑URL
	 * @param param
	 * 璇锋眰鍙傛暟锛岃姹傚弬鏁板簲璇ユ槸name1=value1&name2=value2鐨勫舰寮忋��
	 * @return URL鎵�浠ｈ〃杩滅▼璧勬簮鐨勫搷搴�
	 */
	public static JSONObject sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlName = url + "?" + param;
			URL realUrl = new URL(urlName);
			// 鎵撳紑鍜孶RL涔嬮棿鐨勮繛鎺�
			URLConnection conn = realUrl.openConnection();
			// 璁剧疆閫氱敤鐨勮姹傚睘鎬�
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// 寤虹珛瀹為檯鐨勮繛鎺�
			conn.connect();
			// 鑾峰彇鎵�鏈夊搷搴斿ご瀛楁
			Map<String, List<String>> map = conn.getHeaderFields();
			// 閬嶅巻鎵�鏈夌殑鍝嶅簲澶村瓧娈�
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 瀹氫箟BufferedReader杈撳叆娴佹潵璇诲彇URL鐨勫搷搴�
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("鍙戦�丟ET璇锋眰鍑虹幇寮傚父锛�" + e);
			e.printStackTrace();
		}
		// 浣跨敤finally鍧楁潵鍏抽棴杈撳叆娴�
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return JSONObject.fromObject(result);
	}

	/**
	 * 鍚戞寚瀹歎RL鍙戦�丳OST鏂规硶鐨勮姹�
	 * @param url
	 * 鍙戦�佽姹傜殑URL
	 * @param param
	 * 璇锋眰鍙傛暟锛岃姹傚弬鏁板簲璇ユ槸name1=value1&name2=value2鐨勫舰寮忋��
	 * @return URL鎵�浠ｈ〃杩滅▼璧勬簮鐨勫搷搴�
	 */
	public static JSONObject sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 鎵撳紑鍜孶RL涔嬮棿鐨勮繛鎺�
			URLConnection conn = realUrl.openConnection();
			// 璁剧疆閫氱敤鐨勮姹傚睘鎬�
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// 鍙戦�丳OST璇锋眰蹇呴』璁剧疆濡備笅涓よ
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 鑾峰彇URLConnection瀵硅薄瀵瑰簲鐨勮緭鍑烘祦
			out = new PrintWriter(conn.getOutputStream());
			// 鍙戦�佽姹傚弬鏁�
			out.print(param);
			// flush杈撳嚭娴佺殑缂撳啿
			out.flush();
			// 瀹氫箟BufferedReader杈撳叆娴佹潵璇诲彇URL鐨勫搷搴�
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("鍙戦�丳OST璇锋眰鍑虹幇寮傚父锛�" + e);
			e.printStackTrace();
		}
		// 浣跨敤finally鍧楁潵鍏抽棴杈撳嚭娴併�佽緭鍏ユ祦
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return JSONObject.fromObject(result);
	}
	
	
	public static JSONObject getOpenId(String code,String appid,String appstrect){
		JSONObject access_token=sendGet("https://api.weixin.qq.com/sns/oauth2/access_token", "appid="+appid+"&secret="+appstrect+"&code="+code+"&grant_type=authorization_code");
		return access_token;
	}
	
	public static JSONObject getUserInfo(String Openid,String accessToken){
		//https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN 鍏紬鍙疯幏鍙栦俊鎭�
		//https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN 缃戦〉鑾峰彇淇℃伅
		JSONObject jsonWebUserInfo=sendGet("https://api.weixin.qq.com/sns/userinfo","access_token="+accessToken+"&openid="+Openid+"&lang=zh_CN");
		return jsonWebUserInfo;
	}
	
	public static String getAppid(String sellerid,SqlUtil sqlUtil) throws SQLException {
		String ssql="select appID from merchant where shop_accountname='"+sellerid+"'";
		return sqlUtil.get_string(ssql);
	}
	
	public static String getAppSecret(String sellerid,SqlUtil sqlUtil) throws SQLException {
		
		String ssql="select appsecret from merchant where shop_accountname='"+sellerid+"'";
		return sqlUtil.get_string(ssql);
	}
	
	public static String getLogo(String sellerid,SqlUtil sqlUtil) throws SQLException {
		
		String ssql="select logo_img from merchant where shop_accountname='"+sellerid+"'";
		return sqlUtil.get_string(ssql);
	}
	
	public static String getShopName(String sellerid,SqlUtil sqlUtil) throws SQLException {
		String ssql="select shop_name from merchant where shop_accountname='"+sellerid+"'";
		return sqlUtil.get_string(ssql);
	}
	
	public static String getShopNote(String sellerid,SqlUtil sqlUtil) throws SQLException {
		
		String ssql="select note from merchant where shop_accountname='"+sellerid+"'";
		return sqlUtil.get_string(ssql);
	}
	
	@SuppressWarnings("deprecation")
	public static String useridCheck(HttpSession session,HttpServletRequest request, HttpServletResponse response,JyLogDetect log,String module,String dbName,String sellerid,SqlUtil sqlUtil,JyInOutUtil inOutUtil) throws ServletException, IOException, SQLException {
		String username = "";
        if (session.getAttribute("UserName") != null && !"".equals(session.getAttribute("UserName").toString())) {
			
			//if(session.getAttribute("Sellerid").toString().equals(sellerid)){
				username = session.getAttribute("UserName").toString();
				log.send("01107","useridCheck()-username: ", username);
				/*}else{
				//session.setAttribute("URL", GlobalConstant.getWebBaseAddr()+request.getRequestURI() + "?" + request.getQueryString());
				//log.send(DataType.specialType, "01072", "鎷兼帴鐨剈rl",GlobalConstant.getWebBaseAddr()+request.getRequestURI() + "?" + request.getQueryString());
				String nowurl="";
				String duankou="8222";
				String  yuming="";
				log.send("01107","useridCheck()-username: ", request.getRequestURL().toString());
				if(request.getRequestURL().toString().contains("mingweishipin")){
					nowurl=request.getRequestURL()+"?" + request.getQueryString();
					if(request.getRequestURL().toString().contains("yunzhiyi")){
						yuming="chaochaoa";
					}else{
						yuming="jiaxun";
					}
				}else{
					yuming="chaochaoa";
					nowurl=GlobalConstant.getWebBaseAddr()+request.getRequestURI() + "?" + request.getQueryString();
					duankou=GlobalConstant.getWebPort();
				}
				log.send(DataType.specialType, "01072", "鎷兼帴鐨剈rl",duankou);
				session.setAttribute("URL", nowurl);
				log.send(DataType.specialType, "01072", "鎷兼帴鐨剈rl",nowurl);
				log.send(DataType.specialType, "01072", "鎷兼帴鐨剈rl",session.getAttribute("URL").toString());
				String appid="";
				String dbname="";
				if (module.indexOf("寰俊鏀粯") != -1) {
					String ssql="select appID from merchant where shop_accountname='"+sellerid+"'";
					appid=sqlUtil.get_string(ssql);
					dbname=dbName;
					inOutUtil.toWX("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid
							//+ "&redirect_uri=http%3A%2F%2Fchaochaoa.mingweishipin.com%2Fuiface%2fmembers%3fmode1%3dA-users-search%26mode2%3dUserLoginCheck%26sellerid%3d"+arg[2]+"&response_type=code&scope=snsapi_userinfo&state="+help.order_create()+"&connect_redirect=1#wechat_redirect");
							+ "&redirect_uri=http%3A%2F%2F"+yuming+".mingweishipin.com%2Fuiface%2fmembers%3fmode1%3dA-users-search%26mode2%3dUserLoginCheck%26sellerid%3d"+sellerid+"%26daebname%3d"+dbname+"&response_type=code&scope=snsapi_userinfo&state="+duankou+"&connect_redirect=1#wechat_redirect");
				}else{
					appid="wx15034b1300905282";
					inOutUtil.toWX("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid
							//+ "&redirect_uri=http%3A%2F%2Fchaochaoa.mingweishipin.com%2Fuiface%2fmembers%3fmode1%3dA-users-search%26mode2%3dUserLoginCheck%26sellerid%3d"+arg[2]+"&response_type=code&scope=snsapi_userinfo&state="+help.order_create()+"&connect_redirect=1#wechat_redirect");
							+ "&redirect_uri=http%3A%2F%2F"+yuming+".mingweishipin.com%2Fuiface%2fmembers%3fmode1%3dA-users-search%26mode2%3dUserLoginCheck%26sellerid%3d"+sellerid+"%26daebname%3d"+dbname+"&response_type=code&scope=snsapi_userinfo&state="+duankou+"&connect_redirect=1#wechat_redirect");
				}
				}*/
			
			
		} else {
			
			String nowurl=request.getRequestURL()+"?" + request.getQueryString();
			log.send("01107","useridCheck()-yuming: ", nowurl);
			String[] a=nowurl.split("\\.");
			String yuming=a[0].substring(7);
			log.send("01107","useridCheck()-yuming: ", yuming);
			String targurl="http://code1.mingweishipin.com/Wxcode?" + request.getQueryString();
			log.send("01107","useridCheck()-yuming: ", targurl);
			targurl=java.net.URLEncoder.encode(targurl);
			inOutUtil.toWX("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx15034b1300905282" /*+ GlobalConstant.getAppid()*/
					+ "&redirect_uri="+targurl+"&response_type=code&scope=snsapi_base&state="+yuming+"&connect_redirect=1#wechat_redirect");
			
			
			//log.send(DataType.specialType, "01115", "argOld鍙傛暟", arg);
			//session.setAttribute("URL", request.getRequestURL() +":"+request.getServerPort()  + "?" + request.getQueryString());
			//session.setAttribute("URL", GlobalConstant.getWebBaseAddr()+request.getRequestURI() + "?" + request.getQueryString());
			//log.send(DataType.specialType, "01072", "鎷兼帴鐨剈rl",GlobalConstant.getWebBaseAddr()+request.getRequestURI() + "?" + request.getQueryString());
			/*String nowurl="";
			String duankou="8222";
			log.send("01107","useridCheck()-username: ", request.getRequestURL().toString());
			String  yuming="";
			if(request.getRequestURL().toString().contains("mingweishipin")){
				nowurl=request.getRequestURL()+"?" + request.getQueryString();
				if(request.getRequestURL().toString().contains("yunzhiyi")){
					yuming="chaochaoa";
				}else{
					yuming="jiaxun";
				}
			}else{
				yuming="chaochaoa";
				nowurl=GlobalConstant.getWebBaseAddr()+request.getRequestURI() + "?" + request.getQueryString();
				duankou=GlobalConstant.getWebPort();
			}
			log.send(DataType.specialType, "01072", "鎷兼帴鐨剈rl",duankou);
			session.setAttribute("URL", nowurl);
			log.send(DataType.specialType, "01072", "鎷兼帴鐨剈rl",nowurl);
			log.send(DataType.specialType, "01072", "鎷兼帴鐨剈rl",session.getAttribute("URL").toString());
			String appid="";
			String dbname="";
			if (module.indexOf("寰俊鏀粯") != -1) {
				String ssql="select appID from merchant where shop_accountname='"+sellerid+"'";
				appid=sqlUtil.get_string(ssql);
				dbname=dbName;
				inOutUtil.toWX("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid
						//+ "&redirect_uri=http%3A%2F%2Fchaochaoa.mingweishipin.com%2Fuiface%2fmembers%3fmode1%3dA-users-search%26mode2%3dUserLoginCheck%26sellerid%3d"+arg[2]+"&response_type=code&scope=snsapi_userinfo&state="+help.order_create()+"&connect_redirect=1#wechat_redirect");
						+ "&redirect_uri=http%3A%2F%2F"+yuming+".mingweishipin.com%2Fuiface%2fmembers%3fmode1%3dA-users-search%26mode2%3dUserLoginCheck%26sellerid%3d"+sellerid+"%26daebname%3d"+dbname+"&response_type=code&scope=snsapi_userinfo&state="+duankou+"&connect_redirect=1#wechat_redirect");
			}else{
				appid="wx15034b1300905282";
				inOutUtil.toWX("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid
						//+ "&redirect_uri=http%3A%2F%2Fchaochaoa.mingweishipin.com%2Fuiface%2fmembers%3fmode1%3dA-users-search%26mode2%3dUserLoginCheck%26sellerid%3d"+arg[2]+"&response_type=code&scope=snsapi_userinfo&state="+help.order_create()+"&connect_redirect=1#wechat_redirect");
						+ "&redirect_uri=http%3A%2F%2F"+yuming+".mingweishipin.com%2Fuiface%2fmembers%3fmode1%3dA-users-search%26mode2%3dUserLoginCheck%26sellerid%3d"+sellerid+"%26daebname%3d"+dbname+"&response_type=code&scope=snsapi_userinfo&state="+duankou+"&connect_redirect=1#wechat_redirect");
			}*/
		}
		// log.send(DataType.basicType, "01115", "鐢ㄦ埛ID", userid);
		return username;
	}

	
	public static int getSubscribe(String sellerid, SqlUtil sqlUtil,String openid) throws SQLException {
		
		JSONObject jsonRet = sendGet("https://api.weixin.qq.com/cgi-bin/user/info","access_token="+accessToken+"&openid="+openid);
		int Subscribe = jsonRet.getInt("subscribe");
		return Subscribe;
	}
	public static String getAccessToken(String sellerid, SqlUtil sqlUtil) throws SQLException {
		
		JyLogDetect logDbg = new JyLogDetect();
		// 鍒ゆ柇褰撳墠鏃堕棿鎴虫槸鍚﹁秴鏃�
		if(timestamp != 0) {
			if( new Date().getTime()-timestamp <= 7000) {
				return accessToken;
			}
		}
		
		String appid = JyGlobalConstant.getAppid();	//"wx321142214f00fed6";	//getAppid(sellerid,sqlUtil);
		logDbg.send(DataType.noType, "01107", "Appid: ", appid);
		String appsecret = JyGlobalConstant.getAppsecret();	//"fb63cd9b600e652d42c5c59d116f6196";	//getAppSecret(sellerid,sqlUtil);
		logDbg.send(DataType.noType, "01107", "AppSecret: ", appsecret);
		
		JSONObject jsonRet = sendGet("https://api.weixin.qq.com/cgi-bin/token","grant_type=client_credential&appid="+appid+"&secret="+appsecret);
		
		if(jsonRet.containsKey("access_token")) {
			timestamp = new Date().getTime()/1000;	// 鐢熸垚鏃堕棿鎴�-绉掓暟
			accessToken = jsonRet.getString("access_token");
			
			JSONObject jsonRet2 = sendGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket","access_token="+accessToken+"&type=jsapi");
			if("ok".equals(jsonRet2.getString("errmsg"))) {
				jsApiTicket = jsonRet2.getString("ticket");
				
				//-------------------------------------
				//鐢熸垚绛惧悕
				logDbg.send(DataType.noType, "01107", "鏃堕棿鎴�: ", timestamp+","+new Date(timestamp));
				randomString = getRandomString();
				logDbg.send(DataType.noType, "01107", "鑾峰彇闅忔満瀛楃: ", randomString);
				return accessToken;
			} else {
				logDbg.send(DataType.exceptionType, "01107", "鑾峰彇jsapi_ticket澶辫触: ", jsonRet2);
				return null;
			}
		} else {
			logDbg.send(DataType.exceptionType, "01107", "鑾峰彇access_token澶辫触: ", jsonRet);
			return null;
		}
	}
	
	public static String getJsApiTicket() {
		return jsApiTicket;
	}
	
	public static String getSign(String url) {
		
		JyLogDetect logDbg = new JyLogDetect();
		
		String args = "jsapi_ticket="+jsApiTicket+"&noncestr="+randomString+"&timestamp="+timestamp
				+ "&url="+url;
		logDbg.send(DataType.noType, "01107", "绛惧悕鍓嶅瓧绗︿覆: ", args);
		signature = new SHA1().getDigestOfString(args.getBytes());
		logDbg.send(DataType.noType, "01107", "绛惧悕缁撴灉瀛楃涓�: ", signature);
		return signature;
	}
	
	public static String getRandomStr() {
		
		return randomString;
	}
	
	public static long getTimeStamp() {
		
		return timestamp;
	}
	
	/**
	 * 鑾峰彇闅忔満瀛楃涓�
	 * @return
	 */
	public final static String getRandomString() {
		byte[] buffer = String.valueOf( new Random().nextInt(10000)).getBytes();
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(buffer);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }
}

class SHA1 {   
    private final int[] abcde = {   
            0x67452301, 0xefcdab89, 0x98badcfe, 0x10325476, 0xc3d2e1f0   
        };   
    // 鎽樿鏁版嵁瀛樺偍鏁扮粍   
    private int[] digestInt = new int[5];   
    // 璁＄畻杩囩▼涓殑涓存椂鏁版嵁瀛樺偍鏁扮粍   
    private int[] tmpData = new int[80];   
    // 璁＄畻sha-1鎽樿   
    private int process_input_bytes(byte[] bytedata) {   
        // 鍒濊瘯鍖栧父閲�   
        System.arraycopy(abcde, 0, digestInt, 0, abcde.length);   
        // 鏍煎紡鍖栬緭鍏ュ瓧鑺傛暟缁勶紝琛�10鍙婇暱搴︽暟鎹�   
        byte[] newbyte = byteArrayFormatData(bytedata);   
        // 鑾峰彇鏁版嵁鎽樿璁＄畻鐨勬暟鎹崟鍏冧釜鏁�   
        int MCount = newbyte.length / 64;   
        // 寰幆瀵规瘡涓暟鎹崟鍏冭繘琛屾憳瑕佽绠�   
        for (int pos = 0; pos < MCount; pos++) {   
            // 灏嗘瘡涓崟鍏冪殑鏁版嵁杞崲鎴�16涓暣鍨嬫暟鎹紝骞朵繚瀛樺埌tmpData鐨勫墠16涓暟缁勫厓绱犱腑   
            for (int j = 0; j < 16; j++) {   
                tmpData[j] = byteArrayToInt(newbyte, (pos * 64) + (j * 4));   
            }   
            // 鎽樿璁＄畻鍑芥暟   
            encrypt();   
        }   
        return 20;   
    }   
    // 鏍煎紡鍖栬緭鍏ュ瓧鑺傛暟缁勬牸寮�   
    private byte[] byteArrayFormatData(byte[] bytedata) {   
        // 琛�0鏁伴噺   
        int zeros = 0;   
        // 琛ヤ綅鍚庢�讳綅鏁�   
        int size = 0;   
        // 鍘熷鏁版嵁闀垮害   
        int n = bytedata.length;   
        // 妯�64鍚庣殑鍓╀綑浣嶆暟   
        int m = n % 64;   
        // 璁＄畻娣诲姞0鐨勪釜鏁颁互鍙婃坊鍔�10鍚庣殑鎬婚暱搴�   
        if (m < 56) {   
            zeros = 55 - m;   
            size = n - m + 64;   
        } else if (m == 56) {   
            zeros = 63;   
            size = n + 8 + 64;   
        } else {   
            zeros = 63 - m + 56;   
            size = (n + 64) - m + 64;   
        }   
        // 琛ヤ綅鍚庣敓鎴愮殑鏂版暟缁勫唴瀹�   
        byte[] newbyte = new byte[size];   
        // 澶嶅埗鏁扮粍鐨勫墠闈㈤儴鍒�   
        System.arraycopy(bytedata, 0, newbyte, 0, n);   
        // 鑾峰緱鏁扮粍Append鏁版嵁鍏冪礌鐨勪綅缃�   
        int l = n;   
        // 琛�1鎿嶄綔   
        newbyte[l++] = (byte) 0x80;   
        // 琛�0鎿嶄綔   
        for (int i = 0; i < zeros; i++) {   
            newbyte[l++] = (byte) 0x00;   
        }   
        // 璁＄畻鏁版嵁闀垮害锛岃ˉ鏁版嵁闀垮害浣嶅叡8瀛楄妭锛岄暱鏁村瀷   
        long N = (long) n * 8;   
        byte h8 = (byte) (N & 0xFF);   
        byte h7 = (byte) ((N >> 8) & 0xFF);   
        byte h6 = (byte) ((N >> 16) & 0xFF);   
        byte h5 = (byte) ((N >> 24) & 0xFF);   
        byte h4 = (byte) ((N >> 32) & 0xFF);   
        byte h3 = (byte) ((N >> 40) & 0xFF);   
        byte h2 = (byte) ((N >> 48) & 0xFF);   
        byte h1 = (byte) (N >> 56);   
        newbyte[l++] = h1;   
        newbyte[l++] = h2;   
        newbyte[l++] = h3;   
        newbyte[l++] = h4;   
        newbyte[l++] = h5;   
        newbyte[l++] = h6;   
        newbyte[l++] = h7;   
        newbyte[l++] = h8;   
        return newbyte;   
    }   
    private int f1(int x, int y, int z) {   
        return (x & y) | (~x & z);   
    }   
    private int f2(int x, int y, int z) {   
        return x ^ y ^ z;   
    }   
    private int f3(int x, int y, int z) {   
        return (x & y) | (x & z) | (y & z);   
    }   
    private int f4(int x, int y) {   
        return (x << y) | x >>> (32 - y);   
    }   
    // 鍗曞厓鎽樿璁＄畻鍑芥暟   
    private void encrypt() {   
        for (int i = 16; i <= 79; i++) {   
            tmpData[i] = f4(tmpData[i - 3] ^ tmpData[i - 8] ^ tmpData[i - 14] ^   
                    tmpData[i - 16], 1);   
        }   
        int[] tmpabcde = new int[5];   
        for (int i1 = 0; i1 < tmpabcde.length; i1++) {   
            tmpabcde[i1] = digestInt[i1];   
        }   
        for (int j = 0; j <= 19; j++) {   
            int tmp = f4(tmpabcde[0], 5) +   
                f1(tmpabcde[1], tmpabcde[2], tmpabcde[3]) + tmpabcde[4] +   
                tmpData[j] + 0x5a827999;   
            tmpabcde[4] = tmpabcde[3];   
            tmpabcde[3] = tmpabcde[2];   
            tmpabcde[2] = f4(tmpabcde[1], 30);   
            tmpabcde[1] = tmpabcde[0];   
            tmpabcde[0] = tmp;   
        }   
        for (int k = 20; k <= 39; k++) {   
            int tmp = f4(tmpabcde[0], 5) +   
                f2(tmpabcde[1], tmpabcde[2], tmpabcde[3]) + tmpabcde[4] +   
                tmpData[k] + 0x6ed9eba1;   
            tmpabcde[4] = tmpabcde[3];   
            tmpabcde[3] = tmpabcde[2];   
            tmpabcde[2] = f4(tmpabcde[1], 30);   
            tmpabcde[1] = tmpabcde[0];   
            tmpabcde[0] = tmp;   
        }   
        for (int l = 40; l <= 59; l++) {   
            int tmp = f4(tmpabcde[0], 5) +   
                f3(tmpabcde[1], tmpabcde[2], tmpabcde[3]) + tmpabcde[4] +   
                tmpData[l] + 0x8f1bbcdc;   
            tmpabcde[4] = tmpabcde[3];   
            tmpabcde[3] = tmpabcde[2];   
            tmpabcde[2] = f4(tmpabcde[1], 30);   
            tmpabcde[1] = tmpabcde[0];   
            tmpabcde[0] = tmp;   
        }   
        for (int m = 60; m <= 79; m++) {   
            int tmp = f4(tmpabcde[0], 5) +   
                f2(tmpabcde[1], tmpabcde[2], tmpabcde[3]) + tmpabcde[4] +   
                tmpData[m] + 0xca62c1d6;   
            tmpabcde[4] = tmpabcde[3];   
            tmpabcde[3] = tmpabcde[2];   
            tmpabcde[2] = f4(tmpabcde[1], 30);   
            tmpabcde[1] = tmpabcde[0];   
            tmpabcde[0] = tmp;   
        }   
        for (int i2 = 0; i2 < tmpabcde.length; i2++) {   
            digestInt[i2] = digestInt[i2] + tmpabcde[i2];   
        }   
        for (int n = 0; n < tmpData.length; n++) {   
            tmpData[n] = 0;   
        }   
    }   
    // 4瀛楄妭鏁扮粍杞崲涓烘暣鏁�   
    private int byteArrayToInt(byte[] bytedata, int i) {   
        return ((bytedata[i] & 0xff) << 24) | ((bytedata[i + 1] & 0xff) << 16) |   
        ((bytedata[i + 2] & 0xff) << 8) | (bytedata[i + 3] & 0xff);   
    }   
    // 鏁存暟杞崲涓�4瀛楄妭鏁扮粍   
    private void intToByteArray(int intValue, byte[] byteData, int i) {   
        byteData[i] = (byte) (intValue >>> 24);   
        byteData[i + 1] = (byte) (intValue >>> 16);   
        byteData[i + 2] = (byte) (intValue >>> 8);   
        byteData[i + 3] = (byte) intValue;   
    }   
    // 灏嗗瓧鑺傝浆鎹负鍗佸叚杩涘埗瀛楃涓�   
    private static String byteToHexString(byte ib) {   
        char[] Digit = {   
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C',   
                'D', 'E', 'F'   
            };   
        char[] ob = new char[2];   
        ob[0] = Digit[(ib >>> 4) & 0X0F];   
        ob[1] = Digit[ib & 0X0F];   
        String s = new String(ob);   
        return s;   
    }   
    // 灏嗗瓧鑺傛暟缁勮浆鎹负鍗佸叚杩涘埗瀛楃涓�   
    private static String byteArrayToHexString(byte[] bytearray) {   
        String strDigest = "";   
        for (int i = 0; i < bytearray.length; i++) {   
            strDigest += byteToHexString(bytearray[i]);   
        }   
        return strDigest;   
    }   
    // 璁＄畻sha-1鎽樿锛岃繑鍥炵浉搴旂殑瀛楄妭鏁扮粍   
    public byte[] getDigestOfBytes(byte[] byteData) {   
        process_input_bytes(byteData);   
        byte[] digest = new byte[20];   
        for (int i = 0; i < digestInt.length; i++) {   
            intToByteArray(digestInt[i], digest, i * 4);   
        }   
        return digest;   
    }   
    // 璁＄畻sha-1鎽樿锛岃繑鍥炵浉搴旂殑鍗佸叚杩涘埗瀛楃涓�   
    public String getDigestOfString(byte[] byteData) {   
        return byteArrayToHexString(getDigestOfBytes(byteData));   
    }
}