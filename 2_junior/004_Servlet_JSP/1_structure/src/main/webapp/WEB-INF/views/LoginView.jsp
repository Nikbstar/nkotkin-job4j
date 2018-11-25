<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <script>
        function validate() {
            var result = true;
            var name = document.getElementsByName("login")[0].value;
            if (name === '') {
                result = false;
            }
            if (!result) {
                alert("Please content input data.")
            }
            return result;
        }
    </script>
</head>
<body>
<div class="container">
    <h2>Authorization</h2>
    <form class="form-inline" role="form" action="${pageContext.servletContext.contextPath}/signin" method="post" onsubmit="return validate();">
        <label for="email2" class="mb-2 mr-sm-2">Login:</label>
        <input type="text" class="form-control mb-2 mr-sm-2" id="email2" placeholder="Enter login" name="login" />
        <label for="pwd2" class="mb-2 mr-sm-2">Password:</label>
        <input type="password" class="form-control mb-2 mr-sm-2" id="pwd2" placeholder="Enter password" name="password" />
        <input type="submit" class="btn btn-primary mb-2" value="Sing in" />
    </form>
</div>
</body>
</html>
