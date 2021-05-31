<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Beartooth Hiking JSP Page</title>
</head>
<body>
<h2>Welcome to Beartooth Hiking Company</h2>
	<h3 style=color:red> ${error}</h3>
        <form action="https://web1.jhuep.com:443/courtney_maven-0.0.1-SNAPSHOT/webresources/forminput" method=GET>
            Which hike are you interested in? <br>
            <input type="radio" id="Gardiner Lake" name="hikes" value="gardiner"> 
            <label for="grabiner">Gardiner Lake (3 or 5 days)</label><br>
			<input type="radio" id="Hellroaring Plateau" name="hikes" value="hellroaring">
			<label for="hellroaring">Hellroaring Plateau (3, 4, or 5 days)</label><br>
			<input type="radio" id="The Beaten Path" name="hikes" value="beaten">
			<label for="beaten">The Beaten Path (5 or 7 days)</label><br>
            Start date (YYYYMMDD):  <input type="TEXT" name="start"> <br />
            Duration: <input type="TEXT" name="duration"> <br />
            <input type="SUBMIT">
        </form>
</body>
</html>