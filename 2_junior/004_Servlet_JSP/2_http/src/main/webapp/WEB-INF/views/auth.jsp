<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization</title>
</head>
<body>
<h1>Authorization</h1>
<form action="${pageContext.servletContext.contextPath}/auth" method="post">
    <label>
        Login:
        <input type="text" name="login" />
        <c:if test="${requestScope.error != ''}">
            <div style="color:red"><c:out value="${requestScope.error}" /></div>
        </c:if>
    </label>
    <br />
    <label>
        Password:
        <input type="password" name="password" />
    </label>
    <br />
    <input type="submit" value="login" />
</form>
</body>
</html>
