<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head><title>Title</title></head>
<body>
<h1>Title</h1>
<h1>Flights list: </h1>
<ul>
    <c:forEach var="flight" items="${requestScope.flights}">
        <li><a href="${pageContext.request.contextPath}/tickets?flightId=${flight.id()}">${flight.flightNo()}</a></li>
    </c:forEach>
</ul>
</body>
</html>
