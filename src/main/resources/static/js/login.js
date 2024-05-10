function showPassword() {
    let showPW = document.getElementById("password");
    
    if (showPW.type === "password") {
        showPW.type = "text";
    } else {
        showPW.type = "password";
    }
}