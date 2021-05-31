package com.lcourtn5.js;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcourtn5.js.BookingDay;
import com.lcourtn5.js.Rates;
import com.lcourtn5.js.Rates.HIKE;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public static final String ROUTE="hikes";
	public static final String DATE="start";
	public static final String DURATION="duration";
	public static final String PARTY="party";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ServletContext servletContext = getServletContext();
		session.setAttribute("error", "");
		String hikeString = request.getParameter(ROUTE);
		String dateString = request.getParameter(DATE);
		String durationString = request.getParameter(DURATION);
		String numberPeople = request.getParameter(PARTY);
		int durationLength = 0;
		int numPeople = 0;
		
		if (hikeString == null) {
			RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		} else if (dateString == null){
			RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		} else if (durationString == null){
			RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		} else {
			//read in the duration
			try {
				durationLength = Integer.parseInt(request.getParameter(DURATION));
				numPeople = Integer.parseInt(request.getParameter(PARTY));
				//calculate the rate
				//Set up the dates
				//get the end date
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				Date start;
				try {
					start = sdf.parse(dateString);
					Calendar c = Calendar.getInstance();
					c.setTime(start);
					c.add(Calendar.DATE, durationLength - 1);
					String stringEndDate = sdf.format(c.getTime());
					try {
						//to get this to work with BookingDay get them all broken down
						int startYear = Integer.parseInt(dateString.substring(6,10));
						int startMonth = Integer.parseInt(dateString.substring(0,2));
						int startDay = Integer.parseInt(dateString.substring(3,5));
						BookingDay startDate = new BookingDay(startYear, startMonth, startDay);
						int endYear = Integer.parseInt(stringEndDate.substring(6,10));
						int endMonth = Integer.parseInt(stringEndDate.substring(0,2));
						int endDay = Integer.parseInt(stringEndDate.substring(3,5));
						BookingDay endDate = new BookingDay(endYear, endMonth, endDay);
						System.out.println("Starting Date: " + startDate);
						System.out.println("Ending Date: " + endDate);
						
						if (!startDate.isValidDate()) {
							session.setAttribute("error", "ERROR: " + "The start date is invalid, it may be out of season.");
				            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/index.jsp");
							dispatcher.forward(request, response);
						} else if (!endDate.isValidDate()) {
							session.setAttribute("error", "ERROR: " + "The end date is invalid. Check that you have a valid duration and that it is in season.");
				            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/index.jsp");
							dispatcher.forward(request, response);
						} else {
							//Find the rates
							Rates rates = null;
							if (hikeString.equals("beaten")) {
								rates = new Rates(HIKE.BEATEN);
							} else if (hikeString.equals("hellroaring")) {
								rates = new Rates(HIKE.HELLROARING);
							} else if (hikeString.equals("gardiner")){
								rates = new Rates(HIKE.GARDINER);
							}
							if (rates != null) {
								rates.setBeginDate(startDate);
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
							            	session.setAttribute("finalhike", hikeString);
											session.setAttribute("date", dateString);
											session.setAttribute("length", durationLength);
											session.setAttribute("people", numPeople);
											session.setAttribute("rate", rates.getCost());
											session.setAttribute("totalRate", numPeople * rates.getCost());
											RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/cost.jsp");
											dispatcher.forward(request, response);
						            	} else {
						            		//the number of people in the party was wrong
											session.setAttribute("error", "ERROR: " + "The number of people in the party must be between 1 and 10.");
								            System.out.println("Sorry, but " + "The number of people in the party must be between 1 and 10.");
								            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/index.jsp");
											dispatcher.forward(request, response);
						            	}
						            } else {
						            	//the rate returned with -0.01, there was an error
										session.setAttribute("error", "ERROR: " + rates.getDetails());
							            System.out.println("Sorry, but " + rates.getDetails());
							            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/index.jsp");
										dispatcher.forward(request, response);
						            }
						        } else {
						        	//the dates were invalid
						        	session.setAttribute("error", "ERROR: " + rates.getDetails());
						            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/index.jsp");
									dispatcher.forward(request, response);
						        }
							} else {
								//a hike name wasn't chosen
								session.setAttribute("error", "ERROR: " + "One of the three hikes was not chosen");
					            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/index.jsp");
								dispatcher.forward(request, response);
							}
						}	
					} catch (NumberFormatException nfe) {
						//error parsing the date
						session.setAttribute("error", "ERROR: " + "Date was in the incorrect format. Must be MM/DD/YYYY");
			            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/index.jsp");
						dispatcher.forward(request, response);
					} catch (StringIndexOutOfBoundsException ste) {
						//the date was too short
						session.setAttribute("error", "ERROR: " + "Date was in the incorrect format. Must be MM/DD/YYYY");
			            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/index.jsp");
						dispatcher.forward(request, response);
					}
				} catch (ParseException e) {
					//error parsing the date
					session.setAttribute("error", "ERROR: " + "Date was in the incorrect format. Must be MM/DD/YYYY");
		            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/index.jsp");
					dispatcher.forward(request, response);
				}
			} catch (NumberFormatException nfe) {
				//error parsing the duration
				session.setAttribute("error", "ERROR: " + "Duration or party was in the incorrect format. They must be integers.");
	            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
			}
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
