package com.nuoche.redirect.resolverB.interface4;


import java.security.MessageDigest;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;










import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.nuoche.classroot.interface4.JyGlobalConstant;
import com.nuoche.classroot.interface4.JyLogDetect;
import com.nuoche.classroot.interface4.JyLogDetect.DataType;
import com.nuoche.classroot.interface4.SqlUtil;









public class VipInit implements ServletContextListener {

		// 开一个定时循环线程
		private ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(2);
		//private Map<String,ShopPvInfo> infoOfDay = new HashMap<String,ShopPvInfo>();
		@Override
		public void contextDestroyed(ServletContextEvent arg0) {
			// TODO Auto-generated method stub
			//System.out.println("CtrlInit destroy");
			
			
		}

		@Override
		public void contextInitialized(ServletContextEvent arg0) {
			// TODO Auto-generated method stub
			//System.out.println("CtrlInit initialize");
			final JyLogDetect log=new JyLogDetect();
			executor.scheduleWithFixedDelay(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					//System.out.println("Thread start");
					log.send(DataType.specialType, "01066", "活动检测", "Thread start");
					//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
					SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date today=new Date();
					String a=df1.format(today);
					//System.out.println(a);
					//String b=df.format(today);
					//String c=b+":51:00";
					//String d=b+":51:29";
					log.send(DataType.specialType, "01066", "活动检测", a);
					try {
						//if(a.compareTo(c)>=0 && a.compareTo(d)<=0){
							update(a);
					    //}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}, 10, 5, 
			TimeUnit.SECONDS);
		}
		
		public void update(String nowtime) throws SQLException, ClassNotFoundException{
			JyLogDetect log=new JyLogDetect();
			log.send(DataType.specialType, "01066", "活动检测", nowtime);
			List<String> listNames = new ArrayList<String>();
			listNames.add(JyGlobalConstant.getDbBaseName());
			String sql="";
			ArrayList<Map<String, Object>> list=null;
			for(int i=0;i<listNames.size();i++){
				SqlUtil sqlUtil = new SqlUtil(listNames.get(i));
				
				//约定发布时间的，到时间发布
				sql="select * from release_table where time<'"+nowtime +"' and cw_states=-1  ";
				log.send(DataType.specialType, "01066", "活动检测", sql);
				list=sqlUtil.get_list(sql);
				log.send(DataType.specialType, "01066", "活动检测", list.size());
				for(int m=0;m<list.size();m++){
					
//					if(Integer.parseInt(list.get(m).get("isreturn").toString())==1){
//						sql = "UPDATE appointment SET isrefund =1,set isreturn=0  "
//								+ "WHERE id = '" + list.get(m).get("id") + "' ";
//						log.send(DataType.specialType, "01066", "活动检测", sql);
//						sqlUtil.sql_exec(sql);
//					}else{
						sql = "UPDATE release_table SET cw_states =0  "
								+ "WHERE id = '" + list.get(m).get("id") + "' ";
						log.send(DataType.specialType, "01066", "活动检测", sql);
						sqlUtil.sql_exec(sql);
//					}
					//增加一条v币收入明细
				}
				
				//过了到期时间的，变成过期，不能抢车位
				sql="select * from release_table where end_timef<'"+nowtime +"' and cw_states=0  ";
				log.send(DataType.specialType, "01066", "活动检测", sql);
				list=sqlUtil.get_list(sql);
				log.send(DataType.specialType, "01066", "活动检测", list.size());
				for(int m=0;m<list.size();m++){
					
//					if(Integer.parseInt(list.get(m).get("isreturn").toString())==1){
//						sql = "UPDATE appointment SET isrefund =1,set isreturn=0  "
//								+ "WHERE id = '" + list.get(m).get("id") + "' ";
//						log.send(DataType.specialType, "01066", "活动检测", sql);
//						sqlUtil.sql_exec(sql);
//					}else{
						sql = "UPDATE release_table SET cw_states =1  "
								+ "WHERE id = '" + list.get(m).get("id") + "' ";
						log.send(DataType.specialType, "01066", "活动检测", sql);
						sqlUtil.sql_exec(sql);
//					}
					
					//增加一条v币收入明细
					
				}
				
				
				
				
				
			}
		}
		
	
		public static String get32UUID() {
		   
		        UUID uuid = UUID.randomUUID();
		        /**
		         * .{6c0222ed-e7f5-4cad-a717-a9abfb372239}
		         */
		        //System.out.println(".{" + uuid.toString() + "}");
		        /**
		         * 6c0222ed-e7f5-4cad-a717-a9abfb372239
		         */
		        //System.out.println(uuid.toString());
		        /**
		         * 36
		         */
		        //System.out.println(uuid.toString().length());
		        /**
		         * 32
		         */
		        //System.out.println(uuid.toString().replace("-", "").length());
		        /**
		         * 6c0222ede7f54cada717a9abfb372239
		         */
		        //System.out.println(uuid.toString().replace("-", ""));
				
		        return uuid.toString().replace("-", "");
		   

		}
		
		/**
		 * MD5加密
		 */
		public static class MD5 {

		    private MD5() {}

		    /**
		     * 对传入的数据提取摘要
		     * @param buffer
		     * @return
		     */
		    public final static String getMessageDigest(byte[] buffer) {
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
		
		  /**
	     * 元转换成分
	     * @param money
	     * @return
	     */
	    public static  String getMoney(String amount) {
	        if(amount==null){
	            return "";
	        }
	        // 金额转化为分为单位
	        String currency =  amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额  
	        int index = currency.indexOf(".");  
	        int length = currency.length();  
	        Long amLong = 0l;  
	        if(index == -1){  
	            amLong = Long.valueOf(currency+"00");  
	        }else if(length - index >= 3){  
	            amLong = Long.valueOf((currency.substring(0, index+3)).replace(".", ""));  
	        }else if(length - index == 2){  
	            amLong = Long.valueOf((currency.substring(0, index+2)).replace(".", "")+0);  
	        }else{  
	            amLong = Long.valueOf((currency.substring(0, index+1)).replace(".", "")+"00");  
	        }  
	        return amLong.toString(); 
	    }
}


