<!DOCTYPE html>

<html>
  <head>
    <script type="text/javascript">
      var xmlHttp;

      function startRequest() {
        createXMLHttpRequest();
        xmlHttp.onreadystatechange = handleStateChange;
        xmlHttp.open("GET", "innerHTML.txt", true);
        xmlHttp.send(null);
      }

      function createXMLHttpRequest() {
        if (window.ActiveXObject) {
          xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        } else if (window.XMLHttpRequest) {
          xmlHttp = new XMLHttpRequest();
        }
      }

      function handleStateChange() {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
          document.getElementById("results").innerHTML = xmlHttp.responseText;
        }
      }
    </script>
  </head>
  <body onload="setInterval('startRequest()' ,1000)">
    <div id="results"></div>
  </body>
</html>
