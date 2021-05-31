<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Beartooth Hiking Company with JavaScript</title>
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker();
    //moment(newDate, 'MM/DD/YYYY', true).isValid()
    //onClose: function() {
    //  $( this ).valid();
    //}
  } );
  </script>
 
</head>
<body>
<h2>Welcome to Beartooth Hiking Company</h2>
	<script type="text/javascript" src="input.js" charset="utf-8"></script>
	<h3 style=color:red> ${error}</h3>
        <form action="https://web1.jhuep.com:443/courtney_js/Controller" method=GET>
            Which hike are you interested in? <br>
            <input type="radio" id="Gardiner Lake" name="hikes" value="gardiner" onchange="getValue(this)"> 
            <label for="grabiner">Gardiner Lake</label><br>
			<input type="radio" id="Hellroaring Plateau" name="hikes" value="hellroaring" onchange="getValue(this)">
			<label for="hellroaring">Hellroaring Plateau</label><br>
			<input type="radio" id="The Beaten Path" name="hikes" value="beaten" onchange="getValue(this)">
			<label for="beaten">The Beaten Path</label><br>
			<label for="duration"> Duration: </label>
			<select id="duration" name="duration">
			</select><br/>
			<label for="party"> Number in Party: </label>
			<select id="party" name="party">
				<option value="1"> 1 </option>
				<option value="2"> 2 </option>
				<option value="3"> 3 </option>
				<option value="4"> 4 </option>
				<option value="5"> 5 </option>
				<option value="6"> 6 </option>
				<option value="7"> 7 </option>
				<option value="8"> 8 </option>
				<option value="9"> 9 </option>
				<option value="10"> 10 </option>
			</select><br/>
            Start date:  <input type="TEXT" id="datepicker" name="start"  required pattern="\d{2}\/\d{2}\/\d{4}"> <br />
            <input type="SUBMIT">
        </form>
</body>
</html>