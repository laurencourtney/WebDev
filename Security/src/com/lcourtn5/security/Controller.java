package com.lcourtn5.security;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public static final String DATE="start";
	//private final static String url="jdbc:mysql://web7.jhuep.com:3306/";
	private final static String driver = "com.mysql.jdbc.Driver";
	//private final static String driver = "my.sql.Driver";
	//private final static String user = "johncolter";
	//private final static String pass = "LetMeIn!";
	private final static String db = "class";
	
	private static String url = "";
	public static final String HOST = "web9.jhuep.com";
	public static final String DATABASE_USER = "user";
	public static final String DATABASE_PASSWORD = "password";
	public static final String MYSQL_AUTO_RECONNECT = "autoReconnect";
	public static final String MYSQL_MAX_RECONNECTS = "maxReconnects";
	
	private static final Properties connProperties = new Properties();
		//private static final String HOME="C:\\Users\\laure\\eclipse-workspace\\";
		private static final String HOME="/home/lcourtn5/";
		static {
			String hostname = HOST;
			url = "jdbc:mysql://" + hostname + ":3306/";
			connProperties.put(DATABASE_USER, "johncolter");
			connProperties.put(DATABASE_PASSWORD, "LetMeIn!");
			connProperties.put(MYSQL_AUTO_RECONNECT, "true");
			connProperties.put(MYSQL_MAX_RECONNECTS, "4");
			connProperties.put("useSSL", "true");
			connProperties.put("verifyServerCertificate", "false");
			connProperties.put("truststore", HOME + "truststore");
			//connProperties.put("trustCertificateKeyStoreURL", "file:\\" + HOME + "truststore");
			connProperties.put("trustCertificateKeyStoreURL", "file:/" + HOME + "truststore");
			connProperties.put("trustCertificateKeyStorePassword", "laurenpass");
			connProperties.put("serverTimezone", "US/Eastern");
		}
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        try {
        	Class.forName(driver);
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        }
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ServletContext servletContext = getServletContext();
		session.setAttribute("error", "");
		session.setAttribute("htmlData", "");
		String dateString = request.getParameter(DATE);

		if (dateString == null){
			RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		} else {
			try {
				//parse the date
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				sdf.setLenient(false);
				Date start = sdf.parse(dateString);
				
				//set up the query string, we dont need prepared statements since we arent letting user modify sql query, but good for pracitve
				String query = "SELECT reservation.StartDay, reservation.NumberOfDays, locations.location, guides.First, guides.Last, reservation.First, reservation.Last FROM reservation, guides, locations WHERE reservation.guide = idguides AND reservation.location = locations.idlocations ORDER BY reservation.StartDay ASC";
				//connect to the db
				try (Connection conn = DriverManager.getConnection(url + db, connProperties);
						PreparedStatement statement = conn.prepareStatement(query)) {
					ResultSet rs = statement.executeQuery();
					String htmlString = "<table><tr><th>Start Date</th><th>Duration</th><th>Location</th><th>Guide Name</th><th>Reservation Name</th></tr>";
					while(rs.next()) {
						//only print those rows that are after the date. I couldve done this in the sql query but its easy here
						Date sqlStart = sdf.parse(rs.getString("StartDay"));
						if (sqlStart.after(start)) {
							String location = "<td>" + rs.getString("location") + "</td>";
							String guideName = "<td>" + rs.getString(4) + " " + rs.getString(5) + "</td>";
							String resName = "<td>" + rs.getString(6) + " " + rs.getString(7) + "</td>";
							String row = "<tr> <td>" + rs.getString("StartDay") + "</td><td>" + rs.getString("NumberOfDays") + "</td>" + location + guideName + resName + "</tr>";
							htmlString = htmlString.concat(row);
						}
					}
					htmlString = htmlString.concat("</table>");
					session.setAttribute("htmlData", htmlString);
			        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/datatable.jsp");
					dispatcher.forward(request, response);
					rs.close();
				} catch (SQLException e) {
					//error with SQL
					session.setAttribute("error", "ERROR: " + e);
			        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/index.jsp");
					dispatcher.forward(request, response);
				}
					
			} catch (ParseException e) {
				//error parsing the date
				session.setAttribute("error", "ERROR: " + "Date was invalid. Must be YYYY-MM-DD. " + e);
		        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
