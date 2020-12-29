package database;
import java.sql.*;

public class Database {
	public static Connection CON;
	static {
		try {

	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         CON=DriverManager.getConnection(
	               "jdbc:oracle:thin:@localhost:1521:xe", "playlist", "1234");
	         System.out.println("Connect");
	      }catch(Exception e) {
	         System.out.println(e.toString());
	      }
	}	
}
