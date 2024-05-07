package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static String driver;
	private static String url;
	private static String username;
	private  static String password;
	static {
		driver="com.mysql.jdbc.Driver";
		url="jdbc:mysql://localhost:3306/mydb";
		username="root";
		password="123456";
		
	}
	public static Connection open(){
		
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			
			e.printStackTrace();
			
		}
		return null;
		
	}
	public static void close(Connection conn){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
}