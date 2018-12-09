<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <meta charset="utf-8">
    <title>Users list</title>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <!-- Brand/logo -->
    <p class="navbar-brand"><c:out value="${requestScope.sessionLogin} (${requestScope.sessionRole.desc})" /></p>
    <!-- Links -->
    <ul class="navbar-nav">
        <li class="nav-item">
            <form action="${pageContext.servletContext.contextPath}/list" method="post">
                <input type="hidden" name="exit" value="yes" />
                <input class="btn btn-dark" type="submit" value="Sign out" />
            </form>
        </li>
    </ul>
</nav>
<div class="container">
    <h1>Users list</h1>
    <c:if test="${requestScope.sessionRole.name == 'ADMIN'}">
        <form action="${pageContext.servletContext.contextPath}/create" method="GET">
            <input class="btn btn-dark" type="submit" value="create" />
        </form>
    </c:if>
    <table class="table table-striped">
        <tr>
            <th>id</th>
            <th>name</th>
            <th>login</th>
            <th>role</th>
            <th>email</th>
            <th>create date</th>
            <th>country</th>
            <th>city</th>
            <th>settings</th>
            <c:if test="${requestScope.sessionRole.name == 'ADMIN'}">
                <th></th>
            </c:if>
        </tr>
        <c:forEach items="${requestScope.users}" var="user">
            <tr>
                <td><c:out value="${user.id}" /></td>
                <td><c:out value="${user.name}" /></td>
                <td><c:out value="${user.login}" /></td>
                <td><c:out value="${user.role.desc}" /></td>
                <td><c:out value="${user.email}" /></td>
                <td><c:out value="${user.createDate}" /></td>
                <td><c:out value="${user.country.desc}" /></td>
                <td><c:out value="${user.city.desc}" /></td>
                <c:if test="${requestScope.sessionRole.name == 'ADMIN' || requestScope.sessionLogin == user.login}">
                    <td>
                        <form action="${pageContext.servletContext.contextPath}/update" method="post">
                            <input type="hidden" name="id" value="${user.id}" />
                            <input type="hidden" name="name" value="${user.name}" />
                            <input type="hidden" name="login" value="${user.login}" />
                            <input type="hidden" name="password" value="${user.password}" />
                            <input type="hidden" name="role" value="${user.role.name}" />
                            <input type="hidden" name="email" value="${user.email}" />
                            <input type="hidden" name="country" value="${user.country.name}" />
                            <input type="hidden" name="city" value="${user.city.name}" />
                            <input type="submit" class="btn btn-dark" value="update" />
                        </form>
                    </td>
                </c:if>
                <c:if test="${requestScope.sessionRole.name == 'ADMIN'}">
                    <td>
                        <form action="${pageContext.servletContext.contextPath}/user" method="post">
                            <input type="hidden" name="id" value="${user.id}" />
                            <input type="hidden" name="action" value="delete" />
                            <input type="submit" class="btn btn-dark" value="delete" />
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
