package course_registration_app;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.ConnectionUtil;

public class AccountDB {
	// creates a connection using the utility
	//creates a string with the query passing in the user id , account type, balance, and account number 
	//creates a statement with the connection. 
	//executes the statement passing in the sql string with the values
	//this updates the database
	
	
	public static boolean createAccount(Account account) {

		Connection conn = ConnectionUtil.getConnectionUtil().getConnection();
		String sql = "INSERT INTO accounts (user_id, account_type, account_number, balance) " //
				+ " VALUES (" + account.getUserId() + ",  '" + account.getAccountType() + "'" //
				+ ", '" + account.getAccountNumber() + "', " + account.getBalance() + ")";

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
	//creates connection, passes in values needed for the deposit , deposits are teathered to the account number. 
	//uses statement logic as defined above.
	//i understand it nees to be not double. :O correct type is big decimal.
	public static Double deposit(String accountNumber, Double amount) {

		Connection conn = ConnectionUtil.getConnectionUtil().getConnection();
		String sql = "UPDATE accounts SET balance = balance + " + amount//
				+ " WHERE account_number  = '" + accountNumber + "'";

		try {
			Statement st = conn.createStatement();
			int rows = st.executeUpdate(sql);
			if (rows == 1) {
				return getBalance(accountNumber);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Error_Log(e.toString());
		}
		return null;
	}
	
	// creates connection, balance is teathered to account number, it is passed in. 
	// statment logic is ran similar to the above functions
	public static Double getBalance(String accountNumber) {
		Connection conn = ConnectionUtil.getConnectionUtil().getConnection();
		String sql = "SELECT balance FROM accounts " //
				+ " WHERE account_number  = '" + accountNumber + "'";
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {

				return rs.getDouble("balance");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Error_Log(e.toString());
		}
		return null;
	}
	// withdraw uses the same logic as deposit. 
	

	public static Double withdraw(String accountNumber, Double amount) {

		Connection conn = ConnectionUtil.getConnectionUtil().getConnection();
		String sql = "UPDATE accounts SET balance = balance - " + amount //
				+ " WHERE account_number  = '" + accountNumber + "'";

		try {
			Statement st = conn.createStatement();
			int rows = st.executeUpdate(sql);
			if (rows == 1) {
				return getBalance(accountNumber);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Error_Log(e.toString());
		}
		return null;
	}
	
	//logs an error if there is an error with any code.
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
