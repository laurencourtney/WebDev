<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Beartooth Hiking JDBC</title>
</head>
<body>
<h2>Welcome to Beartooth Hiking Company</h2>
	<h3 style=color:red> ${error}</h3>
        <form action="https://web1.jhuep.com:443/courtney_jdbc/Controller" method=GET>
            View all bookings beyond your chosen start date: <br>
            Start date (YYYY-MM-DD):  <input type="TEXT" name="start"> <br />
            <input type="SUBMIT">
        </form>
</body>
</html>