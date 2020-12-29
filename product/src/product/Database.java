package product;


import java.sql.*;

public class Database {
	public static Connection CON;
	static {
		try {
			// 오라클 jdbc 드라이버 설정
			Class.forName("oracle.jdbc.driver.OracleDriver");
			CON = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "android", "1234");
			System.out.println("connect");
		} catch (Exception error) {
			error.printStackTrace();
		}
	}
}
