<!DOCTYPE html>

<html>

<head>
    <title>Spring Boot learning</title>
</head>

<body>

    <form action="getEmail">

        <input type="text" name="param">Email<br>

        <button id="submit"  onclick="display();">Submit</button>

    </form>

    <div id="test_text">
        This is my DIV element.
      </div>
   <script>
      function show() {
        var x = document.getElementById("test_text");
        if (x.style.display === "none") {
          x.style.display = "block";
          x.innerHTML="Hi";
        } else {
          x.style.display = "none";
        }
      }
    </script>
</body>

</html>