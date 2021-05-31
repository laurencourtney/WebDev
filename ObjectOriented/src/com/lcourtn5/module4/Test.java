package com.lcourtn5.module4;

import java.util.*;

public class Test {

	public Test() {
		
	}

	public static void main(String[] args) {
		//Good examples
		System.out.println("---------------- Good Examples ------------------ ");
		Destroyer d1 = new Destroyer(300, 100, "USS-Fast", 4); //var order is length, speed, name, numMissiles
		Destroyer d2 = new Destroyer(500, 150, "USS-Faster"); //should default to 2 missiles
		Submarine s1 = new Submarine(400, 200, "USS-Sneaky", 6); //length, speed, name, numTorpedos
		Submarine s2 = new Submarine(600, 250, "USS-Stealth"); //should default to 2 missiles
		P3 air1 = new P3(100, 400, "AirChamp", 10000, 6); //length, speed, name, altitude, numEngines
		P3 air2 = new P3(80, 350, "AirPro", 15000, 4);
		
		//print them all individually to make sure each of their to string modules is working
		System.out.println(d1.toString());
		System.out.println(d2.toString());
		System.out.println(s1.toString());
		System.out.println(s2.toString());
		System.out.println(air1.toString());
		System.out.println(air2.toString());
		
		//Examples with errors
		System.out.println("---------------- Bad Examples --------------- ");
		P3 airBad = new P3(-80, -350, "AirFail", -15000, 0);
		airBad.setNumberEngines("0");
		airBad.setNumberEngines("blah");
		Destroyer destBad = new Destroyer(-300, -100, "USS-Bad", -4);
		destBad.setNumberMissiles("blah");
		Submarine subBad = new Submarine(-400, -200, "USS-Worse", 0);
		subBad.setNumberTorpedos("foo");
		Submarine emptySub = new Submarine();
		emptySub.setSpeed("foo");
		emptySub.setSpeed("10");
		//print them all individually to check that defaults are used when errors hit
		System.out.println(airBad.toString());
		System.out.println(destBad.toString());
		System.out.println(subBad.toString());
		System.out.println(emptySub.toString());
		
		//Test equality
		System.out.println("------------------ Equality Check -------------- ");
		Submarine equalSub1 = new Submarine(100, 100, "Same", 4);
		Submarine equalSub2 = new Submarine(100, 100, "Same", 4);
		Submarine notEqualSub = new Submarine(100, 200, "Not Same", 4);
		System.out.println("These are equal, it should say True: " + equalSub1.equals(equalSub2));
		System.out.println("These are not equal, it should say False: " + equalSub1.equals(notEqualSub));
		
		//Make Collections
		ArrayList<Destroyer> destroyerArray = new ArrayList<Destroyer>();
		destroyerArray.add(d1);
		destroyerArray.add(d2);
		destroyerArray.add(destBad);
		ArrayList<Submarine> submarineArray = new ArrayList<Submarine>();
		submarineArray.add(s1);
		submarineArray.add(s2);
		submarineArray.add(subBad);
		submarineArray.add(emptySub);
		List<Ship> shipList = new ArrayList<Ship>();
		shipList.add(d1);
		shipList.add(d2);
		shipList.add(s1);
		shipList.add(s2);
		shipList.add(destBad);
		shipList.add(subBad);
		shipList.add(emptySub);
		List<Contact> contactList = new ArrayList<Contact>();
		contactList.add(d1);
		contactList.add(d2);
		contactList.add(s1);
		contactList.add(s2);
		contactList.add(destBad);
		contactList.add(subBad);
		contactList.add(emptySub);
		contactList.add(air1);
		contactList.add(air2);
		contactList.add(airBad);
		
		//print everything in contact List
		System.out.println("---------------- Contact Collection --------------- ");
		for (Contact con : contactList) {
			System.out.println(con.toString());
		}
	}

}
