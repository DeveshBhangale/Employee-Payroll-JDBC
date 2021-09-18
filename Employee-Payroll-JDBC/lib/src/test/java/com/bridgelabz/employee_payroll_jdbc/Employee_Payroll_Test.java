package com.bridgelabz.employee_payroll_jdbc;

import java.sql.*; 

import org.junit.Test;

public class Employee_Payroll_Test {
	public static EmployeePayrollDB employeePayrollDB = new EmployeePayrollDB();
	
	
	@Test
	public void connection() {
		try{  
			Connection con = employeePayrollDB.connectDb("payroll_service","root","database");
			con.close();  
			}catch(Exception e){e.printStackTrace();}  
	}
	
	@Test
	public void getDataFromTable() throws Exception {
		try {
			Connection con = employeePayrollDB.connectDb("payroll_service","root","database");
			employeePayrollDB.getDataFromTable(con,employeePayrollDB);
			con.close();  
		}catch(SQLException e){e.printStackTrace();} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
