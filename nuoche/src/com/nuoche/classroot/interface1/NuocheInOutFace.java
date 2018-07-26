package com.nuoche.classroot.interface1;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

public interface NuocheInOutFace {
	public void addface() throws SQLException, ServletException, IOException;

	public void modface() throws SQLException, ServletException, IOException, Exception;

	public void deleteface() throws SQLException, ServletException, IOException;

	public void searchface() throws SQLException, ServletException, IOException;
	
	
	
	
}
