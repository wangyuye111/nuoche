package com.nuoche.redirect.resolverB.interface2.mA;

import java.io.IOException;
import java.sql.SQLException;

import com.nuoche.classroot.interface2.NuocheSqlMFace;
import com.nuoche.classroot.interface2.NuocheSqlManager;
import com.nuoche.classroot.interface4.HelpManager;
import com.nuoche.classroot.interface4.JyHelpManager;
//import com.ssctrl.interface4.HelpManager;






public class NuocheSqlBoos_A_01168  extends NuocheSqlManager implements NuocheSqlMFace {
	@Override
	public String addSqlface(int current, String[] arg) throws SQLException,IOException {


		switch (arg[1]) {
		
		}
		return ressql;
	}

	@Override
	public String modSqlface(int current, String[] arg) throws SQLException, IOException {
		// TODO Auto-generated method stub
		switch (arg[1]) {
		case "album_checknopass":
   			/*ressql = "update say_art_table set status='未通过',refusal='"+arg[4]+"' where id='" + arg[2]
   					+ "'";*/
			ressql = "update say_art_table set status='未通过',refusal='"+arg[4]+"' where id='"+arg[2]+"' and update_id='"+arg[3]+"'";
   			break;
		case "album_checkpass":
			/*ressql = "update say_art_table set status='已通过',refusal='"+arg[4]+"' where id='" + arg[2]
   					+ "'";*/
			ressql = "update say_art_table set status='已通过' where id='"+arg[2]+"' and update_id='"+arg[3]+"'";
			break;
		case "card_mod":
			if(current==0){
				//ressql="UPDATE cash_set SET cash_onefee='"+arg[3]+"' WHERE id='1'";
				ressql = "update set_table set card_price='"+arg[3]+"' where id='1'";
			}else if(current==1){
				//ressql="UPDATE cash_set SET agent_fee='"+arg[3]+"' WHERE id='1'";
				ressql = "update set_table set red_packet='"+arg[3]+"' where id='1'";
			}else if(current==2){
				//ressql="UPDATE cash_set SET agent_fee='"+arg[3]+"' WHERE id='1'";
				ressql = "update set_table set agency_price='"+arg[3]+"' where id='1'";
			}
			break;
		case "mod_bili":
			if(current==0){
				//ressql="UPDATE cash_set SET cash_onefee='"+arg[3]+"' WHERE id='1'";
				ressql = "update set_table set cash_ratio='"+arg[3]+"' where id='1'";
			}else if(current==1){
				//ressql="UPDATE cash_set SET agent_fee='"+arg[3]+"' WHERE id='1'";
				ressql = "update set_table set overdue_ratio='"+arg[3]+"' where id='1'";
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
		case "tixian_seach":
			if(current==0){
				ressql = "select * from set_table where id='1'";
			}
			break;
		case "jiage_seach":
			if(current==0){
				ressql = "select * from set_table where id='1'";
			}
			break;
		
		//收入明细
		case "shourumingxi":
			if(current==0){
				if(arg[3].equals("")&&arg[4].equals("")){
					//ressql="select count(*) from cash_withdrawl c,user_data u where c.user_id=u.id and c.c_type="+arg[7];
					ressql="select count(*) from income_details a,user_data b where a.user_id=b.id";
				}else{
					//ressql="select count(*) from cash_withdrawl c,user_data u where c.user_id=u.id and c.time between '"+arg[3]+" 00:00:01' and '"+arg[4]+" 23:59:59' and c.c_type="+arg[7];
					ressql = "select count(*) from income_details a,user_data b where a.user_id=b.id and a.time between '"+arg[3]+" 00:00:01' and '"+arg[4]+" 23:59:59'";
				}
			}else if(current==1){
				if(arg[3].equals("")&&arg[4].equals("")){
				   //ressql="select * from cash_withdrawl c,user_data u where c.user_id=u.id  and c.c_type="+arg[7]+" order by c.time desc  limit "+arg[2]+","+JyHelpManager.item+" ";	
				   ressql = "select * from income_details a,user_data b where a.user_id=b.id order by a.time desc limit "+arg[2]+","+JyHelpManager.item+"";
				}else{
					//ressql="select * from cash_withdrawl c,user_data u where c.user_id=u.id and c.time between '"+arg[3]+" 00:00:01' and '"+arg[4]+" 23:59:59'  and c.c_type="+arg[7]+" order by c.time desc limit "+arg[2]+","+JyHelpManager.item+" ";
					ressql = "select * from income_details a,user_data b where a.user_id=b.id and a.time between '"+arg[3]+" 00:00:01' and '"+arg[4]+" 23:59:59' order by a.time desc limit "+arg[2]+","+JyHelpManager.item+"";
				}
			}else if(current==2){
				if(arg[3].equals("")&&arg[4].equals("")){
				   //ressql="select sum(cash) from cash_withdrawl  where c_type="+arg[7]+"";	
					ressql ="select sum(money) from income_details";
				}else{
					//ressql="select sum(cash) from cash_withdrawl where    c_type="+arg[7]+" and  time between '"+arg[3]+" 00:00:01' and '"+arg[4]+" 23:59:59'";
					ressql = "select sum(money) from income_details where time between '"+arg[3]+" 00:00:01' and '"+arg[4]+" 23:59:59'";
				}
			}else if(current==3){
				//ressql="select count(*) from cash_withdrawl c,user_data u where c.user_id=u.id  and c.c_type="+arg[7]+" and c.time like '%"+arg[6]+"%' ";
				ressql = "select count(*) from income_details a,user_data b where a.user_id=b.id and a.time like '%"+arg[6]+"%'";
			}else if(current==4){
				//ressql="select * from cash_withdrawl c,user_data u where c.user_id and u.id  and c.c_type="+arg[7]+" and c.time like '%"+arg[6]+"%' order by c.time desc  limit "+arg[2]+","+JyHelpManager.item+"";
				ressql = "select * from income_details a,user_data b where a.user_id=b.id and a.time like '%"+arg[6]+"%' order by a.time desc limit "+arg[2]+","+JyHelpManager.item+"";
			}else if(current==5){
				//ressql="select sum(cash) from cash_withdrawl where  c_type="+arg[7]+" and time like '%"+arg[6]+"%'";
				ressql = "select sum(money) from income_details where time like '%"+arg[6]+"%'";
			}
			break;
		
		case "zhichumingxi":
			if(current==0){
				//起始时间、结束时间为空，查询信息数量
				if(arg[3].equals("")&&arg[4].equals("")){
					//ressql="select count(*) from cash_withdrawl c,user_data u where c.user_id=u.id and c.c_type="+arg[7];
					ressql="select count(*) from income_details a,user_data b where a.user_id=b.id";
				}else{
					//ressql="select count(*) from cash_withdrawl c,user_data u where c.user_id=u.id and c.time between '"+arg[3]+" 00:00:01' and '"+arg[4]+" 23:59:59' and c.c_type="+arg[7];
					ressql = "select count(*) from income_details a,user_data b where a.user_id=b.id and a.time between '"+arg[3]+" 00:00:01' and '"+arg[4]+" 23:59:59'";
				}
			}else if(current==1){
				//起始时间、结束时间为空，查询信息
				if(arg[3].equals("")&&arg[4].equals("")){
				   //ressql="select * from cash_withdrawl c,user_data u where c.user_id=u.id  and c.c_type="+arg[7]+" order by c.time desc  limit "+arg[2]+","+JyHelpManager.item+" ";	
				   ressql = "select * from income_details a,user_data b where a.user_id=b.id order by a.time desc limit "+arg[2]+","+JyHelpManager.item+"";
				}else{
					//ressql="select * from cash_withdrawl c,user_data u where c.user_id=u.id and c.time between '"+arg[3]+" 00:00:01' and '"+arg[4]+" 23:59:59'  and c.c_type="+arg[7]+" order by c.time desc limit "+arg[2]+","+JyHelpManager.item+" ";
					ressql = "select * from income_details a,user_data b where a.user_id=b.id and a.time between '"+arg[3]+" 00:00:01' and '"+arg[4]+" 23:59:59' order by a.time desc limit "+arg[2]+","+JyHelpManager.item+"";
				}
			}else if(current==2){
				//起始时间、结束时间为空，计算总数
				if(arg[3].equals("")&&arg[4].equals("")){
				   //ressql="select sum(cash) from cash_withdrawl  where c_type="+arg[7]+"";	
					ressql ="select sum(money) from income_details";
				}else{
					//ressql="select sum(cash) from cash_withdrawl where    c_type="+arg[7]+" and  time between '"+arg[3]+" 00:00:01' and '"+arg[4]+" 23:59:59'";
					ressql = "select sum(money) from income_details where time between '"+arg[3]+" 00:00:01' and '"+arg[4]+" 23:59:59'";
				}
			}else if(current==3){
				//用户选中月或者年，进行查询信息数量
				//ressql="select count(*) from cash_withdrawl c,user_data u where c.user_id=u.id  and c.c_type="+arg[7]+" and c.time like '%"+arg[6]+"%' ";
				ressql = "select count(*) from income_details a,user_data b where a.user_id=b.id and a.time like '%"+arg[6]+"%'";
			}else if(current==4){
				//用户选中月或者年，进行查找信息
				//ressql="select * from cash_withdrawl c,user_data u where c.user_id and u.id  and c.c_type="+arg[7]+" and c.time like '%"+arg[6]+"%' order by c.time desc  limit "+arg[2]+","+JyHelpManager.item+"";
				ressql = "select * from income_details a,user_data b where  a.user_id=b.id and a.time like '%"+arg[6]+"%' order by a.time desc limit "+arg[2]+","+JyHelpManager.item+"";
			}else if(current==5){
				//用户选中月或者年，计算总数
				//ressql="select sum(cash) from cash_withdrawl where  c_type="+arg[7]+" and time like '%"+arg[6]+"%'";
				ressql = "select sum(money) from income_details where time like '%"+arg[6]+"%'";
			}
			break;
		
		
		
		
		
		
		
		
		
		
		
		case "user_list":
			if(current==0){
				ressql = "select count(*) from user_data where id="+arg[4]+"";
			}else if(current==1){
				//ressql = "select * from user_data where id='"+arg[4]+"' limit "+arg[3]+","+HelpManager.item;
				ressql = "select * from user_data where id="+arg[4]+" limit "+arg[5]+","+HelpManager.item;
			}else if(current==2){
				ressql = "select count(*) from user_data";
			}else if(current==3){
				ressql = "select * from user_data limit "+arg[5]+","+HelpManager.item;
			}else if(current==5){
				ressql = "select * from batchcard_info where user_openid='"+arg[0]+"'";
			}
			break;
		
		
		case "nuoche_card":
			if(current==0){
				ressql = "select count(*) from move_code_table";
			}else if(current==1){
				//ressql = "select * from move_code_table limit "+arg[5]+","+HelpManager.item;
				ressql = "select a.id as ids,a.nickname,b.* from user_data a,move_code_table b where a.id=b.user_id limit "+arg[5]+","+HelpManager.item;
			}else if(current==2){
				ressql = "select count(*) from move_code_table where id = '"+arg[4]+"'";
			}else if(current==3){
				ressql = "select a.id as ids,a.nickname,b.* from user_data a,move_code_table b where a.id=b.user_id and a.id='"+arg[4]+"' limit "+arg[5]+","+HelpManager.item;
			}
			break;
		//申请挪车码	
		case "apply_card":
			if(current==0){
				ressql = "select count(*) from apply_move_code_table where user_id ="+arg[4];
			}else if(current==1){
				ressql = "select * from apply_move_code_table where user_id = '"+arg[4]+"' order by id desc  limit "+arg[5]+","+HelpManager.item;
			}else if(current==2){
				ressql = "select count(*) from apply_move_code_table";
			}else if(current==3){
				ressql = "select * from apply_move_code_table  order by id desc  limit "+arg[5]+","+HelpManager.item;
			}
			break;
			
		case "apply_card_youji":
			ressql = "update apply_move_code_table set mail_status='已邮寄',send_number='"+arg[3]+"' where id="+arg[2];
			break;
			
			
		//发布中的信息	
		case "fabu":
			if(current==0){
				ressql = "select count(*) from release_table and user_id = "+arg[4]+"";
			}else if(current==1){
				//ressql = "select * from release_table limit "+arg[3]+","+HelpManager.item;
				/*******************************
				 * 明确字段后修改a.*
				 ******************************/
				ressql = "select a.*,b.* from user_data a,release_table b where a.id=b.user_id and a.id="+arg[4]+" limit "+arg[3]+","+HelpManager.item;
			}
			break;
		//动态	
		case "dynamic":
			if (current == 0) {
				ressql = "select count(*) from say_art_table ";
			} else if (current == 1) {
				ressql = "select a.nickname,b.* from user_data a,say_art_table b where a.id=b.update_id  order by id desc limit " + arg[2] + ","
						+ JyHelpManager.item + "";
			} else if (current == 2) {
				ressql = "select count(*) from say_art_table where status ='"
						+ arg[3] + "'";
			} else if (current == 3) {
				ressql = "select a.nickname,b.* from user_data a,say_art_table b where a.id=b.update_id and status='" + arg[3]
						+ "' order by id desc limit " + arg[2] + "," + JyHelpManager.item + "";
			}
			
			
			break;
		//动态中的图片	
		case "album_list":
			ressql = "select * from say_art_table where id='" + arg[2] + "'";
			break;
			
		}
		return ressql;
	}

}
