package com.nuoche.classroot.interface2;

import java.io.IOException;
import java.sql.SQLException;

public interface NuocheSqlMFace {

	public String addSqlface(int current,String[] arg) throws SQLException, IOException;

	public String modSqlface(int current,String[] arg) throws SQLException, IOException;

	public String deleteSqlface(String[] arg) throws SQLException;

	public String searchSqlface(int current,String[] arg) throws SQLException, IOException;
	
	
}
