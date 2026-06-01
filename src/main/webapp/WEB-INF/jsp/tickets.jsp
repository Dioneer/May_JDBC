<%@ page import="org.example.service.TicketService" %>
<%@ page import="org.example.dto.TicketDto" %>

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
        <%
            TicketService ticketService = TicketService.getInstance();
            Integer flightId = Integer.valueOf(request.getParameter("flightId"));
            for(TicketDto ticketDto: ticketService.flightAllByFlightId(flightId)){
                out.write(String.format("<li>%s</li>", ticketDto.seatNo()));
        }
        %>
        <c:forEach var="ticket" items="${requestScope.tickets}">
            <li>${ticket.seatNo()}</li>
        </c:forEach>
    </ul>
</body>
</html>