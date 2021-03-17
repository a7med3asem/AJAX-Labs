<!DOCTYPE html>
<html>
    <head>
        <script>
            var req = null;

            function submitForm() {
                if (window.XMLHttpRequest) {
                    req = new XMLHttpRequest();
                } else if (window.ActiveXObject) {
                    req = new ActiveXObject(Microsoft.XMLHTTP);
                }
                req.onreadystatechange = handleReq;
                req.open("GET", "simpleResponse.txt?t=" + new Date().getTime(), true);
                req.send(null);
            }

            function handleReq() {
                if(req.readyState == 4) {
                    if(req.status == 200) {
                        document.ajax.dyn.value = "Received:" + req.responseText;
                    } else {
                        document.ajax.dyn.value = "Error code " + req.status;
                    }
                }
            }
        </script>
    </head>

    <body>
        <form method="POST" name="ajax" action="">
            <input type="button" value="Submit" onclick="submitForm();">
            <input type="text" value="" name="dyn" size="200">
        </form>
    </body>
</html>
