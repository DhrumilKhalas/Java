//	Before running the application, configure the database connection by replacing the placeholders in the getDatabaseConnection() method with your actual database details:

//	'<HOST>': Your database host (e.g., 'localhost').
//	'<PORT>': Your database port (default is '3306' for MySQL).
//	'<DATABASE_NAME>': The name of your database (e.g., 'MyDB').
//	'<USERNAME>': Your database user name (e.g., 'root').
//	'<PASSWORD>': Your database password.

package com.staff;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class DatabaseManager {

	// Method to establish and return a database connection
	private static Connection getDatabaseConnection() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:mysql://<HOST>:<PORT>/<DATABASE_NAME>?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
				"<USERNAME>", "<PASSWORD>");
	}

	// Method to view all staff records from the database
	public static void viewAllStaff() throws SQLException {
		String query = "SELECT * FROM staff";
		ArrayList<Staff> staffList = new ArrayList<>();

		try (Connection conn = getDatabaseConnection();
				Statement stmt = conn.createStatement();
				ResultSet rset = stmt.executeQuery(query)) {

			// Check if no records are found
			if (!rset.isBeforeFirst()) {
				System.out.println("\nNo records found.");
			} else {
				// Fetch and store staff details
				while (rset.next()) {
					String id = rset.getString("id");
					String lastName = rset.getString("lastName");
					String firstName = rset.getString("firstName");
					Date dateOfBirth = rset.getDate("dateOfBirth");
					String address = rset.getString("address");
					String city = rset.getString("city");
					String state = rset.getString("state");
					String telephone = rset.getString("telephone");
					String email = rset.getString("email");

					// Create Staff object and add to the list
					Staff staff = new Staff(id, lastName, firstName, dateOfBirth, address, city, state, telephone,
							email);
					staffList.add(staff);
				}

				System.out.println(
						"\nThe records in the Staff table sorted by staff age in ascending order (from youngest to oldest):\n");

				// Sort and print staff records by age (youngest to oldest)
				Collections.sort(staffList);

				for (Staff staff : staffList) {
					System.out.println(staff);
				}
			}
		}
	}

	// Method to insert a new staff record into the database
	public static void insertStaff(String id, String lastName, String firstName, Date dateOfBirth, String address,
			String city, String state, String telephone, String email) throws SQLException {
		String query = "INSERT INTO staff (id, lastName, firstName, dateOfBirth, address, city, state, telephone, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = getDatabaseConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
			// Set the values for the insert query
			pstmt.setString(1, id);
			pstmt.setString(2, lastName);
			pstmt.setString(3, firstName);
			pstmt.setDate(4, new java.sql.Date(dateOfBirth.getTime()));
			pstmt.setString(5, address);
			pstmt.setString(6, city);
			pstmt.setString(7, state);
			pstmt.setString(8, telephone);
			pstmt.setString(9, email);
			// Execute the insert operation
			int rowsInserted = pstmt.executeUpdate();
			System.out.println("\n---------- Inserted " + rowsInserted + " record(s) into the Staff table. ----------");
		}
	}

	// Method to update an existing staff record in the database
	public static void updateStaff(String id, String newAddress, String newCity, String newState, String newTelephone,
			String newEmail) throws SQLException {
		String query = "UPDATE staff SET address = ?, city = ?, state = ?, telephone = ?, email = ? WHERE id = ?";

		try (Connection conn = getDatabaseConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
			// Set the new values for the update query
			pstmt.setString(1, newAddress);
			pstmt.setString(2, newCity);
			pstmt.setString(3, newState);
			pstmt.setString(4, newTelephone);
			pstmt.setString(5, newEmail);
			pstmt.setString(6, id);
			// Execute the update operation
			int rowsUpdated = pstmt.executeUpdate();
			System.out.println("\n---------- Updated " + rowsUpdated + " record(s) in the Staff table. ----------");
		}
	}

}
