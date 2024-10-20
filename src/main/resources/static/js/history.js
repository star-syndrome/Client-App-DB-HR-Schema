// Dashboard
$(document).ready(() => {
	$("#table-history").DataTable({
		ajax: {
			url: "/api/history",
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
			{ data: "startDate" },
			{ data: "endDate" },
			{ data: "department" },
			{ data: "employee" },
			{ data: "job" },
			{
				data: null,
				render: (data) => {
					return /*html*/ `
                <div class="d-flex m-auto gap-4 justify-content-center">
                    <button
                        type="button"
                        class="btn btn-danger d-flex align-items-center"
                        title="Delete ${data.employee}"
                        onclick="deleteHistory(${data.id})">
                        <span class="material-symbols-rounded"> delete </span>
                    </button>
                </div>`;
				},
			},
		],
	});
	$("#table-history").on("draw.dt", function () {
		$("#table-history")
			.DataTable()
			.column(0, { search: "applied", order: "applied" })
			.nodes()
			.each(function (cell, i) {
				cell.innerHTML = i + 1;
			});
	});
});

// Get All Employees for Create History
$.ajax({
	type: "GET",
	contentType: "application/json; charset=utf-8",
	dataType: "JSON",
	url: "/api/employee",
	success: function (response) {
		$.each(response, function (index, employee) {
			$("#create-employee").append(
				$("<option>")
					.text(
						employee.firstName +
							" " +
							employee.lastName +
							", " +
							employee.job +
							", " +
							employee.department
					)
					.val(employee.id)
			);
		});
	},
	error: function (error) {
		console.log(error);
	},
});

// Get All Jobs for Create History
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

// Get All Departments for Create History
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

// Create History
$("#create-history").click(function (e) {
	e.preventDefault();

	let valueEmployee = $("#create-employee").val();
	let valueJob = $("#create-job").val();
	let valueDepartment = $("#create-department").val();

	$.ajax({
		type: "POST",
		contentType: "application/json; charset=utf-8",
		dataType: "JSON",
		beforeSend: addCSRFToken(),
		url: "/api/history",
		data: JSON.stringify({
			employee: valueEmployee,
			job: valueJob,
			department: valueDepartment,
		}),
		success: function (response) {
			$("#create").modal("hide");
			$("#table-history").DataTable().ajax.reload();
			Swal.fire({
				position: "center",
				icon: "success",
				title: "New History Successfully Saved!",
				showConfirmButton: false,
				timer: 2000,
			});
			$("#create-employee").val("");
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
});

// Delete History by ID
function deleteHistory(id) {
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
				url: "/api/history/" + id,
				dataType: "JSON",
				beforeSend: addCSRFToken(),
				contentType: "application/json",
				success: (response) => {
					$("#table-history").DataTable().ajax.reload();
				},
				error: (error) => {
					console.log(error);
				},
			});
			Swal.fire({
				title: "Deleted!",
				text: "History has been deleted.",
				icon: "success",
			});
		}
	});
}
