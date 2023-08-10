$(document).ready(function() {
	$("#open-dialog-btn").click(function() {
		$("#dialog").show().addClass("dialog-resizable");
		
	});

	$(".dialog-close").click(function() {
		
		let text = "Press a button!\nEither OK or Cancel.";
		if (confirm(text) ) {
		  $("#dialog").hide().removeClass("dialog-resizable");
		  text = "You pressed OK!";
		} else {
		  text = "You canceled!";
		}
		document.getElementById("demo").innerHTML = text;
	});
	
	
});
