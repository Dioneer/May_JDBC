<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/login" method="post">
    <label for="email"> Email:
        <input type="text" id="email" name="email" value="${param.email}" required>
    </label><br/>
    <label for="pwd"> Password:
        <input type="password" id="pwd" name="pwd" required>
    </label><br/>
    <input type="submit" value="Submit">
    <a href="/registration"><button type="button">Registration</button></a>
</form>
<c:if test="${not empty requestScope.errors}">
    <div>
        <c:forEach var="error" items="${requestScope.errors}">
            <span>${error}</span><br/>
        </c:forEach>
    </div>
</c:if>
</body>
</html>
