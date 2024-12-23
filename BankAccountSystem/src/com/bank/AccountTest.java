package com.bank;

//Importing Scanner for user input
import java.util.Scanner;

public class AccountTest {
	// Take the user input
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			processUserAccounts(); // Handle Account operations
			// Catch exceptions
		} catch (InvalidIdException e) {
			System.out.println(e);
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			// Finally block
		} finally {
			scanner.close();
		}
	}

	private static void processUserAccounts() throws InvalidIdException {
		// Checking Account
		CheckingAccount checkingAccount = (CheckingAccount) getAccountDetails("Checking");
		if (checkingAccount == null)
			return; // Exit if user quits

		// Saving Account
		SavingAccount savingAccount = (SavingAccount) getAccountDetails("Saving");
		if (savingAccount == null)
			return; // Exit if user quits

		// Process transactions for Checking Account
		processTransactions(checkingAccount, "Checking Account");

		// Process transactions for Saving Account
		processTransactions(savingAccount, "Saving Account");

		// Display account details
		printResult(checkingAccount, savingAccount);
	}

	private static void processTransactions(Account account, String accountType) {
		boolean validTransaction = false;

		// Withdrawal transaction
		while (!validTransaction) {
			System.out.print("\nEnter the withdrawal amount for " + accountType + " ($): ");
			String input = scanner.nextLine().trim();

			if (input.equalsIgnoreCase("q")) {
				System.out.println("Program terminated");
				System.exit(0);
			}

			if (isValidAmount(input)) {
				double withdrawAmount = Double.parseDouble(input);

				if (withdrawAmount <= 0) {
					System.out.println("Withdrawal amount must be positive.");
					System.out.print("Press 'q' to exit or any other key to proceed: ");
					String continueChoice = scanner.nextLine().trim();
					if (continueChoice.equalsIgnoreCase("q")) {
						System.out.println("Program terminated");
						System.exit(0);
					}
					continue;
				}

				account.withdraw(withdrawAmount);
				validTransaction = true;
			} else {
				System.out.println("Amount value is not valid.");
				System.out.print("Press 'q' to exit or any other key to proceed: ");
				String continueChoice = scanner.nextLine().trim();
				if (continueChoice.equalsIgnoreCase("q")) {
					System.out.println("Program terminated");
					System.exit(0);
				}
				continue;
			}
		}

		validTransaction = false;

		// Deposit transaction
		while (!validTransaction) {
			System.out.print("Enter the deposit amount for " + accountType + " ($): ");
			String input = scanner.nextLine().trim();

			if (input.equalsIgnoreCase("q")) {
				System.out.println("Program terminated");
				System.exit(0);
			}

			if (isValidAmount(input)) {
				double depositAmount = Double.parseDouble(input);

				if (depositAmount <= 0) {
					System.out.println("Deposit amount must be positive.");
					System.out.print("Press 'q' to exit or any other key to proceed: ");
					String continueChoice = scanner.nextLine().trim();
					if (continueChoice.equalsIgnoreCase("q")) {
						System.out.println("Program terminated");
						System.exit(0);
					}
					continue;
				}

				account.deposit(depositAmount);
				validTransaction = true;
			} else {
				System.out.println("Amount value is not valid.");
				System.out.print("Press 'q' to exit or any other key to proceed: ");
				String continueChoice = scanner.nextLine().trim();
				if (continueChoice.equalsIgnoreCase("q")) {
					System.out.println("Program terminated");
					System.exit(0);
				}
				continue;
			}
		}
	}

	// Method to validate numeric input
	private static boolean isValidAmount(String input) {
		return input.matches("[0-9]+(\\.[0-9]+)?");
	}

	// Method to get the Account details
	private static Account getAccountDetails(String accountType) throws InvalidIdException {
		Account account = null;
		boolean validInput = false;

		while (!validInput) {
			System.out.println("\nProvide details for " + accountType + " Account:");

			int id = getValidInput("Account ID: ", "Please enter a valid positive ID.");
			if (id == -1)
				return null;

			String firstName = getValidNameInput("First Name: ", "First name cannot be empty.");
			if (firstName == null)
				return null;

			String lastName = getValidNameInput("Last Name: ", "Last name cannot be empty.");
			if (lastName == null)
				return null;

			double balance = getValidBalanceInput("Balance ($): ", "Please enter a valid positive balance.");
			if (balance == -1)
				return null;

			double rate = getValidRateInput("Annual Interest Rate (%): ", "Interest rate should be between 0 and 100.");
			if (rate == -1)
				return null;

			if (accountType.equals("Checking")) {
				account = new CheckingAccount(id, firstName, lastName, balance, rate);
			} else {
				account = new SavingAccount(id, firstName, lastName, balance, rate);
			}

			validInput = true;
		}
		return account;
	}

	// Method to validate Account ID
	private static int getValidInput(String prompt, String errorMessage) {
		while (true) {
			System.out.print(prompt);
			String input = scanner.nextLine().trim();
			if (input.equalsIgnoreCase("q")) {
				System.out.println("Program terminated");
				return -1;
			}

			if (input.matches("\\d+") && Integer.parseInt(input) > 0) {
				return Integer.parseInt(input);
			} else {
				System.out.println(errorMessage);
			}

			System.out.print("Do you want to quit (q) or continue (any key)? ");
			String choice = scanner.nextLine().trim();
			if (choice.equalsIgnoreCase("q")) {
				System.out.println("Program terminated");
				return -1;
			}
		}
	}

	// Method to validate name
	private static String getValidNameInput(String prompt, String errorMessage) {
		while (true) {
			System.out.print(prompt);
			String input = scanner.nextLine().trim();
			if (input.equalsIgnoreCase("q")) {
				System.out.println("Program terminated");
				return null;
			}
			if (!input.isEmpty()) {
				return input;
			} else {
				System.out.println(errorMessage);
			}

			System.out.print("Do you want to quit (q) or continue (any key)? ");
			String choice = scanner.nextLine().trim();
			if (choice.equalsIgnoreCase("q")) {
				System.out.println("Program terminated");
				return null;
			}
		}
	}

	// Method to validate Account balance
	private static double getValidBalanceInput(String prompt, String errorMessage) {
		while (true) {
			System.out.print(prompt);
			String input = scanner.nextLine().trim();
			if (input.equalsIgnoreCase("q")) {
				System.out.println("Program terminated");
				return -1;
			}

			if (input.matches("\\d+(\\.\\d+)?") && Double.parseDouble(input) >= 0) {
				return Double.parseDouble(input);
			} else {
				System.out.println(errorMessage);
			}

			System.out.print("Do you want to quit (q) or continue (any key)? ");
			String choice = scanner.nextLine().trim();
			if (choice.equalsIgnoreCase("q")) {
				System.out.println("Program terminated");
				return -1;
			}
		}
	}

	// Method to validate Interest rate
	private static double getValidRateInput(String prompt, String errorMessage) {
		while (true) {
			System.out.print(prompt);
			String input = scanner.nextLine().trim();
			if (input.equalsIgnoreCase("q")) {
				System.out.println("Program terminated");
				return -1;
			}

			if (input.matches("\\d+(\\.\\d+)?") && Double.parseDouble(input) >= 0) {
				return Double.parseDouble(input);
			} else {
				System.out.println(errorMessage);
			}

			System.out.print("Do you want to quit (q) or continue (any key)? ");
			String choice = scanner.nextLine().trim();
			if (choice.equalsIgnoreCase("q")) {
				System.out.println("Program terminated");
				return -1;
			}
		}
	}

	// Method to display account details
	private static void printResult(CheckingAccount checkingAccount, SavingAccount savingAccount) {
		System.out.println("\nChecking Account Details:\n" + checkingAccount);
		System.out.println("\nSaving Account Details:\n" + savingAccount);
	}
}
