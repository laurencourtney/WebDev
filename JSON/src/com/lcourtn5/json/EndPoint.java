package com.lcourtn5.json;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lcourtn5.json.BookingDay;
import com.lcourtn5.json.Rates;
import com.lcourtn5.json.Rates.HIKE;

import javax.ws.rs.POST;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@Path("rates")
public class EndPoint {
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnText getObject() {
		ReturnText respObject = new ReturnText();
		respObject.setText("ERROR. NO FORM DATA SPECIFIED.");
		return respObject;
	}
	
	@POST
	@Path("toObject")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setObject(Request request) {
			String response = processInput(request.getHike(), request.getDate(), request.getDuration(), request.getParty());
			ReturnText respObject = new ReturnText();
			respObject.setText(response);
			return Response.ok(respObject, MediaType.APPLICATION_JSON).build();
	}
	
	public String processInput(String hike, String start, String duration, String party) {
		String returnString = "";
		int durationLength = 0;
		int numPeople = 0;
		
		if (hike == null) {
			returnString = "Error. You must choose a hike name.";
			return returnString;
		} else if (start == null){
			returnString = "Error. You must choose a start date.";
			return returnString;
		} else if (duration == null){
			returnString = "Error. You must choose a duration.";
			return returnString;
		} else {
			//read in the duration
			try {
				durationLength = Integer.parseInt(duration);
				numPeople = Integer.parseInt(party);
				//calculate the rate
				//Set up the dates
				//get the end date
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				Date startDate;
				try {
					startDate = sdf.parse(start);
					Calendar c = Calendar.getInstance();
					c.setTime(startDate);
					c.add(Calendar.DATE, durationLength - 1);
					String stringEndDate = sdf.format(c.getTime());
					try {
						//to get this to work with BookingDay get them all broken down
						int startYear = Integer.parseInt(start.substring(6,10));
						int startMonth = Integer.parseInt(start.substring(0,2));
						int startDay = Integer.parseInt(start.substring(3,5));
						BookingDay startBookingDay = new BookingDay(startYear, startMonth, startDay);
						int endYear = Integer.parseInt(stringEndDate.substring(6,10));
						int endMonth = Integer.parseInt(stringEndDate.substring(0,2));
						int endDay = Integer.parseInt(stringEndDate.substring(3,5));
						BookingDay endBookingDay = new BookingDay(endYear, endMonth, endDay);
						System.out.println("Starting Date: " + startBookingDay);
						System.out.println("Ending Date: " + endBookingDay);
						
						if (!startBookingDay.isValidDate()) {
							returnString = "Error. Start Date is invalid.";
							return returnString;
						} else if (!endBookingDay.isValidDate()) {
							returnString = "Error. End Date is invalid.";
							return returnString;
						} else {
							//Find the rates
							Rates rates = null;
							if (hike.equals("beaten")) {
								rates = new Rates(com.lcourtn5.json.Rates.HIKE.BEATEN);
							} else if (hike.equals("hellroaring")) {
								rates = new Rates(com.lcourtn5.json.Rates.HIKE.HELLROARING);
							} else if (hike.equals("gardiner")){
								rates = new Rates(com.lcourtn5.json.Rates.HIKE.GARDINER);
							}
							if (rates != null) {
								rates.setBeginDate(startBookingDay);
						        rates.setDuration(durationLength);
						        System.out.println("Are the dates valid? " + rates.isValidDates());
						        if (rates.isValidDates()) {
						            System.out.println("Total Cost of Trip: $" + rates.getCost());
						            System.out.println("# of Weekdays: " + rates.getNormalDays());
						            System.out.println("# of Weekends: " + rates.getPremiumDays());  
						            if(rates.getCost() != -0.01) {
						            	//it worked!
						            	//check the number in the party 
						            	if ((0 < numPeople) && (numPeople < 11)) {
						            		double totalRate = numPeople * rates.getCost();
						            		returnString = hike + " hike for " + duration + " days starting on " + start + " for " + party + " people will cost $" + Double.toString(totalRate);
							            	return returnString;
						            	} else {
						            		//the number of people in the party was wrong
						            		returnString = "Error. The number of people in the part must be between 1 and 10.";
						        			return returnString;
						            	}
						            } else {
						            	//the rate returned with -0.01, there was an error
										returnString = "Error. " + rates.getDetails();
										return returnString;
						            }
						        } else {
						        	//the dates were invalid
						        	returnString = "Error. Invalid Dates. " + rates.getDetails();
									return returnString;
						        }
							} else {
								//a hike name wasn't chosen
								returnString = "Error. One of the three hikes was not chosen";
								return returnString;
							}
						}	
					} catch (NumberFormatException nfe) {
						//error parsing the date
						returnString = "Error. Date was in the incorrect format. Must be MM/DD/YYYY";
						return returnString;
					} catch (StringIndexOutOfBoundsException ste) {
						//the date was too short
						returnString = "Error. Date was in the incorrect format. Must be MM/DD/YYYY";
						return returnString;
					}
				} catch (ParseException e) {
					//error parsing the date
					returnString = "Error. Date was in the incorrect format. Must be MM/DD/YYYY";
					return returnString;
				}
			} catch (NumberFormatException nfe) {
				//error parsing the duration
				returnString = "Error. Duration or party was in the incorrect format. They must be integers.";
				return returnString;
			}
		}
	}
}
