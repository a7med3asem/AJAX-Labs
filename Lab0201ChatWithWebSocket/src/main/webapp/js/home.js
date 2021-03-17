function checkUsername() {
    let userName = $("#userName");
    if (isUserNameValid(userName.val())) {
        changeStyle(true, userName);
    } else {
        changeStyle(false, userName);
    }
}

function changeStyle(valid, element) {
    if (valid) {
        element.removeClass("is-invalid").addClass("is-valid");
    } else {
        element.removeClass("is-valid").addClass("is-invalid");
    }
}