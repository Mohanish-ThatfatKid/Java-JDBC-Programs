package in.jdbcconnection.app;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import com.mysql.cj.jdbc.Driver;
public class TestApp {

	public static void main(String[] args) throws SQLException {
		//Step 1 loading and registering the Driver
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		System.out.println("Driver Register Successfully...");
		
		//step 2 Establish connection b/w java and database
		//JDBC url Syntax::<mainprotocol>:<subprotocol>:<subname>
		String url="jdbc:mysql://localhost:3306/tempconndb";
		String username="root";
		String password="mohanishroot";
		Connection connection =	DriverManager.getConnection(url, username, password);
		System.out.println("Conection object is Created: "+connection);
//		System.out.println();
//		System.out.println("Implementation class name of connection is : "+ connection.getClass().getName());
		
		//step 3 Create Statement object
		
		Statement stmt = connection.createStatement();
		System.out.println("Statement Object is created: "+ stmt);
		
		//step 4 Send And Execute Query
		
		String sqlSelectQuery = "select sid,sname,sage,saddr from student";
		ResultSet resultSet = stmt.executeQuery(sqlSelectQuery);
		

	}

}
