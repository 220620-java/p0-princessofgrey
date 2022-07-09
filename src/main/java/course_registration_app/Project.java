package course_registration_app;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;

//import Services.SignInManager;

import java.util.Scanner;

import utils.ConnectionUtil;

public class Project {

	private static Scanner scan = new Scanner(System.in);
	private static User currentUser;

	public static void main(String[] args) {

		ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();
		Connection conn = connUtil.getConnection();
		if (conn == null) {
			System.out.println("Could not connect to DB.");
			System.exit(0);
		}

		runMenu();

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
		      }
	}
	// logs in using the credentials ignoring case values for the login to prevent errors. 
	//else creates an account. 
	private static void runMenu() {

		System.out.println("Are you an existing customer?");
		String input = scan.nextLine();
		if ("yes".equalsIgnoreCase(input)) {
			login();
		} else {
			createUserAccount();
		}

	}
	//creates a mew account taking in the input from the user and creating a user object from that data. 
	// adds the object to the database using the userDB createUser() function.
	private static void createUserAccount() {
		System.out.println("CREATING NEW ACCOUNT....");

		System.out.println("Enter user name: ");
		String userName = scan.nextLine().toLowerCase();

		System.out.println("Enter password: ");
		String pwd = scan.nextLine();

		System.out.println("Enter FIRST name: ");
		String firstName = scan.nextLine();

		System.out.println("Enter LAST name: ");
		String lastName = scan.nextLine();

		System.out.println("Enter address: ");
		String address = scan.nextLine();

		User user = new User(userName, pwd, firstName, lastName, address);

		boolean success = UserDB.createUser(user);
		if (success) {
			System.out.println("User created successfully.");
		} else {
			System.out.println("User creation FAILED.");
		}

	}
	// takes  in credentials, creates a current user object that holds the credentials of the current user. 
	//runs the login method outlined in the userDB class passing in username and password
	// prints out the user if the credentials match a user in the database

	private static void login() {
		System.out.println("Enter user name: ");
		String userName = scan.nextLine().toLowerCase();

		System.out.println("Enter password: ");
		String pwd = scan.nextLine();

		currentUser = UserDB.login(userName, pwd);
		if (currentUser == null) {
			System.out.println("INVALID LOGIN.");
			return;
		}

		System.out.println("\nWelcome " + currentUser.getFirstName() + "!");
		System.out.println();

		while (true) {
			runBankMenu();
		}
	}
	// displays bank menu giving options to deopsit , create bank account, withdraw, or view balance. 
	//depending on which is selected it will run a different method. 
	private static void runBankMenu() {

		System.out.println("----- BANK MENU----");
		System.out.println("1. Create Account");
		System.out.println("2. Deposit");
		System.out.println("3. Withdraw");
		System.out.println("4. View Balance");
		System.out.println();
		System.out.println("Enter your choice 1 to 4. (0 to Quit): ");
		String input = scan.nextLine();
		int choice = Integer.parseInt(input);
		switch (choice) {
		case 1:
			createBankAccount();
			break;
		case 2:
			deposit();
			break;
		case 3:
			withdraw();
			break;
		case 4:
			viewBalance();
			break;
		case 0:
			System.out.println("\nGood Bye!");
			System.exit(0);
		default:
			System.out.println("Invalid choice");

		}

	}
	// views the balance for the user given the account number. 
	//fails if not given a valid account number.

	private static void viewBalance() {

		System.out.println("Enter Account Number: ");
		String accountNumber = scan.nextLine();

		Double balance = AccountDB.getBalance(accountNumber);
		if (balance != null) {
			System.out.println("Available Balance: " +"$"+ balance);
		} else {
			System.out.println("Invalid account number.");
		}

	}
	//attempts to deposit into the account . if it fails it says deposit failed.

	private static void deposit() {
		System.out.println("DEPOSIT to ACCOUNT....");

		System.out.println("Enter Account Number: ");
		String accountNumber = scan.nextLine();

		System.out.println("Enter Amount: ");
		String input = scan.nextLine();
		Double amount = Double.parseDouble(input);

		Double balance = AccountDB.deposit(accountNumber, amount);
		if (balance != null) {
			System.out.println("Deposit successfull: New Balance: "+"$" + balance);
		} else {
			System.out.println("Deposit FAILED.");
		}

	}

	private static void withdraw() {
		System.out.println("WITHDRAW from ACCOUNT....");

		System.out.println("Enter Account Number: ");
		String accountNumber = scan.nextLine();

		System.out.println("Enter Amount: ");
		String input = scan.nextLine();
		Double amount = Double.parseDouble(input);

		Double balance = AccountDB.getBalance(accountNumber);
		if (amount > balance) {
			System.out.println("Insufficient funds. Available Balance: "+"$" + balance);
			return;
		}

		balance = AccountDB.withdraw(accountNumber, amount);
		if (balance != null) {
			System.out.println("Withdraw successfull: New Balance: "+"$" + balance);
		} else {
			System.out.println("Withdraw FAILED.");
		}

	}

	private static void createBankAccount() {
		System.out.println("CREATING NEW BANK ACCOUNT....");

		System.out.println("Enter Account Type (Savings Or Current): ");
		String input = scan.nextLine();
		if ("Savings".equalsIgnoreCase(input) || "Current".equalsIgnoreCase(input)) {
			String accountType = input.toUpperCase();

			System.out.println("Enter Account Number: ");
			String accountNumber = scan.nextLine();

			System.out.println("Enter Opening Balance: ");
			input = scan.nextLine();
			Double amount = Double.parseDouble(input);

			Account account = new Account(currentUser.getId(), accountType, accountNumber, amount);

			boolean success = AccountDB.createAccount(account);
			if (success) {
				System.out.println("Account created successfully: " + account);
			} else {
				System.out.println("Account creation FAILED.");
			}
		}

	}

}
