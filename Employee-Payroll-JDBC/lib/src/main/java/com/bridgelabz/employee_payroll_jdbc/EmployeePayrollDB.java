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
	
	public void getDataFromTable(Connection con) throws SQLException {
		try{
			ResultSet rs;
			DatabaseMetaData dbm = con.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "employee_payroll", null);
			if(tables.next()) {
				String tName = tables.getString("TABLE_NAME");
				if(tName != null && tName.equals("employee_payroll")) {
					PreparedStatement stmt=con.prepareStatement("select * from employee_payroll"); 
					rs=stmt.executeQuery();
					printData(rs);}
			}else {
				Statement stmt=con.createStatement(); 
				stmt.execute("CREATE TABLE employee_payroll (id INTEGER unsigned NOT NULL AUTO_INCREMENT,name VARCHAR(150) NOT NULL,salary Double NOT NULL,start DATE NOT NULL,PRIMARY KEY (id))");
				System.out.println("New Table Created");
		}}catch(SQLException e) {e.printStackTrace();}
		
	}
	
	public void insertDataIntoTable(Connection con,String name,int salary,String date)throws SQLException {
		try {
			PreparedStatement ps=con.prepareStatement("insert into employee_payroll (name) values(?)",Statement.RETURN_GENERATED_KEYS);
			ResultSet a=ps.getGeneratedKeys();
			int id = 0;
			if(a.next()){
			id=a.getInt(1);
			}
			Statement stmt = con.createStatement();
			stmt.executeUpdate(String.format("insert into employee_payroll values (%d,'%s','M',00,'NA','NA',%d,0,0,0,0,0,'%s')",id,name,salary,date));
		}catch(SQLException e) {e.printStackTrace();}
	}
	
	public void getSalaryByName(Connection con, String name) throws SQLException {
		try {
			PreparedStatement stmt=con.prepareStatement(String.format("select salary from employee_payroll where name = '%s'",name)); 
			ResultSet rs=stmt.executeQuery();
			while(rs.next())System.out.println(rs.getInt(1));
		}catch(SQLException e) {e.printStackTrace();}
	}
	
	public void getSalaryByDateRange(Connection con,String fromDate)  throws SQLException {
		try {
			PreparedStatement stmt=con.prepareStatement(String.format("SELECT * FROM employee_payroll WHERE start BETWEEN CAST('%s' AS DATE) AND DATE(NOW())",fromDate)); 
			ResultSet rs=stmt.executeQuery();
			printData(rs);
		}catch(SQLException e) {e.printStackTrace();}
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
