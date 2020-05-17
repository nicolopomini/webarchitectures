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
function loadCourses() {
	$.ajax({
		url: server + "/course",
		type: "GET",
		crossDomain: true,
		dataType: "json",
		success: function(data) {
			console.log(data);
			col = "";
			if(data != null) {
				for(i = 0; i < data.length; i++) {
					value = data[i];
					col += "<tr  onclick=\"loadModal(" + value["id"] + ")\"><td>";
					col += value["id"];
					col += "</td><td>";
					col += value["name"];
					col += "</td><td>";
					col += value["students"].length;
					col += "</td></tr>";
				}
			}
			$("#allcourses").html(col);
		},
		error: function(xhr, status) {
			error("Something went wrong with retrieving the courses.")
		}
	});
}
function loadModal(id) {
	$('#courseDetail').modal("show");
	$.ajax({
		url: server + "/course/" + id,
		type: "GET",
		crossDomain: true,
		dataType: "json",
		success: function(data) {
			if(data != null) {
				$('#courseDetailTitle').html(data["name"]);
				var body = "Enrolled students: " + data["students"].length + "<br/>";
				if(data["students"].length > 0) {
					body += "<table class='table'><thead><th scope='col'>Name</th><th scope='col'>Surname</th><th scope='col'>Matricola</th></thead>"
					body += "<tbody>";
					for(i = 0; i < data["students"].length; i++) {
						value = data["students"][i];
						body += "<tr><td>";
						body += value["name"];
						body += "</td><td>";
						body += value["surname"];
						body += "</td><td>";
						body += value["matricola"];
						body += "</td></tr>";
					}
					body += "</tbody></table>";
				}
				$('#courseDetailBody').html(body);
			}
		},
		error: function(xhr, status) {
			$('#courseDetail').modal("hide");
			error("Something went wrong.")
		}
	});
}
function loadInsertForm() {
	$.ajax({
		url: server + "/professor",
		type: "GET",
		crossDomain: true,
		dataType: "json",
		success: function(data) {
			if(data != null) {
				for(i = 0; i < data.length; i++) {
					$('#profInsertForm').append(new Option(data[i]["name"] + ' ' + data[i]["surname"], data[i]["id"]));
				}
			}
		},
		error: function(xhr, status) {
			error("Something went wrong.")
		}
	});
}
$("#submitInsert").click(function() {
	var x = $("form").serializeArray();
	var course = {};
	for(var i = 0; i < x.length; i++) {
		course[x[i]["name"]] = x[i]["value"];
	}
	$.ajax({
		url: server + "/course",
		type: "POST",
		crossDomain: true,
		dataType: "json",
		data: course,
		success: function(data) {
			$("#message").html('<div class="alert alert-success alert-dismissible fade show" role="alert"><strong>Success</strong> A new course was added successfully.<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button></div>')
		},
		error: function() {
			error("Something went wrong in inserting a new course.");
		}
	});
});
function loadEnrollForm() {
	$.ajax({
		url: server + "/student",
		type: "GET",
		crossDomain: true,
		dataType: "json",
		success: function(data) {
			if(data != null) {
				for(i = 0; i < data.length; i++) {
					$('#enrollStudent').append(new Option(data[i]["name"] + ' ' + data[i]["surname"], data[i]["id"]));
				}
			}
		},
		error: function(xhr, status) {
			error("Something went wrong.")
		}
	});
	$.ajax({
		url: server + "/course",
		type: "GET",
		crossDomain: true,
		dataType: "json",
		success: function(data) {
			if(data != null) {
				for(i = 0; i < data.length; i++) {
					$('#enrollCourse').append(new Option(data[i]["name"], data[i]["id"]));
				}
			}
		},
		error: function(xhr, status) {
			error("Something went wrong.")
		}
	});
}
$("#submitEnroll").click(function() {
	var x = $("form").serializeArray();
	var course = null;
	var student = null;
	for(var i = 0; i < x.length; i++) {
		if(x[i]["name"] == "course")
			course = x[i]["value"];
		else if(x[i]["name"] == "student")
			student = x[i]["value"];
	}
	$.ajax({
		url: server + "/course/" + course + "/enroll/" + student,
		type: "POST",
		crossDomain: true,
		dataType: "json",
		success: function(data) {
			$("#message").html('<div class="alert alert-success alert-dismissible fade show" role="alert"><strong>Success</strong> Student enrolled successfully.<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button></div>')
		},
		error: function() {
			error("Something went wrong during the enrollment.");
		}
	});
});