<%@ page import="ru.nik66.servlets.User" %>
<%@ page import="ru.nik66.servlets.UserStorage" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/echo" method="post">
    Name: <input type="text" name="name" />
    Email: <input type="text" name="email" />
    <br />
    <input type="submit" />
</form>
<br />
<table style="border: 1px solid black;" border="1">
    <tr>
        <th>Name</th>
        <th>Email</th>
    </tr>
    <%for (User user : UserStorage.getInstance().getUsers()) {%>
    <tr>
        <td><%=user.getName()%></td>
        <td><%=user.getEmail()%></td>
    </tr>
    <%}%>
</table>
</body>
</html>
