package com.babynames;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class BabyNameTest {

	// 2D arrays to store the names and ranks for boys and girls.
	static String[][] boyNames = new String[1000][2];
	static String[][] girlNames = new String[1000][2];

	public static void main(String[] args) {

		// Create a Scanner object to take user input.
		Scanner input = new Scanner(System.in);

		// Initialize variables to store valid year and gender.
		String year = null;
		char gender = '\0';

		try {

			// Start the main loop for user input.
			while (true) {

				// Prompt user for year only if it hasn't been provided correctly yet.
				if (year == null) {

					// Prompt user for year.
					System.out.print("Enter the year (2001-2010): ");
					String yearInput = input.nextLine();

					// Validate the year input (It should be a 4-digit number between 2001 and
					// 2010).
					if (!yearInput.matches("\\d{4}") || Integer.parseInt(yearInput) < 2001
							|| Integer.parseInt(yearInput) > 2010) {

						System.out.println("Invalid year.");

						// Ask if the user wants to continue or exit.
						if (!promptContinue(input)) {

							System.out.println("===== Program exit successful =====");
							break; // Exit if the user doesn't want to continue.

						}

						continue; // Re-prompt for the year if invalid.

					}

					// Store the valid year input.
					year = yearInput;

				}

				if (gender == '\0') {

					// Prompt user for gender (M/F).
					System.out.print("Enter the gender (M/F): ");
					char genderInput = input.nextLine().toUpperCase().charAt(0);

					// Validate the gender input (it should be either 'M' or 'F').
					if (genderInput != 'M' && genderInput != 'F') {

						System.out.println("Invalid gender.");

						// Ask if the user wants to continue or exit.
						if (!promptContinue(input)) {

							System.out.println("===== Program exit successful =====");
							break; // Exit if the user doesn't want to continue.

						}

						continue; // Re-prompt for the gender if invalid.

					}

					// Store the valid gender input.
					gender = genderInput;

				}

				// Prompt user for the name to search.
				System.out.print("Enter the name: ");
				String name = input.nextLine();

				// Validation for name input (It should not be empty).
				if (name.trim().isEmpty()) {

					System.out.println("Invalid name.");

					// Ask if the user wants to continue or exit.
					if (!promptContinue(input)) {

						System.out.println("===== Program exit successful =====");
						break; // Exit if the user doesn't want to continue.

					}

					continue; // Re-prompt for name if invalid.

				}

				// Load the data for the entered year.
				boolean dataLoaded = loadData(year);

				// Initialize rank variable.
				int rank = -1; // Initialize rank to -1 (indicating "not found" by default).

				// If data was loaded successfully, find the rank.
				if (dataLoaded) {

					rank = findRank(name, gender); // Find the rank of the name for the given gender.

				}

				// Display the result based on whether the name was found or not.
				if (dataLoaded) {

					if (rank == -1) {

						// Name was not found in the data.
						System.out.println("The name " + name + " is not ranked in year " + year);

					} else if (rank == -2) {

						// Error occurred while trying to find the rank.

					} else {

						// Name was found, display its rank.
						System.out.println(name + " is ranked #" + rank + " in year " + year);

					}

				}

				// Exit after successful input and processing.
				break; // Exit after successful input and processing.

			}

		} catch (Exception e) {

			// Catch any unexpected errors and display a message.
			System.out.println("An unexpected error occurred: " + e.getMessage());

		} finally {

			// Always close the scanner to prevent resource leaks.
			input.close();

		}

	}

	// Method to load data from the file based on the given year.
	public static boolean loadData(String year) {

		// File name pattern based on the year entered by the user.
		final String FILENAME_PREFIX = "babynameranking";

		// Create a File object for the corresponding year's data file.
		File file = new File("src\\NameRankingsData\\" + FILENAME_PREFIX + year + ".txt");

		try (Scanner fileScanner = new Scanner(file)) {

			int index = 0; // Track the current row in the arrays.

			// Read each line from the file.
			while (fileScanner.hasNextLine()) {

				// Split the line by whitespace.
				String[] line = fileScanner.nextLine().split("\\s+");

				// Store boy's name and count in the boyNames array.
				boyNames[index][0] = line[1]; // Boy's name
				boyNames[index][1] = line[2]; // Boy's count

				// Store girl's name and count in the girlNames array.
				girlNames[index][0] = line[3]; // Girl's name
				girlNames[index][1] = line[4]; // Girl's count

				index++; // Move to the next row.

			}

			return true; // Data was loaded successfully.

		} catch (IOException ioException) {

			// Handle any errors that occur while loading the data file.
			System.out.println("Error Message: Unable to load the data for the year " + year
					+ ". Please try again or contact support.");
			System.out.print("Error: ");
			ioException.printStackTrace();
			return false; // Data was not loaded.

		}

	}

	// Method to find the rank of the given name for the specified gender.
	public static int findRank(String name, char gender) {

		try {

			if (Character.toUpperCase(gender) == 'M') { // If gender is Male.

				for (int i = 0; i < boyNames.length; i++) {

					// Check if the name matches the one in the boyNames array.
					if (boyNames[i][0] != null && boyNames[i][0].equalsIgnoreCase(name)) {
						return i + 1; // Return rank (index + 1).
					}

				}

			} else if (Character.toUpperCase(gender) == 'F') { // If gender is Female.

				for (int i = 0; i < girlNames.length; i++) {

					if (girlNames[i][0] != null && girlNames[i][0].equalsIgnoreCase(name)) {
						return i + 1; // Return rank (index + 1).
					}

				}

			}

		} catch (Exception e) {

			// Handle any unexpected errors during the rank search.
			System.out.println("Error Message: An error occurred while finding the rank: " + e.getMessage());
			return -2; // Return -2 to indicate an error.

		}

		return -1; // Return -1 if the name was not found.

	}

	// Method to prompt the user to continue or exit.
	private static boolean promptContinue(Scanner input) {

		System.out.print("Press 'c' to continue or any key other than 'c' to exit: ");
		String choice = input.nextLine(); // Get the user's choice.
		return choice.equalsIgnoreCase("c"); // Return true if the user chooses 'c', otherwise false.

	}
}
