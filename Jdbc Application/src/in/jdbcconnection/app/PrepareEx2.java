package in.jdbcconnection.app;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import jdbcUtil.*;
public class PrepareEx2 {

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner scanner = new Scanner(System.in);
		
		//taking user input
		
		System.out.print("Enter Name: ");
		String sname = scanner.next();
		
		System.out.print("Enter Age: ");
		int sage = scanner.nextInt();
		
		System.out.print("Enter Address:");
		String saddr = scanner.next(); 
		try {
			connection = JdbcUtil.getjdbcConnection();
			if (connection != null) {
				String sqlInsertQuery = "insert into student (`sname`,`sage`,`saddr`) values(?,?,?)";
				pstmt = connection.prepareStatement(sqlInsertQuery);
				pstmt.setString(1, sname);
				pstmt.setInt(2, sage);
				pstmt.setString(3, saddr);
				
				int rowAffected = pstmt.executeUpdate();
				
				System.out.println(rowAffected);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				JdbcUtil.jdbcCloseConnection(null, pstmt, connection);
			} catch (Exception e2) {
				e2.printStackTrace();
}
		}
		
		
		
		
		
	}

}
