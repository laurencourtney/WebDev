package com.lcourtn5.module4;

public class Destroyer extends Ship {
	
	private int numberMissiles=0;
	private int DEFAULT_MISSILES=2;
	
	public Destroyer() {
		super();
		this.setType("Destroyer");
	}
	
	public Destroyer(int length, int speed, String name) {
		super(length, speed, name, "Destroyer");
		this.numberMissiles = DEFAULT_MISSILES;
	}
	
	public Destroyer(int length, int speed, String name, int numberMissiles) {
		super(length, speed, name, "Destroyer");
		if (numberMissiles < 0) {
			System.out.println("Oops, a destroyer can't have a negative numberMissiles. Defaulting to " + DEFAULT_MISSILES + ".");
		} else {
			this.numberMissiles = numberMissiles;
		}
	}

	public int getNumberMissiles() {
		return numberMissiles;
	}

	public void setNumberMissiles(int numberMissiles) {
		if (numberMissiles >= 0) {
			this.numberMissiles = numberMissiles;
		} else {
			System.out.println("Oops, looks like you passed a negative number to setNumberMissiles. Defaulting to " + DEFAULT_MISSILES + ".");
			this.numberMissiles = DEFAULT_MISSILES;
		}
	}
	
	public void setNumberMissiles(String numberMissiles) {
		try {
			int intMissiles = Integer.parseInt(numberMissiles);
			if (intMissiles >= 0) {
				this.numberMissiles = intMissiles;
			} else {
				System.out.println("Oops, looks like you passed a negative number to setNumberMissiles. Defaulting to " + DEFAULT_MISSILES + ".");
				this.numberMissiles = DEFAULT_MISSILES;
			}
		} catch (NumberFormatException nfe) {
			System.out.println("Oops, looks like the value you passed to setNumberMissiles wasn't a number. Defaulting to " + DEFAULT_MISSILES + ".");
			System.out.println("Caught NumberFormatException" + nfe.getMessage());
			this.numberMissiles = DEFAULT_MISSILES;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + numberMissiles;
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
		Destroyer other = (Destroyer) obj;
		if (numberMissiles != other.numberMissiles)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Destroyer [numberMissiles=" + numberMissiles + "] inherited from " + super.toString();
	}

}
