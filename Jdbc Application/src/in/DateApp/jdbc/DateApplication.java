package in.DateApp.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import  jdbcUtil.*;

public class DateApplication {

	public static void main(String[] args) throws ParseException {
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet =null;
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter Name : ");
		String name = scanner.next();
		
		System.out.print("Enter Address: ");
		String addr = scanner.next();
		
		System.out.print("Enter Gender: ");
		String gender = scanner.next();
		
		System.out.print("Enter Date of Birth (dd-mm-yyyy): ");
		String dob = scanner.next();
		
		System.out.print("Enter Date of Joining (mm-dd-yyyy): ");
		String doj = scanner.next();
		
		System.out.println("Enter Date of Moving (yyyy-mm-dd): ");
		String dom = scanner.next();
		
		//for DOB
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date uDate = sdf.parse(dob);
		
		Long l = uDate.getTime();
		java.sql.Date sqlDob = new java.sql.Date(l);
		
		
		//for DOj
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yyyy");
		
		uDate = sdf.parse(doj);
		
		l = uDate.getTime();
		java.sql.Date sqlDoj = new java.sql.Date(l);
		
		//for dom
		
		java.sql.Date sqldom = java.sql.Date.valueOf(dom);
		
		
		//sql query for preparedStatement
		String SqlInserQuery = "insert into employee (`name`,`address`,`gender`,`dob`,`doj`,`dom`) values(?,?,?,?,?,?)";
		String sqlSelectQuery = "select name, address, gender, dob, doj, dom from employee";
		try {
			connection = JdbcUtil.getjdbcConnection();
			if (connection!=null) {
				preparedStatement = connection.prepareStatement(SqlInserQuery);
				if (preparedStatement != null) {
					preparedStatement.setString(1, name);

					preparedStatement.setString(2, addr);

					preparedStatement.setString(3, gender);

					preparedStatement.setDate(4, sqlDob);
					
					preparedStatement.setDate(5, sqlDoj);
					
					preparedStatement.setDate(6, sqldom);
					
					int rowAffected = preparedStatement.executeUpdate();
					preparedStatement=null;
					if (rowAffected!=0){
//						System.out.println("Name\tAddress\tGender\tDOB\tDOJ\tDOM");
						preparedStatement = connection.prepareStatement(sqlSelectQuery);
						resultSet = preparedStatement.executeQuery();
						if (resultSet != null) {
							while (resultSet.next()) {
								name = resultSet.getString(1);
								addr = resultSet.getString(2);
								gender =resultSet.getString(3);
								
								sqlDob = resultSet.getDate(4);
								dob = sdf.format(sqlDob);
								
								sqlDoj = resultSet.getDate(5);
								doj = sdf2.format(sqlDoj);
								
								sqldom = resultSet.getDate(6);

								System.out.println("Name: "+name);
								System.out.println("Address: "+addr);
								System.out.println("Gender: "+gender);
								System.out.println("DOB: "+dob);
								System.out.println("DOJ: "+doj);
								System.out.println("DOM: "+sqldom);
							}	
						}else {
							System.out.println("No Date To Show....");
						}
					}
				}
				else {
					System.out.println("Data Insertion Failed....");
				}
				
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				JdbcUtil.jdbcCloseConnection(resultSet, preparedStatement, connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
