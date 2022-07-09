package utils;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
//database name database-1 password princessofgrey;
//singleton design pattern: only have one instance of something, in this case, the connection the database.
public class ConnectionUtil {
	
	
	private static ConnectionUtil connUtil;
	private Properties props;
	
	private ConnectionUtil() {
		props = new Properties();
		InputStream propsFile = ConnectionUtil.class.getClassLoader()
				.getResourceAsStream("database.properties");
		
				try {
					props.load(propsFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}
	
	public static synchronized ConnectionUtil getConnectionUtil() {
		if(connUtil == null) {
			connUtil  = new ConnectionUtil();
		}
		return connUtil;
	}
	
	//factory design pattern: 
public Connection getConnection() {
	//connection
	//JDBC driver
	//database URL
	//user name
	//password


	
	Connection conn = null;
	//variables from props
	String dbUrl = props.getProperty("url");
	String dbUser = props.getProperty("usr");
	String dbPass = props.getProperty("pwd");
	
	
	try {
		//create gitignore and add it to the file. be sure to include the database.properties file in the gitignore.
		Class.forName("org.postgresql.Driver");
		conn = DriverManager.getConnection(dbUrl,dbUser, dbPass);
		System.out.println("Connected to DB!");
	}
	catch(SQLException e) {
		e.printStackTrace();
		
	}catch(Exception ex) {
		ex.printStackTrace();
	}
	
	return conn;
	
	
	
}

}
