<%--
  Created by IntelliJ IDEA.
  User: fox
  Date: пн 01.06.26
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <span>CONTENT</span>
    <p>Size: ${requestScope.flightsAll.size()}</p>
    <p>Description: ${requestScope.flightsAll.get(0).description()}</p>
    <p>Id: ${requestScope.flightsAll[1].id()}</p>
    <p>JSESSIONID: ${cookie.get("JSESSIONID")}</p>
    <p>PARAM ID: ${param.id}</p>
    <p>HEADER ID: ${header["cookie"]}</p>
    <p>NOT EMPTY: ${not empty flightsAll}</p>
</div>
</body>
</html>
