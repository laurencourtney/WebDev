package com.lcourtn5.module4;

public class P3 extends Aircraft {

	private int numberEngines=1;
	
	public P3() {
		super();
		this.setType("P3");
	}
	public P3(int length, int speed, String name, int altitude) {
		super(length, speed, name, "P3", altitude);
		this.numberEngines = 0;
	}
	
	public P3(int length, int speed, String name, int altitude, int numberEngines) {
		super(length, speed, name, "P3", altitude);
		if (numberEngines <= 0) {
			System.out.println("Oops, a P3 needs a postitive number of engines. Failed to set.");
		} else {
			this.numberEngines = numberEngines;
		}
	}

	public int getNumberEngines() {
		return numberEngines;
	}

	public void setNumberEngines(int numberEngines) {
		if (numberEngines > 0) {
			this.numberEngines = numberEngines;
		} else {
			System.out.println("Oops, a P3 needs a positive number of engines in setNumberEngines. Failed to set.");
		}
	}
	
	public void setNumberEngines(String numberEngines) {
		try {
			int intEngines = Integer.parseInt(numberEngines);
			if (intEngines > 0) {
				this.numberEngines = intEngines;
			} else {
				System.out.println("Oops, a P3 needs a positive number of engines in setNumberEngines. Failed to set.");
			}
		} catch (NumberFormatException nfe) {
			System.out.println("Oops, looks like the value you passed to setNumberEngines wasn't a number. Failed to set.");
			System.out.println("Caught NumberFormatException" + nfe.getMessage());
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + numberEngines;
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
		P3 other = (P3) obj;
		if (numberEngines != other.numberEngines)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "P3 [numberEngines=" + numberEngines + "] inherited from " + super.toString();
	}
	
	

}
