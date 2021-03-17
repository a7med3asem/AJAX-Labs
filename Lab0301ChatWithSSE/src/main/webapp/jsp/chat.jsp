<jsp:useBean id="currentUser" scope="session" class="com.mak.model.User"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chat</title>
    <link rel="stylesheet" href="/AsyncL3/bootstrap-5.0.0-beta2-dist/css/bootstrap.min.css">
</head>
<body onload="startWorker()" onunload="stopWorker()">
<main class="container-fluid my-3">
    <div class="row">
        <div class="col-sm-6">
            <section class="card mb-sm-3 shadow">
                <h2 class="card-header text-uppercase">Recent Chat history</h2>
                <section class="row card-body g-sm-3 align-items-sm-end">
                    <div id="messagesContainer" class="col-sm-12 rounded-3 border border-start overflow-auto " style="height: 50vh;">
                    </div>
                    <div class="input-group mb-3 col-sm-12">
                        <input type="text" id="message" class="form-control" placeholder="Type a message" onblur="checkMessage()">
                        <button class="btn btn-primary" type="button" onclick="sendMessage()">Send</button>
                    </div>
                </section>
            </section>
        </div>
        <div class="col-sm-6">
            <section class="card mb-sm-3 shadow">
                <h2 class="card-header text-uppercase">Online Users</h2>
                <section class="row card-body g-sm-3 align-items-sm-end">
                    <div id="usersContainer" class="col-sm-12 rounded-3 border border-start overflow-auto "
                         style="height: 35vh;">
                    </div>
                </section>
                <section class="card-footer">
                    <div class="row">
                        <div class="col-sm-9 align-self-sm-center">
                            <span>Welcome, </span>
                            <span id="welcomeUserName" class="fw-bold"><jsp:getProperty name="currentUser" property="name"/></span>
                        </div>
                        <button class="col-sm-3 btn btn-primary" type="button" onclick="disconnect()">Log Out</button>
                    </div>
                </section>
            </section>
        </div>
    </div>
</main>
<script src="/AsyncL3/bootstrap-5.0.0-beta2-dist/js/jquery-3.5.1.min.js"></script>
<script src="/AsyncL3/bootstrap-5.0.0-beta2-dist/js/bootstrap.min.js"></script>
<script src="/AsyncL3/js/Validation.js"></script>
<script src="/AsyncL3/js/chat.js"></script>
</body>
</html>