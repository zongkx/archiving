package DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils {
		  static String driver;
		  static String url;
		  static String username;
		  static String password;
		  static {
			  driver = "com.mysql.jdbc.Driver";
			  url = "jdbc:mysql://localhost:3306/test?character=utf-8";
			  username = "root";
			  password = "123456";
			  
			  
		  }
		  public static Connection open() {
			  try {
				  Class.forName(driver);
				  return DriverManager.getConnection(url,username, password);
				  
			  }catch (Exception e) {
				  e.printStackTrace();
			}
			  return null;
			  
		  }
		  public static void close (Connection conn) {
			  if(conn!=null) {
				  try {
					  conn.close();
				  }catch (Exception e) {
					  e.printStackTrace();
				}
			  }
		  }
		}

