package course_registration_app;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.ConnectionUtil;

public class UserDB {
// Obtain java.sql.connection object 
	//create a statement object using create statement method on the  java.sql.connection
	// result set is the records that are grabbed from the database using execute query method passing in the sql string.
	//we create a new user and assign the values from th output of the data from the sql statement. 
	//we are using get string and get int to grab the data from the result of the query.
	
	public static User login(String userName, String pwd) {
		Connection conn = ConnectionUtil.getConnectionUtil().getConnection();
		String sql = "SELECT * FROM users WHERE user_name = '" + userName + "' AND PWD = '" + pwd + "'";
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			//check if there is any record
			if (rs.next()) {
				//now resultSet points to first record
				//field values can be fetched from resultset record by column name
				
				//Create User object with user name
				User user = new User(userName);
				//set the attributes of user, by fetching it from resultset record by column name
				user.setId(rs.getInt("id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setAddress(rs.getString("address"));

				//retun the user object
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Error_Log(e.toString());
		}
		//if no user null is returned
		//if exception/error null is returned
		return null;
	}
	//create connection
	//create sql statement passing in the username and password of the user object passed in.
	// run the sql statement with the sql string as the query
	public static boolean createUser(User user) {
		Connection conn = ConnectionUtil.getConnectionUtil().getConnection();
		String sql = "INSERT INTO users (user_name, pwd, first_name, last_name, address) " //
				+ " VALUES ('" + user.getUsername() + "',  '" + user.getPassword() + "'" //
				+ ", '" + user.getFirstName() + "'" + ", '" + user.getLastName() + "'" //
				+ ", '" + user.getAddress() + "')";
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			Error_Log(e.toString());
		}
		return false;
	}
	
	
	
	public static void Error_Log(String n) {
		   try {
		        FileWriter myWriter = new FileWriter("log_file.txt");
		        myWriter.write(n);
		        myWriter.close();
		        System.out.println("Successfully wrote to the file.");
		      } catch (IOException e) {
		        System.out.println("An error occurred.");
		        e.printStackTrace();
		        Error_Log(e.toString());
		      }
	}

}
