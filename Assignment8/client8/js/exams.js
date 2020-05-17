var server = "http://localhost:8080";
function error(message) {
	content = '<div class="alert alert-danger alert-dismissible fade show" role="alert">' +
	  '<strong>Error:</strong> ' + message +
	  '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
	    '<span aria-hidden="true">&times;</span>' +
	  '</button>' +
	'</div>';
	$("#message").html(content);
}
function loadExams() {
	$.ajax({
		url: server + "/exam",
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
					col += value["course"]["name"];
					col += "</td><td>";
					col += value["date"];
					col += "</td></tr>";
				}
			}
			$("#allexams").html(col);
		},
		error: function(xhr, status) {
			error("Something went wrong with retrieving the exams.")
		}
	});
}
function loadInsertForm() {
	$.ajax({
		url: server + "/course",
		type: "GET",
		crossDomain: true,
		dataType: "json",
		success: function(data) {
			if(data != null) {
				for(i = 0; i < data.length; i++) {
					$('#courseInsertForm').append(new Option(data[i]["name"], data[i]["id"]));
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
	var exam = {};
	for(var i = 0; i < x.length; i++) {
		exam[x[i]["name"]] = x[i]["value"];
	}
	$.ajax({
		url: server + "/exam",
		type: "POST",
		crossDomain: true,
		dataType: "json",
		data: exam,
		success: function(data) {
			$("#message").html('<div class="alert alert-success alert-dismissible fade show" role="alert"><strong>Success</strong> A new exam was added successfully.<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button></div>')
		},
		error: function() {
			error("Something went wrong in inserting a new exam.");
		}
	});
});
function loadModal(examid) {
	$('#examDetail').modal("show");
	$.ajax({
		url: server + "/exam/" + examid,
		type: "GET",
		crossDomain: true,
		dataType: "json",
		success: function(data) {
			if(data != null) {
				$('#examDetailTitle').html(data["course"]["name"] + " exam - " + data["date"]);
			}
		},
		error: function(xhr, status) {
			$('#courseDetail').modal("hide");
			error("Something went wrong.")
		}
	});
	$.ajax({
		url: server + "/student",
		type: "GET",
		crossDomain: true,
		dataType: "json",
		success: function(data) {
			$('<input />').attr('type', 'hidden')
		          .attr('name', "examid")
		          .attr('value', examid)
		          .appendTo('#enrollStudentForm');
			if(data != null) {
				for(i = 0; i < data.length; i++) {
					$('#enrollStudent').append(new Option(data[i]["name"] + ' ' + data[i]["surname"] + ', Matricola: ' + data[i]["matricola"], data[i]["id"]));
				}
			}
		},
		error: function(xhr, status) {
			$('#courseDetail').modal("hide");
			error("Something went wrong.")
		}
	});
	$.ajax({
		url: server + "/exam/" + examid + "/enrolled",
		type: "GET",
		crossDomain: true,
		dataType: "json",
		success: function(data) {
			var body = "<h3>Enrolled students</h3>#Enrolled students: " + data.length + "<br/>";
				if(data.length > 0) {
					body += "<table class='table'><thead><th scope='col'>Name</th><th scope='col'>Surname</th><th scope='col'>Matricola</th><th scope='col'></th></thead>"
					body += "<tbody>";
					for(i = 0; i < data.length; i++) {
						value = data[i];
						body += "<tr><td>";
						body += value["student"]["name"];
						body += "</td><td>";
						body += value["student"]["surname"];
						body += "</td><td>";
						body += value["student"]["matricola"];
						body += '</td><td id="td' + value["student"]["id"] + '">';
						if(value["mark"] == null) {		// form
							body += '<form class="form-inline">';
							body += '<label class="sr-only" for="mark' + value["student"]["id"] + '">Name</label>';
							body += '<input type="text" class="form-control mb-2 mr-sm-2" id="mark' + value["student"]["id"] + '" placeholder="Mark">';
							body += '<button type="button" class="btn btn-primary mb-2" onclick="addMark(' + value["student"]["id"] + ', ' + examid + ')">Add mark</button>';
							body += '</form>';
						}
						else {
							body += "Mark: " + value["mark"];
						}
						body += "</td></tr>";
					}
					body += "</tbody></table>";
				}
				$('#modalList').html(body);
		},
		error: function(xhr, status) {
			error("Something went wrong with retrieving the exams.")
		}
	});
}
function addMark(student, exam) {
	var mark = $('#mark' + student + '').val();
	var obj = {"mark": mark};
	$.ajax({
		url: server + "/exam/" + exam + "/enrolled/" + student,
		type: "POST",
		crossDomain: true,
		dataType: "json",
		data: obj,
		success: function(data) {
			if(data != null) {
				$('#td' + student).html("Mark: " + mark);
			}
		},
		error: function(xhr, status) {
			$('#courseDetail').modal("hide");
			error("Something went wrong with adding a vote.")
		}
	});
}
$("#submitEnroll").click(function() {
	var x = $("form").serializeArray();
	var exam = {};
	for(var i = 0; i < x.length; i++) {
		exam[x[i]["name"]] = x[i]["value"];
	}
	console.log(exam);
	$.ajax({
		url: server + "/exam/" + exam["examid"] + "/enroll/" + exam["student"],
		type: "POST",
		crossDomain: true,
		dataType: "json",
		success: function(data) {
			$("#modalMessage").html('<div class="alert alert-success alert-dismissible fade show" role="alert"><strong>Success</strong> A new exam was added successfully.<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button></div>')
		},
		error: function() {
			error("Something went wrong in inserting a new exam.");
		}
	});
});