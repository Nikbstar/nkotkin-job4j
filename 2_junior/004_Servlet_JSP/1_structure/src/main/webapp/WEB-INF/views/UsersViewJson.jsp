<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <script>
        $(loadUsers());
        function loadUsers() {
            $.ajax('./json', {
                method : 'get',
                complete: function (data) {
                    var result = "<tr><th>Login</th><th>Email</th></tr>";
                    var users = JSON.parse(data.responseText);
                    for (var i = 0; i !== users.length ; ++i) {
                        result += "<tr>" +
                            "<td>"+ users[i].login + "</td>" +
                            "<td>" + users[i].email + "</td>" +
                            "</tr>";
                    }
                    var table = document.getElementById("table-users");
                    table.innerHTML = result;
                }
            });
        }
        function createUser() {
            $.ajax('./json', {
                method : 'post',
                data : {login : document.getElementsByName("login")[0].value,
                        email : document.getElementsByName("email")[0].value},
                complete: loadUsers()
            });
            return false; // чтобы по нажатии никакие действия не происходили
        }
    </script>
</head>
<body>
<div class="container">
    <h2>Add Users</h2>
    <form class="form-inline" role="form" action="${pageContext.servletContext.contextPath}/" method="post">
        <label for="login" class="mb-2 mr-sm-2">Login:</label>
        <input type="text" class="form-control mb-2 mr-sm-2" id="login" placeholder="Enter login" name="login" />
        <label for="email" class="mb-2 mr-sm-2">Email:</label>
        <input type="text" class="form-control mb-2 mr-sm-2" id="email" placeholder="Enter email" name="email" />
        <input type="button" class="btn btn-primary mb-2" value="Add" onclick="return createUser();" />
    </form>
</div>
<div class="container">
    <table id="table-users" class="table table-striped">
        <%-- Datas added from script --%>
    </table>
</div>
</body>
</html>
