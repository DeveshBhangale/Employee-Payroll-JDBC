package com.bridgelabz.employee_payroll_jdbc;
import java.sql.*;  

public class EmployeePayrollDB {
	
	public Connection connectDb(String dbName, String user,String pass) throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(String.format("jdbc:mysql://localhost:3306/%s?useSSL=false",dbName),user,pass);
			return con;
		}catch(SQLException e){ System.out.println(e);}
		catch(ClassNotFoundException e){ System.out.println(e);}
		return null;
	}
	
	

}
