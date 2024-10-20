// Dashboard
$(document).ready(() => {
	$("#table-department").DataTable({
		ajax: {
			url: "/api/department",
			dataSrc: "",
		},
		columnDefs: [
			{
				className: "text-center",
				targets: "_all",
				searchable: true,
				orderable: true,
			},
		],
		order: [[1, "asc"]],
		columns: [
			{ data: "id" },
			{ data: "name" },
			{
				data: null,
				render: (data) => {
					return /*html*/ `
                <div class="d-flex m-auto gap-4 justify-content-center">
                    <button
                        type="button"
                        class="btn btn-primary d-flex align-items-center"
                        data-bs-toggle="modal"
                        data-bs-target="#details"
                        title="Details ${data.name}"
                        onclick="findById(${data.id})">
                        <span class="material-symbols-rounded"> info </span>
                    </button>
                    <button
                        type="button"
                        class="btn btn-warning d-flex align-items-center"
                        data-bs-toggle="modal"
                        data-bs-target="#update"
                        title="Update ${data.id}"
                        onclick="beforeUpdateDepartment(${data.id})">
                        <span class="material-symbols-rounded"> sync </span>
                    </button>
                    <button
                        type="button"
                        class="btn btn-danger d-flex align-items-center"
                        title="Delete ${data.name}"
                        onclick="deleteDepartment(${data.id})">
                        <span class="material-symbols-rounded"> delete </span>
                    </button>
                </div>`;
				},
			},
		],
	});
	$("#table-department").on("draw.dt", function () {
		$("#table-department")
			.DataTable()
			.column(0, { search: "applied", order: "applied" })
			.nodes()
			.each(function (cell, i) {
				cell.innerHTML = i + 1;
			});
	});
});

// Get All Locations for Create Department
$.ajax({
	type: "GET",
	contentType: "application/json; charset=utf-8",
	dataType: "JSON",
	url: "/api/location",
	success: function (response) {
		$.each(response, function (index, location) {
			$("#create-location").append(
				$("<option>")
					.text(location.streetAddress + ", " + location.city)
					.val(location.id)
			);
		});
	},
	error: function (error) {
		console.log(error);
	},
});

// Get All Managers for Create Department
$.ajax({
	type: "GET",
	contentType: "application/json; charset=utf-8",
	dataType: "JSON",
	url: "/api/employee/manager",
	success: function (response) {
		$.each(response, function (index, manager) {
			$("#create-manager").append(
				$("<option>")
					.text(
						manager.firstName +
							" " +
							manager.lastName +
							", " +
							manager.job +
							", " +
							manager.department
					)
					.val(manager.id)
			);
		});
	},
	error: function (error) {
		console.log(error);
	},
});

// Create Department
$("#create-department").click(function (e) {
	e.preventDefault();

	let valueId = $("#create-id").val();
	let valueName = $("#create-name").val();
	let valueLocation = $("#create-location").val();
	let valueManager = $("#create-manager").val();

	let validateName = validationName(valueName);

	if (validateName !== "LFG!") {
		Swal.fire({
			position: "center",
			icon: "error",
			title: validateName,
			showConfirmButton: true,
		});
	} else {
		$.ajax({
			type: "POST",
			contentType: "application/json; charset=utf-8",
			dataType: "JSON",
			beforeSend: addCSRFToken(),
			url: "/api/department",
			data: JSON.stringify({
				id: valueId,
				name: valueName,
				location_id: valueLocation,
				manager_id: valueManager,
			}),
			success: function (response) {
				$("#create").modal("hide");
				$("#table-department").DataTable().ajax.reload();
				Swal.fire({
					position: "center",
					icon: "success",
					title: "New Department Successfully Saved!",
					showConfirmButton: false,
					timer: 2000,
				});
				$("#create-id").val("");
				$("#create-name").val("");
				$("#create-location").val("");
				$("#create-manager").val("");
			},
			error: function (error) {
				console.log(error);
				Swal.fire({
					position: "center",
					icon: "error",
					title: "Error!",
					showConfirmButton: false,
					timer: 2000,
				});
			},
		});
	}
});

// Find Department by ID
function findById(id) {
	$.ajax({
		type: "GET",
		contentType: "application/json; charset=utf-8",
		dataType: "JSON",
		url: "/api/department/" + id,
		success: function (response) {
			$("#details-id").val(response.id);
			$("#details-name").val(response.name);
			$("#details-location-street").val(response.location);
			$("#details-manager-name").val(response.manager);
		},
		error: function (error) {
			console.log(error);
		},
	});
}

// Get Data Department for Update
function beforeUpdateDepartment(id) {
	$.ajax({
		type: "GET",
		contentType: "application/json; charset=utf-8",
		dataType: "JSON",
		url: "/api/department/" + id,
		success: function (response) {
			$("#update-id").val(response.id);
			$("#update-name").val(response.name);
			$("#create-location-street").val(response.location);
			$("#create-manager-name").val(response.manager);
		},
		error: function (error) {
			console.log(error);
		},
	});
}

// Get All Locations for Update Department
$.ajax({
	type: "GET",
	contentType: "application/json; charset=utf-8",
	dataType: "JSON",
	url: "/api/location",
	success: function (response) {
		$.each(response, function (index, location) {
			$("#update-location-street").append(
				$("<option>")
					.text(location.streetAddress + ", " + location.city)
					.val(location.id)
			);
		});
	},
	error: function (error) {
		console.log(error);
	},
});

// Get All Managers for Update Department
$.ajax({
	type: "GET",
	contentType: "application/json; charset=utf-8",
	dataType: "JSON",
	url: "/api/employee/manager",
	success: function (response) {
		$.each(response, function (index, manager) {
			$("#update-manager-name").append(
				$("<option>")
					.text(
						manager.firstName +
							" " +
							manager.lastName +
							", " +
							manager.job +
							", " +
							manager.department
					)
					.val(manager.id)
			);
		});
	},
	error: function (error) {
		console.log(error);
	},
});

// Update Department
$("#update-department").click(function (e) {
	e.preventDefault();

	let valueId = $("#update-id").val();
	let valueName = $("#update-name").val();
	let valueLocation = $("#update-location-street").val();
	let valueManager = $("#update-manager-name").val();

	let validateName = validationName(valueName);

	if (validateName !== "LFG!") {
		Swal.fire({
			position: "center",
			icon: "error",
			title: validateName,
			showConfirmButton: true,
		});
	} else {
		$.ajax({
			type: "PUT",
			contentType: "application/json; charset=utf-8",
			dataType: "JSON",
			beforeSend: addCSRFToken(),
			url: "/api/department/" + valueId,
			data: JSON.stringify({
				name: valueName,
				location_id: valueLocation,
				manager_id: valueManager,
			}),
			success: function (response) {
				$("#update").modal("hide");
				$("#table-department").DataTable().ajax.reload();
				Swal.fire({
					position: "center",
					icon: "success",
					title: "Department Successfully Updated!",
					showConfirmButton: false,
					timer: 2000,
				});
				$("#update-id").val("");
				$("#update-name").val("");
				$("#update-location-street").val("");
				$("#update-manager-name").val("");
			},
			error: function (error) {
				console.log(error);
				Swal.fire({
					position: "center",
					icon: "error",
					title: "Error!",
					showConfirmButton: false,
					timer: 2000,
				});
			},
		});
	}
});

// Delete Department by ID
function deleteDepartment(id) {
	Swal.fire({
		title: "Are you sure?",
		text: "You won't be able to revert this!",
		icon: "warning",
		showCancelButton: true,
		confirmButtonColor: "#3085d6",
		cancelButtonColor: "#d33",
		confirmButtonText: "Yes, delete it!",
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
				type: "DELETE",
				url: "/api/department/" + id,
				dataType: "JSON",
				beforeSend: addCSRFToken(),
				contentType: "application/json",
				success: (response) => {
					$("#table-department").DataTable().ajax.reload();
				},
				error: (error) => {
					console.log(error);
				},
			});
			Swal.fire({
				title: "Deleted!",
				text: "Department has been deleted.",
				icon: "success",
			});
		}
	});
}

// Validation for Name Department
function validationName(name) {
	if (name.length > 30) {
		return "Name Too Long!";
	} else {
		return "LFG!";
	}
}
