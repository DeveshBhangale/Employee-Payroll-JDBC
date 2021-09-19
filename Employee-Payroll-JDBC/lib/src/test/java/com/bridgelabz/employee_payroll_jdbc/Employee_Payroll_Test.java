package com.bridgelabz.employee_payroll_jdbc;

import java.sql.*; 

import org.junit.Test;

public class Employee_Payroll_Test {
	public static EmployeePayrollDB employeePayrollDB = new EmployeePayrollDB();
	
	
	@Test //UC1
	public void connection() {
		try{  
			Connection con = employeePayrollDB.connectDb("payroll_service","root","database");
			con.close();  
			}catch(Exception e){e.printStackTrace();}  
	}
	
	@Test // UC 2
	public void getDataFromTable() throws Exception {
		try {
			Connection con = employeePayrollDB.connectDb("payroll_service","root","database");
			employeePayrollDB.getDataFromTable(con);
			con.close();  
		}catch(SQLException e){e.printStackTrace();} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test //UC3 & UC4
	public void updateBasePayByName() throws Exception {
		Connection con = employeePayrollDB.connectDb("payroll_service","root","database");
		employeePayrollDB.updateBasePayByName(con, "Terisa", 3000000.00);
	}

	
	@Test //UC5
	public void getSalaryByNameAndDateRange() throws Exception {
		Connection con = employeePayrollDB.connectDb("payroll_service","root","database");
		employeePayrollDB.getSalaryByName(con,"dev");
		employeePayrollDB.getSalaryByDateRange(con, "2019-11-13");
	}
	
	@Test //UC6
	public void getSumAvgMinMaxCountOfEmployees()throws Exception{
		Connection con = employeePayrollDB.connectDb("payroll_service","root","database");
		employeePayrollDB.getSumOfSalaryByGender(con, "M");
		employeePayrollDB.getAvgOfSalaryByGender(con, "M");
		employeePayrollDB.getMinOfSalaryByGender(con, "M");
		employeePayrollDB.getCountOfNameByGender(con);
	}
	
	@Test //UC7
	public void insertDataIntoEmployee_Payroll() throws Exception {
		Connection con = employeePayrollDB.connectDb("payroll_service","root","database");
		employeePayrollDB.insertDataIntoTable(con,"Venkat",1000012,"2021-08-02");
		employeePayrollDB.insertDataIntoTable(con,"john",10022300,"2020-10-02");
	}
}