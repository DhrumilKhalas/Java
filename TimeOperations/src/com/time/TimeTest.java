package com.time;

import java.util.Scanner;

public class TimeTest {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		try {
			// Input for time1 (hour, minute, second)
			Time t1 = inputTime("Enter time1 (hour minute second): ", scanner);
			System.out.println(t1);

			// Input for time2 (elapsed time in seconds)
			Time t2 = inputElapsedTime("Enter time2 (elapsed time): ", scanner);
			System.out.println(t2);

			// Comparing time1 and time2
			System.out.println("time1.compareTo(time2)? " + t1.compareTo(t2));

			// Cloning time1 to create time3 and comparing with it
			Time t3 = (Time) t1.clone();
			System.out.println("time3 is created as a clone of time1");
			System.out.println("time1.compareTo(time3)? " + t1.compareTo(t3));

		} catch (InvalidElapsedTimeException e) {
			System.out.println(e); // Handle invalid elapsed time exception
		} catch (CloneNotSupportedException e) {
			System.out.println(e); // Handle cloning exception
		} catch (Exception e) {
			System.out.println(e); // Handle other exceptions
		} finally {
			scanner.close(); // Close the scanner to release resources
		}
	}

	// Method to input time in hours, minutes, and seconds
	private static Time inputTime(String prompt, Scanner scanner) {
		int hour = 0, minute = 0, second = 0;
		boolean validInput = false;

		while (!validInput) {
			System.out.print(prompt);
			String input = scanner.nextLine();
			String[] timeParts = input.split(" "); // Split input into hours, minutes, seconds

			if (timeParts.length == 3) {
				try {
					hour = Integer.parseInt(timeParts[0]);
					minute = Integer.parseInt(timeParts[1]);
					second = Integer.parseInt(timeParts[2]);

					// Validate that time is non-negative
					if (hour < 0 || minute < 0 || second < 0) {
						System.out.println("Error: Hours, minutes, and seconds must be non-negative numbers.");
						handleRetry(scanner); // Handle retry on invalid input
						continue;
					}
					validInput = true;
				} catch (NumberFormatException e) {
					System.out.println(
							"Error: Please enter valid numbers without decimal points for hours, minutes, and seconds.");
					handleRetry(scanner); // Handle retry on invalid number format
					continue;
				}
			} else {
				System.out.println(
						"Error: Please enter exactly 3 values separated by spaces for hours, minutes, and seconds.");
				handleRetry(scanner); // Handle retry on wrong input length
				continue;
			}
		}
		return new ConcreteTime(hour, minute, second); // Return ConcreteTime object
	}

	// Method to input elapsed time in seconds
	private static Time inputElapsedTime(String prompt, Scanner scanner) throws InvalidElapsedTimeException {
		boolean validInput = false;
		long elapsedTime = 0;

		while (!validInput) {
			System.out.print(prompt);
			String input = scanner.nextLine();
			try {
				elapsedTime = Long.parseLong(input); // Parse elapsed time as long
				if (elapsedTime < 0) {
					System.out.println(
							"Error: Please enter a non-negative number without decimal points for elapsed time.");
					handleRetry(scanner); // Handle retry on negative input
					continue;
				}
				validInput = true;
			} catch (NumberFormatException e) {
				System.out.println(
						"Error: Please enter a valid non-negative number without decimal points for elapsed time.");
				handleRetry(scanner); // Handle retry on invalid number format
				continue;
			}
		}
		return new ConcreteTime(elapsedTime); // Return ConcreteTime object with elapsed time
	}

	// Method to handle retry logic (quit or continue)
	private static void handleRetry(Scanner scanner) {
		while (true) {
			System.out.print("Press 'q' to quit or any other key than 'q' to continue: ");
			String choice = scanner.nextLine().trim().toLowerCase();
			if (choice.equals("q")) {
				System.out.println("----- You have chosen to exit. Thank you for using our service. -----");
				System.exit(0); // Exit the program
			}
			return; // Return to continue the input loop
		}
	}

}
