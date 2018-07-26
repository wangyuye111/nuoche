package com.nuoche.redirect.resolverB.interface2.mA;

import java.io.IOException;
import java.sql.SQLException;

import com.nuoche.classroot.interface2.NuocheSqlMFace;
import com.nuoche.classroot.interface2.NuocheSqlManager;
import com.nuoche.classroot.interface4.HelpManager;




public class NuocheSqlBoos_A_01178  extends NuocheSqlManager implements NuocheSqlMFace {
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
		
		case "time_slot":
			/*if(current==0){
				ressql = "select rush_time from set_table where id = '1'";
			}else if(current==1){
				ressql = "select send_time from set_table where id = '1'";
			}else if(current==2){
				ressql = "INSERT INTO set_table (rush_time) VALUES ('"+arg[4]+"') where id = '1'";
			}else if(current==3){
				ressql = "INSERT INTO set_table (send_time) VALUES ('"+arg[4]+"') where id = '1'";
			}else */
			if(current==0){
				ressql = "UPDATE set_table set rush_time='"+arg[4]+"' where id = '1'";
			}else if(current==1){
				ressql = "UPDATE set_table set send_time='"+arg[4]+"' where id = '1'";
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
		
		case "shijian_search":
			if(current==0){
				ressql = "select * from set_table";
			}
			break;
			
		case "to_editshijian_search":
			if(current==0){
				ressql = "select * from set_table";
			}
			break;
			
		case "nuoche_to_details":
			if(current==0){
				ressql = "select count(*) from nuoche_details";
			}else if(current==1){
				ressql = "select a.nickname,b.* from user_data a,nuoche_details b where a.id=b.user_id limit "+arg[5]+","+HelpManager.item;
//select a.nickname,a.photo,a.introduce,b.user_id,a.user_state from user_data a,attention b where a.id=b.user_id and b.attention_id = '"+arg[2]+"'
			}
			break;
			//where b.time like '%"+arg[7]+"%'"
			//and b.time between '"+arg[5]+" 00:00:01' and '"+arg[6]+" 23:59:59' order by b.time desc
		case "nuoche_details":
			if(current==0){
				ressql = "select count(*) from nuoche_details";
			}else if(current==1){
				ressql = "select a.nickname,b.* from user_data a,nuoche_details b where a.id=b.user_id order by b.time desc limit "+arg[9]+","+HelpManager.item;
				//ressql = "select * from nuoche_details limit "+arg[5]+","+HelpManager.item;
			}else if(current==2){
				ressql = "select a.nickname,b.* from user_data a,nuoche_details b where a.id=b.user_id and b.time like '%"+arg[7]+"%' order by b.time desc limit "+arg[9]+","+HelpManager.item;
			}else if(current==3){
				ressql = "select a.nickname,b.* from user_data a,nuoche_details b where a.id=b.user_id and b.time between '"+arg[5]+" 00:00:01' and '"+arg[6]+" 23:59:59' order by b.time desc limit "+arg[9]+","+HelpManager.item;
			}/*else if(current==4){
				ressql = "select a.nickname,b.* from user_data a,nuoche_details b where a.id=b.user_id and b.time between '"+arg[5]+" 00:00:01' and '"+arg[6]+" 23:59:59' order by b.time desc limit "+arg[9]+","+HelpManager.item;
			}else if(current==5){
				ressql = "select a.nickname,b.* from user_data a,nuoche_details b where a.id=b.user_id and b.time between '"+arg[5]+" 00:00:01' and '"+arg[6]+" 23:59:59' order by b.time desc limit "+arg[9]+","+HelpManager.item;
			}*/
			break;
			
		}
		return ressql;
	}

}
