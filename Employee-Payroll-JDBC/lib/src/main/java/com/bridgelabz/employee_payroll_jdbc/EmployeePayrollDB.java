package com.bridgelabz.employee_payroll_jdbc;
import java.sql.*;  

public class EmployeePayrollDB {
	
	public Connection connectDb(String dbName, String user,String pass) throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(String.format("jdbc:mysql://localhost:3306/%s?useSSL=false",dbName),user,pass);
			return con;
		}catch(SQLException e){ e.printStackTrace();}
		catch(ClassNotFoundException e){e.printStackTrace();}
		return null;
	}
	
	public void getDataFromTable(Connection con,EmployeePayrollDB employeePayrollDB) throws SQLException {
		try{
			ResultSet rs;
			DatabaseMetaData dbm = con.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "employee_payroll", null);
			if(tables.next()) {
				String tName = tables.getString("TABLE_NAME");
				if(tName != null && tName.equals("employee_payroll")) {
					PreparedStatement stmt=con.prepareStatement("select * from employee_payroll"); 
					rs=stmt.executeQuery();
					employeePayrollDB.printData(rs);}
			}else {
				Statement stmt=con.createStatement(); 
				stmt.execute("CREATE TABLE employee_payroll (id INTEGER unsigned NOT NULL AUTO_INCREMENT,name VARCHAR(150) NOT NULL,salary Double NOT NULL,start DATE NOT NULL,PRIMARY KEY (id))");
				System.out.println("New Table Created");
		}}catch(SQLException e) {e.printStackTrace();}
		
	}
	
	public void printData(ResultSet rs) throws SQLException {
		try {
			rs.toString();
			while(rs.next())  
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)
							+" "+rs.getInt(4)+"  "+rs.getString(5)+"  "+rs.getString(6)
							+" "+rs.getInt(7)+"  "+rs.getInt(8)+"  "+rs.getInt(9)
							+" "+rs.getInt(10)+"  "+rs.getInt(11)+"  "+rs.getInt(12)+" "+rs.getString(13));
			}catch(SQLException e){ e.printStackTrace();}
	}

	
	
	

}
