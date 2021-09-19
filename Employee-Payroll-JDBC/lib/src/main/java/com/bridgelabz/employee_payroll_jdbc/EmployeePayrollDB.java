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
				stmt.execute("CREATE TABLE employee_payroll (id INTEGER unsigned NOT NULL AUTO_INCREMENT,name VARCHAR(150) NOT NULL,gender varchar(20) not null,salary Double NOT NULL,start DATE NOT NULL,PRIMARY KEY (id))");
				System.out.println("New Table Created");
		}}catch(SQLException e) {e.printStackTrace();}
		
	}
	
	public void updateIDSequentially(Connection con) throws SQLException {
		try {
		PreparedStatement ps=con.prepareStatement("SET  @num := 0;");
		ps.executeUpdate();
		ps=con.prepareStatement("UPDATE employee_payroll SET id = @num := (@num+1);");
		ps.executeUpdate();
		}catch(SQLException e) {e.printStackTrace();}
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
			stmt.executeUpdate(String.format("insert into employee_payroll values (%d,'%s','N',%d,'%s')",id,name,salary,date));
			updateIDSequentially(con);
			System.out.println("Data inserted !");
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
	
	public void getSumOfSalaryByGender(Connection con, String gender) throws SQLException {
		try {
			PreparedStatement stmt=con.prepareStatement(String.format("select sum(salary) from employee_payroll where gender = '%s'",gender)); 
			ResultSet rs=stmt.executeQuery();
			while(rs.next())System.out.println(rs.getInt(1));
		}catch(SQLException e) {e.printStackTrace();}
	}
	
	public void getAvgOfSalaryByGender(Connection con, String gender) throws SQLException {
		try {
			PreparedStatement stmt=con.prepareStatement(String.format("select avg(salary) from employee_payroll where gender = '%s'",gender)); 
			ResultSet rs=stmt.executeQuery();
			while(rs.next())System.out.println(rs.getInt(1));
		}catch(SQLException e) {e.printStackTrace();}
	}
	
	public void getMinOfSalaryByGender(Connection con, String gender) throws SQLException {
		try {
			PreparedStatement stmt=con.prepareStatement(String.format("select min(salary) from employee_payroll where gender = '%s'",gender)); 
			ResultSet rs=stmt.executeQuery();
			while(rs.next())System.out.println(rs.getInt(1));
		}catch(SQLException e) {e.printStackTrace();}
	}
	
	public void getMaxOfSalaryByGender(Connection con, String gender) throws SQLException {
		try {
			PreparedStatement stmt=con.prepareStatement(String.format("select max(salary) from employee_payroll where gender = '%s'",gender)); 
			ResultSet rs=stmt.executeQuery();
			while(rs.next())System.out.println(rs.getInt(1));
		}catch(SQLException e) {e.printStackTrace();}
	}
	
	public void getCountOfNameByGender(Connection con) throws SQLException {
		try {
			PreparedStatement stmt=con.prepareStatement("select gender, count(name) from employee_payroll group by gender"); 
			ResultSet rs=stmt.executeQuery();
			while(rs.next())System.out.println(rs.getString(1)+" "+rs.getString(2));
		}catch(SQLException e) {e.printStackTrace();}
	}
	
	public void updateGenderByName(Connection con, String name,String gender) throws SQLException {
		try {
			String query ="update employee_payroll set gender=? where name=?";
			PreparedStatement stmt=con.prepareStatement(query);
			stmt.setString(1, gender);
			stmt.setString(2, name);
			stmt.executeUpdate();
			System.out.println("Updated !");
		}catch(SQLException e) {e.printStackTrace();}
	}
	
	public void updateBasePayByName(Connection con, String name,double basePay) throws SQLException {
		try {
			String query ="update employee_payroll set salary=? where name=?";
			PreparedStatement stmt=con.prepareStatement(query);
			stmt.setDouble(1, basePay);
			stmt.setString(2, name);
			stmt.executeUpdate();
			System.out.println("Updated !");
		}catch(SQLException e) {e.printStackTrace();}
	}
	
	public void printData(ResultSet rs) throws SQLException {
		try {
			while(rs.next())  
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)
							+" "+rs.getInt(4)+"  "+rs.getString(5));
			}catch(SQLException e){ e.printStackTrace();}
	}

	
	
	

}