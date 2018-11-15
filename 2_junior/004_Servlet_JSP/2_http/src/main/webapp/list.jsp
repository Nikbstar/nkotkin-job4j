<%@ page import="ru.nik66.crudservlet.model.User" %>
<%@ page import="ru.nik66.crudservlet.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users list</title>
</head>
<body>
<h1>Users list</h1>
<form action="<%=request.getContextPath()%>/create.jsp" method="GET">
    <input type="submit" value="create" />
</form>
<table border="1">
    <%for (User user : ValidateService.getInstance().findAll()) {%>
    <tr>
        <td><%=user.getId()%></td>
        <td><%=user.getName()%></td>
        <td><%=user.getLogin()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=user.getCreateDate()%></td>
        <td>
            <form action="<%=request.getContextPath()%>/update.jsp" method="post">
                <input type="hidden" name="id" value="<%=user.getId()%>" />
                <input type="hidden" name="name" value="<%=user.getName()%>" />
                <input type="hidden" name="login" value="<%=user.getLogin()%>" />
                <input type="hidden" name="email" value="<%=user.getEmail()%>" />
                <input type="submit" value="update" />
            </form>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/user" method="post">
                <input type="hidden" name="id" value="<%=user.getId()%>" />
                <input type="hidden" name="action" value="delete" />
                <input type="submit" value="delete" />
            </form>
        </td>
    </tr>
    <%}%>
</table>
</body>
</html>
