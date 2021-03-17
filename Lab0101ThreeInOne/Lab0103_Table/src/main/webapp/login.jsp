<!DOCTYPE html>

<html>
  <head>
    <title>Login</title>
    <link rel="stylesheet" href="styles.css">
    <!-- <style src="styles.css"/> -->
    <script>
      var req = null;
      function submitForm() {
        if (window.XMLHttpRequest) {
          req = new XMLHttpRequest();
        } else if (window.ActiveXObject) {
          req = new ActiveXObject(Microsoft.XMLHTTP);
        }
        req.onreadystatechange = handleStateChange;
        yourvalue = document.getElementById("userName").value;
        url = "login?uName=" + yourvalue + "&timeStamp=" + new Date().getTime();
        req.open("GET", url, true);
        req.send(null);
      }
      function handleStateChange() {
        if (req.readyState == 4 && req.status == 200) {
          xmlvalue = req.responseText;
          document.getElementById("status").value = xmlvalue;
        }
      }
    </script>
  </head>

  <body>
  <div class="container" id="container">
	<div class="form-container sign-up-container">
	</div>
	<div class="form-container sign-in-container">
		<form action="#" name="ajax">
			<h1>Sign in</h1>
            <label for="userName">Enter your name: </label><br />
            <input
              type="text"
              id="userName"
              name="userName"
              value=""
              size="20"
              onblur="submitForm();"
            />
            <input type="text" name="status" id="status" /><br>
            <label for="pass">Enter your password: </label><br>
            <input type="password" id="pass" name="pass" /><br>
                  <a href="#">Forgot your password?</a>
			<button>Sign In</button>
		</form>
	</div>
</div>
</html>

<footer>
	<p>
		Created with <i class="fa fa-heart"></i> by
		<a target="_blank" href="https://florin-pop.com">Florin Pop</a>
		- Read how I created this and how you can join the challenge
		<a target="_blank" href="https://www.florin-pop.com/blog/2019/03/double-slider-sign-in-up-form/">here</a>.
	</p>
</footer>