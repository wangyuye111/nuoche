package com.nuoche.classroot.interface1;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class NuocheInOutManager extends JyInOut {
	
	public NuocheInOutManager(String[] arg, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException, SQLException {
		super(arg,request,response);
	}
}
