package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	public static Connection CON;
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			CON=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "shop", "1234");
			System.out.println("connected : " + CON);
		} catch (Exception error) {
			error.printStackTrace();
		}
	}
}
