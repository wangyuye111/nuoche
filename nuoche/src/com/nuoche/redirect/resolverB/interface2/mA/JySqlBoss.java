package com.nuoche.redirect.resolverB.interface2.mA;

import java.sql.SQLException;

import com.nuoche.classroot.interface2.NuocheSqlMFace;
import com.nuoche.classroot.interface2.NuocheSqlManager;
import com.nuoche.classroot.interface4.JyHelpManager;
import com.nuoche.classroot.interface4.JyLogDetect.DataType;



public class JySqlBoss extends NuocheSqlManager implements NuocheSqlMFace {

	@Override
	public String addSqlface(int current,String[] arg) throws SQLException {

		switch (arg[1]) {
		//文章增加
		case "ArticleAdd":{
			ressql = "insert into article (name,referral,img,type) values ('"+ arg[2]
					+ "','"
					+ arg[5]
					+ "','"
					+ arg[3]
					+ "','"
					+ arg[4]
					+ "')";
	 		}
			
		break;
		
		case "batchcard_add":{
			ressql = "insert into batchcard_list (batch_num,start_num,sum_num,end_num,agent_id) values ('"+ arg[2]
					+ "','"
					+ arg[3]
					+ "','"
					+ arg[4]
					+ "','"
					+ arg[5]
					+ "','"
					+ arg[6]
					+ "')";
	 		}
			
		break;
		
		
		
		}
		log.send(DataType.specialType, "01066", arg[1], ressql);
		return ressql;
	}
	@Override
	public String deleteSqlface(String[] arg) throws SQLException {

		switch (arg[1]) {
		
		case "del_article":
			String w3 = "";
			for (int i = 2; i < arg.length; i++) {

				w3 = w3 + ",'" + arg[i] + "'";

			}
			ressql="delete from article where id in(" + w3.substring(1) + ")";
			break;
			
				
		}

		return ressql;
	}
	@Override
	public String modSqlface(int current, String[] arg) throws SQLException {
		String art="";
		switch (arg[1]) {
		//文章修改
		case "ArticleMove":
			ressql = "update article set name='"+arg[2]+"',referral='"+arg[5]+"',img='"+arg[3]+"',type='"+arg[4]+"' where id= '"+arg[6]+"'";
		break;
		}
		log.send(DataType.specialType, "01066", arg[1], ressql);
		return ressql;
	}
	@Override
	public String searchSqlface(int current, String[] arg) throws SQLException {
		switch (arg[1]) {
		
		case "agent_list":	
			if(current == -1){
				ressql = "select count(*) from agent_list";
			}else{
				//ressql = "select id,name,img,type from article  order by time  desc limit "+current+","+JyHelpManager.item;
				ressql = "select * from agent_list  order by id  desc limit "+current+","+JyHelpManager.item;
			}
	    break;
		
		case "batchcardinfo":	
			if(current == -1){
				ressql = "select count(*) from batchcard_info where batchcard_num='"+arg[2]+"'";
			}else{
				//ressql = "select id,name,img,type from article  order by time  desc limit "+current+","+JyHelpManager.item;
				ressql = "select * from batchcard_info  where batchcard_num='"+arg[2]+"'   order by card_status  desc limit "+current+","+JyHelpManager.item;
			}
	    break;
	    
	    
	    
		case "batch_addbf":	
			ressql = "select card_num from batchcard_info order by id  desc limit 0,1";
	    break;
		case "batchcard_down":	
			ressql = "select card_num from batchcard_info where batchcard_num='"+arg[2]+"'";
	    break;
	    
	    
	    
		case "batchcard_list":	
			if(current == -1){
				ressql = "select count(*) from batchcard_list";
			}else{
				//ressql = "select id,name,img,type from article  order by time  desc limit "+current+","+JyHelpManager.item;
				ressql = "select * from batchcard_list  order by id  desc limit "+current+","+JyHelpManager.item;
			}
	    break;
	    
		case "withdraw_list":	
			if(current == -1){
				ressql = "select count(*) from withdraw_list";
			}else{
				//ressql = "select id,name,img,type from article  order by time  desc limit "+current+","+JyHelpManager.item;
				ressql = "select * from withdraw_list  as a ,user_data as b where a.user_id=b.id order by a.id  desc limit "+current+","+JyHelpManager.item;
			}
	    break;
	    
		case "withdraw_info":	
			if(current == -1){
				ressql = "select count(*) from release_table where withdraw_id="+arg[2];
			}else{
				//ressql = "select id,name,img,type from article  order by time  desc limit "+current+","+JyHelpManager.item;
				ressql = "select * from release_table  where withdraw_id="+arg[2]+" order by id  desc limit "+current+","+JyHelpManager.item;
			}
	    break;
	    
		//文章列表
		case "ArticleSearch":	
			if(current == -1){
				ressql = "select count(*) from article";
			}else{
				//ressql = "select id,name,img,type from article  order by time  desc limit "+current+","+JyHelpManager.item;
				ressql = "select id,name,img,type from article  order by time  desc ";
			}
	    break;
	  
	  	//修改文章查询
	  	case "ArticleIdSearch":
	  		if(current==0){
	  			ressql = "select * from article where id = '"+arg[2]+"' ";
	  		}/*else if(current==1){
	  			ressql="UPDATE member SET earnings=earnings+'null' WHERE id='3'";
	  		}*/
	  	break;
			
		}
		log.send(DataType.specialType, "01066", arg[1], ressql);
		return ressql;
	}
}
