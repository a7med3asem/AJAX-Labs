var jQ;

this.onmessage = function (workerEvent) {
    if (workerEvent.data.event == 'sendMessage') {
        let message = new Message(workerEvent.data.data, (new Date()).toLocaleString());
        jQ.post("chatService", message, null);
    } else if (workerEvent.data.event == 'disconnect') {
        $.post("chatService", {isClosed: true}, (req, status, xhr) => {
            if (status == "success") {
                postMessage("success");
                location.href = "/AsyncL3";
            }
        });
    } else {
        jQ = workerEvent.data;
    }
}

function Message(content, time) {
    this.content = content;
    this.time = time;
}
