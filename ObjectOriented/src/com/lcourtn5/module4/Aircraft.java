package com.lcourtn5.module4;

public abstract class Aircraft implements Contact {
	private int length=0;
	private int speed=0;
	private String name="Aircraft";
	private String type="Aircraft";
	
	private int altitude;
	
	//Constructor
	public Aircraft() {
		
	}
	public Aircraft(int length, int speed, String name, String type, int altitude) {
		if (length < 0) {
			System.out.println("Oops, an aircraft can't have a negative length. Failed to set.");
		} else {
			this.length = length;
		}
		
		if (speed < 0) {
			System.out.println("Oops, an aircraft can't have a negative speed. Failed to set.");
		} else {
			this.speed = speed;
		}
		
		if (altitude < 0) {
			System.out.println("Oops, an aircraft can't have a negative altitude. Failed to set.");
		} else {
			this.altitude = altitude;
		}
		
		this.name = name;
		this.type = type;
	}
	
	@Override
	public int getLength() {
		return length;
	}

	@Override
	public void setLength(int length) {
		if (length >= 0) {
			this.length = length;
		} else {
			System.out.println("Oops, looks like you passed a negative number to setLength. Failed to set.");
		}
	}

	@Override
	public int getSpeed() {
		return speed;
	}

	@Override
	public void setSpeed(int speed) {
		// TODO Do I want to allow a negative speed to mean the Aircraft is moving in reverse? Think on this.
		if (speed >= 0) {
			this.speed = speed;
		} else {
			System.out.println("Oops, looks like you passed a negative number to setSpeed. Failed to set.");
		}
	}

	@Override
	public void setSpeed(String speed) {
		try {
			int intSpeed = Integer.parseInt(speed);
			if (intSpeed >= 0) {
				this.speed = intSpeed;
			} else {
				System.out.println("Oops, looks like you passed a negative number to setSpeed. Failed to set.");
			}
		} catch (NumberFormatException nfe) {
			System.out.println("Oops, looks like the value you passed to setSpeed wasn't a number. Failed to set.");
			System.out.println("Caught NumberFormatException" + nfe.getMessage());
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	public int getAltitude() {
		return altitude;
	}
	
	public void setAltitude(int altitude) {
		if (altitude >= 0) {
			this.altitude = altitude;
		} else {
			System.out.println("Oops, looks like you passed a negative number to setAltitude. Failed to set.");
		}
	}
	
	public void setAltitude(String altitude) {
		try {
			int intAltitude = Integer.parseInt(altitude);
			if (intAltitude >= 0) {
				this.altitude = intAltitude;
			} else {
				System.out.println("Oops, looks like you passed a negative number to setAltitude. Failed to set.");
			}
		} catch (NumberFormatException nfe) {
			System.out.println("Oops, looks like the value you passed to setAltitude wasn't a number. Failed to set.");
			System.err.println("Caught NumberFormatException" + nfe.getMessage());
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + altitude;
		result = prime * result + length;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + speed;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aircraft other = (Aircraft) obj;
		if (altitude != other.altitude)
			return false;
		if (length != other.length)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (speed != other.speed)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Aircraft [length=" + length + ", speed=" + speed + ", name=" + name + ", type=" + type + ", altitude="
				+ altitude + "]";
	}
	
	
}
