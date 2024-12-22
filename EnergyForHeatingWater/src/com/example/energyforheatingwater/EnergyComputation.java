package com.example.energyforheatingwater;

import java.util.Scanner;

public class EnergyComputation {

	public static void main(String[] args) {

		// Creates a Scanner object.
		Scanner input = new Scanner(System.in);

		// Prompts the user to enter the amount of water in kilograms.
		System.out.print("Enter the amount of water in kilograms (kg): ");
		double waterWeight = input.nextDouble();

		String errorMessage = "";

		if (waterWeight < 0) {

			errorMessage = "Water amount cannot be negative number!";
			System.out.print(errorMessage);

		} else {

			// Prompts the user to enter the initial and final temperatures of the water.
			System.out.print("Enter the initial temperature (°C): ");
			double initialTemperature = input.nextDouble();

			System.out.print("Enter the final temperature (°C): ");
			double finalTemperature = input.nextDouble();

			// Energy computation
			double result = waterWeight * (finalTemperature - initialTemperature) * 4184;
			System.out.print("The energy needed is: " + result + " Joules");

		}

		input.close();

	}

}
