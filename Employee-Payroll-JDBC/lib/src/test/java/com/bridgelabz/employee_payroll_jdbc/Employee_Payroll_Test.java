package com.bridgelabz.employee_payroll_jdbc;

import java.sql.*; 

import org.junit.Test;

public class Employee_Payroll_Test {
	public static EmployeePayrollDB employeePayrollDB = new EmployeePayrollDB();
	@Test
	public void connection() {
		try{  
			Connection con = employeePayrollDB.connectDb("payroll_service","root","database");
			PreparedStatement stmt=con.prepareStatement("select * from employee_payroll"); 
			ResultSet rs=stmt.executeQuery();
			while(rs.next())  
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)
						+" "+rs.getInt(4)+"  "+rs.getString(5)+"  "+rs.getString(6)
						+" "+rs.getInt(7)+"  "+rs.getInt(8)+"  "+rs.getInt(9)
						+" "+rs.getInt(10)+"  "+rs.getInt(11)+"  "+rs.getInt(12)+" "+rs.getString(13));  
			
			con.close();  
			}catch(Exception e){ System.out.println(e);}  
	}

}
