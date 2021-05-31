package com.lcourtn5.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


import com.lcourtn5.server.Rates.HIKE;

public class ClientThread extends Thread {

	private final Socket socket;
	public ClientThread(Socket clientSocket) {
		this.socket = clientSocket;
	}
	
	protected String parseInput(String input) {
		String returnString = "";
		Rates rates = null;
		String [] splitString = input.split(":");
		if (splitString.length != 5) {
			returnString = "Invalid argument number. Format should be hike:year:month:day:duration";
			return returnString;
		} else {
			//set the hike value
			String hikeString = splitString[0];
			if (hikeString.equals("2")) {
				rates = new Rates(HIKE.BEATEN);
			} else if (hikeString.equals("1")) {
				rates = new Rates(HIKE.HELLROARING);
			} else if (hikeString.equals("0")){
				rates = new Rates(HIKE.GARDINER);
			} else {
				returnString = "Invalid hike number: must be 0, 1, or 2. Not " + hikeString;
				return returnString;
			}
			
			//test the dates
			try {
				int duration = Integer.parseInt(splitString[4]);
				int startYear = Integer.parseInt(splitString[1]);
				int startMonth = Integer.parseInt(splitString[2]);
				int startDay = Integer.parseInt(splitString[3]);
				
				BookingDay startDate = new BookingDay(startYear, startMonth, startDay);
				rates.setBeginDate(startDate);
				rates.setDuration(duration);
				
				if (rates.isValidDates()) {
					if(rates.getCost() != -0.01) {
						String cost = String.valueOf(rates.getCost());
		            	returnString = cost + " Quoted Rate";
		            	return returnString;
		            } else {
		            	//the rate returned with -0.01, there was an error
		            	returnString = rates.getCost() + " " + rates.getDetails();
		            	return returnString;
		            }
				} else {
					//the dates were invalid
					returnString = rates.getCost() + " " + rates.getDetails();
					return returnString;
				}
				
			} catch (NumberFormatException ne) {
				//couldnt parse duration or dates
				returnString = "Error parsing duration or dates: " + ne;
				return returnString;
			}
			
		}

	}
	

	public void run () {
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String inputLine = null;
			while (!socket.isClosed()) {
				inputLine = in.readLine();
				if (inputLine == null) {
					break;
				}
				else {
					out.println(parseInput(inputLine));
				}
			} 
		} catch (IOException ex) {
			System.err.println("Error: " + ex);
		} finally {
			try {
				if (out != null) {
					out.close();
				} 
				if (in != null) {
					in.close();
				}
				if (socket != null) {
					socket.close();
				}
			} catch (IOException ex) {
				System.err.println("Error: " + ex);
			}
		}
	}

}
