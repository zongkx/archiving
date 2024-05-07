package DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBU {
	public static Connection open() {
		  try {
			  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			  return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatebaseName=coursetbl","sa", "123456");
			  
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

