<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/" method="post">
    <label>
        Name:
        <input type="text" name="name"/>
    </label>
    <label>
        Email:
        <input type="text" name="email"/>
    </label>
    <br />
    <input type="submit" />
</form>
<br />
<table style="border: 1px solid black;" border="1">
    <tr>
        <th>Name</th>
        <th>Email</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.name}" /></td>
            <td><c:out value="${user.email}" /></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
