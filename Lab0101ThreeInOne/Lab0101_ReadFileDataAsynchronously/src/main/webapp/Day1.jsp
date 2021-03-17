<!DOCTYPE html>
<html>
    <head>
        <script>
            var req = null;

            function submitForm() {
                // after this readyState = 0 => UNOPENED
                if (window.XMLHttpRequest) {
                    // for Firefox, safari and chrome
                    req = new XMLHttpRequest();
                } else if (window.ActiveXObject) {
                    // for Internet Explorer
                    req = new ActiveXObject(Microsoft.XMLHTTP);
                }
                // after open() ready State incremented to 1 => OPENED
                // send date to prevent cached url, as cached url cannot be reloaded
                req.open("GET", "simpleResponse.txt?t=" + new Date().getTime(), true);
                // assign callback method to be called
                req.onreadystatechange = handleReq;
                // send a dummy data
                // after send() readyState = 2 => HEADERS_RECEIVED
                req.send(null);
                // after that readyState = 3 => LOADING Downloading data, responseText holds the data
            }

            // instructions to process the response
            function handleReq() {
                // readyState = 4 => DONE response loaded successfully
                if(req.readyState == 4) {
                    // status number of a request
                    if(req.status == 200) { // success
                        document.ajax.dyn.value = "Received:" + req.responseText;
                    } else { // failed
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
