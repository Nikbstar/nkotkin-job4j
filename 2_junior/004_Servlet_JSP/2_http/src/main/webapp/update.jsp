<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update user</title>
</head>
<body>
<h1>Update user</h1>
<form action="<%=request.getContextPath()%>/user" method="post">
    <input type="hidden" name="id" value="<%=request.getParameter("id")%>" />
    <input type="hidden" name="action" value="update" />
    <label>
        Name:
        <input type="text" name="name" value="<%=request.getParameter("name")%>" />
    </label>
    <br />
    <label>
        Login:
        <input type="text" name="login" value="<%=request.getParameter("login")%>" />
    </label>
    <br />
    <label>
        Email:
        <input type="text" name="email" value="<%=request.getParameter("email")%>" />
    </label>
    <br />
    <input type="submit" value="update" />
</form>
</body>
</html>
