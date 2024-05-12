// Register User
$("#register-button").click(function (e) { 
    e.preventDefault();
    
    let valueName = $("#regist-name").val();
    let valueEmail = $("#regist-email").val();
    let valuePhone = $("#regist-phone").val();
    let valueUsername = $("#regist-username").val();
    let valuePassword = $("#regist-password").val();
    
        $.ajax({
            type: "POST",
            contentType: "application/json",
            beforeSend: addCSRFToken(),
            dataType: "JSON",
            url: "http://localhost:8080/auth/registration",
            data: JSON.stringify({
                name: valueName,
                email: valueEmail,
                phone: valuePhone,
                username: valueUsername,
                password: valuePassword
            }),
            success: function (response) {
                Swal.fire({
                    position: "center",
                    icon: "success",
                    title: "Registration Successful!",
                    html: '<a href="/auth/login"><b>Log in here!</b></a>',
                    showConfirmButton: false
                });
                $("#regist-name").val("");
                $("#regist-email").val("");
                $("#regist-phone").val("");
                $("#regist-username").val("");
                $("#regist-password").val("");
            },
            error: function (error) {
                console.log(error);
                Swal.fire({
                    position: "center",
                    icon: "error",
                    title: "Registration Failed!",
                    showConfirmButton: false,
                    timer: 1500
                });
            }
        });
});

// Show/Hide Password
function showPassword() {
    let showPW = document.getElementById("regist-password");
    if (showPW.type === "password") {
        showPW.type = "text";
    } else {
        showPW.type = "password";
    }
}