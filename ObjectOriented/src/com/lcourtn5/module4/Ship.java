package com.lcourtn5.module4;

public abstract class Ship implements Contact {
	private int length=0;
	private int speed=0;
	private String name="Ship";
	private String type="Ship";
	
	//Constructor
	public Ship() {
		
	}
	
	public Ship(int length, int speed, String name, String type) {
		if (length < 0) {
			System.out.println("Oops, a ship can't have a negative length. Failed to set.");
		} else {
			this.length = length;
		}
		
		if (speed < 0) {
			System.out.println("Oops, a ship can't have a negative speed. Failed to set.");
		} else {
			this.speed = speed;
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
		// TODO Do I want to allow a negative speed to mean the Ship is moving in reverse? Think on this.
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Ship other = (Ship) obj;
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
		return "Ship [length=" + length + ", speed=" + speed + ", name=" + name + ", type=" + type + "]";
	}
}
