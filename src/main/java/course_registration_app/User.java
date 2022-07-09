package course_registration_app;

public class User {
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String address;

	public User(String username, String password, String firstName, String lastName, String address) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
	}

	public User(String userName) {
		this.username = userName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {

		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	// method for adding the user to the database
	// if the user is a teacher then they will input a verification key for their
	// first account creation
	// adds user to the database on the table

	// MOTHOD FOR REMOVING USER FROM TABLE
	// removes a user from the table given a user name and password. it will remove
	// it ad the corresponding id.

	// AFTER LOGIN YOU CAN UPDATE YOUR REGISTRATION

	// AS A TEACHER YOU CAN UPDATE THE CLASSES
	// ONCE A CLASS IS REMOVED IT WILL CASCADE DROP ALL USERS.

}
