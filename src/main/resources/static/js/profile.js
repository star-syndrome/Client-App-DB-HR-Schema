// Dashboard
$(document).ready(function () {
	$.ajax({
		type: "GET",
		contentType: "application/json; charset=utf-8",
		dataType: "JSON",
		url: "/api/profile/get",
		success: function (response) {
			$("#profile-id").val(response.id);
			$("#profile-name").val(response.name);
			$("#profile-email").val(response.email);
			$("#profile-phone").val(response.phone);
			$("#profile-username").val(response.username);
		},
		error: function (error) {
			console.log(error);
		},
	});
});

// Update User
$("#update-user").click(function (e) {
	e.preventDefault();

	let valueName = $("#profile-name").val();
	let valueEmail = $("#profile-email").val();
	let valuePhone = $("#profile-phone").val();

	let validateProfile = ValidationsProfile(valueEmail, valuePhone, valueName);

	if (validateProfile !== "LFG!") {
		Swal.fire({
			position: "center",
			icon: "error",
			title: validateProfile,
			showConfirmButton: false,
			timer: 1500,
		});
	} else {
		$.ajax({
			type: "PUT",
			contentType: "application/json; charset=utf-8",
			dataType: "JSON",
			beforeSend: addCSRFToken(),
			url: "/api/profile/update",
			data: JSON.stringify({
				name: valueName,
				email: valueEmail,
				phone: valuePhone,
			}),
			success: function (response) {
				Swal.fire({
					position: "center",
					icon: "success",
					title: "User Successfully Updated!",
					showConfirmButton: false,
					timer: 1500,
				});
			},
			error: function (error) {
				console.log(error);
				Swal.fire({
					position: "center",
					icon: "error",
					title: "Email or Phone Already Exists!",
					showConfirmButton: false,
					timer: 1500,
				});
			},
		});
	}
});

// Update Password
$("#update-password").click(function (e) {
	e.preventDefault();

	let valueCurrentPassword = $("#current-password").val();
	let valueNewPassword = $("#new-password").val();
	let valueRepeatNewPassword = $("#repeat-new-password").val();

	let validatePassword = validationsPassword(
		valueCurrentPassword,
		valueNewPassword,
		valueRepeatNewPassword
	);
	if (validatePassword !== "LFG!") {
		Swal.fire({
			position: "center",
			icon: "error",
			title: validatePassword,
			showConfirmButton: false,
			timer: 1500,
		});
	} else {
		$.ajax({
			type: "PUT",
			contentType: "application/json; charset=utf-8",
			dataType: "JSON",
			beforeSend: addCSRFToken(),
			url: "/api/profile/changePassword",
			data: JSON.stringify({
				currentPassword: valueCurrentPassword,
				newPassword: valueNewPassword,
				confirmationPassword: valueRepeatNewPassword,
			}),
			success: function (response) {
				Swal.fire({
					position: "center",
					icon: "success",
					title: "Password Successfully Updated!",
					text: "Please log out and re-login :)",
					showConfirmButton: true,
				});
				$("#current-password").val("");
				$("#new-password").val("");
				$("#repeat-new-password").val("");
			},
			error: function (error) {
				console.log(error);
				Swal.fire({
					position: "center",
					icon: "error",
					title: "Update Password Failed!",
					showConfirmButton: false,
					timer: 1500,
				});
			},
		});
	}
});

function ValidationsProfile(email, phone, name) {
	if (email === "") {
		return "Email Must Not Blank!";
	} else if (phone === "") {
		return "Phone Must Not Blank!";
	} else if (name === "") {
		return "Name Must Not Blank!";
	} else {
		return "LFG!";
	}
}

// Validations Password
function validationsPassword(currentPassword, newPassword, repeatNewPassword) {
	if (currentPassword === "") {
		return "Password Must Not Blank!";
	} else if (newPassword === "" || repeatNewPassword === "") {
		return "New Password Must Not Blank!";
	} else if (newPassword !== repeatNewPassword) {
		return "The Passwords Do Not Match!";
	} else {
		return "LFG!";
	}
}

function showCurrentPassword() {
	let showPW = document.getElementById("current-password");
	if (showPW.type === "password") {
		showPW.type = "text";
	} else {
		showPW.type = "password";
	}
}

function showNewPassword() {
	let showPW = document.getElementById("new-password");
	if (showPW.type === "password") {
		showPW.type = "text";
	} else {
		showPW.type = "password";
	}
}

function showRepeatNewPassword() {
	let showPW = document.getElementById("repeat-new-password");
	if (showPW.type === "password") {
		showPW.type = "text";
	} else {
		showPW.type = "password";
	}
}
