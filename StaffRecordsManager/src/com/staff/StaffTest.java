package com.staff;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class StaffTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			while (true) {
				// Display the main menu
				System.out.println("\nMenu:");
				System.out.println("1. View All Staff");
				System.out.println("2. Insert Staff");
				System.out.println("3. Update Staff");
				System.out.println("4. Exit");
				System.out.print("\nChoose an option: ");
				// Get user input for menu option
				int choice = getIntInput(scanner, "\nInvalid input! Please enter a valid option (1-4): ");

				switch (choice) {
				case 1 -> {
					// View all staff details
					DatabaseManager.viewAllStaff();
					System.out.println("\n********** Thank you for using the system. **********");
					return; // Exit after viewing staff
				}
				case 2 -> {
					// Insert new staff into the database
					insertStaff(scanner);
					System.out.println("\n********** Thank you for using the system. **********");
					return; // Exit after inserting staff
				}
				case 3 -> {
					// Update existing staff details
					updateStaff(scanner);
					System.out.println("\n********** Thank you for using the system. **********");
					return; // Exit after updating staff
				}
				case 4 -> {
					// Exit the program
					System.out.println("\n********** Thank you for using the system. **********");
					return;
				}
				default -> System.out.println("\nInvalid choice. Please choose between 1 and 4.");
				}
			}
		} catch (SQLException e) {
			// Handle SQL exceptions
			System.out.println("Database error: " + e);
		} catch (IllegalArgumentException e) {
			// Handle invalid input
			System.out.println("Invalid input: " + e);
		} catch (Exception e) {
			// Handle any unexpected exceptions
			System.out.println("An unexpected error occurred: " + e);
		} finally {
			scanner.close(); // Close the scanner resource
		}
	}

	// Method to insert a new staff record into the database
	private static void insertStaff(Scanner scanner) throws SQLException {
		System.out.println("\nPlease provide the following values to insert the data:");
		// Get staff details from the user
		System.out.print("Enter Staff ID (9 characters): ");
		String id = getValidStaffId(scanner); // Validate Staff ID
		System.out.print("Enter Last Name (max 15 characters): ");
		String lastName = getValidNameInput(scanner, "Last Name"); // Validate Last Name
		System.out.print("Enter First Name (max 15 characters): ");
		String firstName = getValidNameInput(scanner, "First Name"); // Validate First Name
		System.out.print("Enter Date of Birth (yyyy-MM-dd): ");
		String dob = getValidDateInput(scanner); // Validate Date of Birth
		System.out.print("Enter Address (max 20 characters): ");
		scanner.nextLine();
		String address = getValidAddress(scanner); // Validate Address
		System.out.print("Enter City (max 20 characters): ");
		String city = getValidCity(scanner); // Validate City
		System.out.print("Enter State (2 characters): ");
		String state = getValidState(scanner); // Validate State
		System.out.print("Enter Telephone (10 digits): ");
		String telephone = getValidPhoneInput(scanner); // Validate Telephone
		System.out.print("Enter Email (max 40 characters): ");
		String email = getValidEmailInput(scanner); // Validate Email

		// Insert the staff details into the database
		DatabaseManager.insertStaff(id, lastName, firstName, Date.valueOf(dob), address, city, state, telephone, email);
	}

	// Method to update existing staff details in the database
	private static void updateStaff(Scanner scanner) throws SQLException {
		System.out.println("\nPlease provide the following values to update the data:");
		// Get Staff ID to update
		System.out.print("Enter Staff ID to update: ");
		String id = scanner.next();
		System.out.print("Enter New Address (max 20 characters): ");
		scanner.nextLine();
		String newAddress = getValidAddress(scanner); // Validate new Address
		System.out.print("Enter New City (max 20 characters): ");
		String newCity = getValidCity(scanner); // Validate new City
		System.out.print("Enter New State (2 characters): ");
		String newState = getValidState(scanner); // Validate new State
		System.out.print("Enter New Telephone (10 digits): ");
		String newTelephone = getValidPhoneInput(scanner); // Validate new Telephone
		System.out.print("Enter New Email (max 40 characters): ");
		String newEmail = getValidEmailInput(scanner); // Validate new Email

		// Update the staff details in the database
		DatabaseManager.updateStaff(id, newAddress, newCity, newState, newTelephone, newEmail);
	}

	// Method to get integer input and handle invalid entries
	private static int getIntInput(Scanner scanner, String errorMessage) {
		while (!scanner.hasNextInt()) {
			System.out.print(errorMessage); // Show error if input is invalid
			scanner.next();
		}
		return scanner.nextInt();
	}

	// Method to validate staff ID (must be exactly 9 characters)
	private static String getValidStaffId(Scanner scanner) {
		while (true) {
			String input = scanner.next();
			if (input.length() == 9) {
				return input; // Return valid input
			} else {
				System.out.println("Invalid Staff ID. It must be exactly 9 characters.");
				System.out.print("Enter Staff ID: ");
			}
		}
	}

	// Method to validate name input (must be at most 15 characters)
	private static String getValidNameInput(Scanner scanner, String fieldName) {
		while (true) {
			String input = scanner.next();
			if (input.length() <= 15) {
				return input; // Return valid input
			} else {
				System.out.println("Invalid " + fieldName + ". It must be at most 15 characters.");
				System.out.print("Enter " + fieldName + ": ");
			}
		}
	}

	// Method to validate date input (must be in yyyy-MM-dd format and not in the
	// future)
	private static String getValidDateInput(Scanner scanner) throws IllegalArgumentException {
		while (true) {
			String input = scanner.next();
			try {
				// Check if the date is in correct format
				Date date = Date.valueOf(input);

				// Check if the year is not in the future
				int currentYear = java.time.LocalDate.now().getYear();
				int inputYear = date.toLocalDate().getYear();
				if (inputYear > currentYear) {
					System.out.println("Invalid Date. Year cannot be in the future.");
					System.out.print("Enter Date of Birth (yyyy-MM-dd): ");
					continue;
				}

				// Check if the month is between 1 and 12
				int inputMonth = date.toLocalDate().getMonthValue();
				if (inputMonth < 1 || inputMonth > 12) {
					System.out.println("Invalid Month. It must be between 1 and 12.");
					System.out.print("Enter Date of Birth (yyyy-MM-dd): ");
					continue;
				}

				// Check if the day is valid for the given month
				int inputDay = date.toLocalDate().getDayOfMonth();
				int maxDay = java.time.Month.of(inputMonth).length(java.time.Year.isLeap(inputYear));
				if (inputDay < 1 || inputDay > maxDay) {
					System.out.println("Invalid Day. Please enter a valid day for the month.");
					System.out.print("Enter Date of Birth (yyyy-MM-dd): ");
					continue;
				}

				return input; // Return valid date
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid Date of Birth. Please enter in yyyy-MM-dd format.");
				System.out.print("Enter Date of Birth (yyyy-MM-dd): ");
			}
		}
	}

	// Method to validate address input (max 20 characters)
	private static String getValidAddress(Scanner scanner) {
		while (true) {
			String input = scanner.nextLine();
			if (input.length() <= 20) {
				return input; // Return valid address
			} else {
				System.out.println("Invalid address. It must be at most 20 characters.");
				System.out.print("Enter Address: ");
			}
		}
	}

	// Method to validate city input (max 20 characters)
	private static String getValidCity(Scanner scanner) {
		while (true) {
			String input = scanner.next();
			if (input.length() <= 20) {
				return input; // Return valid city
			} else {
				System.out.println("Invalid city. It must be at most 20 characters.");
				System.out.print("Enter City: ");
			}
		}
	}

	// Method to validate state input (exactly 2 characters)
	private static String getValidState(Scanner scanner) {
		while (true) {
			String input = scanner.next();
			if (input.length() == 2) {
				return input; // Return valid state
			} else {
				System.out.println("Invalid state. It must be exactly 2 characters.");
				System.out.print("Enter State: ");
			}
		}
	}

	// Method to validate telephone input (must be exactly 10 digits)
	private static String getValidPhoneInput(Scanner scanner) {
		while (true) {
			String input = scanner.next();
			if (input.matches("\\d{10}")) {
				return input; // Return valid phone number
			} else {
				System.out.println("Invalid phone number. It must be exactly 10 digits.");
				System.out.print("Enter Telephone: ");
			}
		}
	}

	// Method to validate email input (valid format and max 40 characters)
	private static String getValidEmailInput(Scanner scanner) {
		while (true) {
			String input = scanner.next();
			if (input.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$") && input.length() <= 40) {
				return input; // Return valid email
			} else {
				System.out.println("Invalid email address. It must be a valid email and no longer than 40 characters.");
				System.out.print("Enter Email: ");
			}
		}
	}
}
