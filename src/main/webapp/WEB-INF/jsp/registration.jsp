<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/registration" method="post">
    <label for="name"> Name:
        <input type="text" id="name" name="name">
    </label><br/>
    <label for="birthday"> Birthday:
        <input type="date" id="birthday" name="birthday">
    </label><br/>
    <label for="email"> Email:
        <input type="text" id="email" name="email">
    </label><br/>
    <label for="pwd"> Password:
        <input type="password" id="pwd" name="pwd">
    </label><br/>
    <select name="role" id="role">
        <c:forEach var="role" items="${requestScope.roles}">
            <option label="${role}">${role}</option><br/>
        </c:forEach>
    </select> <br/>
    <input type="radio" name="gender" value="male" checked="checked"> Male<br/>
    <input type="radio" name="gender" value="female"> Feale<br/>
    <input type="submit" value="Submit">
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
