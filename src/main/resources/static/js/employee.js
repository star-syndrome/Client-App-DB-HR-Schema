// Dashboard
$(document).ready(() => {
	$("#table-employee").DataTable({
		ajax: {
			url: "/api/employee",
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
			{ data: "firstName" },
			{ data: "lastName" },
			{ data: "hireDate" },
			{ data: "email" },
			{ data: "phoneNumber" },
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
                        title="Details ${data.firstName + " " + data.lastName}"
                        onclick="findById(${data.id})">
                        <span class="material-symbols-rounded"> info </span>
                    </button>
                    <button
                        type="button"
                        class="btn btn-warning d-flex align-items-center"
                        data-bs-toggle="modal"
                        data-bs-target="#update"
                        title="Update ${data.firstName + " " + data.lastName}"
                        onclick="beforeUpdateEmployee(${data.id})">
                        <span class="material-symbols-rounded"> sync </span>
                    </button>
                    <button
                        type="button"
                        class="btn btn-danger d-flex align-items-center"
                        title="Delete ${data.firstName + " " + data.lastName}"
                        onclick="deleteEmployee(${data.id})">
                        <span class="material-symbols-rounded"> delete </span>
                    </button>
                </div>`;
				},
			},
		],
	});
	$("#table-employee").on("draw.dt", function () {
		$("#table-employee")
			.DataTable()
			.column(0, { search: "applied", order: "applied" })
			.nodes()
			.each(function (cell, i) {
				cell.innerHTML = i + 1;
			});
	});
});

// Get All Managers for Create Employee
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

// Get All Jobs for Create Employee
$.ajax({
	type: "GET",
	contentType: "application/json; charset=utf-8",
	dataType: "JSON",
	url: "/api/job",
	success: function (response) {
		$.each(response, function (index, job) {
			$("#create-job").append($("<option>").text(job.title).val(job.id));
		});
	},
	error: function (error) {
		console.log(error);
	},
});

// Get All Departments for Create Employee
$.ajax({
	type: "GET",
	contentType: "application/json; charset=utf-8",
	dataType: "JSON",
	url: "/api/department",
	success: function (response) {
		$.each(response, function (index, department) {
			$("#create-department").append(
				$("<option>").text(department.name).val(department.id)
			);
		});
	},
	error: function (error) {
		console.log(error);
	},
});

// Create Employee
$("#create-employee").click(function (e) {
	e.preventDefault();

	let valueFirstName = $("#create-first-name").val();
	let valueLastName = $("#create-last-name").val();
	let valueEmail = $("#create-email").val();
	let valuePhoneNumber = $("#create-phone").val();
	let valueSalary = $("#create-salary").val();
	let valueCommission = $("#create-commission").val();
	let valueManager = $("#create-manager").val();
	let valueJob = $("#create-job").val();
	let valueDepartment = $("#create-department").val();

	let validateFirstName = validationFirstName(valueFirstName);
	let validateLastName = validationLastName(valueLastName);

	if (validateFirstName !== "LFG!") {
		Swal.fire({
			position: "center",
			icon: "error",
			title: validateFirstName,
			showConfirmButton: true,
		});
	} else if (validateLastName !== "LFG!") {
		Swal.fire({
			position: "center",
			icon: "error",
			title: validateLastName,
			showConfirmButton: true,
		});
	} else {
		$.ajax({
			type: "POST",
			contentType: "application/json; charset=utf-8",
			dataType: "JSON",
			beforeSend: addCSRFToken(),
			url: "/api/employee",
			data: JSON.stringify({
				firstName: valueFirstName,
				lastName: valueLastName,
				email: valueEmail,
				phoneNumber: valuePhoneNumber,
				salary: valueSalary,
				commissionPct: valueCommission,
				manager: valueManager,
				job: valueJob,
				department: valueDepartment,
			}),
			success: function (response) {
				$("#create").modal("hide");
				$("#table-employee").DataTable().ajax.reload();
				Swal.fire({
					position: "center",
					icon: "success",
					title: "New Employee Successfully Saved!",
					showConfirmButton: false,
					timer: 2000,
				});
				$("#create-id").val("");
				$("#create-first-name").val("");
				$("#create-last-name").val("");
				$("#create-email").val("");
				$("#create-phone").val("");
				$("#create-salary").val("");
				$("#create-commission").val("");
				$("#create-manager").val("");
				$("#create-job").val("");
				$("#create-department").val("");
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

// Find Employee by ID
function findById(id) {
	$.ajax({
		type: "GET",
		contentType: "application/json; charset=utf-8",
		dataType: "JSON",
		url: "/api/employee/" + id,
		success: function (response) {
			$("#details-id").val(response.id);
			$("#details-first-name").val(response.firstName);
			$("#details-last-name").val(response.lastName);
			$("#details-email").val(response.email);
			$("#details-phone").val(response.phoneNumber);
			$("#details-hire").val(response.hireDate);
			$("#details-salary").val(response.salary);
			$("#details-commission").val(response.commissionPct);
			$("#details-manager").val(response.manager);
			$("#details-job").val(response.job);
			$("#details-department").val(response.department);
		},
		error: function (error) {
			console.log(error);
		},
	});
}

// Get Data Employee for Update
function beforeUpdateEmployee(id) {
	$.ajax({
		type: "GET",
		contentType: "application/json; charset=utf-8",
		dataType: "JSON",
		url: "/api/employee/" + id,
		success: function (response) {
			$("#update-id").val(response.id);
			$("#update-first-name").val(response.firstName);
			$("#update-last-name").val(response.lastName);
			$("#update-email").val(response.email);
			$("#update-phone").val(response.phoneNumber);
			$("#update-hire").val(response.hireDate);
			$("#update-salary").val(response.salary);
			$("#update-commission").val(response.commissionPct);
			$("#create-manager").val(response.manager);
			$("#create-job").val(response.job);
			$("#create-department").val(response.department);
		},
		error: function (error) {
			console.log(error);
		},
	});
}

// Get All Managers for Update Employee
$.ajax({
	type: "GET",
	contentType: "application/json; charset=utf-8",
	dataType: "JSON",
	url: "/api/employee/manager",
	success: function (response) {
		$.each(response, function (index, manager) {
			$("#update-manager").append(
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

// Get All Jobs for Update Employee
$.ajax({
	type: "GET",
	contentType: "application/json; charset=utf-8",
	dataType: "JSON",
	url: "/api/job",
	success: function (response) {
		$.each(response, function (index, job) {
			$("#update-job").append($("<option>").text(job.title).val(job.id));
		});
	},
	error: function (error) {
		console.log(error);
	},
});

// Get All Departments for Update Employee
$.ajax({
	type: "GET",
	contentType: "application/json; charset=utf-8",
	dataType: "JSON",
	url: "/api/department",
	success: function (response) {
		$.each(response, function (index, department) {
			$("#update-department").append(
				$("<option>").text(department.name).val(department.id)
			);
		});
	},
	error: function (error) {
		console.log(error);
	},
});

// Update Employee
$("#update-employee").click(function (e) {
	e.preventDefault();

	let valueId = $("#update-id").val();
	let valueFirstName = $("#update-first-name").val();
	let valueLastName = $("#update-last-name").val();
	let valueEmail = $("#update-email").val();
	let valuePhoneNumber = $("#update-phone").val();
	let valueSalary = $("#update-salary").val();
	let valueCommission = $("#update-commission").val();
	let valueManager = $("#update-manager").val();
	let valueJob = $("#update-job").val();
	let valueDepartment = $("#update-department").val();

	let validateFirstName = validationFirstName(valueFirstName);
	let validateLastName = validationLastName(valueLastName);

	if (validateFirstName !== "LFG!") {
		Swal.fire({
			position: "center",
			icon: "error",
			title: validateFirstName,
			showConfirmButton: true,
		});
	} else if (validateLastName !== "LFG!") {
		Swal.fire({
			position: "center",
			icon: "error",
			title: validateLastName,
			showConfirmButton: true,
		});
	} else {
		$.ajax({
			type: "PUT",
			contentType: "application/json; charset=utf-8",
			dataType: "JSON",
			beforeSend: addCSRFToken(),
			url: "/api/employee/" + valueId,
			data: JSON.stringify({
				firstName: valueFirstName,
				lastName: valueLastName,
				email: valueEmail,
				phoneNumber: valuePhoneNumber,
				salary: valueSalary,
				commissionPct: valueCommission,
				manager: valueManager,
				job: valueJob,
				department: valueDepartment,
			}),
			success: function (response) {
				$("#update").modal("hide");
				$("#table-employee").DataTable().ajax.reload();
				Swal.fire({
					position: "center",
					icon: "success",
					title: "Employee Successfully Updated!",
					showConfirmButton: false,
					timer: 2000,
				});
				$("#update-id").val("");
				$("#update-first-name").val("");
				$("#update-last-name").val("");
				$("#update-email").val("");
				$("#update-phone").val("");
				$("#update-hire").val("");
				$("#update-salary").val("");
				$("#update-commission").val("");
				$("#update-manager").val("");
				$("#update-job").val("");
				$("#update-department").val("");
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

// Delete Employee by ID
function deleteEmployee(id) {
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
				url: "/api/employee/" + id,
				dataType: "JSON",
				beforeSend: addCSRFToken(),
				contentType: "application/json",
				success: (response) => {
					$("#table-employee").DataTable().ajax.reload();
				},
				error: (error) => {
					console.log(error);
				},
			});
			Swal.fire({
				title: "Deleted!",
				text: "Employee has been deleted.",
				icon: "success",
			});
		}
	});
}

// Validation for First Name
function validationFirstName(firstName) {
	if (firstName.length > 20) {
		return "First Name Too Long!";
	} else {
		return "LFG!";
	}
}

// Validation for Last Name
function validationLastName(lastName) {
	if (lastName.length > 25) {
		return "Last Name Too Long!";
	} else if (lastName === "") {
		return "Last Name Cannot Be Empty!";
	} else {
		return "LFG!";
	}
}
