package in.jdbcconnection.app;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jdbcUtil.*;
public class PrepareEx1 {

	public static void main(String[] args) {
		Connection connection = null;
		Statement stmt = null; 
		ResultSet resultSet = null;

		try {
			connection = JdbcUtil.getjdbcConnection();
			if (connection != null) {
				stmt = connection.createStatement();
			if (stmt != null) {
				String sqlSelectQuery = "select sid,sname,sage,saddr from student";
				resultSet = stmt.executeQuery(sqlSelectQuery);
				System.out.println("sid\tsname\tsage\tsaddr");
				while(resultSet.next()) {
					if (resultSet != null) {
						int sid = resultSet.getInt(1);
						String sname = resultSet.getString(2);
						int sage = resultSet.getInt(3);
						String saddr = resultSet.getString(4);
						System.out.println(sid+"\t"+sname+"\t"+sage+"\t"+saddr);
						
					}
					else {
						System.out.println("no data to Show");
					}
				}
			}
			}
			
		} 
		catch (SQLException se) {
			se.printStackTrace();
		}
		
		finally {
			try {
				
				JdbcUtil.jdbcCloseConnection(resultSet, stmt, connection);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
