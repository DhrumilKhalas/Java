package com.bank;

// Abstract class
public abstract class Account {

	private int id = 0; // Account ID
	private String firstName; // First name
	private String lastName; // Last name
	private double balance = 0; // Balance
	private double annualInterestRate = 0; // Annual interest rate
	private java.util.Date dateCreated; // Creation date

	// Default constructor
	protected Account() {
		this.dateCreated = new java.util.Date();
	}

	// Parameterized constructor
	protected Account(int id, String firstName, String lastName, double balance) throws InvalidIdException {
		setId(id); //
		setFirstName(firstName);
		setLastName(lastName);
		setBalance(balance);
		this.dateCreated = new java.util.Date();
	}

	// Setter for Account ID
	public void setId(int id) throws InvalidIdException {
		if (id <= 0) {
			throw new InvalidIdException(id);
		}
		this.id = id;
	}

	// Getter for Account ID
	public int getId() {
		return id;
	}

	// Setter for First name
	public void setFirstName(String firstName) throws IllegalArgumentException {
		if (firstName.trim().isEmpty()) {
			throw new IllegalArgumentException("First name is empty.");
		}
		this.firstName = firstName;
	}

	// Getter for First name
	public String getFirstName() {
		return firstName;
	}

	// Setter for Last name
	public void setLastName(String lastName) throws IllegalArgumentException {
		if (lastName.trim().isEmpty()) {
			throw new IllegalArgumentException("Last name is empty.");
		}
		this.lastName = lastName;
	}

	// Getter for Last name
	public String getLastName() {
		return lastName;
	}

	// Setter for Balance
	public void setBalance(double balance) throws IllegalArgumentException {
		if (Double.isNaN(balance)) {
			throw new IllegalArgumentException("Balance value is invalid.");
		}
		this.balance = balance;
	}

	// Getter for Balance
	public double getBalance() {
		return balance;
	}

	// Setter for Annual interest rate
	public void setAnnualInterestRate(double annualInterestRate) throws IllegalArgumentException {
		if (annualInterestRate < 0) {
			throw new IllegalArgumentException("Annual interest rate is negative.");
		}
		this.annualInterestRate = annualInterestRate;
	}

	// Getter for Annual interest rate
	public double getAnnualInterestRate() {
		return annualInterestRate;
	}

	// Getter for Account creation date
	public java.util.Date getDateCreated() {
		return dateCreated;
	}

	// Method to calculate Monthly interest rate
	public double getMonthlyInterestRate() {
		return Math.round((getAnnualInterestRate() / 12) * 1000.0) / 1000.0;
	}

	// Method to calculate Monthly interest
	public double getMonthlyInterest() {
		return Math.round((getBalance() * (getMonthlyInterestRate() / 100)) * 1000.0) / 1000.0;
	}

	// Abstract method
	public abstract void withdraw(double amount) throws IllegalArgumentException;

	// Abstract method
	public abstract void deposit(double amount) throws IllegalArgumentException;

	// Overriding toString() method
	@Override
	public String toString() {
		return "Account ID: " + getId() + "\nCustomer Name: " + getFirstName() + " " + getLastName() + "\nBalance: "
				+ "$" + getBalance() + "\nMonthly Interest: " + "$" + getMonthlyInterest() + "\nMonthly Interest Rate: "
				+ getMonthlyInterestRate() + "%" + "\nAnnual Interest Rate: " + getAnnualInterestRate() + "%"
				+ "\nAccount Creation Date: " + getDateCreated();
	}

}
