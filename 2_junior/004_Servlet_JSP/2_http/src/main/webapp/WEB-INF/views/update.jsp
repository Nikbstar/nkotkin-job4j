<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update user</title>
</head>
<body>
<h1>Update user</h1>
<form action="${pageContext.servletContext.contextPath}/user" method="post">
    <input type="hidden" name="id" value="${requestScope.id}" />
    <input type="hidden" name="action" value="update" />
    <label>
        Name:
        <input type="text" name="name" value="${requestScope.name}" />
    </label>
    <br />
    <label>
        Login:
        <input type="text" name="login" value="${requestScope.login}" />
    </label>
    <br />
    <label>
        Email:
        <input type="text" name="email" value="${requestScope.email}" />
    </label>
    <br />
    <input type="submit" value="update" />
</form>
</body>
</html>
