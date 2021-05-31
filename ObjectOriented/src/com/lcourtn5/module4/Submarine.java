package com.lcourtn5.module4;

public class Submarine extends Ship {

	private int DEFAULT_TORPEDOS=2;
	private int numberTorpedos=DEFAULT_TORPEDOS;
	
	public Submarine() {
		super();
		this.setType("Submarine");
	}
	
	public Submarine(int length, int speed, String name) {
		super(length, speed, name, "Submarine");
		this.numberTorpedos = DEFAULT_TORPEDOS;
	}
	
	public Submarine(int length, int speed, String name, int numberTorpedos) {
		super(length, speed, name, "Submarine");
		if (numberTorpedos < 0) {
			System.out.println("Oops, a submarine can't have a negative numberTorpedos. Defaulting to " + DEFAULT_TORPEDOS + ".");
			this.numberTorpedos = DEFAULT_TORPEDOS;
		} else {
			this.numberTorpedos = numberTorpedos;
		}
	}

	public int getNumberTorpedos() {
		return numberTorpedos;
	}

	public void setNumberTorpedos(int numberTorpedos) {
		if (numberTorpedos >= 0) {
			this.numberTorpedos = numberTorpedos;
		} 
		else {
			System.out.println("Oops, looks like you passed a negative number to setNumberTorpedos. Defaulting to " + DEFAULT_TORPEDOS + ".");
			this.numberTorpedos = DEFAULT_TORPEDOS;
		}
	}
	
	public void setNumberTorpedos(String numberTorpedos) {
		try {
			int intTorpedos = Integer.parseInt(numberTorpedos);
			if (intTorpedos >= 0) {
				this.numberTorpedos = intTorpedos;
			} 
			else {
				System.out.println("Oops, looks like you passed a negative number to setNumberTorpedos. Defaulting to " + DEFAULT_TORPEDOS + ".");
				this.numberTorpedos = DEFAULT_TORPEDOS;
			}
		} catch (NumberFormatException nfe) {
			System.out.println("Oops, looks like the value you passed to setNumberTorpedos wasn't a number. Defaulting to " + DEFAULT_TORPEDOS + ".");
			System.out.println("Caught NumberFormatException" + nfe.getMessage());
			this.numberTorpedos = DEFAULT_TORPEDOS;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + numberTorpedos;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Submarine other = (Submarine) obj;
		if (numberTorpedos != other.numberTorpedos)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Submarine [numberTorpedos=" + numberTorpedos + "] inherited from " + super.toString();
	}


}
