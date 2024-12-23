package com.bank;

// SavingAccount class inherits from Account
public class SavingAccount extends Account {

	private double transactionFee; // Transaction fee

	// Default constructor
	public SavingAccount() {
		setTransactionFee(5.0);
	}

	// Parameterized constructor
	public SavingAccount(int id, String firstName, String lastName, double balance, double annualInterestRate)
			throws InvalidIdException {
		super(id, firstName, lastName, balance);
		setAnnualInterestRate(annualInterestRate);
		setTransactionFee(5.0);
	}

	// Setter for Transaction fee
	public void setTransactionFee(double transactionFee) throws IllegalArgumentException {
		if (transactionFee <= 0) {
			throw new IllegalArgumentException("Transaction fee must be positive.");
		}
		this.transactionFee = transactionFee;
	}

	// Getter for the Transaction fee
	public double getTransactionFee() {
		return transactionFee;
	}

	// Overriding withdraw method
	@Override
	public void withdraw(double amount) throws IllegalArgumentException {
		if (getBalance() < amount + getTransactionFee()) {
			throw new IllegalArgumentException("Funds are not sufficient for the withdrawal.");
		}
		setBalance(getBalance() - (amount + getTransactionFee()));
	}

	// Overriding deposit method
	@Override
	public void deposit(double amount) throws IllegalArgumentException {
		if (amount <= 0) {
			throw new IllegalArgumentException("Deposit amount must be positive.");
		}
		setBalance(getBalance() + amount - getTransactionFee());
	}

	// Overriding toString() method
	@Override
	public String toString() {
		return super.toString() + "\nAccount Type: Saving Account" + "\nTransaction Fee: " + "$" + getTransactionFee();
	}

}
