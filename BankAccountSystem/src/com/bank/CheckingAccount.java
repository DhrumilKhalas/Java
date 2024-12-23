package com.bank;

// CheckingAccount class inherits from Account
public class CheckingAccount extends Account {
	private double overdraftLimit; // Overdraft limit

	// Default constructor
	public CheckingAccount() {
		setOverdraftLimit(1000.0);
	}

	// Parameterized constructor
	public CheckingAccount(int id, String firstName, String lastName, double balance, double annualInterestRate)
			throws InvalidIdException {
		super(id, firstName, lastName, balance);
		setAnnualInterestRate(annualInterestRate);
		setOverdraftLimit(1000.0);
	}

	// Setter for Overdraft limit
	public void setOverdraftLimit(double overdraftLimit) throws IllegalArgumentException {
		if (overdraftLimit < 0) {
			throw new IllegalArgumentException("Overdraft limit cannot be negative.");
		}
		this.overdraftLimit = overdraftLimit;
	}

	// Getter for Overdraft limit
	public double getOverdraftLimit() {
		return overdraftLimit;
	}

	// Method to withdraw an amount
	@Override
	public void withdraw(double amount) throws IllegalArgumentException {
		if (amount > getBalance() + getOverdraftLimit()) {
			throw new IllegalArgumentException("Withdrawal amount exceeds available balance and overdraft limit.");
		}
		setBalance(getBalance() - amount);
	}

	// Method to deposit an amount
	@Override
	public void deposit(double amount) throws IllegalArgumentException {
		if (amount <= 0) {
			throw new IllegalArgumentException("Deposit amount must be positive.");
		}
		setBalance(getBalance() + amount);
	}

	// Overriding toString() method
	@Override
	public String toString() {
		return super.toString() + "\nAccount Type: Checking Account" + "\nOverdraft Limit: " + "$"
				+ getOverdraftLimit();
	}

}
