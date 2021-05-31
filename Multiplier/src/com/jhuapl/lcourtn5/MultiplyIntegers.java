package com.jhuapl.lcourtn5;
import java.lang.Math;

public class MultiplyIntegers {

	public static int multiply(int val1, int val2) {
		return (val1 * val2);
	}
	
	public static void main(String[] args) {
		int val1 = Integer.parseInt(args[0]);
		int val2 = Integer.parseInt(args[1]);
		
		int product = multiply(val1, val2);
		
		if (product > 0) {
			System.out.println("The product is: " + String.valueOf(product));
		} else {
			product = Math.abs(product);
			System.out.println("The product is: (" + String.valueOf(product) + ")");
		}
	}
	
}
