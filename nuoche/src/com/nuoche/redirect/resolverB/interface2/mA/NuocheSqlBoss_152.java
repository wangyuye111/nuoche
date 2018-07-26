package com.nuoche.redirect.resolverB.interface2.mA;

import java.io.IOException;
import java.sql.SQLException;

import com.nuoche.classroot.interface2.NuocheSqlMFace;
import com.nuoche.classroot.interface2.NuocheSqlManager;
import com.nuoche.classroot.interface4.JyHelpManager;






public class NuocheSqlBoss_152  extends NuocheSqlManager implements NuocheSqlMFace {
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
		case "mod_duanxin":
			if(current==0){
				ressql = "update message_table set content1='"+arg[3]+"' where id='1'";
			}else if(current==1){
				ressql = "update message_table set content2='"+arg[3]+"' where id='1'";
			}else if(current==2){
				ressql = "update message_table set content3='"+arg[3]+"' where id='1'";
			}else if(current==3){
				ressql = "update message_table set content4='"+arg[3]+"' where id='1'";
			}
			break;	
		
		
		}
		return ressql;
	}

	@Override
	public String deleteSqlface(String[] arg) throws SQLException {
	
		switch (arg[1]) {
		
	     }
		
		return ressql;
	}

	@Override
	public String searchSqlface(int current, String[] arg) throws SQLException, IOException {
		switch(arg[1]){
		
		//抢车位明细
		case "qiangcheweimingxi":
			if(current==0){
				//起始时间、结束时间为空，查询信息数量
				if(arg[3].equals("")&&arg[4].equals("")){
					//ressql="select count(*) from cash_withdrawl c,user_data u where c.user_id=u.id and c.c_type="+arg[7];
					ressql="select count(*) from robbing_table a,user_data b where a.user_id=b.id";
				}else{
					//ressql="select count(*) from cash_withdrawl c,user_data u where c.user_id=u.id and c.time between '"+arg[3]+" 00:00:01' and '"+arg[4]+" 23:59:59' and c.c_type="+arg[7];
					ressql = "select count(*) from robbing_table a,user_data b where a.user_id=b.id and a.starttime between '"+arg[3]+" 00:00:01' and '"+arg[4]+" 23:59:59'";
				}
			}else if(current==1){
				//起始时间、结束时间为空，查询信息
				if(arg[3].equals("")&&arg[4].equals("")){
				   //ressql="select * from cash_withdrawl c,user_data u where c.user_id=u.id  and c.c_type="+arg[7]+" order by c.time desc  limit "+arg[2]+","+JyHelpManager.item+" ";	
				   ressql = "select a.nickname,b.*,c.nickname as nicknames from user_data a,robbing_table b,user_data c where a.id = b.user_id and b.qiang_id=c.id order by b.starttime desc limit "+arg[2]+","+JyHelpManager.item+"";
				}else{
					//ressql="select * from cash_withdrawl c,user_data u where c.user_id=u.id and c.time between '"+arg[3]+" 00:00:01' and '"+arg[4]+" 23:59:59'  and c.c_type="+arg[7]+" order by c.time desc limit "+arg[2]+","+JyHelpManager.item+" ";
					ressql = "select a.nickname,b.*,c.nickname as nicknames from user_data a,robbing_table b,user_data c where a.id = b.user_id and b.qiang_id=c.id and b.starttime between '"+arg[3]+" 00:00:01' and '"+arg[4]+" 23:59:59' order by b.starttime desc limit "+arg[2]+","+JyHelpManager.item+"";
				}
			}else if(current==2){
				//起始时间、结束时间为空，计算总数
				if(arg[3].equals("")&&arg[4].equals("")){
				   //ressql="select sum(cash) from cash_withdrawl  where c_type="+arg[7]+"";	
					ressql ="select sum(price) from robbing_table";
				}else{
					//ressql="select sum(cash) from cash_withdrawl where    c_type="+arg[7]+" and  time between '"+arg[3]+" 00:00:01' and '"+arg[4]+" 23:59:59'";
					ressql = "select sum(price) from robbing_table where starttime between '"+arg[3]+" 00:00:01' and '"+arg[4]+" 23:59:59'";
				}
			}else if(current==3){
				//用户选中月或者年，进行查询信息数量
				//ressql="select count(*) from cash_withdrawl c,user_data u where c.user_id=u.id  and c.c_type="+arg[7]+" and c.time like '%"+arg[6]+"%' ";
				ressql = "select count(*) from robbing_table a,user_data b where a.user_id=b.id and a.starttime like '%"+arg[6]+"%'";
			}else if(current==4){
				//用户选中月或者年，进行查找信息
				//ressql="select * from cash_withdrawl c,user_data u where c.user_id and u.id  and c.c_type="+arg[7]+" and c.time like '%"+arg[6]+"%' order by c.time desc  limit "+arg[2]+","+JyHelpManager.item+"";
				ressql = "select a.nickname,b.*,c.nickname as nicknames from user_data a,robbing_table b,user_data c where a.id = b.user_id and b.qiang_id=c.id and b.starttime like '%"+arg[6]+"%' order by b.starttime desc limit "+arg[2]+","+JyHelpManager.item+"";
			}else if(current==5){
				//用户选中月或者年，计算总数
				//ressql="select sum(cash) from cash_withdrawl where  c_type="+arg[7]+" and time like '%"+arg[6]+"%'";
				ressql = "select sum(price) from robbing_table where starttime like '%"+arg[6]+"%'";
			}
			break;
		
		
		
		
		case "duanxin_seach":
			if(current==0){
				ressql = "select * from message_table where id='1'";
			}
		break;
		case"sefabunuoche":
			if(current==0){
				ressql="select count(0) from release_table where place like '%"+arg[4]+"%'";
			}
			else if(current==1){
				ressql = "select R.*,U.* from release_table as R left join user_data as U on R.User_id =U.id where R.place like '%"+arg[4]+"%' order by time desc limit "+arg[5]+","+JyHelpManager.item+"";	
			}
			else if(current==2){
				ressql="select count(0) from release_table";
			}
			else if(current==3){
				ressql = "select R.*,U.* from release_table as R left join user_data as U on R.User_id =U.id order by time desc limit "+arg[5]+","+JyHelpManager.item+"";
			/*
			 * Use_date 	用户表    Id Nicknam 姓名   Head_photo头像
			 * 
	 		Release_table		车位发布表
			Id
			User_id	用户id
			Time		时间      date(defualt CURRENT_TIMESTAMP)
			Place		地点
			Price		价格
			Type		类型(转让车位，职业占位)
			States		状态（暂放）
			Longitude	经度
			Latitude	纬度
			 */
			}
		break;
		
		
		
		}
		return ressql;
	}

}
