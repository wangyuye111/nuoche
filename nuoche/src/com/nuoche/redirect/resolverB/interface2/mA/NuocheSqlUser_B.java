package com.nuoche.redirect.resolverB.interface2.mA;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import com.nuoche.classroot.interface2.NuocheSqlMFace;
import com.nuoche.classroot.interface2.NuocheSqlManager;
import com.nuoche.classroot.interface4.JyHelpManager;






public class NuocheSqlUser_B  extends NuocheSqlManager implements NuocheSqlMFace {
	@Override
	public String addSqlface(int current, String[] arg) throws SQLException,IOException 
	{
		switch (arg[1]) {
		case "regiest":
			if(current==0){
				ressql = "select count(*) from user_data where openid='"+arg[2]+"'";
			}else if(current==1){
				ressql = "insert into user_data (nickname,head_photo,openid,user_longitude,user_latitude) values ('"+arg[4]+"','"+arg[3]+"','"+arg[2]+"','"+arg[5]+"','"+arg[6]+"')";
			}else if(current==2){
				ressql = "select * from user_data where openid='"+arg[2]+"'";
			}
			break;
		
		
		case "apply_move_code_submit":
			if(current==0){
				String money=String.valueOf(Integer.parseInt(arg[4])*0.01);
				ressql = "INSERT INTO apply_move_code_table "
						+ "(user_id,name,phone,address,number,mail_status,apply_time,is_pay,code_id,code_phone,order_num,price) "
						+ "VALUES ("+arg[2]+",'"+arg[5]+"','"+arg[6]+"','"+arg[7]+"','"+arg[4]+"','待邮寄',now(),'0','"+arg[3]+"','"+arg[8]+"','"+arg[0]+"','"+money+"')";
			}else{
				ressql = "select openid from user_data where id="+arg[2];
			}
			
			/*else if(current==1){
				ressql = "INSERT INTO move_code_table (user_id,move_time) VALUES ("+arg[2]+",NOW())";
			}else if(current==2){
				ressql = "SELECT id FROM move_code_table "
						+ "WHERE user_id= "+arg[2]+" and move_time = (select max(move_time) "
						+ "from move_code_table where move_time<=NOW() "
						+ "and  user_id = "+arg[2]+" group by user_id)";
			}*/
			break;
			
		
		case "apply_move_code_pay":
			if(current==0){
				ressql="select * from  apply_move_code_table as a,user_data as b where a.id="+arg[2]+" and a.user_id=b.id";
			}
			break;
			
		case "say_art_submit":
			if(current==0){
				ressql="INSERT INTO say_art_table "
						+ "(update_id,title,photo,isdel,update_time,status) "
						+ "VALUES ("+arg[2]+",'"+arg[4]+"','"+arg[3]+"',0,NOW(),'待审核')";
			}
			break;
		
			
			
			
		/**************
		 * 问题	
		 */
		case "release_submit":
			if(current==0)
			{
				/*ressql = "INSERT INTO release_table "   
						+ "(user_id,time,place,price,type,longitude,latitude) "
						+ "VALUES ("+arg[2]+",NOW(),'"+arg[3]+"','"+arg[4]+"','"+arg[5]+"','"+arg[6]+"','"+arg[7]+"')";*/
				/*ressql = "INSERT INTO release_table "
						+ "(user_id,time,place,price,type,longitude,latitude) "
						+ "VALUES ("+arg[2]+",'"+arg[8]+"','"+arg[3]+"','"+arg[4]+"','"+arg[5]+"','"+arg[6]+"','"+arg[7]+"')";*/
				String order_num=new JyHelpManager().order_create();
				/**
				 * 孙仪双修改sql语句
				 * */
				ressql = "INSERT INTO release_table "   
						+ "(user_id,price,hours,place,time,longitude,latitude,end_timef,nowphone,order_num,refuseorder_num,cw_states,state) "
						+ "VALUES ("+arg[2]+",'"+arg[8]+"','1','"+arg[3]+"','"+arg[4]+"','"+arg[5]+"','"+arg[6]+"','"+arg[9]+"','"+arg[7]+"','"+order_num+"','"+order_num+"1','"+arg[11]+"','"+arg[10]+"')";
			}else if(current==1){
				ressql = "select send_time from set_table where id = 1";
			}else if(current==2){
				ressql = "select DATE_ADD('"+arg[4]+"',INTERVAL '"+arg[9]+"' HOUR) as endtimes from robbing_table where id=1";
			}
			break;
			/**
			 * arg[2] User_id			用户id
			 * arg[3] Number_plate 		车牌号
			 * arg[4] Phone 			手机号
			 * arg[5] Greetings 		问候语
			 */
		case "move_code_submit":
			if(current == 0){
				ressql = "INSERT INTO move_code_table "
						+ "(user_id,number_plate,phone,greetings,move_time) "
						+ "VALUES "
						+ "("+arg[2]+",'"+arg[3]+"','"+arg[4]+"','"+arg[5]+"',NOW())";
			}else if(current==1){
				ressql = "UPDATE move_code_table set number_plate "
						+ "= '"+arg[3]+"',phone= '"+arg[4]+"',greetings='"+arg[5]+"',move_time=NOW() "
						+ "WHERE id = "+arg[6];
			}
			break;
		}
		return ressql;
	}

	@Override
	public String modSqlface(int current, String[] arg) throws SQLException, IOException {
		// TODO Auto-generated method stub
		switch (arg[1]) {
		case "release_delete":
			if(current==0){
				ressql="delete from release_table  where id="+arg[2];
			}
			break;	
		default:
			break;
		}
		return ressql;
	}

	@Override
	public String deleteSqlface(String[] arg) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String searchSqlface(int current, String[] arg) throws SQLException, IOException {
		switch(arg[1]){
		
			case "yuqi":
			if(current==0){
				ressql = "select overdue_ratio from set_table where id=1";
			}else if(current==1){
				ressql = "select price from release_table where id='"+arg[2]+"'";
			}
			break;
		
		 case "wxlogin_1":
         	if(current==0){
         		ressql="select * from user_data where openid='"+arg[3]+"'";	
         	}else if(current==2){
         		//ressql = "insert into ";
         	}
         	break;
		
		
		
		
		case "release_del":
			if(current==0){
				ressql = "select send_time from set_table";
			}
			break;
		
		
		
		
		case "user_search":
			if(current==0){
				ressql = "SELECT * FROM use_date";
			}
			break;
		case "shourumingxi":
			if(current==0){
				ressql = "select count(*) from release_table where user_id='"+arg[2]+"' and res_msg='success'";
			}else if(current==1){
				ressql = "select * from release_table where  user_id='"+arg[2]+"' and res_msg='success' order by time desc limit "+arg[3]+","+JyHelpManager.item+"";
			}
		    break;
		     
		case "zhichumingxi":
			if(current==0){
				ressql = "select count(*) from release_table where payuser_id='"+arg[2]+"' and res_msg='success'";
			}else if(current==1){
				ressql = "select * from release_table where  payuser_id='"+arg[2]+"' and res_msg='success' order by time desc limit "+arg[3]+","+JyHelpManager.item+"";
			}
	        break;
	         
		case "youjikalist":
			if(current==0){
				ressql = "select count(*) from apply_move_code_table where user_id='"+arg[2]+"' ";
			}else if(current==1){
				ressql = "select * from apply_move_code_table where  user_id='"+arg[2]+"'  order by id desc limit "+arg[3]+","+JyHelpManager.item+"";
			}
	        break;    
	        
	        
	        
		case "nuochemingxi":
			if(current==0){
				ressql = "select count(*) from nuoche_details where user_id='"+arg[2]+"'";
			}else if(current==1){
				ressql = "select * from nuoche_details where user_id = '"+arg[2]+"' order by time desc limit "+arg[3]+","+JyHelpManager.item+"";
			}
			break;
		case "say_art_search":
			if(current==0){
				ressql = "SELECT COUNT(*) FROM say_art_table as a,user_data as b WHERE b.id = a.update_id and status='已通过'";
			}else{
				ressql = "SELECT * FROM say_art_table as a,user_data as b WHERE b.id = a.update_id and status='已通过' order by update_time desc limit "+arg[3]+","+JyHelpManager.item+"";
			}
			break;
		case "apply_move_code_search":
			if(current ==0){
				ressql = "SELECT * FROM apply_move_code_table "
						+ "WHERE code_id ="+arg[3]+" and user_id = "+arg[2];
			}
		}
		return ressql;
	}

}
