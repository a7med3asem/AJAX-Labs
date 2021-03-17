<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>Chat Application</title>
    <script src=http://code.jquery.com/jquery-latest.min.js></script>
    <link rel="stylesheet" href="ChatStyle.css"/>
</head>
<body>
<div id="wrapper">
    <div id="menu">
        <p class="welcome">Welcome, <b></b></p>
        <p class="logout"><a id="exit" href="index.jsp">Exit Chat</a></p>
    </div>

    <div id="chatbox"></div>

    <form name="message" action="">
        <input name="usermsg" type="text" id="usermsg"/>
        <input
                name="submitmsg"
                type="button"
                id="submitmsg"
                value="Send"

                onclick="sendMessage()"
        /><br/>
        <input name="userName" type="text" id="userName"/>
    </form>
    <div id="results">
        <table style="border: 1px solid chocolate; text-align: center; width: 100%">
            <thead>
            <tr>
                <th>User name</th>
                <th>Message</th>
            </tr>
            </thead>
            <tbody id="tableBody"></tbody>
        </table>
    </div>
</div>
<script>
    var lastMessagesCount = 0;

    function sendMessage() {
        $.post("ChatServlet", {
            name: $("#userName").val(),
            message: $("#usermsg").val()
        }, ajaxCallBack);
        $("#userName").clear();
        $("#usermsg").clear();
    }

    // $(document).ready(function () {
    //     $("#submitmsg").click(function () {
    //         name = $("#userName").val();
    //         message = $("#usermsg").val();
    //         varjsonData = {name: name, msg: message};
    //         $.post("ChatServlet? userName=" + $("#userName").val()
    //             + " message=" + $("#usermsg").val(), ajaxCallBack);
    //     });
    // });

    function ajaxCallBack(responseTxt, statusTxt, xhr) {
        if (statusTxt == "success") {
            // alert("Kaaaaak");
            $.ajax({
                    url: "ChatServlet",
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
            // $("#results").html(responseTxt);
        }
    }
</script>
</body>
</html>
