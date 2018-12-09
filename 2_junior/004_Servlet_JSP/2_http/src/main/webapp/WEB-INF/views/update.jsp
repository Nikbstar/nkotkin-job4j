<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update user</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
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
    </script></head>
<body>
<div class="container">
    <h1>Update user</h1>
    <form action="${pageContext.servletContext.contextPath}/user" method="post" onsubmit="return validate();">
        <input type="hidden" name="id" value="${requestScope.id}" />
        <input type="hidden" name="action" value="update" />
        <c:if test="${requestScope.sessionRole != 'ADMIN'}">
            <input type="hidden" name="login" value="${requestScope.login}" />
            <input type="hidden" name="role" value="${requestScope.role}" />
        </c:if>
        <div class="form-group">
            <label>
                Name:
                <input type="text" name="name" class="form-control" placeholder="Enter name" value="${requestScope.name}" />
            </label>
        </div>
        <div class="form-group">
            <label>
                Login:
                <input ${requestScope.sessionRole == 'ADMIN' ? '' : 'disabled'} type="text" name="login" class="form-control" placeholder="Enter login" value="${requestScope.login}" />
            </label>
        </div>
        <div class="form-group">
            <label>
                Password:
                <input type="password" name="password" class="form-control" placeholder="Enter password" value="${requestScope.password}" />
            </label>
        </div>
        <div class="form-group">
            <label>
                Role:
                <select name="role" ${requestScope.sessionRole == 'ADMIN' ? '' : 'disabled'} class="form-control">
                    <c:forEach items="${requestScope.roles}" var="rol">
                        <option value="${rol.name}" ${rol.name == requestScope.role ? 'selected' : ''}><c:out value="${rol.desc}" /> </option>
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
                        <option value="${cou.name}" ${cou.name == requestScope.country ? 'selected' : ''}><c:out value="${cou.desc}" /></option>
                    </c:forEach>
                </select>
            </label>
            <label>
                City:
                <select name="city" id="city" class="form-control">
                    <c:forEach items="${requestScope.cities}" var="cit">
                        <option value="${cit.name}" ${cit.name == requestScope.city ? 'selected' : ''}><c:out value="${cit.desc}" /></option>
                    </c:forEach>
                </select>
            </label>
        </div>
        <div class="form-group">
            <label>
                Email:
                <input type="text" name="email" class="form-control" placeholder="Enter email" value="${requestScope.email}" />
            </label>
        </div>
        <input type="submit" class="btn btn-dark" value="update" />
    </form>
</div>
</body>
</html>
