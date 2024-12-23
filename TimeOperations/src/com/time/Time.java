package com.time;

public abstract class Time implements Comparable<Time>, Cloneable {

	// Time in milliseconds
	private long time;

	// Initializes time to current system time
	protected Time() {
		this.time = System.currentTimeMillis();
	}

	// Initializes time based on hour, minute, second
	protected Time(int hour, int minute, int second) {
		setTime(hour, minute, second);
	}

	// Initializes time based on elapsed time
	protected Time(long elapsedTime) throws InvalidElapsedTimeException {
		setTime(elapsedTime);
	}

	// Sets time based on hour, minute, second
	public void setTime(int hour, int minute, int second) throws IllegalArgumentException {
		if (hour < 0) {
			throw new IllegalArgumentException("Hour must be non-negative.");
		} else if (minute < 0) {
			throw new IllegalArgumentException("Minute must be non-negative.");
		} else if (second < 0) {
			throw new IllegalArgumentException("Second must be non-negative.");
		}
		this.time = ((hour * 3600) + (minute * 60) + second) * 1000L;
	}

	// Sets time based on elapsed time
	public void setTime(long elapsedTime) throws InvalidElapsedTimeException {
		if (elapsedTime < 0) {
			throw new InvalidElapsedTimeException(elapsedTime);
		}
		this.time = elapsedTime * 1000L;
	}

	// Returns time in milliseconds
	public long getTime() {
		return time;
	}

	// Abstract method for hour
	public abstract int getHour();

	// Abstract method for minute
	public abstract int getMinute();

	// Abstract method for second
	public abstract int getSecond();

	// Abstract method for elapsed total seconds
	public abstract long getSeconds();

	// Abstract toString method
	@Override
	public abstract String toString();

	// Compares two Time objects based on their elapse seconds
	@Override
	public int compareTo(Time other) {
		return (int) ((this.time / 1000) - (other.time / 1000));
	}

	// Clones the Time object
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
