/**
 * JavaScript file to accompany Beartooth Hiking Company Servlet
*/
function getValue(radio) {
	if (radio.value == "gardiner") {
		//clear the list
		document.getElementById('duration').options.length = 0;
		//add 3 and 5
		var newItem=document.createElement('option');
		newItem.text= "3";
		document.getElementById("duration").add(newItem, null);
		var newItem=document.createElement('option');
		newItem.text= "5";
		document.getElementById("duration").add(newItem, null);
		
	
	} else if (radio.value == "hellroaring") {
		//clear the list
		document.getElementById('duration').options.length = 0;
		//add 3, 4, and 5
		var newItem=document.createElement('option');
		newItem.text= "3";
		document.getElementById("duration").add(newItem, null);
		var newItem=document.createElement('option');
		newItem.text= "4";
		document.getElementById("duration").add(newItem, null);
		var newItem=document.createElement('option');
		newItem.text= "5";
		document.getElementById("duration").add(newItem, null);
		
	} else if (radio.value == "beaten"){
		//clear the list
		document.getElementById('duration').options.length = 0;
		//add 5 and 7
		var newItem=document.createElement('option');
		newItem.text= "5";
		document.getElementById("duration").add(newItem, null);
		var newItem=document.createElement('option');
		newItem.text= "7";
		document.getElementById("duration").add(newItem, null);
		
	} else {
		//clear the list 
		document.getElementById('duration').options.length = 0;
		alert("One of the hikes was not chosen.")
	}
}