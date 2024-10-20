// Dashboard
$(document).ready(() => {
	$("#table-job").DataTable({
		ajax: {
			url: "/api/job",
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
			{ data: "title" },
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
                        title="Details ${data.title}"
                        onclick="findById(${data.id})">
                        <span class="material-symbols-rounded"> info </span>
                    </button>
                    <button
                        type="button"
                        class="btn btn-warning d-flex align-items-center"
                        data-bs-toggle="modal"
                        data-bs-target="#update"
                        title="Update ${data.title}"
                        onclick="beforeUpdateJob(${data.id})">
                        <span class="material-symbols-rounded"> sync </span>
                    </button>
                    <button
                        type="button"
                        class="btn btn-danger d-flex align-items-center"
                        title="Delete ${data.title}"
                        onclick="deleteJob(${data.id})">
                        <span class="material-symbols-rounded"> delete </span>
                    </button>
                </div>`;
				},
			},
		],
	});
	$("#table-job").on("draw.dt", function () {
		$("#table-job")
			.DataTable()
			.column(0, { search: "applied", order: "applied" })
			.nodes()
			.each(function (cell, i) {
				cell.innerHTML = i + 1;
			});
	});
});

// Create Job
$("#create-job").click(function (e) {
	e.preventDefault();

	let valueId = $("#create-id").val();
	let valueCode = $("#create-code").val();
	let valueTitle = $("#create-title").val();
	let valueMin = $("#create-min").val();
	let valueMax = $("#create-max").val();

	let validateTitle = validationTitle(valueTitle);
	let validateCode = validationCode(valueCode);

	if (validateTitle !== "LFG!") {
		Swal.fire({
			position: "center",
			icon: "error",
			title: validateTitle,
			showConfirmButton: true,
		});
	} else if (validateCode !== "LFG!") {
		Swal.fire({
			position: "center",
			icon: "error",
			title: validateCode,
			showConfirmButton: true,
		});
	} else {
		$.ajax({
			type: "POST",
			contentType: "application/json; charset=utf-8",
			dataType: "JSON",
			beforeSend: addCSRFToken(),
			url: "/api/job",
			data: JSON.stringify({
				id: valueId,
				code: valueCode,
				title: valueTitle,
				minSalary: valueMin,
				maxSalary: valueMax,
			}),
			success: function (response) {
				$("#create").modal("hide");
				$("#table-job").DataTable().ajax.reload();
				Swal.fire({
					position: "center",
					icon: "success",
					title: "New Job Successfully Saved!",
					showConfirmButton: false,
					timer: 2000,
				});
				$("#create-id").val("");
				$("#create-code").val("");
				$("#create-title").val("");
				$("#create-min").val("");
				$("#create-max").val("");
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

// Find Job by ID
function findById(id) {
	$.ajax({
		type: "GET",
		contentType: "application/json; charset=utf-8",
		dataType: "JSON",
		url: "/api/job/" + id,
		success: function (response) {
			$("#details-id").val(response.id);
			$("#details-code").val(response.code);
			$("#details-title").val(response.title);
			$("#details-min").val(response.minSalary);
			$("#details-max").val(response.maxSalary);
		},
		error: function (error) {
			console.log(error);
		},
	});
}

// Get Data Job for Update
function beforeUpdateJob(id) {
	$.ajax({
		type: "GET",
		contentType: "application/json; charset=utf-8",
		dataType: "JSON",
		url: "/api/job/" + id,
		success: function (response) {
			$("#update-id").val(response.id);
			$("#update-code").val(response.code);
			$("#update-title").val(response.title);
			$("#update-min").val(response.minSalary);
			$("#update-max").val(response.maxSalary);
		},
		error: function (error) {
			console.log(error);
		},
	});
}

// Update Job
$("#update-job").click(function (e) {
	e.preventDefault();

	let valueId = $("#update-id").val();
	let valueCode = $("#update-code").val();
	let valueTitle = $("#update-title").val();
	let valueMin = $("#update-min").val();
	let valueMax = $("#update-max").val();

	let validateTitle = validationTitle(valueTitle);
	let validateCode = validationCode(valueCode);

	if (validateTitle !== "LFG!") {
		Swal.fire({
			position: "center",
			icon: "error",
			title: validateTitle,
			showConfirmButton: true,
		});
	} else if (validateCode !== "LFG!") {
		Swal.fire({
			position: "center",
			icon: "error",
			title: validateCode,
			showConfirmButton: true,
		});
	} else {
		$.ajax({
			type: "PUT",
			contentType: "application/json; charset=utf-8",
			dataType: "JSON",
			beforeSend: addCSRFToken(),
			url: "/api/job/" + valueId,
			data: JSON.stringify({
				title: valueTitle,
				code: valueCode,
				minSalary: valueMin,
				maxSalary: valueMax,
			}),
			success: function (response) {
				$("#update").modal("hide");
				$("#table-job").DataTable().ajax.reload();
				Swal.fire({
					position: "center",
					icon: "success",
					title: "Job Successfully Updated!",
					showConfirmButton: false,
					timer: 2000,
				});
				$("#update-id").val("");
				$("#update-code").val("");
				$("#update-title").val("");
				$("#update-min").val("");
				$("#update-max").val("");
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

// Delete Job by ID
function deleteJob(id) {
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
				url: "/api/job/" + id,
				dataType: "JSON",
				beforeSend: addCSRFToken(),
				contentType: "application/json",
				success: (response) => {
					$("#table-job").DataTable().ajax.reload();
				},
				error: (error) => {
					console.log(error);
				},
			});
			Swal.fire({
				title: "Deleted!",
				text: "Job has been deleted.",
				icon: "success",
			});
		}
	});
}

// Validation for Title Job
function validationTitle(title) {
	if (title.length > 35) {
		return "Title Too Long!";
	} else {
		return "LFG!";
	}
}

// Validation for Code Job
function validationCode(code) {
	if (code.length > 10) {
		return "Code Too Long!";
	} else {
		return "LFG!";
	}
}
