let user = null;
let socket = null;

function connect() {
    let params = new URLSearchParams(window.location.search);
    if (params.has("userName") && params.has("gender")) {
        let userName = params.get("userName");
        let gender = params.get("gender");
        if (userName != null && gender != null) {
            user = {name: userName, gender: gender};
            $("#welcomeUserName").text(userName);
            console.log(JSON.stringify(user));
            socket = new WebSocket("ws://localhost:9595/AsyncL3/chat/{" + JSON.stringify(user) + "}");
            socket.onmessage = onMessage;
        }
    }
}

function disconnect() {
    if (socket != null) {
        socket.close();
        window.location.href = "http://localhost:9595/AsyncL3/"
    }
}

function Message(content, time, user) {
    this.content = content;
    this.time = time;
    this.user = user;
}

function onMessage(evt) {
    let data = JSON.parse(evt.data);
    console.log(data);
    if (data instanceof Array) {
        try {
            if (data[0].user !== undefined) {
                $("#messagesContainer").empty();
            }
            data.forEach((e) => {
                displayMessage(e);
            });
        } catch (e) {
            $("#usersContainer").empty();
            data.forEach((e) => {
                displayOnline(e);
            });
        }
    } else {
        try {
            data.user;
            displayMessage(data);
        } catch (e) {
            displayOnline(data);
        }
    }
}

function displayMessage(message) {
    let img = $("<img>").addClass("me-3 align-self-sm-center")
        .attr("atr", "User Image")
        .css({
            "height": "50%",
            "width": "10%"
        });
    if (message.user.gender == "male") {
        img.attr("src", "/AsyncL3/images/male.png");
    } else {
        img.attr("src", "/AsyncL3/images/female.png");
    }
    let userName = $("<span></span>").addClass("text-secondary fw-bold").text(message.user.name);
    let userMessage = $("<span></span>").addClass("p-2 bg-light").text(message.content);
    let time = $("<span></span>").addClass("text-secondary text-uppercase").css("font-size", ".7em").text(message.time);
    let container = $("<div></div>").addClass("d-flex flex-column flex-sm-grow-1").append(userName, userMessage, time);
    let parent = $("<div></div>").addClass("d-flex my-3").append(img, container);
    $("#messagesContainer").append(parent);
}

function displayOnline(user) {
    let img = $("<img>").addClass("me-3 align-self-sm-center")
        .attr("atr", "User Image")
        .css({
            "height": "50%",
            "width": "10%"
        });
    if (user.gender == "male") {
        img.attr("src", "/AsyncL3/images/male.png");
    } else {
        img.attr("src", "/AsyncL3/images/female.png");
    }
    let userName = $("<span></span>").addClass("text-secondary fw-bold").text(user.name);
    let time = $("<span></span>").addClass("text-secondary text-uppercase").css("font-size", ".7em").text(user.time);
    let container = $("<div></div>").addClass("d-flex flex-column flex-sm-grow-1").append(userName, time);
    let parent = $("<div></div>").addClass("d-flex my-3").append(img, container);
    $("#usersContainer").append(parent);
}

function sendMessage() {
    let messageField = $("#message");
    if (!isEmpty(messageField.val())) {
        if (socket != null) {
            let message = new Message(messageField.val(), (new Date()).toLocaleString(), user);
            socket.send(JSON.stringify(message));
            messageField.val("");
        }
    }
}

function checkMessage() {
    let messageField = $("#message");
    if (!isEmpty(messageField.val())) {
        changeStyle(true, messageField);
    } else {
        changeStyle(false, messageField);
    }
}


function changeStyle(valid, element) {
    if (valid) {
        element.removeClass("is-invalid").addClass("is-valid");
    } else {
        element.removeClass("is-valid").addClass("is-invalid");
    }
}