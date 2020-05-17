var server = "http://localhost:8080"
function error(message) {
	content = '<div class="alert alert-danger alert-dismissible fade show" role="alert">' +
	  '<strong>Error:</strong> ' + message +
	  '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
	    '<span aria-hidden="true">&times;</span>' +
	  '</button>' +
	'</div>';
	$("#message").html(content);
}
function loadStudents() {
	$.ajax({
		url: server + "/student",
		type: "GET",
		crossDomain: true,
		dataType: "json",
		success: function(data) {
			col = "";
			if(data != null) {
				for(i = 0; i < data.length; i++) {
					value = data[i];
					col += "<tr><td>";
					col += value["name"];
					col += "</td><td>";
					col += value["surname"];
					col += "</td><td>";
					col += value["matricola"];
					col += "</td></tr>";
				}
			}
			$("#allstudents").html(col);
		},
		error: function(xhr, status) {
			error("Something went wrong with retrieving the students.")
		}
	});
}
$("#submit").click(function() {
	var x = $("form").serializeArray();
	var student = {};
	for(var i = 0; i < x.length; i++) {
		student[x[i]["name"]] = x[i]["value"];
	}
	$.ajax({
		url: server + "/student",
		type: "POST",
		crossDomain: true,
		dataType: "json",
		data: student,
		success: function(data) {
			$("#message").html('<div class="alert alert-success alert-dismissible fade show" role="alert"><strong>Success</strong> A new student was inserted successfully.<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button></div>')
		},
		error: function() {
			error("Something went wrong in inserting a new student.");
		}
	});
});