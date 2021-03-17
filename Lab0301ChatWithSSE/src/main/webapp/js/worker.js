let onlineUsers = [];

this.onmessage = function (event) {
    if (event.data.event == 'message') {
        var messageData = JSON.parse(event.data.data);
        if (messageData.length > 0) {
            postMessage(new CustomEventObject('message', messageData));
        }
    } else if (event.data.event == 'onlineUser') {
        var onlineUser = JSON.parse(event.data.data);
        if (onlineUser.length > 0) {
            postMessage(new CustomEventObject('onlineUser', onlineUser));
            onlineUser.forEach((user) => {
                onlineUsers.push(user);
            });
        }
    } else if (event.data.event == 'removeUser') {
        var removedUser = JSON.parse(event.data.data);
        if (removedUser.length > 0) {
            removedUser.forEach((user) => {
                let index = onlineUsers.findIndex((value) => {
                    if (value.name == user.name && value.time == user.time) {
                        return true;
                    }
                });
                if (index != -1) {
                    onlineUsers.splice(index, 1);
                }
            });
            postMessage(new CustomEventObject('removeUser', onlineUsers));
        }
    }
}

function CustomEventObject(event, data) {
    this.event = event;
    this.data = data;
}