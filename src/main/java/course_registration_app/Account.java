package course_registration_app;

public class Account {
	private int userId;
	private String accountType;
	private String accountNumber;
	private Double balance;

	public Account(int userId, String accountType, String accountNumber, Double balance) {
		this.userId = userId;
		this.accountType = accountType;
		this.accountNumber = accountNumber;
		this.balance = balance;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [userId=" + userId + ", accountType=" + accountType + ", accountNumber=" + accountNumber
				+ ", balance=" + balance + "]";
	}

}
