let eventSource = null;
var w;

function startWorker() {
    if (typeof (Worker) !== "undefined") {
        if (typeof (w) == "undefined") {
            w = new Worker("/AsyncL3/js/worker.js");
        }
        w.onmessage = function (event) {
            console.log(event.data);
            if (event.data.event == 'message') {
                event.data.data.forEach((m) => {
                    displayMessage(m);
                });
            } else if (event.data.event == 'onlineUser') {
                event.data.data.forEach((user) => {
                    displayOnline(user);
                });
            } else if (event.data.event == 'removeUser') {
                $("#usersContainer").empty();
                event.data.data.forEach(e => displayOnline(e));
            }
        };
        connect();
    }
}

function CustomEventObject(event, data) {
    this.event = event;
    this.data = data;
}

function stopWorker() {
    w.terminate();
    w = undefined;
}

function connect() {
    if (typeof (EventSource) !== undefined) {
        eventSource = new EventSource("chatService");

        eventSource.addEventListener('message', function (e) {
            w.postMessage(new CustomEventObject('message', e.data));
        });

        eventSource.addEventListener('onlineUser', function (e) {
            w.postMessage(new CustomEventObject('onlineUser', e.data));
        });

        eventSource.addEventListener('removeUser', function (e) {
            w.postMessage(new CustomEventObject('removeUser', e.data));
        });
    }
}

function disconnect() {
    if (eventSource != null) {
        $.post("chatService", {isClosed: true}, (req, status, xhr) => {
            if (status == "success") {
                eventSource.close();
                window.location.href = "/AsyncL3"
            }
        });
    }
}

function Message(content, time) {
    this.content = content;
    this.time = time;
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
    let userMessage = $("<span></span>").addClass("p-2 bg-light rounded-3").text(message.content);
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
        let message = new Message(messageField.val(), (new Date()).toLocaleString());
        $.post("chatService", message, null);
        messageField.val(null).removeClass("is-valid");
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