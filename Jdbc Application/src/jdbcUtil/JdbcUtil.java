package jdbcUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	private JdbcUtil() {
	}

	public static Connection getjdbcConnection() throws SQLException {
		Connection connection =null;
		String url ="jdbc:mysql://localhost:3306/tempconndb";
		String username="root";
		String password = "mohanishroot";
		
		connection= DriverManager.getConnection(url,username,password);
		
		if (connection!= null) {
			return connection;
			
		}
		else {
			return null;
		}
	}
	
	public static void jdbcCloseConnection(ResultSet resultSet, Statement statement, Connection connection) throws SQLException {
		if (resultSet != null) {
			resultSet.close();
		}
		if (statement != null) {
			statement.close();
			
		}
		if (connection != null) {
			connection.close();
			
		}
	}
}
