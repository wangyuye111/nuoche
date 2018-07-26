package com.nuoche.redirect.resolverB.interface2.mA;

import java.io.IOException;
import java.sql.SQLException;

import com.nuoche.classroot.interface2.NuocheSqlMFace;
import com.nuoche.classroot.interface2.NuocheSqlManager;
import com.nuoche.classroot.interface4.JyHelpManager;






public class NuocheSqlUser_152  extends NuocheSqlManager implements NuocheSqlMFace {
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
		case "fukuanfail":
			if(current==0){
				ressql = "update release_table set set_top = '0' , click = '0' where id = '"+arg[2]+"'";
			}
			break;

		}
		return ressql;
	}

	@Override
	public String deleteSqlface(String[] arg) throws SQLException {

		switch (arg[1]) {
		case "fukuansuccess":

			ressql = "delete from release_table where id = '"+arg[2]+"'";
			break;
		}

		return ressql;
	}

	@Override
	public String searchSqlface(int current, String[] arg) throws SQLException, IOException {
		switch(arg[1]){

		case "user_move_search":
			if(current==0){
				//查询用户最后生成的一张二维码
				ressql = "select a.nickname,a.head_photo,b.code_photo from user_data a,move_code_table b "
						+ "where a.id=b.user_id and a.id='"+arg[2]+"' order by b.id  limit 0,1";
			}
			break;


		case "shourumingxi":
			if(current==0){
				ressql = "select count(*) from income_details where user_id='"+arg[2]+"'";
			}else if(current==1){
				ressql = "select * from income_details where user_id='"+arg[2]+"' order by time  limit "+arg[3]+","+JyHelpManager.item+"";
			}
			break;

		case "zhichumingxi":
			if(current==0){
				ressql = "select count(*) from pay_details where user_id='"+arg[2]+"'";
			}else if(current==1){
				ressql = "select * from pay_details where user_id='"+arg[2]+"' order by time  limit "+arg[3]+","+JyHelpManager.item+"";
			}
			break;

		case "nuochemingxi":
			if(current==0){
				ressql = "select count(*) from nuoche_details where user_id='"+arg[2]+"'";
			}else if(current==1){
				ressql = "select * from nuoche_details where user_id = '"+arg[2]+"' order by time  limit "+arg[3]+","+JyHelpManager.item+"";
			}
			break;

		case "qiangchewei":
			/*if(current==0){
				ressql = "select * from release_table where user_id != '"+arg[2]+"'";
			}else if(current==1){
				ressql = "update release_table set set_top = '1' , click = '1' where id = '"+arg[3]+"' and user_id = '"+arg[2]+"'";
			}else if(current==2){
				ressql = "insert into robbing_table (user_id,qiang_id,starttime,status) values ('"+arg[3]+"','"+arg[2]+"',now(),'待付款')";
			}*/
			/****
			 * 问题
			 * 需要进行判断,别人点击抢后的数据,不要再进行查询
			 * arg[2],arg[3]的值需要进行检查修改
			 */
			if(current==0){
				ressql = "select * from release_table";
			}else if(current==1){
				ressql = "update release_table set set_top = '1' , click = '1' where id = '"+arg[3]+"' and user_id = '"+arg[2]+"'";
			}else if(current==2){
				ressql = "insert into robbing_table (user_id,qiang_id,price,starttime,status,end_timeq) values ('"+arg[2]+"','"+arg[3]+"','"+arg[4]+"',now(),'待付款','"+arg[0]+"')";
			}else if(current==10){
				/***************
				 * 01168添加,有问题可修改或询问
				 */
				//查询设置表中信息
				ressql = "select rush_time from set_table where id=1";
			}else if(current==11){
				//不需要固定任何表,只是为了计算“当前系统时间+抢单结束时间”的值
				ressql = "select DATE_ADD(now(),INTERVAL '"+arg[0]+"' MINUTE) as endtimes from robbing_table where id=1";
			}

			break;

			/*case "jiqiuchewei":
			if(current==0){
				ressql = "select count(*) from release_table";
			}else if(current==1){
				ressql = "select * from release_table order by time  limit "+arg[3]+","+JyHelpManager.item+"";
				//ressql = "select address,price,type";
			}else if(current==2){
				ressql = "select * from user_data where id ="+arg[2];
			}
			break;*/
		case "jiqiuchewei":
			if(current==0){
				/**
				 * 暂时取消范围寻找
				 */
				ressql = "select count(*) from release_table  where state="+arg[6]+" and click=0 and cw_states=0 and order_status=0 ";
				//ressql = "select count(*) from release_table  where state="+arg[6]+" and click=0 and cw_states=0  and latitude between '"+arg[10]+"' and '"+arg[11]+"' and longitude between '"+arg[12]+"' and '"+arg[13]+"' and order_status=0 ";
			}else if(current==1){
				if (Integer.parseInt(arg[7])==1) {
					/**
					 * 修改通过经纬度计算距离方法；
					 * 144-176
					 */
					ressql = "select *, ROUND( 6378.138 * 2 * ASIN( SQRT( POW( SIN( ( '"+arg[4]+"' * PI() / 180 - latitude * PI() / 180 ) / 2 ), 2 ) + COS('"+arg[4]+"' * PI() / 180) * COS(latitude * PI() / 180) * POW( SIN( ( '"+arg[5]+"' * PI() / 180 - longitude * PI() / 180 ) / 2 ), 2 ) ) )* 1000 ) AS juli from release_table  where state="+arg[6]+" and click=0 and cw_states=0 and price between 0 and 5 and order_status=0 order by price,juli  limit "+arg[3]+","+JyHelpManager.item+"";
					break;
				}else if (Integer.parseInt(arg[7])==2) {
					ressql = "select *, ROUND( 6378.138 * 2 * ASIN( SQRT( POW( SIN( ( '"+arg[4]+"' * PI() / 180 - latitude * PI() / 180 ) / 2 ), 2 ) + COS('"+arg[4]+"' * PI() / 180) * COS(latitude * PI() / 180) * POW( SIN( ( '"+arg[5]+"' * PI() / 180 - longitude * PI() / 180 ) / 2 ), 2 ) ) )* 1000 ) AS juli from release_table  where state="+arg[6]+" and click=0 and cw_states=0 and price between 5 and 10 and order_status=0 order by price desc, juli  limit "+arg[3]+","+JyHelpManager.item+"";
					break;
				}else if (Integer.parseInt(arg[7])==3) {
					ressql = "select *, ROUND( 6378.138 * 2 * ASIN( SQRT( POW( SIN( ( '"+arg[4]+"' * PI() / 180 - latitude * PI() / 180 ) / 2 ), 2 ) + COS('"+arg[4]+"' * PI() / 180) * COS(latitude * PI() / 180) * POW( SIN( ( '"+arg[5]+"' * PI() / 180 - longitude * PI() / 180 ) / 2 ), 2 ) ) )* 1000 ) AS juli from release_table  where state="+arg[6]+" and click=0 and cw_states=0 and price between 10 and 15 and order_status=0 order by price,juli  limit "+arg[3]+","+JyHelpManager.item+"";
					break;
				}else if (Integer.parseInt(arg[7])==4) {
					ressql = "select *, ROUND( 6378.138 * 2 * ASIN( SQRT( POW( SIN( ( '"+arg[4]+"' * PI() / 180 - latitude * PI() / 180 ) / 2 ), 2 ) + COS('"+arg[4]+"' * PI() / 180) * COS(latitude * PI() / 180) * POW( SIN( ( '"+arg[5]+"' * PI() / 180 - longitude * PI() / 180 ) / 2 ), 2 ) ) )* 1000 ) AS juli from release_table  where state="+arg[6]+" and click=0 and cw_states=0 and price between 15 and 20 and order_status=0 order by price,juli  limit "+arg[3]+","+JyHelpManager.item+"";
					break;
//				}else if (Integer.parseInt(arg[7])==5){
//					ressql = "select *, acos(cos("+arg[4]+"*pi()/180 )*cos(latitude*pi()/180)*cos("+arg[5]+"*pi()/180 -longitude*pi()/180)+sin("+arg[4]+"*pi()/180 )*sin(latitude*pi()/180))*6370996.81/1000  as juli from release_table  where state="+arg[6]+" and click=0 and cw_states=0 and price >20 and order_status=0 order by juli  limit "+arg[3]+","+JyHelpManager.item+"";
//					break;
				}
				if (Integer.parseInt(arg[8])==1) {
					ressql = "select * FROM (SELECT *, ROUND( 6378.138 * 2 * ASIN( SQRT( POW( SIN( ( '"+arg[4]+"' * PI() / 180 - latitude * PI() / 180 ) / 2 ), 2 ) + COS('"+arg[4]+"' * PI() / 180) * COS(latitude * PI() / 180) * POW( SIN( ( '"+arg[5]+"' * PI() / 180 - longitude * PI() / 180 ) / 2 ), 2 ) ) )* 1000 ) AS juli from release_table) t1  where juli between 0 and 1000 and state="+arg[6]+" and click=0 and cw_states=0  and order_status=0      order by juli  limit "+arg[3]+","+JyHelpManager.item+"";
					break;
				}else if (Integer.parseInt(arg[8])==2) {
					ressql = "select * FROM (SELECT *, ROUND( 6378.138 * 2 * ASIN( SQRT( POW( SIN( ( '"+arg[4]+"' * PI() / 180 - latitude * PI() / 180 ) / 2 ), 2 ) + COS('"+arg[4]+"' * PI() / 180) * COS(latitude * PI() / 180) * POW( SIN( ( '"+arg[5]+"' * PI() / 180 - longitude * PI() / 180 ) / 2 ), 2 ) ) )* 1000 ) AS juli from release_table) t1  where juli between 1000 and 3000 and state="+arg[6]+" and click=0 and cw_states=0  and order_status=0      order by juli  limit "+arg[3]+","+JyHelpManager.item+"";
					break;
				}else if (Integer.parseInt(arg[8])==3) {
					ressql = "select * FROM (SELECT *, ROUND( 6378.138 * 2 * ASIN( SQRT( POW( SIN( ( '"+arg[4]+"' * PI() / 180 - latitude * PI() / 180 ) / 2 ), 2 ) + COS('"+arg[4]+"' * PI() / 180) * COS(latitude * PI() / 180) * POW( SIN( ( '"+arg[5]+"' * PI() / 180 - longitude * PI() / 180 ) / 2 ), 2 ) ) )* 1000 ) AS juli from release_table) t1  where juli between 3000 and 5000 and state="+arg[6]+" and click=0 and cw_states=0  and order_status=0      order by juli  limit "+arg[3]+","+JyHelpManager.item+"";
					break;
				}else if (Integer.parseInt(arg[8])==4) {
					ressql = "select * FROM (SELECT *, ROUND( 6378.138 * 2 * ASIN( SQRT( POW( SIN( ( '"+arg[4]+"' * PI() / 180 - latitude * PI() / 180 ) / 2 ), 2 ) + COS('"+arg[4]+"' * PI() / 180) * COS(latitude * PI() / 180) * POW( SIN( ( '"+arg[5]+"' * PI() / 180 - longitude * PI() / 180 ) / 2 ), 2 ) ) )* 1000 ) AS juli from release_table) t1  where juli between 5000 and 10000 and state="+arg[6]+" and click=0 and cw_states=0  and order_status=0      order by juli  limit "+arg[3]+","+JyHelpManager.item+"";
					break;
				}
				if (arg[9]!=""||arg[9]!=null) {
					ressql = "select * FROM (SELECT *, ROUND( 6378.138 * 2 * ASIN( SQRT( POW( SIN( ( '"+arg[4]+"' * PI() / 180 - latitude * PI() / 180 ) / 2 ), 2 ) + COS('"+arg[4]+"' * PI() / 180) * COS(latitude * PI() / 180) * POW( SIN( ( '"+arg[5]+"' * PI() / 180 - longitude * PI() / 180 ) / 2 ), 2 ) ) )* 1000 ) AS juli from release_table) t1  where place like '%"+arg[9]+"%' and state="+arg[6]+" and click=0 and cw_states=0  and order_status=0      order by juli  limit "+arg[3]+","+JyHelpManager.item+"";
					break;
				}
				ressql = "select * FROM (SELECT *, ROUND( 6378.138 * 2 * ASIN( SQRT( POW( SIN( ( '"+arg[4]+"' * PI() / 180 - latitude * PI() / 180 ) / 2 ), 2 ) + COS('"+arg[4]+"' * PI() / 180) * COS(latitude * PI() / 180) * POW( SIN( ( '"+arg[5]+"' * PI() / 180 - longitude * PI() / 180 ) / 2 ), 2 ) ) )* 1000 ) AS juli from release_table   where state="+arg[6]+" and click=0 and cw_states=0 and order_status=0      order by juli  limit "+arg[3]+","+JyHelpManager.item+"";
				
			}else if(current==2){
				ressql = "select * from user_data where id ="+arg[2];
			}else if(current==3){
				ressql = "select send_time from set_table where id = 1";
			}else if(current==4){
				ressql = "delete from release_table where id = '"+arg[0]+"'";
			}
			/********************
			 * 01168添加,有问题可修改或询问
			 */
			else if(current==5){
				ressql = "select set_top from release_table where id='"+arg[2]+"'";
			}else if(current==6){
				ressql = "select set_top from release_table where id='"+arg[4]+"'";
			}else if(current==7){
				ressql = "select end_timef from release_table where id = '"+arg[4]+"'";
			}else if(current==8){
				ressql = "select end_timeq from robbing_table where id = '"+arg[4]+"'";
			}else if(current==9){
				ressql = "select *, acos(cos("+arg[4]+"*pi()/180 )*cos(latitude*pi()/180)*cos("+arg[5]+"*pi()/180 -longitude*pi()/180)+sin("+arg[4]+"*pi()/180 )*sin(latitude*pi()/180))*6370996.81/1000  as juli from release_table  where state="+arg[6]+" and click=0 and cw_states=0 and order_status=0 order by juli  limit "+arg[3]+","+JyHelpManager.item+"";

			}
			break;
		case "jiqiuchewei_01168":
			if(current==1){
				ressql = "select send_time from set_table where id=1";
			}else if(current==2){
				ressql = "select * from release_table  limit "+arg[3]+","+JyHelpManager.item+"";
			}
			break;


		case "code_search":
			if(current==0){
				ressql = "select * from move_code_table where id='"+arg[2]+"'";
			}
			break;


		}
		return ressql;
	}

}
