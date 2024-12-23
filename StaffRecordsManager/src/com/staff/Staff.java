package com.staff;

import java.util.Date;

public class Staff implements Comparable<Staff> {

	private String id; // Staff ID
	private String lastName; // Last name of the staff
	private String firstName; // First name of the staff
	private Date dateOfBirth; // Date of Birth of the staff
	private String address; // Address of the staff
	private String city; // City where the staff lives
	private String state; // State of the staff
	private String telephone; // Telephone number of the staff
	private String email; // Email address of the staff

	// Default constructor
	public Staff() {

	}

	// Constructor to initialize all attributes of the staff object
	public Staff(String id, String lastName, String firstName, Date dateOfBirth, String address, String city,
			String state, String telephone, String email) {
		setID(id);
		setLastName(lastName);
		setFirstName(firstName);
		setDateOfBirth(dateOfBirth);
		setAddress(address);
		setCity(city);
		setState(state);
		setTelephone(telephone);
		setEmail(email);
	}

	// Sets the staff ID
	public void setID(String id) throws IllegalArgumentException {
		if (id == null || id.length() != 9) {
			throw new IllegalArgumentException("Invalid Staff ID. It must be exactly 9 characters.");
		}
		this.id = id;
	}

	// Gets the staff ID
	public String getID() {
		return id;
	}

	// Sets the last name of the staff
	public void setLastName(String lastName) throws IllegalArgumentException {
		if (lastName == null || lastName.length() > 15) {
			throw new IllegalArgumentException("Invalid Last Name. It must be at most 15 characters.");
		}
		this.lastName = lastName;
	}

	// Gets the last name of the staff
	public String getLastName() {
		return lastName;
	}

	// Sets the first name of the staff
	public void setFirstName(String firstName) throws IllegalArgumentException {
		if (firstName == null || firstName.length() > 15) {
			throw new IllegalArgumentException("Invalid First Name. It must be at most 15 characters.");
		}
		this.firstName = firstName;
	}

	// Gets the first name of the staff
	public String getFirstName() {
		return firstName;
	}

	// Sets the date of birth for the staff
	public void setDateOfBirth(Date dateOfBirth) {
		// Validate if the date is null
		if (dateOfBirth == null) {
			throw new IllegalArgumentException("Date of Birth cannot be null.");
		}

		this.dateOfBirth = dateOfBirth;

	}

	// Gets the date of birth of the staff
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	// Sets the address for the staff
	public void setAddress(String address) throws IllegalArgumentException {
		if (address == null || address.length() > 20) {
			throw new IllegalArgumentException("Invalid Address. It must be at most 20 characters.");
		}
		this.address = address;
	}

	// Gets the address of the staff
	public String getAddress() {
		return address;
	}

	// Sets the city for the staff
	public void setCity(String city) throws IllegalArgumentException {
		if (city == null || city.length() > 20) {
			throw new IllegalArgumentException("Invalid City. It must be at most 20 characters.");
		}
		this.city = city;
	}

	// Gets the city of the staff
	public String getCity() {
		return city;
	}

	// Sets the state for the staff
	public void setState(String state) throws IllegalArgumentException {
		if (state == null || state.length() != 2) {
			throw new IllegalArgumentException("Invalid State. It must be exactly 2 characters.");
		}
		this.state = state;
	}

	// Gets the state of the staff
	public String getState() {
		return state;
	}

	// Sets the telephone number for the staff
	public void setTelephone(String telephone) throws IllegalArgumentException {
		if (telephone == null || !telephone.matches("\\d{10}")) {
			throw new IllegalArgumentException("Invalid Telephone. It must be exactly 10 digits.");
		}
		this.telephone = telephone;
	}

	// Gets the telephone number of the staff
	public String getTelephone() {
		return telephone;
	}

	// Sets the email address for the staff
	public void setEmail(String email) throws IllegalArgumentException {
		if (email == null || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
				|| email.length() > 40) {
			throw new IllegalArgumentException(
					"Invalid Email. It must be a valid email and no longer than 40 characters.");
		}
		this.email = email;
	}

	// Gets the email address of the staff
	public String getEmail() {
		return email;
	}

	// Compares this staff object with another staff object based on their age
	@Override
	public int compareTo(Staff other) {
		return this.dateOfBirth.compareTo(other.dateOfBirth);
	}

	// Returns a string representation of the staff object with all details
	@Override
	public String toString() {
		return "ID: " + getID() + "\nLast Name: " + getLastName() + "\nFirst Name: " + getFirstName()
				+ "\nDate of Birth: " + getDateOfBirth() + "\nAddress: " + getAddress() + "\nCity: " + getCity()
				+ "\nState: " + getState() + "\nTelephone: " + getTelephone() + "\nEmail: " + getEmail()
				+ "\n--------------------";
	}
}
