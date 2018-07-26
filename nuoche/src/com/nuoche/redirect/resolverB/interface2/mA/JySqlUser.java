package com.nuoche.redirect.resolverB.interface2.mA;

import java.sql.SQLException;

import org.apache.taglibs.standard.lang.jstl.test.Bean1;

import com.nuoche.classroot.interface2.NuocheSqlMFace;
import com.nuoche.classroot.interface2.NuocheSqlManager;
import com.nuoche.classroot.interface4.JyHelpManager;
import com.nuoche.classroot.interface4.JyLogDetect.DataType;

public class JySqlUser extends NuocheSqlManager implements NuocheSqlMFace {
	@Override
	public String addSqlface(int current, String[] arg) throws SQLException {

		switch (arg[1]) {
		case "agentadd":
			ressql = "insert into agent_list (agentname,agentphone,agent_wx,agent_email,agent_address) values('"+arg[2]+"','"+arg[3]+"','"+arg[4]+"','"+arg[5]+"','"+arg[6]+"')";
			break;	



		}
		log.send(DataType.specialType, "01076", arg[1], ressql);
		return ressql;
	}

	@Override
	public String deleteSqlface(String[] arg) throws SQLException {

		switch (arg[1]) {

		}

		return ressql;
	}

	@Override
	public String modSqlface(int current, String[] arg) throws SQLException {

		switch (arg[1]) {
		case "batchcard_mod":
			if(current==0){
				ressql = "update batchcard_info set car_number='"+arg[3]+"',car_phone='"+arg[4]+"',car_text='"+arg[5]+"',user_openid='"+arg[6]+"',card_status=1 where card_num='"+arg[2]+"'";
			}else if(current==1){
				ressql = "update batchcard_list set abled_num=abled_num+1 where batch_num='"+arg[0]+"'";
			}else{
				ressql = "select batchcard_num from batchcard_info where card_num='"+arg[2]+"'";
			}

			break;	

		case "batchcard_delete":
			if(current==0){
				ressql = "update batchcard_info set car_number='',car_phone='',car_text='',user_openid='',card_status=0 where card_num='"+arg[2]+"'";
			}else if(current==1){
				ressql = "update batchcard_list set abled_num=abled_num-1 where batch_num='"+arg[0]+"'";
			}else{
				ressql = "select batchcard_num from batchcard_info where card_num='"+arg[2]+"'";
			}

			break;	
		case "qchewei_shifang":
			if(current==0){
				ressql = "update release_table set payuser_id=0,click=0,paymoney='' where id='"+arg[2]+"'";
			}
			break;	

		case "qchewei_tuikuan":
			if(current==0){
				ressql = "select * from  release_table where id='"+arg[2]+"'";
			}
			break;		

		case "yiyuding":
			ressql = "select b.head_photo,a.place,a.nowphone,a.starting_time,a.id FROM release_table a INNER JOIN user_data b ON a.user_id=b.id WHERE b.id=(SELECT user_id FROM release_table WHERE payuser_id='"+arg[2]+"') and payuser_id='"+arg[2]+"'";
			break;
		case "qchewei_mod":
			if(current==0){
				ressql = "update release_table set payuser_id='"+arg[2]+"',order_status=1,click=1 where id='"+arg[3]+"'";
			}else if (current==4) {
				ressql = "update release_table set starting_time='"+arg[0]+"' where id='"+arg[3]+"'";
			}else if(current==2){
				ressql = "select click,end_timef,price from release_table where id='"+arg[3]+"' and cw_states=0";
			}else if(current==3){
				ressql = "select (select head_photo from user_data where id=71) as head_photo,nowphone,place,starting_time from release_table where payuser_id="+arg[2];
			}else {
				ressql = "select count(*) from release_table where click=1 and cw_states=0 and payuser_id='"+arg[2]+"'";
			}
			break;	
		case "changqi":
			if(current==0) {
				ressql = "select * from release_table where payuser_id='"+arg[2]+"' and id='"+arg[3]+"'";
			} else {
				ressql = "select * from release_table where id='"+arg[3]+"'";
			}
			break;
		case "quxiao":
			if(current==0) {
				ressql = "update release_table set starting_time=0,click=0,payuser_id=0,cw_states=0,order_status=0,paymoney='' where id='"+arg[3]+"' and payuser_id='"+arg[2]+"' ";
			}else {
				ressql = "select count(*) from release_table where id='"+arg[3]+"' and payuser_id='"+arg[2]+"' and order_status=1";
			}
			break;
		case "linshi":
			ressql = "select click,starting_time,price from release_table where id='"+arg[3]+"' and cw_states=0";
			break;
		case"fukuan_wancheng":
			ressql ="update release_table set starting_time=0,click=0,payuser_id=0,cw_states=0,order_status=0,paymoney='' where id='"+arg[3]+"' and payuser_id='"+arg[2]+"' ";;
			break;
		case "fukuan":
			if(current==0){
				ressql = "update release_table set payuser_id='"+arg[2]+"',order_status=1,click=1,paymoney='"+arg[0]+"',starting_time='"+arg[4]+"' where id='"+arg[3]+"'";
			}else if(current==2){
				ressql = "select click,end_timef,price from release_table where id='"+arg[3]+"' and cw_states=0";
			}else if(current==3){
				ressql = "select (select head_photo from user_data where id=71) as head_photo,nowphone,place from release_table where payuser_id="+arg[2];
			}else {
				ressql = "select count(*) from release_table where state=2 and id="+arg[3];
			}
			break;	

		}
		log.send(DataType.specialType, "01076", arg[1], ressql);
		return ressql;
	}

	@Override
	public String searchSqlface(int current, String[] arg) throws SQLException {

		switch (arg[1]) {

		case "search_article_list":
			ressql = "select id,name,img from article where type='"+arg[2]+"'";
			break;
		case "search_article_info":
			ressql = "select * from article where id = '" + arg[2] + "' ";
			break;	

		case "ad_show":
			ressql = "select isshow from ad_show where id =1";
			break;		

		case "chewei_pay":
			ressql = "select * from release_table as a ,user_data as b  where a.id ="+arg[2]+" and a.payuser_id=b.id";
			break;	

		case "isbangding":
			ressql ="select * from batchcard_info as a ,user_data as b  where  a.user_openid='"+arg[2]+"' and a.user_openid=b.openid";
			break;	

		case "userinfo":
			if(current==0){
				ressql ="select * from batchcard_info as a ,user_data as b  where  a.user_openid='"+arg[2]+"' and a.user_openid=b.openid";
			}else{
				ressql ="select * from user_data   where openid='"+arg[2]+"' ";
			}

			break;		

		case "withdraw_up":
			if(current==0){
				ressql ="select count(*) as num ,sum(paymoney) as totlemoney,MAX(id) as maxid from release_table   where user_id="+arg[2] +" and withdraw_status='可提现'";
			}
			break;	

		case "withdraw_apply":
			if(current==0){
				ressql ="select count(*) as num ,sum(paymoney) as totlemoney from release_table   where user_id="+arg[2] +" and withdraw_status='可提现'";
			}else if(current==1){
				ressql ="update  release_table set withdraw_status='申请中',withdraw_id="+arg[0]+"  where user_id="+arg[2] +" and withdraw_status='可提现'";
			}else if(current==2){
				ressql ="insert into withdraw_list (user_id,order_nums,withdraw_money,withdraw_status) values ('"+arg[2]+"','"+arg[0]+"','"+arg[3]+"','申请中')";
			}else{
				ressql ="select MAX(id) as maxid from withdraw_list where user_id="+arg[2]+" and withdraw_status='申请中'";
			}
			break;	

		}

		log.send(DataType.specialType, "01076", arg[1], ressql);
		return ressql;
	}

}
