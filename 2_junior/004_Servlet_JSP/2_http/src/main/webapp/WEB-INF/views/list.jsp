<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users list</title>
</head>
<body>
<div>
    <c:out value="${requestScope.sessionLogin} (${requestScope.sessionRole.desc})" />
    <form action="${pageContext.servletContext.contextPath}/list" method="post">
        <input type="hidden" name="exit" value="yes" />
        <input type="submit" value="Sign out" />
    </form>
</div>
<hr />
<h1>Users list</h1>
<c:if test="${requestScope.sessionRole.name == 'ADMIN'}">
    <form action="${pageContext.servletContext.contextPath}/create" method="GET">
        <input type="submit" value="create" />
    </form>
</c:if>
<table border="1">
    <c:forEach items="${requestScope.users}" var="user">
        <tr>
            <td><c:out value="${user.id}" /></td>
            <td><c:out value="${user.name}" /></td>
            <td><c:out value="${user.login}" /></td>
            <td><c:out value="${user.role.desc}" /></td>
            <td><c:out value="${user.email}" /></td>
            <td><c:out value="${user.createDate}" /></td>
            <c:if test="${requestScope.sessionRole.name == 'ADMIN' || requestScope.sessionLogin == user.login}">
                <td>
                    <form action="${pageContext.servletContext.contextPath}/update" method="post">
                        <input type="hidden" name="id" value="${user.id}" />
                        <input type="hidden" name="name" value="${user.name}" />
                        <input type="hidden" name="login" value="${user.login}" />
                        <input type="hidden" name="password" value="${user.password}" />
                        <input type="hidden" name="role" value="${user.role.name}" />
                        <input type="hidden" name="email" value="${user.email}" />
                        <input type="submit" value="update" />
                    </form>
                </td>
            </c:if>
            <c:if test="${requestScope.sessionRole.name == 'ADMIN'}">
                <td>
                    <form action="${pageContext.servletContext.contextPath}/user" method="post">
                        <input type="hidden" name="id" value="${user.id}" />
                        <input type="hidden" name="action" value="delete" />
                        <input type="submit" value="delete" />
                    </form>
                </td>
            </c:if>
        </tr>
    </c:forEach>
</table>
</body>
</html>
