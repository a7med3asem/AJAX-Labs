var lastMessagesCount = 0;

$(function () {
        setInterval(getData, 5000);
    }
);

function checkUsername() {
    let userNameField = document.querySelector("#userName");
    if (isUserNameValid(userNameField.value)) {
        changeStyle(true, userNameField);
    } else {
        changeStyle(false, userNameField);
    }
}

function checkMessage() {
    let messageField = document.querySelector("#message");
    if (!isEmpty(messageField.value)) {
        changeStyle(true, messageField);
    } else {
        changeStyle(false, messageField);
    }
}

function send() {
    $.post("chat", {userName: $("#userName").val(), message: $("#message").val()}, (data, status) => {
        if (status == "success") {
           getData();
        }
    });
}

function changeStyle(valid, element) {
    if (valid) {
        if (element.classList.contains("is-invalid")) {
            element.classList.remove("is-invalid");
        }
        element.classList.add("is-valid");
    } else {
        if (element.classList.contains("is-valid")) {
            element.classList.remove("is-valid");
        }
        element.classList.add("is-invalid");
    }
}

function getData() {
    $.ajax({
            url: "chat",
            type: "GET",
            contentType: "application/json",
            data: {length: lastMessagesCount},
            dataType: "json",
            success: (data) => {
                lastMessagesCount += data.length;
                for (let i = 0; i < data.length; i++) {
                    let user = $("<td></td>").text(data[i].userName);
                    let message = $("<td></td>").text(data[i].message);
                    $("#tableBody").append($("<tr></tr>").append(user, message));
                }
            }
        }
    )
}