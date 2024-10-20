// Dashboard
$(document).ready(() => {
	$("#table-location").DataTable({
		ajax: {
			url: "/api/location",
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
			{ data: "streetAddress" },
			{ data: "city" },
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
                        title="Details ${data.streetAddress}"
                        onclick="findById(${data.id})">
                        <span class="material-symbols-rounded"> info </span>
                    </button>
                    <button
                        type="button"
                        class="btn btn-warning d-flex align-items-center"
                        data-bs-toggle="modal"
                        data-bs-target="#update"
                        title="Update ${data.streetAddress}"
                        onclick="beforeUpdateLocation(${data.id})">
                        <span class="material-symbols-rounded"> sync </span>
                    </button>
                    <button
                        type="button"
                        class="btn btn-danger d-flex align-items-center"
                        title="Delete ${data.streetAddress}"
                        onclick="deleteLocation(${data.id})">
                        <span class="material-symbols-rounded"> delete </span>
                    </button>
                </div>`;
				},
			},
		],
	});
	$("#table-location").on("draw.dt", function () {
		$("#table-location")
			.DataTable()
			.column(0, { search: "applied", order: "applied" })
			.nodes()
			.each(function (cell, i) {
				cell.innerHTML = i + 1;
			});
	});
});

// Get All Countries for Create Location
$.ajax({
	type: "GET",
	contentType: "application/json; charset=utf-8",
	dataType: "JSON",
	url: "/api/country/getAll",
	success: function (response) {
		$.each(response, function (index, country) {
			$("#create-country").append(
				$("<option>").text(country.name).val(country.id)
			);
		});
	},
	error: function (error) {
		console.log(error);
	},
});

// Create Location
$("#create-location").click(function (e) {
	e.preventDefault();

	let valueId = $("#create-id").val();
	let valueStreetAddress = $("#create-street").val();
	let valuePostalCode = $("#create-postal").val();
	let valueCity = $("#create-city").val();
	let valueStateProvince = $("#create-province").val();
	let valueCountry = $("#create-country").val();

	let validateID = validationLocationId(valueId);
	let validateCity = validationCity(valueCity);
	let validateStreetAddress = validationStreetAddress(valueStreetAddress);

	if (validateID !== "LFG!") {
		Swal.fire({
			position: "center",
			icon: "error",
			title: validateID,
			showConfirmButton: true,
		});
	} else if (validateCity !== "LFG!") {
		Swal.fire({
			position: "center",
			icon: "error",
			title: validateCity,
			showConfirmButton: true,
		});
	} else if (validateStreetAddress !== "LFG!") {
		Swal.fire({
			position: "center",
			icon: "error",
			title: validateStreetAddress,
			showConfirmButton: true,
		});
	} else {
		$.ajax({
			type: "POST",
			contentType: "application/json; charset=utf-8",
			dataType: "JSON",
			beforeSend: addCSRFToken(),
			url: "/api/location",
			data: JSON.stringify({
				id: valueId,
				streetAddress: valueStreetAddress,
				postalCode: valuePostalCode,
				city: valueCity,
				stateProvince: valueStateProvince,
				country: valueCountry,
			}),
			success: function (response) {
				$("#create").modal("hide");
				$("#table-location").DataTable().ajax.reload();
				Swal.fire({
					position: "center",
					icon: "success",
					title: "New Location Successfully Saved!",
					showConfirmButton: false,
					timer: 2000,
				});
				$("#create-id").val("");
				$("#create-street").val("");
				$("#create-postal").val("");
				$("#create-city").val("");
				$("#create-province").val("");
				$("#create-country").val("");
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

// Find Location by ID
function findById(id) {
	$.ajax({
		type: "GET",
		contentType: "application/json; charset=utf-8",
		dataType: "JSON",
		url: "/api/location/" + id,
		success: function (response) {
			$("#details-id").val(response.id);
			$("#details-street").val(response.streetAddress);
			$("#details-postal").val(response.postalCode);
			$("#details-city").val(response.city);
			$("#details-province").val(response.stateProvince);
			$("#details-country-name").val(response.countryName);
		},
		error: function (error) {
			console.log(error);
		},
	});
}

// Get Data Location for Update
function beforeUpdateLocation(id) {
	$.ajax({
		type: "GET",
		contentType: "application/json; charset=utf-8",
		dataType: "JSON",
		url: "/api/location/" + id,
		success: function (response) {
			$("#update-id").val(response.id);
			$("#update-street").val(response.streetAddress);
			$("#update-postal").val(response.postalCode);
			$("#update-city").val(response.city);
			$("#update-province").val(response.stateProvince);
			$("#create-country").val(response.countryName);
		},
		error: function (error) {
			console.log(error);
		},
	});
}

// Get All Countries for Update Location
$.ajax({
	type: "GET",
	contentType: "application/json; charset=utf-8",
	dataType: "JSON",
	url: "/api/country/getAll",
	success: function (response) {
		$.each(response, function (index, country) {
			$("#update-country-name").append(
				$("<option>").text(country.name).val(country.id)
			);
		});
	},
	error: function (error) {
		console.log(error);
	},
});

// Update Location
$("#update-location").click(function (e) {
	e.preventDefault();

	let valueId = $("#update-id").val();
	let valueStreetAddress = $("#update-street").val();
	let valuePostalCode = $("#update-postal").val();
	let valueCity = $("#update-city").val();
	let valueStateProvince = $("#update-province").val();
	let valueCountry = $("#update-country-name").val();

	let validateID = validationsLocationId(valueId);
	let validateCity = validationsCity(valueCity);
	let validateStreetAddress = validationsStreetAddress(valueStreetAddress);

	if (validateID !== "LFG!") {
		Swal.fire({
			position: "center",
			icon: "error",
			title: validateID,
			showConfirmButton: true,
		});
	} else if (validateCity !== "LFG!") {
		Swal.fire({
			position: "center",
			icon: "error",
			title: validateCity,
			showConfirmButton: true,
		});
	} else if (validateStreetAddress !== "LFG!") {
		Swal.fire({
			position: "center",
			icon: "error",
			title: validateStreetAddress,
			showConfirmButton: true,
		});
	} else {
		$.ajax({
			type: "PUT",
			contentType: "application/json; charset=utf-8",
			dataType: "JSON",
			beforeSend: addCSRFToken(),
			url: "/api/location/" + valueId,
			data: JSON.stringify({
				streetAddress: valueStreetAddress,
				postalCode: valuePostalCode,
				city: valueCity,
				stateProvince: valueStateProvince,
				country: valueCountry,
			}),
			success: function (response) {
				$("#update").modal("hide");
				$("#table-location").DataTable().ajax.reload();
				Swal.fire({
					position: "center",
					icon: "success",
					title: "Location Successfully Updated!",
					showConfirmButton: false,
					timer: 2000,
				});
				$("#update-id").val("");
				$("#update-street").val("");
				$("#update-postal").val("");
				$("#update-city").val("");
				$("#update-province").val("");
				$("#update-country").val("");
				$("#update-country-name").val("");
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

// Delete Location by ID
function deleteLocation(id) {
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
				url: "/api/location/" + id,
				dataType: "JSON",
				beforeSend: addCSRFToken(),
				contentType: "application/json",
				success: (response) => {
					$("#table-location").DataTable().ajax.reload();
				},
				error: (error) => {
					console.log(error);
				},
			});
			Swal.fire({
				title: "Deleted!",
				text: "Location has been deleted.",
				icon: "success",
			});
		}
	});
}

// Validation for Location ID Create
function validationLocationId(id) {
	if (id === "") {
		return "Location ID Must Not Blank!";
	} else {
		return "LFG!";
	}
}

// Validation for City Create
function validationCity(city) {
	if (city === "") {
		return "City Must Not Blank!";
	} else {
		return "LFG!";
	}
}

// Validation for Street Address Create
function validationStreetAddress(streetAddress) {
	if (streetAddress.length > 40) {
		return "Street Address Too Long!";
	} else {
		return "LFG!";
	}
}
