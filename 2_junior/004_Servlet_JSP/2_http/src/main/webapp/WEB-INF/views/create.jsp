<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create user</title>
</head>
<body>
<h1>Create user</h1>
<form action="${pageContext.servletContext.contextPath}/user" method="post">
    <input type="hidden" name="action" value="add" />
    <label>
        Name:
        <input type="text" name="name" />
    </label>
    <br />
    <label>
        Login:
        <input type="text" name="login" />
    </label>
    <br />
    <label>
        Email:
        <input type="text" name="email" />
    </label>
    <br />
    <input type="submit" value="create" />
</form>
</body>
</html>
