package com.time;

public class ConcreteTime extends Time {

	private String timeLabel; // Label for the time (e.g., "time1" or "time2")

	// Default constructor
	public ConcreteTime() {

	}

	// Constructor with hour, minute, second
	public ConcreteTime(int hour, int minute, int second) {
		super(hour, minute, second);
		setTimeLabel("time1"); // Default label
	}

	// Constructor with elapsed time
	public ConcreteTime(long elapsedTime) throws InvalidElapsedTimeException {
		super(elapsedTime);
		setTimeLabel("time2"); // Default label
	}

	// Sets the time label (either "time1" or "time2")
	public void setTimeLabel(String timeLabel) throws IllegalArgumentException {
		if (!timeLabel.equals("time1") && !timeLabel.equals("time2")) {
			throw new IllegalArgumentException("Invalid time label. Only 'time1' or 'time2' are allowed.");
		}
		this.timeLabel = timeLabel;
	}

	// Returns the time label
	public String getTimeLabel() {
		return timeLabel;
	}

	// Returns the hour from time in milliseconds
	@Override
	public int getHour() {
		return (int) (getTime() / (1000 * 60 * 60)) % 24;
	}

	// Returns the minute from time in milliseconds
	@Override
	public int getMinute() {
		return (int) (getTime() / (1000 * 60)) % 60;
	}

	// Returns the second from time in milliseconds
	@Override
	public int getSecond() {
		return (int) (getTime() / 1000) % 60;
	}

	// Returns total elapsed seconds from time in milliseconds
	@Override
	public long getSeconds() {
		return (getTime() / 1000);
	}

	// Converts time to a string format (hours, minutes, seconds)
	@Override
	public String toString() {
		String hourStr = ((getHour() == 0 || getHour() == 1) ? " hour " : " hours ");
		String minuteStr = ((getMinute() == 0 || getMinute() == 1) ? " minute " : " minutes ");
		String secondStr = ((getSecond() == 0 || getSecond() == 1) ? " second" : " seconds");

		return getHour() + hourStr + getMinute() + minuteStr + getSecond() + secondStr + "\nElapsed seconds in "
				+ getTimeLabel() + ": " + getSeconds();
	}

}
