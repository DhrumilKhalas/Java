package com.lockers;

public class LockerStatus {

	public static void main(String[] args) {

		// Initialize the number of lockers
		int number_of_lockers = 100;

		// Create an array of booleans to represent the lockers, all initially closed
		// (false)
		boolean[] lockers = new boolean[number_of_lockers + 1]; // Using 1-based indexing

		// Loop through each student
		for (int student = 1; student <= number_of_lockers; student++) {
			// Each student toggles the lockers based on their number
			for (int locker = student; locker <= number_of_lockers; locker += student) {
				lockers[locker] = !lockers[locker]; // Toggle the locker state
			}
		}

		// Output the state of the lockers
		for (int locker = 1; locker <= number_of_lockers; locker++) {
			if (lockers[locker]) { // If the locker is open
				System.out.println("Locker L" + locker + " is open");
			}
		}

	}

}
