<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <title>Create user</title>
    <script>
        $(document).ready(function () {
            $("#country").bind("change", function () {
                $("#city").empty();
                $.ajax({
                    type: "GET",
                    url: "${pageContext.servletContext.contextPath}/country",
                    data: {country:$("#country").val()},
                    complete: function (data) {
                        data = JSON.parse(data.responseText);
                        for (var key in data) {
                            $("#city").append($("<option value='" + key + "'>" + data[key] + "</option>"));
                        }
                    }
                });
            });
        });
        function validate() {
            var boolReturn = true;
            var name = $("#name").val();
            var login = $("#login").val();
            var pass = $("#password").val();
            var email = $("#email").val();
            var result = '';
            if (name === '') {
                result += " Name is not filled."
            }
            if (login === '') {
                result += " Login is not filled."
            }
            if (email === '') {
                result += " Email is not filled."
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
    <h1>Create user</h1>
    <form action="${pageContext.servletContext.contextPath}/user" method="post" onsubmit="return validate();">
        <input type="hidden" name="action" value="add" />
        <div class="form-group">
            <label>
                Name:
                <input type="text" name="name" id="name" class="form-control" placeholder="Enter name" />
            </label>
        </div>
        <div class="form-group">
            <label>
                Login:
                <input type="text" name="login" id="login" class="form-control" placeholder="Enter login" />
            </label>
        </div>
        <div class="form-group">
            <label>
                Password:
                <input type="password" name="password" id="password" class="form-control" placeholder="Enter password" />
            </label>
        </div>
        <div class="form-group">
            <label>
                Role:
                <select name="role" class="form-control">
                    <c:forEach items="${requestScope.roles}" var="rol">
                        <option value="${rol.name}"><c:out value="${rol.desc}" /></option>
                    </c:forEach>
                </select>
            </label>
        </div>
        <div class="form-group">
            <label>
                Country:
                <select name="country" id="country" class="form-control">
                    <option value="0" selected="selected">Select country</option>
                    <c:forEach items="${requestScope.countries}" var="cou">
                        <option value="${cou.name}"><c:out value="${cou.desc}" /></option>
                    </c:forEach>
                </select>
            </label>
            <label>
                City:
                <select name="city" id="city" class="form-control">
                    <option value="0"></option>
                </select>
            </label>
        </div>
        <div class="form-group">
            <label>
                Email:
                <input type="text" name="email" id="email" class="form-control" placeholder="Enter email" />
            </label>
        </div>
        <input type="submit" class="btn btn-dark" value="create" />
    </form>
</div>
</body>
</html>
