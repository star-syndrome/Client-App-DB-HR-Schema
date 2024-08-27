// Dashboard
$(document).ready(() => {
	$("#table-country").DataTable({
		ajax: {
			url: "/api/country/getAll",
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
			{ data: "code" },
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
                        title="Update ${data.name}"
                        onclick="beforeUpdateCountry(${data.id})">
                        <span class="material-symbols-rounded"> sync </span>
                    </button>
                    <button
                        type="button"
                        class="btn btn-danger d-flex align-items-center"
                        title="Delete ${data.name}"
                        onclick="deleteCountry(${data.id})">
                        <span class="material-symbols-rounded"> delete </span>
                    </button>
                </div>`;
				},
			},
		],
	});
	$("#table-country").on("draw.dt", function () {
		$("#table-country")
			.DataTable()
			.column(0, { search: "applied", order: "applied" })
			.nodes()
			.each(function (cell, i) {
				cell.innerHTML = i + 1;
			});
	});
});

// Get All Regions For Create Country
$.ajax({
	type: "GET",
	contentType: "application/json; charset=utf-8",
	dataType: "JSON",
	url: "/api/region/getAll",
	success: function (response) {
		$.each(response, function (index, region) {
			$("#create-region").append(
				$("<option>").text(region.name).val(region.id)
			);
		});
	},
	error: function (error) {
		console.log(error);
	},
});

// Create Country
$("#create-country").click(function (e) {
	e.preventDefault();

	let valueCode = $("#create-code").val();
	let valueName = $("#create-name").val();
	let valueRegion = $("#create-region").val();

	let validateCode = validationsCountryCode(valueCode);
	let validateName = validationsCountryName(valueName);

	if (validateCode !== "LFG!") {
		Swal.fire({
			position: "center",
			icon: "error",
			title: validateCode,
			showConfirmButton: true,
		});
	} else if (validateName !== "LFG!") {
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
			url: "/api/country/create",
			data: JSON.stringify({
				code: valueCode,
				name: valueName,
				regionId: valueRegion,
			}),
			success: function (response) {
				$("#create").modal("hide");
				$("#table-country").DataTable().ajax.reload();
				Swal.fire({
					position: "center",
					icon: "success",
					title: "New Country Successfully Saved!",
					showConfirmButton: false,
					timer: 1500,
				});
				$("#create-code").val("");
				$("#create-name").val("");
				$("#create-region").val("");
			},
			error: function (error) {
				console.log(error);
				Swal.fire({
					position: "center",
					icon: "error",
					title: "Country Code or Country Name Already Exists!",
					showConfirmButton: false,
					timer: 1500,
				});
			},
		});
	}
});

// Find Country By ID
function findById(id) {
	$.ajax({
		type: "GET",
		contentType: "application/json; charset=utf-8",
		dataType: "JSON",
		url: "/api/country/" + id,
		success: function (response) {
			$("#details-id").val(response.id);
			$("#details-code").val(response.code);
			$("#details-name").val(response.name);
			$("#details-region-name").val(response.regionName);
		},
		error: function (error) {
			console.log(error);
		},
	});
}

// Get Data Country for Update
function beforeUpdateCountry(id) {
	$.ajax({
		type: "GET",
		contentType: "application/json; charset=utf-8",
		dataType: "JSON",
		url: "/api/country/" + id,
		success: function (response) {
			$("#update-id").val(response.id);
			$("#update-code").val(response.code);
			$("#update-name").val(response.name);
			$("#create-region").val(response.regionName);
		},
		error: function (error) {
			console.log(error);
		},
	});
}

// Get All Regions For Update Country
$.ajax({
	type: "GET",
	contentType: "application/json; charset=utf-8",
	dataType: "JSON",
	url: "/api/region/getAll",
	success: function (response) {
		$.each(response, function (index, region) {
			$("#update-region-name").append(
				$("<option>").text(region.name).val(region.id)
			);
		});
	},
	error: function (error) {
		console.log(error);
	},
});

// Update Country
$("#update-country").click(function (e) {
	e.preventDefault();

	let valueId = $("#update-id").val();
	let valueCode = $("#update-code").val();
	let valueName = $("#update-name").val();
	let valueRegion = $("#update-region-name").val();

	let validateCode = validationsCountryCode(valueCode);
	let validateName = validationsCountryName(valueName);

	if (validateCode !== "LFG!") {
		Swal.fire({
			position: "center",
			icon: "error",
			title: validateCode,
			showConfirmButton: false,
			timer: 1500,
		});
	} else if (validateName !== "LFG!") {
		Swal.fire({
			position: "center",
			icon: "error",
			title: validateName,
			showConfirmButton: false,
			timer: 1500,
		});
	} else {
		$.ajax({
			type: "PUT",
			contentType: "application/json; charset=utf-8",
			dataType: "JSON",
			beforeSend: addCSRFToken(),
			url: "/api/country/update/" + valueId,
			data: JSON.stringify({
				code: valueCode,
				name: valueName,
				regionId: valueRegion,
			}),
			success: function (response) {
				$("#update").modal("hide");
				$("#table-country").DataTable().ajax.reload();
				Swal.fire({
					position: "center",
					icon: "success",
					title: "Country Successfully Updated!",
					showConfirmButton: false,
					timer: 1500,
				});
				$("#update-id").val("");
				$("#update-code").val("");
				$("#update-name").val("");
				$("#update-region-name").val("");
			},
			error: function (error) {
				console.log(error);
				Swal.fire({
					position: "center",
					icon: "error",
					title: "Country Code or Country Name Already Exists!",
					showConfirmButton: false,
					timer: 1500,
				});
			},
		});
	}
});

// Delete Country By ID
function deleteCountry(id) {
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
				url: "/api/country/delete/" + id,
				dataType: "JSON",
				beforeSend: addCSRFToken(),
				contentType: "application/json",
				success: (response) => {
					$("#table-country").DataTable().ajax.reload();
				},
				error: (error) => {
					console.log(error);
				},
			});
			Swal.fire({
				title: "Deleted!",
				text: "Country has been deleted.",
				icon: "success",
			});
		}
	});
}

// Validations for Country Code Create
function validationsCountryCode(code) {
	if (code === "") {
		return "Country Code Must Not Blank!";
	} else if (code.length !== 2) {
		return "Country Code Must Consist of 2 Characters!";
	} else {
		return "LFG!";
	}
}

// Validations for Country Name Create
function validationsCountryName(name) {
	if (name === "") {
		return "Country Name Must Not Blank!";
	} else if (name.length > 50) {
		return "Country Name Too Long!";
	} else {
		return "LFG!";
	}
}
