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
            var boolReturn = true;
            var login = $("#login").val();
            var pass = $("#password").val();
            var result = '';
            if (login === '') {
                result += " Login is not filled."
            }
            if (pass === '') {
                result += " Password is not filled."
            }
            if (result !== '') {
                alert("Please fill in all fields:" + result);
                boolReturn = false;
            }
            return boolReturn;
        }
    </script>
</head>
<body>
<div class="container">
    <h1>Authorization</h1>
    <form action="${pageContext.servletContext.contextPath}/auth" method="post" onsubmit="return validate();">
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text">Sign in:</span>
            </div>
            <input type="text" class="form-control" placeholder="Enter login" name="login" id="login">
            <input type="password" class="form-control" placeholder="Enter password" name="password" id="password">
            <c:if test="${requestScope.error != ''}">
                <div style="color:red"><c:out value="${requestScope.error}" /></div>
            </c:if>
        </div>
        <input type="submit" class="btn btn-dark" value="login" />
    </form>
</div>
</body>
</html>
