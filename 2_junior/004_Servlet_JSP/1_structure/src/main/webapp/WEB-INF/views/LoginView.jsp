<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization</title>
</head>
<body>
<c:if test="${requestScope.error != ''}">
    <div style="background-color: red;"><c:out value="${requestScope.error}" /></div>
</c:if>
<form action="${pageContext.servletContext.contextPath}/signin" method="post">
    <label>
        Login:
        <input type="text" name="login"/>
    </label>
    <label>
        Password:
        <input type="password" name="password"/>
    </label>
    <br />
    <input type="submit" value="login" />
</form>
</body>
</html>
