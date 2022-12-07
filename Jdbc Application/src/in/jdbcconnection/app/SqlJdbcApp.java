package in.jdbcconnection.app;
import jdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SqlJdbcApp {
	
	static Connection connection = null;
	static PreparedStatement pstmt = null;
	static ResultSet resultSet = null;
	static Scanner scanner = null;
	
	
	public void selectAllData() throws SQLException {
		connection = JdbcUtil.getjdbcConnection();
		if (connection != null) {
			String sqlSelectQuery = "select sid,sname,sage,saddr from student";
			pstmt = connection.prepareStatement(sqlSelectQuery);
			if(pstmt != null) {
				resultSet = pstmt.executeQuery();
				if (resultSet != null) {
					System.out.println("SID\tSNAME\tSAGE\tSADDR");
					while (resultSet.next()) {
						int sid = resultSet.getInt(1);
						String sname = resultSet.getString(2);
						int sage = resultSet.getInt(3);
						String saddr = resultSet.getString(4);
						System.out.println(sid+"\t"+sname+"\t"+sage+"\t"+saddr);
					}					
				}
				else {
					System.out.println("No Data found in table.");
					}
				}
			}
	}
	
	
	public void insertData()  throws SQLException{
		connection = JdbcUtil.getjdbcConnection();
		if (connection != null) {
			System.out.println("Enter Student Name: ");
			String sname = scanner.next();
			
			System.out.println("Enter Student age: ");
			int sage = scanner.nextInt();
			
			System.out.println("Enter Student Address: ");
			String saddr = scanner.next();
			
			String sqlInserQuery = "insert into student (`sname`,`sage`,`saddr`) values(?,?,?)";
			pstmt =connection.prepareStatement(sqlInserQuery);
			if (pstmt != null) {
				pstmt.setString(1, sname);
				pstmt.setInt(2, sage);
				pstmt.setString(3, saddr);
				int rowAffected = pstmt.executeUpdate();
				System.out.println("Number of Rows Affected: "+rowAffected);
			}
			else {
				System.out.println("Something went Wrong !!!");
			}
		}
	}
	
	public void updateData() throws SQLException {
		System.out.println();
		System.out.println("---------------------Update Operation-------------------");
		connection = JdbcUtil.getjdbcConnection();
		
		if (connection != null) {
			System.out.println("Enter SID to perform Updation operations: ");
		int sid = scanner.nextInt();
		if (sid != 0) {
			System.out.println("What do you want to update: ");
			System.out.println("For Name:        1");
			System.out.println("For Age:         2");
			System.out.println("For Address:     3");
			System.out.println("For Name & age:  4");
			System.out.println("For Name & Adrs: 5");
			System.out.println("For Age & Adrs:  6");
			System.out.println("For All:         7");
			int updateOption = scanner.nextInt();
			switch (updateOption) {
				case 1: {
					//name Update
					System.out.print("Enter Student name:");
					String nameUpdate = scanner.next();

					String sqlUpdateQuery = "update student set sname =? where sid =?";
					pstmt =connection.prepareStatement(sqlUpdateQuery);
					pstmt.setString(1, nameUpdate);
					pstmt.setInt(2, sid);
					int rowAffected = pstmt.executeUpdate();
					if(rowAffected!=0) {
					System.out.println("Student name Changed to :"+nameUpdate);
					}
					else {
						System.out.println("Something bad happened !!!");
					}
					break;
			 }
				case 2:{
					// Age Update
					System.out.print("Enter Student age:");
					int ageUpdate = scanner.nextInt();

					String sqlUpdateQuery = "update student set sage =? where sid =?";
					pstmt =connection.prepareStatement(sqlUpdateQuery);
					pstmt.setInt(1, ageUpdate);
					pstmt.setInt(2, sid);
					int rowAffected = pstmt.executeUpdate();
					if(rowAffected!=0) {
					System.out.println("Student age Changed to :"+ageUpdate);
					}
					else {
						System.out.println("Something bad happened !!!");
					}
					break;

				}
				case 3:
				{
					//Address Update
					System.out.print("Enter Student address:");
					String addressUpdate = scanner.next();

					String sqlUpdateQuery = "update student set saddr =? where sid =?";
					pstmt =connection.prepareStatement(sqlUpdateQuery);
					pstmt.setString(1, addressUpdate);
					pstmt.setInt(2, sid);
					int rowAffected = pstmt.executeUpdate();
					if(rowAffected!=0) {
					System.out.println("Student name Changed to :"+addressUpdate);
					}
					else {
						System.out.println("Something bad happened !!!");
					}
					break;

				}
				case 4:{
					//NAme And Age Update
					System.out.print("Enter Student name:");
					String nameUpdate = scanner.next();
					System.out.println("Enter Student Age: ");
					int ageUpdate = scanner.nextInt();
					
					String sqlUpdateQuery = "update student set `sname` =?, `sage`=? where `sid` =?";
					pstmt =connection.prepareStatement(sqlUpdateQuery);
					pstmt.setString(1, nameUpdate);
					pstmt.setInt(2, ageUpdate);
					pstmt.setInt(3, sid);
					int rowAffected = pstmt.executeUpdate();
					if(rowAffected!=0) {
					System.out.println("Student data Updated :\nName: "+nameUpdate+"\nAge: "+ageUpdate);
					}
					else {
						System.out.println("Something bad happened !!!");
					}
					break;
				}
				case 5:{
					//name and address update
					System.out.print("Enter Student name:");
					String nameUpdate = scanner.next();
					System.out.println("Enter Student Address: ");
					String addressupdate = scanner.next();

					String sqlUpdateQuery = "update student set `sname` =?, `saddr`=? where `sid` =?";
					pstmt =connection.prepareStatement(sqlUpdateQuery);
					pstmt.setString(1, nameUpdate);
					pstmt.setString(2, addressupdate);
					pstmt.setInt(3, sid);
					int rowAffected = pstmt.executeUpdate();
					if(rowAffected!=0) {
					System.out.println("Student data updated :\nNAme: "+nameUpdate+"\nAddress: "+addressupdate);
					}
					else {
						System.out.println("Something bad happened !!!");
					}
					break;
				}
				case 6:{
					//age and address update
					System.out.println("Enter Student age: ");
					int ageUpdate = scanner.nextInt();
					
					System.out.print("Enter Student address:");
					String addressUpdate = scanner.next();

					String sqlUpdateQuery = "update student set sage =?, saddr=? where sid =?";
					pstmt =connection.prepareStatement(sqlUpdateQuery);
					pstmt.setInt(1, ageUpdate);
					pstmt.setString(2, addressUpdate);
					pstmt.setInt(3, sid);
					int rowAffected = pstmt.executeUpdate();
					if(rowAffected!=0) {
					System.out.println("Student age Changed to :"+ageUpdate+"\nAddress Changed to :"+addressUpdate);
					}
					else {
						System.out.println("Something bad happened !!!");
					}
					break;
				}
				case 7:{
					//Update all
					System.out.print("Enter Student name:");
					String nameUpdate = scanner.next();
					
					System.out.println("Enter Student age: ");
					int ageUpdate=scanner.nextInt();
					
					System.out.println("Enter Student address:");
					String addressUpdate = scanner.next();

					String sqlUpdateQuery = "update student set sname =?,sage=?,saddr=? where sid =?";
					pstmt =connection.prepareStatement(sqlUpdateQuery);
					pstmt.setString(1, nameUpdate);
					pstmt.setInt(2, sid);
					int rowAffected = pstmt.executeUpdate();
					if(rowAffected!=0) {
					System.out.println("Student data updated "+"\nName: "+nameUpdate+"\nAge: "+ageUpdate+"\nAddress: "+addressUpdate);
					}
					else {
						System.out.println("Something bad happened !!!");
					}
					break;
				}
			default:
				throw new IllegalArgumentException("Unexpected value: " + updateOption);
			}
			
			}
		}
	}
	public void deleteData() throws SQLException {
		connection = JdbcUtil.getjdbcConnection();
		if(connection != null) {
			System.out.println("Enter Student Id to delete the record:");
			int sid = scanner.nextInt();
			String sqlDeleteQuery = "delete from student where sid=?";
			pstmt= connection.prepareStatement(sqlDeleteQuery);
			if (pstmt != null) {
				pstmt.setInt(1, sid);
				int rowAffcted = pstmt.executeUpdate();
				if(rowAffcted!=0) {
					System.out.println("Student data deleted Successfully!!!");
				}
				else {
					System.out.println("Something Bad Happend");
				}
			}
		}
	}
	public int userOption() {
		scanner = new Scanner(System.in);
		System.out.println("Enter What operation you want to perform on Table (Student)[Enter numbers shown below] : ");
		System.out.println("For Exit   : 0");
		System.out.println("For Select : 1");
		System.out.println("For Insert : 2");
		System.out.println("For Update : 3");
		System.out.println("For Delete : 4");
		int option = scanner.nextInt();
		return option;
	}

	public static void main(String[] args) {
		SqlJdbcApp sjp = new SqlJdbcApp();
		try {
			while (true) {
				int option = sjp.userOption();
				if (option == 0) {
					System.out.println("Thank you For using this Application!!!");
					System.exit(0);
					
					} 
				else if (option == 1) 
				{
					sjp.selectAllData();
					System.out.println("Do you want to perform any other Operation?[YES : 1 / NO: 0]: ");
					int contoption = scanner.nextInt();
					if (contoption == 0){
						System.out.println("Thank you for using this Application");
						System.exit(0);
					}
					else {
						continue;			
					}
				}
				else if (option == 2)
				{
					sjp.insertData();
					System.out.println("Do you want to perform any other Operation?[YES : y / NO: n]: ");
					int contoption = scanner.nextInt();
					if (contoption == 0){
						System.out.println("Thank you for using this Application");
						System.exit(0);
					}
					else {
						continue;			
					}
					
				} 
				else if (option == 3) {
					sjp.updateData();
					System.out.println("Do you want to perform any other Operation?[YES : y / NO: n]: ");
					int contoption = scanner.nextInt();
					if (contoption == 0){
						System.out.println("Thank you for using this Application");
						System.exit(0);
					}
					else {
						continue;			
					}
				}
				else if (option == 4) {
					sjp.deleteData();
					System.out.println("Do you want to perform any other Operation?[YES : y / NO: n]: ");
					int contoption = scanner.nextInt();
					if (contoption == 0){
						System.out.println("Thank you for using this Application");
						System.exit(0);
					}
					else {
						continue;			
					}
				}
				else {
					System.out.println("Invalid input Provided !!!");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
