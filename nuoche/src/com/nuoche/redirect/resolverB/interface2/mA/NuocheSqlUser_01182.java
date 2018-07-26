package com.nuoche.redirect.resolverB.interface2.mA;

import java.io.IOException;
import java.sql.SQLException;

import com.nuoche.classroot.interface2.NuocheSqlMFace;
import com.nuoche.classroot.interface2.NuocheSqlManager;
import com.nuoche.classroot.interface4.JyHelpManager;



public class NuocheSqlUser_01182  extends NuocheSqlManager implements NuocheSqlMFace {
	@Override
	public String addSqlface(int current, String[] arg) throws SQLException,IOException {
		// TODO Auto-generated method stub
		switch (arg[1]) {
		case"fabudongtai":
			if(current==0){
				ressql="insert into say_art_table(update_id,title,photo,isdel,update_time) values('"+arg[2]+"','"+arg[3]+"','"+arg[4]+"',0,Now())";
			}
		case"dongtaipinglun":
			if(current==0){
				//动态id、评论id、内容
				//ressql="insert into discuss_table(say_art_id,comment_id,commen_title,comment_time) values('"+arg[2]+"','"+arg[3]+"','"+arg[4]+"','"+arg[5]+"',now())";
				/***************
				 * 168进行修改,有错误可询问或修改
				 */
				ressql="insert into discuss_table(say_art_id,comment_id,commen_title,comment_time) values('"+arg[2]+"','"+arg[3]+"','"+arg[4]+"',now())";
					/*
					Discuss_table  动态评论表
					say_art_id  动态ID
					Comment_id评论者ID
					Commen_title内容
					comment_time评论时间
					*/
			}
			else if(current==1){
				 ressql="update say_art_table set comments_number=(select count(0) from discuss_table where say_art_id='"+arg[2]+"')  where id='"+arg[2]+"'";	
			}
		}
		return ressql;
	}


	@Override
	public String modSqlface(int current, String[] arg) throws SQLException, IOException {
		// TODO Auto-generated method stub
		//arg[2] say_art_id 动态ID  
		//arg[3] user_id 点赞者ID
		switch (arg[1]) {
		//点赞
		case "dianjidianzan":
		 if(current==0){
			//查询点赞表是否有记录
			 ressql="select count(0) from well_table where say_art_id='"+arg[2]+"'";
					 /*
					  * Well_table点赞表
						say_art_id  动态ID
						user_id    点赞者ID
					  */
		 }
		 else if(current==1){
			 //删除点赞表数据
			 ressql="delete from well_table where say_art_id='"+arg[2]+"' and user_id='"+arg[3]+"'";
		 }
		 else if(current==2){
			 //添加点赞表数据
			 ressql="insert into well_table (say_art_id,user_id) values('"+arg[2]+"','"+arg[3]+"')";
		 }
		 else if(current==3){//arg[2]字段代表上传人id
			 //修改动态表点赞数量
			 ressql="update say_art_table set like_number=(select count(0) from well_table where say_art_id='"+arg[2]+"')  where id='"+arg[2]+"'";
		 }
		 else if(current==4){
			 //查询点赞数量 
			 ressql="select like_number from say_art_table where id='"+arg[2]+"'";
		 }
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
	
		}
		return ressql;
	}

}
